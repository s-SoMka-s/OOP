package task2_2.employees;

import task2_2.Order;
import task2_2.OrderStatus;

import java.util.concurrent.LinkedBlockingQueue;

public class Baker extends Thread {
    private final LinkedBlockingQueue<Order> stock;
    private final LinkedBlockingQueue<Order> orders;
    private boolean isFree;

    public Baker(LinkedBlockingQueue<Order> stock, LinkedBlockingQueue<Order> orders){
        this.stock = stock;
        this.orders = orders;
        this.isFree = true;
    }

    @Override
    public void run() {
        while(this.canNext()){
            var order = this.orders.remove();
            try {
                this.bake(order);
                order.Status = OrderStatus.WaitingForDelivery;
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
