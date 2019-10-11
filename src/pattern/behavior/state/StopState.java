package pattern.behavior.state;

public class StopState implements State {
    @Override
    public void start(Context context) {
        context.setState(new StartState());
        System.out.println("change to start state");
    }

    @Override
    public void stop(Context context) {
        System.out.println("current state is stop");
    }
}
