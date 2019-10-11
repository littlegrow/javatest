package pattern.behavior.state;

public class StartState implements State {
    @Override
    public void start(Context context) {
        System.out.println("current state is start");
    }

    @Override
    public void stop(Context context) {
        context.setState(new StopState());
        System.out.println("change to stop state");
    }
}
