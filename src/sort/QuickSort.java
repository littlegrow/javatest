package sort;

public class QuickSort implements ISort {
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        int i = left, j = right;
        int flag = array[left];
        while (i < j) {
            while (j > i && array[j] >= flag) {
                j--;
            }
            while (i < j && array[i] < flag) {
                i++;
            }
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        if (array[i] < array[left]) {
            int temp = array[left];
            array[left] = array[i];
            array[i] = temp;
        }
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }
}
