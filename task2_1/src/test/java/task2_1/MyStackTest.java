package task2_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {
    @Test
    public void Test1(){
        var myLittleStack = new MyStack(1);
        myLittleStack.push("122");
        myLittleStack.push("222");
        myLittleStack.push("322");

        myLittleStack.pop();
        myLittleStack.pop();

        assertEquals(myLittleStack.top(), "122");
        assertEquals(myLittleStack.howManyElements(), 1);
        assertEquals(myLittleStack.pop(), "122");
        assertEquals(myLittleStack.howManyElements(), 0);

    }
}