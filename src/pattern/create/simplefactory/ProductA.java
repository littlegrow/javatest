package pattern.create.simplefactory;

public class ProductA implements Product {
    @Override
    public String getProductName() {
        return ProductA.class.getSimpleName();
    }
}
