package pattern.create.abstractfactory;


import pattern.create.abstractfactory.factory.AbstractFactory;
import pattern.create.abstractfactory.factory.ColorFactory;
import pattern.create.abstractfactory.factory.ShapeFactory;

public class FactoryProducer {
    public static final String FACTORY_SHAPE = "shape_factory";
    public static final String FACTORY_COLOR = "color_factory";

    public static AbstractFactory getFactory(String factoryType) {
        if (FACTORY_SHAPE.equals(factoryType)) {
            return new ShapeFactory();
        } else if (FACTORY_COLOR.equals(factoryType)) {
            return new ColorFactory();
        }
        return null;
    }
}
