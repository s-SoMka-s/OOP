package task2_2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MyPriorityQueue<KeyType extends Comparable<KeyType>, ValueType> {
    private Node<KeyType, ValueType>[] container;
    private int elementsCount = 0;
    private int capacity;
    private final Comparator<? super KeyType> comparator;

    public MyPriorityQueue(int initialCapacity, Comparator<? super KeyType> newComparator) {
        container = new Node[initialCapacity];
        capacity = initialCapacity;
        comparator = newComparator;
    }

    public MyPriorityQueue(Comparator<? super KeyType> newComparator) {
        container = new Node[1];
        capacity = 1;
        comparator = newComparator;
    }

    public MyPriorityQueue() {
        container = new Node[1];
        capacity = 1;
        comparator = Comparator.naturalOrder();
    }

    Stream<Node> stream() {
        return StreamSupport.stream(new MyPriorityQueueSpliterator(), false);
    }


    public void insert(KeyType newKey, ValueType newValue) {
        var node = new Node(newKey, newValue);
        if (elementsCount >= capacity) {
            capacity = capacity * 2 + 1;
            container = Arrays.copyOf(container, capacity);
        }

        container[elementsCount++] = node;
        siftUpLast();
    }

    public Node<KeyType, ValueType> extractMax() {
        var res = container[0];
        container[0] = container[elementsCount--];
        container[elementsCount] = res;

        heapify(0);

        return res;
    }

    private void siftUpLast() {
        int i = elementsCount - 1;
        int parent = (i - 1) / 2;

        while (i > 0 && comparator.compare(container[parent].key, container[i].key) < 0) {
            var temp = container[i];
            container[i] = container[parent];
            container[parent] = temp;

            i = parent;
            parent = (i - 1) / 2;
        }
    }

    // Binary heap conversions of a subtree rooted with i
    // n - binary heap size
    private void heapify(int subtreeRootNode) {
        int largestChild = subtreeRootNode;
        int leftChild = 2 * subtreeRootNode + 1;
        int rightChild = 2 * subtreeRootNode + 2;

        // If left child bigger than root
        if (leftChild < elementsCount && comparator.compare(container[leftChild].key, container[largestChild].key) > 0)
            largestChild = leftChild;

        // If right child bigger than root
        if (rightChild < elementsCount && comparator.compare(container[rightChild].key, container[largestChild].key) > 0)
            largestChild = rightChild;

        // If the largest elem is not a root elem
        if (largestChild != subtreeRootNode) {
            var tmp = container[subtreeRootNode];
            container[subtreeRootNode] = container[largestChild];
            container[largestChild] = tmp;

            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(largestChild);
        }
    }

    public class MyPriorityQueueSpliterator implements Spliterator<Node> {
        private int firstPosition;
        private int lastPosition;

        public MyPriorityQueueSpliterator() {
            firstPosition = 0;
            lastPosition = elementsCount;
        }

        public MyPriorityQueueSpliterator(int f, int l) {
            firstPosition = f;
            lastPosition = l;
        }

        /**
         * If a remaining element exists, performs the given action on it,
         * returning {@code true}; else returns {@code false}.  If this
         * Spliterator is {@link #ORDERED} the action is performed on the
         * next element in encounter order.  Exceptions thrown by the
         * action are relayed to the caller.
         *
         * @param action The action
         * @return {@code false} if no remaining elements existed
         * upon entry to this method, else {@code true}.
         * @throws NullPointerException if the specified action is null
         */
        @Override
        public boolean tryAdvance(Consumer action) {
            if (firstPosition <= lastPosition) {
                firstPosition++;
                action.accept(container[firstPosition]);
            }

            return false;
        }

        /**
         * If this spliterator can be partitioned, returns a Spliterator
         * covering elements, that will, upon return from this method, not
         * be covered by this Spliterator.
         *
         * <p>If this Spliterator is {@link #ORDERED}, the returned Spliterator
         * must cover a strict prefix of the elements.
         *
         * <p>Unless this Spliterator covers an infinite number of elements,
         * repeated calls to {@code trySplit()} must eventually return {@code null}.
         * Upon non-null return:
         * <ul>
         * <li>the value reported for {@code estimateSize()} before splitting,
         * must, after splitting, be greater than or equal to {@code estimateSize()}
         * for this and the returned Spliterator; and</li>
         * <li>if this Spliterator is {@code SUBSIZED}, then {@code estimateSize()}
         * for this spliterator before splitting must be equal to the sum of
         * {@code estimateSize()} for this and the returned Spliterator after
         * splitting.</li>
         * </ul>
         *
         * <p>This method may return {@code null} for any reason,
         * including emptiness, inability to split after traversal has
         * commenced, data structure constraints, and efficiency
         * considerations.
         *
         * @return a {@code Spliterator} covering some portion of the
         * elements, or {@code null} if this spliterator cannot be split
         * @apiNote An ideal {@code trySplit} method efficiently (without
         * traversal) divides its elements exactly in half, allowing
         * balanced parallel computation.  Many departures from this ideal
         * remain highly effective; for example, only approximately
         * splitting an approximately balanced tree, or for a tree in
         * which leaf nodes may contain either one or two elements,
         * failing to further split these nodes.  However, large
         * deviations in balance and/or overly inefficient {@code
         * trySplit} mechanics typically result in poor parallel
         * performance.
         */
        @Override
        public Spliterator trySplit() {
            int half = (lastPosition - firstPosition) / 2;

            if (half <= 1) {
                return null;
            }

            int f = firstPosition;
            int l = firstPosition + half;

            firstPosition = l + 1;

            return new MyPriorityQueueSpliterator(f, l);
        }

        /**
         * Returns an estimate of the number of elements that would be
         * encountered by a {@link #forEachRemaining} traversal, or returns {@link
         * Long#MAX_VALUE} if infinite, unknown, or too expensive to compute.
         *
         * <p>If this Spliterator is {@link #SIZED} and has not yet been partially
         * traversed or split, or this Spliterator is {@link #SUBSIZED} and has
         * not yet been partially traversed, this estimate must be an accurate
         * count of elements that would be encountered by a complete traversal.
         * Otherwise, this estimate may be arbitrarily inaccurate, but must decrease
         * as specified across invocations of {@link #trySplit}.
         *
         * @return the estimated size, or {@code Long.MAX_VALUE} if infinite,
         * unknown, or too expensive to compute.
         * @apiNote Even an inexact estimate is often useful and inexpensive to compute.
         * For example, a sub-spliterator of an approximately balanced binary tree
         * may return a value that estimates the number of elements to be half of
         * that of its parent; if the root Spliterator does not maintain an
         * accurate count, it could estimate size to be the power of two
         * corresponding to its maximum depth.
         */
        @Override
        public long estimateSize() {
            return lastPosition - firstPosition;
        }

        /**
         * Returns a set of characteristics of this Spliterator and its
         * elements. The result is represented as ORed values from {@link
         * #ORDERED}, {@link #DISTINCT}, {@link #SORTED}, {@link #SIZED},
         * {@link #NONNULL}, {@link #IMMUTABLE}, {@link #CONCURRENT},
         * {@link #SUBSIZED}.  Repeated calls to {@code characteristics()} on
         * a given spliterator, prior to or in-between calls to {@code trySplit},
         * should always return the same result.
         *
         * <p>If a Spliterator reports an inconsistent set of
         * characteristics (either those returned from a single invocation
         * or across multiple invocations), no guarantees can be made
         * about any computation using this Spliterator.
         *
         * @return a representation of characteristics
         * @apiNote The characteristics of a given spliterator before splitting
         * may differ from the characteristics after splitting.  For specific
         * examples see the characteristic values {@link #SIZED}, {@link #SUBSIZED}
         * and {@link #CONCURRENT}.
         */
        @Override
        public int characteristics() {
            return SIZED;
        }
    }

    public class Node<KeyType extends Comparable<KeyType>, ValueType> {
        private KeyType key;
        private ValueType value;

        public Node(KeyType newKey, ValueType newValue) {
            key = newKey;
            value = newValue;
        }

        public KeyType getKey() {
            return key;
        }

        public ValueType getValue() {
            return value;
        }
    }
}
