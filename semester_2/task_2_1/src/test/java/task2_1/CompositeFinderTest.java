package task2_1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFinderTest {
    @Test
    public void Test1() throws InterruptedException {
        var finder = new CompositeFinder();
        var arr = new Integer[] {1,51217,71317,111217,13337,1313,19,23,4};
        var numbers = new ArrayList<Integer>(Arrays.asList(arr));

        finder.findThreads(numbers, 4);
    }
}