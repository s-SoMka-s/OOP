package task2_2;

import java.util.Arrays;

public class MyPriorityQueue<KeyType, ValueType> {
    private Node<KeyType, ValueType>[] container;
    private int elementsCount = 0;
    private int currPriority = 0;
    private int capacity;

    public MyPriorityQueue(int initialCapacity){
        container = new Node[initialCapacity];
        capacity = initialCapacity;
    }

    public MyPriorityQueue(){
        container = new Node[1];
        capacity = 1;
    }

    public void insert(KeyType newKey, ValueType newValue){
        var node = new Node(newKey, newValue, currPriority++);
        if(elementsCount >= capacity){
            capacity = capacity * 2 + 1;
            container = Arrays.copyOf(container, capacity);
        }

        container[elementsCount] = node;

        heapify(container, elementsCount + 1, elementsCount);
        elementsCount++;
    }

    public Node<KeyType, ValueType> extractMax(){
        var res = container[0];
        container[0] = container[elementsCount--];
        container[elementsCount] = res;

        heapify(container, elementsCount, 0);

        return res;
    }

    // Binary heap conversions of a subtree rooted with i
    // n - binary heap size
    private void heapify(Node[] arr, int n, int subtreeRootNode) {
        int largestChild = subtreeRootNode;
        int leftChild = 2 * subtreeRootNode + 1;
        int rightChild = 2 * subtreeRootNode + 2;

        // If left child bigger than root
        if (leftChild < n && arr[leftChild].priority > arr[largestChild].priority)
            largestChild = leftChild;

        // If right child bigger than root
        if (rightChild < n && arr[rightChild].priority > arr[largestChild].priority)
            largestChild = rightChild;

        // If the largest elem is not a root elem
        if (largestChild != subtreeRootNode) {
            var tmp = arr[subtreeRootNode];
            arr[subtreeRootNode] = arr[largestChild];
            arr[largestChild] = tmp;

            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(arr, n, largestChild);
        }
    }

    public class Node<KeyType, ValueType> {
        private KeyType key;
        private ValueType value;
        private int priority;

        public Node(KeyType newKey, ValueType newValue, int newPriority){
            key = newKey;
            value = newValue;
            priority = newPriority;
        }

        public KeyType getKey(){
            return key;
        }

        public ValueType getValue() {
            return value;
        }
    }
}
