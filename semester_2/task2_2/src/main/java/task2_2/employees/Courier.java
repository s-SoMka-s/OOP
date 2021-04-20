package task2_2.employees;

import task2_2.Order;
import task2_2.OrderStatus;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Courier extends Thread {
    private final LinkedBlockingQueue<Order> stock;
    private final Long capacity;
    private boolean isFree;

    public Courier (LinkedBlockingQueue<Order> stock, long capacity){
        this.stock = stock;
        this.capacity = capacity;
        this.isFree = true;
    }

    @Override
    public void run() {
        while(this.canNext()){
            try {
                var orders = this.takeWhileCan();
                this.deliver(orders);
                this.isFree = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<Order> takeWhileCan() throws InterruptedException {
        var bag = new ArrayList<Order>();
        while(this.capacity > 0) {
            bag.add(this.stock.take());
        }

        return bag;
    }

    private void deliver(ArrayList<Order> orders) throws InterruptedException {
        isFree = false;
        for (var order : orders) {
            order.Status = OrderStatus.Delivering;
            Thread.sleep(2000);
            order.Status = OrderStatus.Closed;
        }
    }

    private boolean canNext(){
        return this.isFree;
    }
}
