package pattern.struct.bridge;

public class DrawBlueCircle implements DrawAPI {
    @Override
    public void drawCircle(float x, float y, float radius) {
        System.out.println("draw blue circle");
    }
}
