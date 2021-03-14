package task2_1;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class ThreadHandler<T> extends Thread {
    private final Iterator<T> iterator;
    private final Predicate<T> predicate;
    private AtomicBoolean result;

    public ThreadHandler(Iterator<T> iterator, Predicate<T> predicate, AtomicBoolean result) {
        this.iterator = iterator;
        this.predicate = predicate;
        this.result = result;
    }

    private boolean canNext() {
        return this.iterator.hasNext() && !this.result.get();
    }

    @Override
    public void run() {
        while (this.canNext()) {
            var elem = this.iterator.next();
            if (predicate.test(elem)) {
                this.result.set(true);
            }
        }
    }
}
