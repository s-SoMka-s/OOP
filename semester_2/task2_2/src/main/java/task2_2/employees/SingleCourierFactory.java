package task2_2.employees;

import task2_2.Order;

import java.util.concurrent.LinkedBlockingQueue;

public class SingleCourierFactory {
        public static SingleCourierFactory instance = new SingleCourierFactory();
        private long lastId = 0;

        private SingleCourierFactory() {
        }

        public static SingleCourierFactory getInstance() {
            return instance;
        }

        public Courier createCourier(LinkedBlockingQueue<Order> stock, long capacity) {
            return new Courier(this.lastId++, stock, capacity);
        }
    }
