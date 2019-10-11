package pattern.struct.bridge;

public abstract class Shape {
    protected DrawAPI mDrawAPI;

    public Shape(DrawAPI mDrawAPI) {
        this.mDrawAPI = mDrawAPI;
    }

    public abstract void draw();
}
