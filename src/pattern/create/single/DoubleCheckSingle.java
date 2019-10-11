package pattern.create.single;

public class DoubleCheckSingle {
    private static volatile DoubleCheckSingle INSTANCE = null;

    private DoubleCheckSingle() {

    }

    public static DoubleCheckSingle getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckSingle.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckSingle();
                }
            }
        }
        return INSTANCE;
    }
}
