package pattern.struct.proxy;

public class ProxyObj implements IProxy {
    private RealObj realObj;

    @Override
    public void deal(Object... args) {
        if (realObj == null) {
            realObj = new RealObj();
        }
        System.out.println("我是代理类，现在要调用具体类处理数据");
        realObj.deal(args);
    }
}
