package task3_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordBookTest {
    @Test
    public void Test1() {
        var rb = new RecordBook("Chiesov Igor Andreevich");

        System.out.println(rb.getOwner());
        System.out.println(1);
    }
}