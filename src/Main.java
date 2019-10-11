public class Main {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println("\nn = cap - 1");
        System.out.println(fillToLength(Integer.toBinaryString(n)));
        System.out.println("\nn |= n >>> 1");
        print(n, n >>> 1);
        n |= n >>> 1;
        System.out.println(fillToLength(Integer.toBinaryString(n)));
        System.out.println("\nn |= n >>> 2");
        print(n, n >>> 2);
        n |= n >>> 2;
        System.out.println(fillToLength(Integer.toBinaryString(n)));
        System.out.println("\nn |= n >>> 4");
        print(n, n >>> 4);
        n |= n >>> 4;
        System.out.println(fillToLength(Integer.toBinaryString(n)));
        System.out.println("\nn |= n >>> 8");
        print(n, n >>> 8);
        n |= n >>> 8;
        System.out.println(fillToLength(Integer.toBinaryString(n)));
        System.out.println("\nn |= n >>> 16");
        print(n, n >>> 16);
        n |= n >>> 16;
        System.out.println(fillToLength(Integer.toBinaryString(n)));
        System.out.println("\nn + 1");
        System.out.println(fillToLength(Integer.toBinaryString(n + 1)));
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    private static void print(int a, int b) {
        System.out.println(fillToLength(Integer.toBinaryString(a)) + " |\n"
                + fillToLength(Integer.toBinaryString(b)) + " =");
    }

    private static String fillToLength(String source) {
        if (source.length() < 32) {
            StringBuilder builder = new StringBuilder(source);
            while (builder.length() < 32) {
                builder.insert(0, '0');
            }
            return builder.toString();
        }
        return source;
    }

    static int numberOf1(int number) {
        int result = 0;
        while (number != 0) {
            result++;
            number &= (number - 1);
        }
        return result;
    }

    static boolean isSameSymbolNumber(int a, int b) {
        return ((a >>> 31) ^ (b >>> 31)) == 0;
    }

    public static void main(String[] args) {
        String source = "1000000000000110000000011000000";
        int cap = Integer.parseInt(source, 2);
        System.out.println("cap: " + cap);
        System.out.println("cap bin: \n" + fillToLength(source));
        int size = tableSizeFor(cap);
        System.out.println("result: " + size);

//        System.out.println(numberOf1(-1));
        System.out.println(isSameSymbolNumber(1, 2));
    }
}
