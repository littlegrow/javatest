package lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) {
        int logCollectThreadCount = 3;
        CountDownLatch countDownLatch = new CountDownLatch(logCollectThreadCount);
        Random random = new Random();
        for (int i = 0; i < logCollectThreadCount; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 开始收集日志");
                try {
                    Thread.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " 收集日志完毕");
            }, "Collect-thread-" + i).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("收集日志完成");
    }
}
