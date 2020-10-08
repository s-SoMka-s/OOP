package task2_1;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Just one of the stack implementations,
 * not using standard collections
 */
public class MyStack implements Iterable{
    private Object[] container;
    private int capacity;
    private int elementCount;

    public MyStack(int newCapacity){
        capacity = newCapacity;
        elementCount = 0;
        container = new Object[newCapacity];
    }

    /**
     * The method guarantees that there is free space on the stack
     * and adds a new element to the top
     * @param elem new element for stack
     */
    public void push(Object elem){
        if(elementCount >= capacity){
            capacity = capacity * 2 + 1;
            container = Arrays.copyOf(container, capacity);
        }

        container[elementCount++] = elem;
    }

    /**
     * method removes the top element and returns it
     * @return the top element
     * @throws IndexOutOfBoundsException if stack is empty
     */
    public Object pop() throws IndexOutOfBoundsException{
        if(elementCount == 0){
            throw new IndexOutOfBoundsException();
        }

        var res = container[elementCount - 1];
        elementCount--;

        return res;
    }

    /**
     * method gets the top element and returns it, not removing
     * @return the top element
     * @throws IndexOutOfBoundsException if stack is empty
     */
    public Object top() throws  IndexOutOfBoundsException{
        if(elementCount == 0){
            throw new IndexOutOfBoundsException();
        }

        return container[elementCount - 1];
    }

    /**
     * method returns how many elements stack can accommodate
     * if it is no elements in it
     * @return the stack capacity
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * method returns how many elements are in stack now
     * @return number of elements
     */
    public int howManyElements(){
        return elementCount;
    }

    @Override
    public Iterator iterator(){
        return new Iterator() {
            int pos = elementCount - 1;

            @Override
            public boolean hasNext() {
                return pos >= 0;
            }

            @Override
            public Object next() throws IndexOutOfBoundsException{
                if(pos < 0){
                    throw new IndexOutOfBoundsException();
                }

                return container[pos--];
            }
        };
    }
}




