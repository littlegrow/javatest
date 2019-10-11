package sort;

import java.util.Random;

/**
 * 随机生成 n 个数， 分别使用以上实现算法排序，记录耗时
 */
public class SortTest {

    public static void main(String[] args) {
        int size = 100000;

        int[] array = new int[size];
        Random random = new Random(System.currentTimeMillis());
        TimeDelta timeDelta = new TimeDelta();
        ISort sort;

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100) + 1;
        }
        sort = new BubbleSort();
        timeDelta.renew();
        sort.sort(array);
        System.out.println(String.format("冒泡耗时(size:%s): %s", size, timeDelta.getDelta()));

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100) + 1;
        }
        sort = new QuickSort();
        timeDelta.renew();
        sort.sort(array);
        System.out.println(String.format("快排耗时(size:%s): %s", size, timeDelta.getDelta()));

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100) + 1;
        }
        sort = new HeepSort();
        timeDelta.renew();
        sort.sort(array);
        System.out.println(String.format("堆排耗时(size:%s): %s", size, timeDelta.getDelta()));
    }
}
