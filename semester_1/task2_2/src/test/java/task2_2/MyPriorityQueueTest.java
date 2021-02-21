package task2_2;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MyPriorityQueueTest {
    @Test
    public void Test1(){
        var pq = new MyPriorityQueue<Integer, String>();
        var dog = "111";
        pq.insert(200, "dog");
        pq.insert(1000, "parrot");

        var myMax = pq.extractMax();
        assertEquals(myMax.getKey(), 1000);
    }

    @Test
    public void Test2(){
        var pq = new MyPriorityQueue<Integer, String>();
        var iter = pq.stream().iterator();
            while (iter.hasNext()) {

                System.out.print(iter.next()
                        + ", ");

                // ConcurrentModificationException
                // is raised here as an element
                // is added during the iteration
                System.out.println(
                        "\n\nTrying to add"
                                + " an element in "
                                + "between iteration\n");
                pq.insert(12, "Five");
            }

        pq.insert(20, "dog20");
        pq.insert(100, "parrot100");
        pq.insert(200, "dog");
        pq.insert(1000, "parrot");

        var sp = pq.stream();
        assertEquals(sp.count(), 4L);
    }

}