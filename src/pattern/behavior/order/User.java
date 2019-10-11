package pattern.behavior.order;

import java.util.HashMap;
import java.util.Map;

public class User {
    Map<Integer, Order> orderMap = new HashMap<>();

    public void takeOrder(Order order) {
        orderMap.put(order.getOrderType(), order);
    }

    public int executeOrder(int orderType, int... array) {
        Order order = orderMap.get(orderType);
        if (order != null) {
            return order.execute(array);
        } else {
            throw new RuntimeException("you has not " + orderType + " order");
        }
    }
}
