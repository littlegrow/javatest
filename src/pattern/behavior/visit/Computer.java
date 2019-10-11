package pattern.behavior.visit;

import java.util.HashMap;
import java.util.Map;

public class Computer {
    public static final int SUM_VISITOR = 0;
    public static final int SUB_VISITOR = 1;
    public static final int MUL_VISITOR = 2;

    Map<Integer, Calculator> calculatorMap = new HashMap<>();

    public Computer() {
        calculatorMap.put(SUM_VISITOR, new Sum());
        calculatorMap.put(SUB_VISITOR, new Sub());
        calculatorMap.put(MUL_VISITOR, new Mul());
    }

    public int visit(int visitorType, int a, int b) {
        Calculator calculator = calculatorMap.get(visitorType);
        if (calculator != null) {
            return calculator.calculator(a, b);
        } else {
            throw new RuntimeException("unknown visitor type: " + visitorType);
        }
    }
}
