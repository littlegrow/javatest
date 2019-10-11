package pattern.behavior.mediator;

public class Mediator {
    public static void sendMessage(User fromUser, User toUser, Message message) {
        System.out.println(fromUser + " -> " + toUser + ": " + message);
    }
}
