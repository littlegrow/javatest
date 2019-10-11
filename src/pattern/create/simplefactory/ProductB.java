package pattern.create.simplefactory;

public class ProductB implements Product {
    @Override
    public String getProductName() {
        return ProductB.class.getSimpleName();
    }
}
