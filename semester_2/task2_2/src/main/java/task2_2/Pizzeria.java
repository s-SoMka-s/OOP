package task2_2;

import task2_2.employees.Baker;
import task2_2.employees.Courier;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Pizzeria {
    private final LinkedBlockingQueue<Order> stock;
    private final LinkedBlockingQueue<Order> orders;
    private final ArrayList<Baker> bakers = new ArrayList<>();
    private final ArrayList<Courier> couriers = new ArrayList<>();

    public Pizzeria() {
        this.orders = new LinkedBlockingQueue<Order>();
        this.stock = new LinkedBlockingQueue<Order>();
        this.initBakers(1);
        this.initCouriers(1);
    }

    public void AddOrder(Order order) throws InterruptedException {
        order.Status = OrderStatus.Opened;
        this.orders.put(order);
    }

    private void initBakers(long count){
        for (var i = 0;i<count;i++){
            var baker = new Baker(this.stock, this.orders);
            this.bakers.add(baker);
        }
    }

    private void initCouriers(long count){
        for (var i = 0;i<count;i++){
            var courier = new Courier(this.stock, 1);
            this.couriers.add(courier);
        }
    }
}
