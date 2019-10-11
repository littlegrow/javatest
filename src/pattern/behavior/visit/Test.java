package pattern.behavior.visit;

import java.util.concurrent.ExecutorService;

public class Test {
    public static void main(String[] args) {
        Computer computer = new Computer();
        System.out.println("sum visitor: 2 + 3 = " + computer.visit(Computer.SUM_VISITOR, 2, 3));
        System.out.println("sub visitor: 2 - 3 = " + computer.visit(Computer.SUB_VISITOR, 2, 3));
        System.out.println("mul visitor: 2 * 3 = " + computer.visit(Computer.MUL_VISITOR, 2, 3));
        System.out.println("unknown visitor: 2 / 3 = " + computer.visit(-1, 2, 3));

//        ExecutorService
    }
}
