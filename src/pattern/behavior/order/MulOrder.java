package pattern.behavior.order;

public class MulOrder implements Order {
    private Request request;

    public MulOrder(Request request) {
        this.request = request;
    }

    @Override
    public int getOrderType() {
        return Order.ORDER_MUL;
    }

    @Override
    public int execute(int... array) {
        return request.mul(array);
    }
}
