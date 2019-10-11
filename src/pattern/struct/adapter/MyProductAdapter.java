package pattern.struct.adapter;

public class MyProductAdapter implements IAdapter {

    private ThirdProduct thirdProduct = new ThirdProduct();

    @Override
    public Object dealMine(String... args) {
        // 前置处理
        Object result = thirdProduct.dealSomething(args);
        // 后置处理
        return result;
    }

}
