package pattern.behavior.state;

public interface State {
    void start(Context context);

    void stop(Context context);
}
