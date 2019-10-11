package pattern.struct.facade;

public class ShapeMaker {
    private IShape circle = new Circle();
    private IShape square = new Square();

    public void drawCircle() {
        circle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}
