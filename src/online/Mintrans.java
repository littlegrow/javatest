package online;

import java.util.Scanner;

public class Mintrans {

    public static int solution(int[] array) {
        if (null == array) {
            return -1;
        }
        int sum = 0;
        for (int number : array) {
            sum += number;
        }
        if (sum % array.length != 0) {
            return -1;
        }
        int times = 0;
        int minPos, maxPos;
        int min, max;
        while (true) {
            min = array[0];
            max = array[0];
            minPos = 0;
            maxPos = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] < min) {
                    min = array[i];
                    minPos = i;
                }
                if (array[i] > max) {
                    max = array[i];
                    maxPos = i;
                }
            }
            if (minPos != maxPos) {
                array[maxPos]--;
                array[minPos]++;
                times += Math.abs(maxPos - minPos);
            } else {
                break;
            }
        }
        return times;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line;
        while (true) {
            line = scanner.nextLine();
            if (line.contains("$")) {
                break;
            }
            try {
                String[] numStr = line.split(",");
                System.out.println(line.endsWith("\n"));
                int[] array = new int[numStr.length];
                for (int i = 0; i < numStr.length; i++) {
                    array[i] = Integer.parseInt(numStr[i]);
                }
                System.out.println(solution(array));
            } catch (Exception e) {
                System.out.println(-1);
            }
        }
        scanner.close();
    }
}
