package pattern.behavior.order;

public class Request {
    public int sum(int... array) {
        int result = 0;
        if (array != null) {
            for (int i : array) {
                result += i;
            }
        }
        return result;
    }

    public int mul(int... array) {
        int result = 1;
        if (array != null) {
            for (int i : array) {
                result *= i;
            }
        }
        return result;
    }
}
