package pattern.create.simplefactory;

public class FactoryA implements Factory {
    @Override
    public Product getProduct() {
        return new ProductA();
    }
}
