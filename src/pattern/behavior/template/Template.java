package pattern.behavior.template;

public abstract class Template {
    public Template() {
        onCreate();
    }

    public void onCreate() {
        initView();
        initData();
        initListener();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();
}
