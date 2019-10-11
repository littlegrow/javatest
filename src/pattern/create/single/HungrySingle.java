package pattern.create.single;

public class HungrySingle {
    private static HungrySingle INSTANCE = new HungrySingle();

    private HungrySingle() {
    }

    public static HungrySingle getInstance() {
        return INSTANCE;
    }
}
