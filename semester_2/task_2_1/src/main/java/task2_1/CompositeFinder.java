package task2_1;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class CompositeFinder {
    private final Predicate<Integer> isComposite;

    public CompositeFinder() {
        this.isComposite = x -> !this.isPrime(x);
    }

    /**
     * Последовательно ищем хотя-бы одно не простое число
     * в входном списке целых чисел
     *
     * @param numbers - список целых чисел
     * @return true - если список содержит хотя-бы одно простое число
     * false - в противном случае
     */
    public boolean findSequent(ArrayList<Integer> numbers) {
        return numbers.stream()
                      .anyMatch(this.isComposite);
    }

    public boolean findThreads(ArrayList<Integer> numbers, int threadCnt) throws InterruptedException {
        var result = new AtomicBoolean(false);
        var queue = new ConcurrentLinkedQueue<Integer>(numbers);
        var consumers = new ArrayList<MyConsumer<Integer>>();
        for(var i = 0;i<threadCnt;i++){
            var consumer = new MyConsumer<Integer>(queue, this.isComposite, result, i);
            consumer.start();
            consumers.add(consumer);
        }

        consumers.forEach(c -> {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return result.get();
    }

    /**
     * ищем хотя-бы одно не простое число
     * в входном списке целых чисел, с использование parallelStream
     *
     * @param numbers - список целых чисел
     * @return true - если список содержит хотя-бы одно простое число
     * false - в противном случае
     */
    public boolean findParallelStream(ArrayList<Integer> numbers) {
        return numbers.parallelStream()
                      .anyMatch(this.isComposite);
    }

    private boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }

        return true;
    }
}
