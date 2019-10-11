package pattern.struct.bridge;

public class Circle extends Shape {
    private float x;
    private float y;
    private float radius;

    public Circle(float x, float y, float radius, DrawAPI mDrawAPI) {
        super(mDrawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        mDrawAPI.drawCircle(x, y, radius);
    }
}
