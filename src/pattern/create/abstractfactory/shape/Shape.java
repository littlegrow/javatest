package pattern.create.abstractfactory.shape;

public interface Shape {
    int SHAPE_RECTANGLE = 1;
    int SHAPE_SQUARE = 2;
    int SHAPE_CIRCLE = 3;

    /**
     * 形状
     */
    int getShapeType();

    /**
     * 面积
     */
    float getArea();

    /**
     * 周长
     */
    float getPerimeter();
}
