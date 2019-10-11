package pattern.create.abstractfactory.color;

public class White implements Color {
    @Override
    public String getColorType() {
        return COLOR_WHITE;
    }

    @Override
    public int getColor() {
        return 0xffffff;
    }
}
