package pattern.behavior.observer;

public class Test {
    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.setState(0);
        subject.addObserver(new AObserver());
        subject.addObserver(new BObserver());
        subject.setState(1);
    }
}
