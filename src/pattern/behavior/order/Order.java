package pattern.behavior.order;

public interface Order {
    int ORDER_SUM = 0;
    int ORDER_MUL = 1;

    int getOrderType();

    int execute(int... array);
}
