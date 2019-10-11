package sort;

public class BubbleSort implements ISort {

    @Override
    public void sort(int[] array) {
        for (int i = array.length - 2; i > 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
