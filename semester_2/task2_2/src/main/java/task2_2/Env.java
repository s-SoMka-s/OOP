package task2_2;

import java.io.IOException;
import java.nio.file.Path;

public class Env {
    public Env() throws IOException, InterruptedException {
        this.StartWorking();
    }

    public void StartWorking() throws IOException, InterruptedException {
        var path = Path.of("src/init.json");
        var pizzeria = new Pizzeria(path);
        var orderFactory = SingleOrderFactory.getInstance();
        while(true){
            var order = orderFactory.createOrder();
            pizzeria.AddOrder(order);
            Thread.sleep(5000);
        }
    }
}
