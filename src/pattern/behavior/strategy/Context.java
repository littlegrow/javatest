package pattern.behavior.strategy;

public class Context implements Strategy {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public float operator(float a, float b) {
        if (strategy == null) {
            throw new RuntimeException("no strategy set");
        }
        return strategy.operator(a, b);
    }
}
