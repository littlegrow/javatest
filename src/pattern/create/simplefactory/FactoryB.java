package pattern.create.simplefactory;

public class FactoryB implements Factory {
    @Override
    public Product getProduct() {
        return new ProductB();
    }
}
