package pattern.behavior.state;

public class Test {
    public static void main(String[] args) {
        Context context = new Context();
        context.setState(new StopState());
        context.start();
        context.stop();
        context.stop();
        context.start();
    }
}
