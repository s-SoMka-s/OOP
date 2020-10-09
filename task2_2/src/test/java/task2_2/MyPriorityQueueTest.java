package task2_2;

import org.junit.jupiter.api.Test;


class MyPriorityQueueTest {
    @Test
    public void Test1(){
        var pq = new MyPriorityQueue<Integer, String>();
        var dog = "111";
        pq.insert(200, "dog");
        pq.insert(10, "parrot");

        var myMax = pq.extractMax();
        System.out.println(myMax.getKey() + "\n" + myMax.getValue());
    }
}