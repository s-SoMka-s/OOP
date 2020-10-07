package task2_1;

import java.util.ArrayList;
import java.util.Iterator;

public class MyStack<T> implements Iterable<T>{
    private ArrayList<T> list;

    public MyStack(){
        list = new ArrayList<T>();
    }

    public void push(T elem){
        list.add(elem);
    }

    public T pop(){
        if(list.isEmpty()){
            return null;
        }

        return list.remove(list.size() - 1);
    }

    public T top() throws  IndexOutOfBoundsException{
        if(list.isEmpty()){
            return null;
        }

        return list.get(list.size() - 1);
    }

    public int getSize(){
        return list.size();
    }

    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            int pos = list.size() - 1;

            @Override
            public boolean hasNext() {
                return pos >= 0;
            }

            @Override
            public T next() {
                return hasNext() ? list.get(pos--) : null;
            }
        };
    }
}




