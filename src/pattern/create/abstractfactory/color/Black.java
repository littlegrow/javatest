package pattern.create.abstractfactory.color;

public class Black implements Color {
    @Override
    public String getColorType() {
        return COLOR_BLACK;
    }

    @Override
    public int getColor() {
        return 0x000000;
    }
}
