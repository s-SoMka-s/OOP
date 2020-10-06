package task1;

public class PyramidSort {
    /**
    * @param arr input array collection
    */
    public void sort(int[] arr) {
        int n = arr.length;

        // building max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Forech elem from heap take it from collection
        for (int i = n - 1; i >= 0; i--) {
            // Swap current and root elements
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call heapify on reduced heap
            heapify(arr, i, 0);
        }
    }

    // Binary heap conversions of a subtree rooted with i
    // n - binary heap size
    private void heapify(int[] arr, int n, int subtreeRootNode) {
        int largestChild = subtreeRootNode;
        int leftChild = 2 * subtreeRootNode + 1;
        int rightChild = 2 * subtreeRootNode + 2;

        // If left child bigger than root
        if (leftChild < n && arr[leftChild] > arr[largestChild])
            largestChild = leftChild;

        // If right child bigger than root
        if (rightChild < n && arr[rightChild] > arr[largestChild])
            largestChild = rightChild;

        // If the largest elem is not a root elem
        if (largestChild != subtreeRootNode) {
            int tmp = arr[subtreeRootNode];
            arr[subtreeRootNode] = arr[largestChild];
            arr[largestChild] = tmp;

            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(arr, n, largestChild);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 9, 7};

        PyramidSort ob = new PyramidSort();
        ob.sort(arr);
    }
}
