package task2_2.employees;

import task2_2.Order;
import task2_2.OrderStatus;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Courier extends Thread {
    private final LinkedBlockingQueue<Order> stock;
    private final long capacity;
    private final long id;
    private boolean isFree;

    public Courier (long id, LinkedBlockingQueue<Order> stock, long capacity){
        this.stock = stock;
        this.capacity = capacity;
        this.isFree = true;
        this.id = id;
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
        var ordersCount = 0;
        while(this.capacity > ordersCount) {
            bag.add(this.stock.take());
            ordersCount += 1;
        }

        return bag;
    }

    private void deliver(ArrayList<Order> orders) throws InterruptedException {
        isFree = false;
        for (var order : orders) {
            order.Status = OrderStatus.Delivering;
            System.out.println(order.Id + " change status to " + order.Status);
            Thread.sleep(2000);
            order.Status = OrderStatus.Closed;
            System.out.println(order.Id + " change status to " + order.Status);
        }
    }

    private boolean canNext(){
        return this.isFree;
    }
}
