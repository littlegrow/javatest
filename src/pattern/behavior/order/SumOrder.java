package pattern.behavior.order;

public class SumOrder implements Order {
    private Request request;

    public SumOrder(Request request) {
        this.request = request;
    }

    @Override
    public int getOrderType() {
        return Order.ORDER_SUM;
    }

    @Override
    public int execute(int... array) {
        return request.sum(array);
    }
}
