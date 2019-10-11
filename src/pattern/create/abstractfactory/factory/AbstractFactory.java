package pattern.create.abstractfactory.factory;

import pattern.create.abstractfactory.color.Color;
import pattern.create.abstractfactory.shape.Shape;

public interface AbstractFactory {
    Shape getShape(int shapeType, float... args);

    Color getColor(String colorType);
}
