package task2_2;

public class SingleOrderFactory {
    public static SingleOrderFactory instance = new SingleOrderFactory();
    private long lastId = 0;

    private SingleOrderFactory() {
    }

    public static SingleOrderFactory getInstance() {
        return instance;
    }

    public Order createOrder() {
        return new Order(this.lastId++);
    }
}
