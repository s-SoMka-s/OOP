package task2_1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PrimeFinderTest {
    @Test
    public void testSequentFinder() {
        var finder = new PrimeFinder();
        var numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(finder.hasPrimesSequent(numbers), true);
    }

    @Test
    public void TestThreadFinder() throws InterruptedException {
        var finder = new PrimeFinder();
        var numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(finder.hasPrimesOnThreads(numbers, 6), true);
    }
}