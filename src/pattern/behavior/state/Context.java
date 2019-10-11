package pattern.behavior.state;

public class Context {
    private State state;

    public Context() {
        this.state = null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void start() {
        getState().start(this);
    }

    public void stop() {
        getState().stop(this);
    }
}
