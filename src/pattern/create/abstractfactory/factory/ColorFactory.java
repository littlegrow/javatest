package pattern.create.abstractfactory.factory;

import pattern.create.abstractfactory.color.Black;
import pattern.create.abstractfactory.color.Color;
import pattern.create.abstractfactory.color.Red;
import pattern.create.abstractfactory.color.White;
import pattern.create.abstractfactory.shape.Shape;

public class ColorFactory implements AbstractFactory {
    @Override
    public Shape getShape(int shapeType, float... args) {
        return null;
    }

    @Override
    public Color getColor(String colorType) {
        if (Color.COLOR_RED.equals(colorType)) {
            return new Red();
        } else if (Color.COLOR_BLACK.equals(colorType)) {
            return new Black();
        } else if (Color.COLOR_WHITE.equals(colorType)) {
            return new White();
        }
        return null;
    }
}
