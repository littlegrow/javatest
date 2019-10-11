package pattern.behavior.visit;

public class Sub implements Calculator {
    @Override
    public int calculator(int a, int b) {
        return a - b;
    }
}
