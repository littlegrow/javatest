package pattern.create.prototype;

public class Shape implements Cloneable {

    @Override
    protected Shape clone() {
        Shape clone = null;
        try {
            clone = (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
