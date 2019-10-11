package pattern.behavior.interpreter;

import java.util.ArrayList;
import java.util.List;

// 找出一段区间内同事能被 5，7，11 整除的数
public class Solution {
    private IDivisible fiveDivisible = new FiveDivisible();
    private IDivisible sevenDivisible = new SevenDivisible();
    private IDivisible elevenDivisible = new ElevenDivisible();

    public List<Integer> solution(int start, int end) {
        List<Integer> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (divisible(i)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean divisible(int number) {
        return fiveDivisible.divisible(number)
                && sevenDivisible.divisible(number)
                && elevenDivisible.divisible(number);
    }
}
