package pattern.behavior.observer;

public class BObserver implements IObserver {
    @Override
    public void update() {
        System.out.println("B observer update");
    }
}
