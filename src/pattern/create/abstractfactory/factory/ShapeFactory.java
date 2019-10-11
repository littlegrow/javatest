package pattern.create.abstractfactory.factory;

import pattern.create.abstractfactory.color.Color;
import pattern.create.abstractfactory.shape.CircleShape;
import pattern.create.abstractfactory.shape.RectangleShape;
import pattern.create.abstractfactory.shape.Shape;
import pattern.create.abstractfactory.shape.SquareShape;

public class ShapeFactory implements AbstractFactory {

    @Override
    public Shape getShape(int shapeType, float... args) {
        if (shapeType == Shape.SHAPE_RECTANGLE) {
            float width = 0, height = 0;
            if (args != null && args.length == 2) {
                width = args[0];
                height = args[1];
            }
            return new RectangleShape(width, height);
        } else if (shapeType == Shape.SHAPE_SQUARE) {
            float length = 0;
            if (args != null && args.length == 1) {
                length = args[0];
            }
            return new SquareShape(length);
        } else if (shapeType == Shape.SHAPE_CIRCLE) {
            float radius = 0;
            if (args != null && args.length == 1) {
                radius = args[0];
            }
            return new CircleShape(radius);
        }
        return null;
    }

    @Override
    public Color getColor(String colorType) {
        return null;
    }
}
