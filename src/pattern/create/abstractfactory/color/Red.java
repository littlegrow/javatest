package pattern.create.abstractfactory.color;

public class Red implements Color {
    @Override
    public String getColorType() {
        return COLOR_RED;
    }

    @Override
    public int getColor() {
        return 0xff0000;
    }
}
