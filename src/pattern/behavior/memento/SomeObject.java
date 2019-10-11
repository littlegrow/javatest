package pattern.behavior.memento;

public class SomeObject {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento getMemento() {
        return new Memento(state);
    }
}
