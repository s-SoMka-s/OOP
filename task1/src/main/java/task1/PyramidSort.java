package task1;

public class PyramidSort {
    public void sort(int[] arr) {
        int n = arr.length;

        // Построение max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Поочередно извлекаем элементы из кучи
        for (int i = n - 1; i >= 0; i--) {
            // Перемещаем текущий корень в конец
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Вызываем процедуру heapify на уменьшенной куче
            heapify(arr, i, 0);
        }
    }

    // Преобразования в двоичную кучу поддерева с корневым узлом i
    // n - размер кучи
    private void heapify(int[] arr, int n, int subtreeRootNode) {
        int largestChild = subtreeRootNode;
        int leftChild = 2 * subtreeRootNode + 1;
        int rightChild = 2 * subtreeRootNode + 2;

        // Если левый дочерний элемент больше корня
        if (leftChild < n && arr[leftChild] > arr[largestChild])
            largestChild = leftChild;

        // Если правый дочерний элемент больше корня
        if (rightChild < n && arr[rightChild] > arr[largestChild])
            largestChild = rightChild;

        // Если самый большой элемент не корень
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
