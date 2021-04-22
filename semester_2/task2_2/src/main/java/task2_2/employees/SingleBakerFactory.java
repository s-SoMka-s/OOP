package task2_2.employees;

import task2_2.Order;

import java.util.concurrent.LinkedBlockingQueue;

public class SingleBakerFactory {
    public static SingleBakerFactory instance = new SingleBakerFactory();
    private long lastId = 0;

    private SingleBakerFactory() {
    }

    public static SingleBakerFactory getInstance() {
        return instance;
    }

    public Baker createBaker(LinkedBlockingQueue<Order> stock, LinkedBlockingQueue<Order> orders) {
        return new Baker(this.lastId++, stock, orders);
    }
}
