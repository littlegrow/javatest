package pattern.behavior.strategy;

public class AddStrategy implements Strategy {
    @Override
    public float operator(float a, float b) {
        return a + b;
    }
}
