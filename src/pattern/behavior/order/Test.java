package pattern.behavior.order;

public class Test {
    public static void main(String[] args) {
        Request request = new Request();

        User user = new User();
        user.takeOrder(new MulOrder(request));
        user.takeOrder(new SumOrder(request));

        System.out.println("execute sum order: " + user.executeOrder(Order.ORDER_SUM, 1, 2, 3, 4, 5));
        System.out.println("execute mul order: " + user.executeOrder(Order.ORDER_MUL, 1, 2, 3, 4, 5));
    }
}
