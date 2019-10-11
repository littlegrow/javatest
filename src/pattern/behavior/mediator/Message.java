package pattern.behavior.mediator;

public class Message {
    public String message;

    public Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
