package pattern.create.abstractfactory.shape;

import pattern.create.abstractfactory.shape.Shape;

public class SquareShape implements Shape {
    private float length;

    public SquareShape(float length) {
        this.length = length;
    }

    @Override
    public int getShapeType() {
        return SHAPE_SQUARE;
    }

    @Override
    public float getArea() {
        return length * length;
    }

    @Override
    public float getPerimeter() {
        return length * 4;
    }
}
