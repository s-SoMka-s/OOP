package task2_2;

public class Order {
    public long Id;
    public String Address;
    public String Text;
    public OrderStatus Status;

    public Order(long id) {
        this.Id = id;
        this.Status = OrderStatus.Created;
    }
}

