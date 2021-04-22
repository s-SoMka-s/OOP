package task2_1;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class MyConsumer<T> extends Thread {
    private final ConcurrentLinkedQueue<T> queue;
    private final Predicate<T> predicate;
    private final Integer id;
    private AtomicBoolean result;

    public MyConsumer(ConcurrentLinkedQueue<T> queue, Predicate<T> predicate, AtomicBoolean result, Integer id) {
        this.queue = queue;
        this.id = id;
        this.predicate = predicate;
        this.result = result;
    }

    private boolean canNext() {
        return !this.queue.isEmpty() && !this.result.get();
    }

    @Override
    public void run() {
        try {
            while (this.canNext()) {
                var elem = this.queue.remove();
                System.out.println("worker_id: " + id + " processing = "+elem);
                Thread.sleep(1000);
                if (predicate.test(elem)) {
                    this.result.set(true);
                    System.out.println("worker_id: " + id + " find composite");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
