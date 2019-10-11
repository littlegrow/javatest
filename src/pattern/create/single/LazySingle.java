package pattern.create.single;


public class LazySingle {
    private static LazySingle INSTANCE = null;

    private LazySingle() {
    }

    public static LazySingle getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingle();
        }
        return INSTANCE;
    }
}
