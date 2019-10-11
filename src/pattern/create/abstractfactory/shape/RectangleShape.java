package pattern.create.abstractfactory.shape;

import pattern.create.abstractfactory.shape.Shape;

public class RectangleShape implements Shape {
    private float width;
    private float height;

    public RectangleShape(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getShapeType() {
        return SHAPE_RECTANGLE;
    }

    @Override
    public float getArea() {
        return width * height;
    }

    @Override
    public float getPerimeter() {
        return (width + height) * 2;
    }
}
