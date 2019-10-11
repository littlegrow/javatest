package sort;

public class HeepSort implements ISort {
    @Override
    public void sort(int[] array) {
        heepSort(array);
    }

    private void heepSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            heepUp(array, i);
        }
        for (int i = array.length - 1; i > 0; i--) {
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            heepDown(array, i - 1, 0);
        }
    }

    private void heepUp(int[] array, int pos) {
        while (pos > 0) {
            int father = (pos - 1) / 2;
            if (array[pos] > array[father]) {
                int tmp = array[pos];
                array[pos] = array[father];
                array[father] = tmp;
            }
            pos = father;
        }
    }

    private void heepDown(int[] array, int size, int root) {
        int large = root;
        int left = root * 2 + 1;
        int right = root * 2 + 2;
        if (left <= size && array[large] < array[left]) {
            large = left;
        }
        if (right <= size && array[large] < array[right]) {
            large = right;
        }
        if (large != root) {
            int tmp = array[root];
            array[root] = array[large];
            array[large] = tmp;
            heepDown(array, size, large);
        }
    }
}
