package pattern.behavior.mediator;

public class User {
    public String id;
    public String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
