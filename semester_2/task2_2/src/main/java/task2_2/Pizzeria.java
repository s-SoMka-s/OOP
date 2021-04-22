package task2_2;

import com.google.gson.annotations.SerializedName;
import task2_2.employees.Baker;
import task2_2.employees.Courier;
import com.google.gson.Gson;
import task2_2.employees.SingleBakerFactory;
import task2_2.employees.SingleCourierFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Pizzeria {
    private final LinkedBlockingQueue<Order> stock = new LinkedBlockingQueue<Order>();
    private final LinkedBlockingQueue<Order> orders = new LinkedBlockingQueue<Order>();;
    private final ArrayList<Baker> bakers = new ArrayList<>();
    private final ArrayList<Courier> couriers = new ArrayList<>();

    @SerializedName("bakers_count")
    private long bakersCount;

    @SerializedName("couriersCount")
    private long couriersCount;

    public Pizzeria(Path path) throws IOException {
        this.init(path);
    }


    public void AddOrder(Order order) throws InterruptedException {
        this.orders.put(order);
    }

    private void init(Path path) throws IOException {
        var jsonString = new String(Files.readAllBytes(path));

        var gson = new Gson();
        var res = gson.fromJson(jsonString, Pizzeria.class);
        this.bakersCount = res.bakersCount;
        this.couriersCount = res.couriersCount;
        this.initBakers(this.bakersCount);
        this.initCouriers(this.couriersCount);

        System.out.println("Pizzeria started" +
                "\nInitial bakers count: " + this.couriersCount +
                "\nInitial couriers count: " + this.couriersCount);
    }

    private void initBakers(long count){
        var bakersFactory = SingleBakerFactory.getInstance();
        for (var i = 0;i<count;i++){
            var baker = bakersFactory.createBaker(this.stock, this.orders);
            baker.start();
            this.bakers.add(baker);
        }
    /*
        this.bakers.forEach(b -> {
            try {
                b.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        */

    }

    private void initCouriers(long count){
        var courierFactory = SingleCourierFactory.getInstance();
        for (var i = 0;i<count;i++){
            var courier = courierFactory.createCourier(this.stock, 1);
            courier.start();
            this.couriers.add(courier);
        }

        /*this.couriers.forEach(c -> {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

         */
    }
}
