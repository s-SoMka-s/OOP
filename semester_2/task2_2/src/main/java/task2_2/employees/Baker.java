package task2_2.employees;

import task2_2.Order;
import task2_2.OrderStatus;

import java.util.concurrent.LinkedBlockingQueue;

public class Baker extends Thread {
    private final LinkedBlockingQueue<Order> stock;
    private final LinkedBlockingQueue<Order> orders;
    private final long Id;
    private boolean isFree;

    public Baker(long id, LinkedBlockingQueue<Order> stock, LinkedBlockingQueue<Order> orders){
        this.stock = stock;
        this.orders = orders;
        this.isFree = true;
        this.Id = id;
    }

    @Override
    public void run() {
        while(this.canNext()){
            try {
                var order = this.orders.take();
                order.Status = OrderStatus.Baked;
                System.out.println(order.Id + " change status to " + order.Status);
                this.bake(order);
                order.Status = OrderStatus.WaitingForDelivery;
                System.out.println(order.Id + " change status to " + order.Status);
                this.stock.put(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isFree = true;
        }
    }

    private void bake(Order order) throws InterruptedException {
        isFree = false;
        order.Status = OrderStatus.Baked;
        Thread.sleep(2000);
    }

    private boolean canNext(){
        return this.isFree;
    }
}
