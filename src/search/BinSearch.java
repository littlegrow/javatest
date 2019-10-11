package search;

public class BinSearch {

    /**
     * 二分查找
     *
     * 数据源是有序的
     * 1. 取数据源中间位置元素与目标值对比
     * 2. 如果相等则返回结果，如果小于目标值则在数据源前半部分搜索，否则在后半部分搜索
     * 3. 重复执行 步骤 1、2，直至找到结果或者再没有可搜索的区间
     */
    public static int binSearch(int[] array, int start, int end, int value) {
        if (null == array) {
            throw new RuntimeException("array is null");
        }
        if (start < 0 || end >= array.length) {
            throw new RuntimeException("index out of bounds");
        }
        if (start >= end) {
            return -1;
        }
        int mid = (start + end) >> 1;
        if (array[mid] > value) {
            return binSearch(array, start, mid, value);
        } else if (array[mid] < value) {
            return binSearch(array, mid, end, value);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(binSearch(array, 0, array.length - 1, 3));
    }
}
