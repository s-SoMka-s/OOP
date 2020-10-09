package task2_2;

import java.util.Arrays;
import java.util.Comparator;

public class MyPriorityQueue<KeyType extends Comparable<KeyType>, ValueType> {
    private Node<KeyType, ValueType>[] container;
    private int elementsCount = 0;
    private int capacity;
    private final Comparator<? super KeyType> comparator;

    public MyPriorityQueue(int initialCapacity, Comparator<? super KeyType> newComparator){
        container = new Node[initialCapacity];
        capacity = initialCapacity;
        comparator = newComparator;
    }

    public MyPriorityQueue(Comparator<? super KeyType> newComparator){
        container = new Node[1];
        capacity = 1;
        comparator = newComparator;
    }

    public MyPriorityQueue(){
        container = new Node[1];
        capacity = 1;
        comparator = Comparator.naturalOrder();
    }

    public void insert(KeyType newKey, ValueType newValue){
        var node = new Node(newKey, newValue);
        if(elementsCount >= capacity){
            capacity = capacity * 2 + 1;
            container = Arrays.copyOf(container, capacity);
        }

        container[elementsCount++] = node;
        siftUpLast();
    }

    public Node<KeyType, ValueType> extractMax(){
        var res = container[0];
        container[0] = container[elementsCount--];
        container[elementsCount] = res;

        heapify(0);

        return res;
    }

    private void siftUpLast(){
        int i = elementsCount - 1;
        int parent = (i - 1) / 2;

        while (i > 0 && comparator.compare(container[parent].key, container[i].key) < 0)
        {
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

    public class Node<KeyType extends Comparable<KeyType>, ValueType> {
        private KeyType key;
        private ValueType value;

        public Node(KeyType newKey, ValueType newValue){
            key = newKey;
            value = newValue;
        }

        public KeyType getKey(){
            return key;
        }

        public ValueType getValue() {
            return value;
        }
    }
}
