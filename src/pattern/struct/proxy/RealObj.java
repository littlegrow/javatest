package pattern.struct.proxy;

public class RealObj implements IProxy {
    @Override
    public void deal(Object... args) {
        System.out.println("我是处理类");
    }
}
