package task1;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PyramidSortTest {
    @Test
    public void SimpleTest(){
        int[] arr = {5,4,3,2,1};
        int[] expected = {1,2,3,4,5};

        var sortObj = new PyramidSort();
        sortObj.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void EqualsTest(){
        int[] arr = {1,2,3,4,5};
        int[] expected = {1,2,3,4,5};

        var sortObj = new PyramidSort();
        sortObj.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void RandomNumbersTest(){
        int[] arr = new int[1000];
        for(int i = 0;i<arr.length;i++){
            arr[i] = (int)(Math.random()*2000) - 1000;
        }
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        var sortObj = new PyramidSort();
        sortObj.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void EmptyArrTest(){
        int[] arr = {};
        int[] expected = {};

        var sortObj = new PyramidSort();
        sortObj.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void RandomNumbersMaxLengthTest(){
        int[] arr = new int[30000];
        for(int i = 0;i<arr.length;i++){
            arr[i] = (int)(Math.random()*(Integer.MAX_VALUE-1)) - (Integer.MAX_VALUE-1)/2;
        }

        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        var sortObj = new PyramidSort();
        sortObj.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void OneElemArrTest(){
        int[] arr = {1};
        int[] expected = {1};

        var sortObj = new PyramidSort();
        sortObj.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void TwoElementsArrTest(){
        int[] arr = {5,1};
        int[] expected = {1,5};

        var sortObj = new PyramidSort();
        sortObj.sort(arr);

        assertArrayEquals(expected, arr);
    }
}