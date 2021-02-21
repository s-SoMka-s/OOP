package task2_1;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

public class PrimeFinder {
    /**
     * Последовательно проверяет все числа, полученного на вход списка целых чисел, на простоту
     *
     * @param numbers - список целых чисел
     * @return true - если список содержит хотя-бы одно простое число
     * false - в противном случае
     */
    public boolean hasPrimesSequent(ArrayList<Integer> numbers) {
        return numbers.stream().anyMatch(n -> this.isPrime(n));
    }

    public boolean hasPrimesOnThreads(ArrayList<Integer> numbers, int threadCnt) throws InterruptedException {
        var hasPrimes = new AtomicBoolean(false);
        var threadsLimit = new AtomicInteger(threadCnt);

        numbers.stream().forEach(n -> {
            while (threadsLimit.get() <= 0) {
                System.out.println("waiting...");
            }
            threadsLimit.getAndDecrement();
            new Thread(() -> {
                var isPrime = this.isPrime(n);
                System.out.println(n + " = " + isPrime);
                hasPrimes.compareAndSet(false, isPrime);

                threadsLimit.getAndIncrement();
            }).start();
        });

        while (threadsLimit.get() != threadCnt) {
            System.out.println("waiting...");
        }

        return hasPrimes.get();
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
