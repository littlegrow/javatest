package sort;

/**
 * 记录一段时间间隔
 */
public class TimeDelta {

    private long time;

    public TimeDelta() {
        this.time = System.currentTimeMillis();
    }

    public long getDelta() {
        return System.currentTimeMillis() - time;
    }

    public void renew() {
        time = System.currentTimeMillis();
    }
}
