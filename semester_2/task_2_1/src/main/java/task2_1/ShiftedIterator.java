package task2_1;

import java.util.ArrayList;
import java.util.Iterator;

public class ShiftedIterator<T> {
    private final Iterator<T> rawIterator;
    private final Integer offset;

    public ShiftedIterator(Iterator<T> iterator, int offset)
    {
        this.rawIterator = iterator;
        this.offset = offset;
    }

    public Iterator<T> getShiftedIterator() {
        var result = new ArrayList<T>();
        for (int i = 0;this.rawIterator.hasNext();i++){
            if (i % this.offset == 0){
                result.add(this.rawIterator.next());
            }
        }

        return result.iterator();
    }
}
