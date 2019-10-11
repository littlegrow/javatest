package algorithm;

public class Fab {

    public static int fab1(int n) {
        if (n < 0) {
            throw new RuntimeException("number less than zero");
        }
        if (n <= 1) {
            return n;
        }
        return fab1(n - 1) + fab1(n - 2);
    }

    public static int fab2(int n) {
        if (n < 0) {
            throw new RuntimeException("number less than zero");
        }
        if (n <= 1) {
            return n;
        }
        int[] array = new int[n + 1];
        array[0] = 0;
        array[1] = 1;
        int index = 2;
        while (index < array.length) {
            array[index] = array[index - 1] + array[index - 2];
            index++;
        }
        return array[array.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(fab1(10));
        System.out.println(fab2(50));
    }
}
