package online;

import java.util.Arrays;

public class StringProb {

    /**
     * 空格替换
     */
    public static String replaceBlackChar(String source, String replace) {
        if (source == null) {
            return null;
        }
        if (replace == null) {
            return null;
        }
        int blank = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) == ' ') {
                blank++;
            }
        }
        char[] target = new char[source.length() + (replace.length() - 1) * blank];
        int pos = target.length - 1;
        for (int i = source.length() - 1; i >= 0; i--) {
            if (source.charAt(i) == ' ') {
                for (int j = replace.length() - 1; j >= 0; j--) {
                    target[pos--] = replace.charAt(j);
                }
            } else {
                target[pos--] = source.charAt(i);
            }
        }
        return new String(target);
    }

    /**
     * 全排列
     */
    public static void permutationStr(String source) {
        permutationStr(source.toCharArray(), 0, source.length());
    }

    private static void permutationStr(char[] source, int depth, int length) {
        if (depth == length) {
            System.out.println(source);
            return;
        }
        char tmp;
        for (int i = depth; i < length; i++) {
            tmp = source[i];
            source[i] = source[depth];
            source[depth] = tmp;
            permutationStr(source, depth + 1, length);
            tmp = source[i];
            source[i] = source[depth];
            source[depth] = tmp;
        }
    }

    /**
     * 第一个只出现一次的字符
     */
    private static void firstNotRepeat(String source) {
        if (source == null) {
            return;
        }
        firstNotRepeat(source.toCharArray(), source.length());
    }

    private static void firstNotRepeat(char[] chars, int length) {
        int[] table = new int[256];
        Arrays.fill(table, 0);
        for (int i = 0; i < length; i++) {
            table[chars[i]]++;
        }
        for (int i = 0; i < length; i++) {
            if (table[chars[i]] == 1) {
                System.out.println(chars[i]);
                return;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(replaceBlackChar("jh fsadj khhj jdsafjjh jfsa hjdj h", "abc"));

        permutationStr("abc");

        firstNotRepeat("abca");
    }
}
