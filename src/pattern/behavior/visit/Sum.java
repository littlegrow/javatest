package pattern.behavior.visit;

public class Sum implements Calculator {
    @Override
    public int calculator(int a, int b) {
        return a + b;
    }
}
