package pattern.struct.decorator;

import java.awt.*;

public class RedShapeDecorator extends ShapeDecorator {
    private Color color;

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        setBorderColor(Color.RED);
        super.draw();
    }

    private void setBorderColor(Color red) {
        color = red;
    }
}
