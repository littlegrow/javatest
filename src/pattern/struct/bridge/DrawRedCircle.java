package pattern.struct.bridge;

public class DrawRedCircle implements DrawAPI {
    @Override
    public void drawCircle(float x, float y, float radius) {
        System.out.println("draw red circle");
    }
}
