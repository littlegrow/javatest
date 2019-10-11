package pattern.create.abstractfactory.shape;

public class CircleShape implements Shape {
    private float radius;

    public CircleShape(float radius) {
        this.radius = radius;
    }

    @Override
    public int getShapeType() {
        return SHAPE_SQUARE;
    }

    @Override
    public float getArea() {
        return (float) Math.PI * radius * radius;
    }

    @Override
    public float getPerimeter() {
        return (float) Math.PI * 2 * radius;
    }
}
