import sort.TimeDelta;

import java.util.concurrent.CountDownLatch;

public class StringBufferBuilderTest {
    /**
     * 测试线程个数
     */
    static final int THREAD_COUNT = 100;

    /**
     * 记录执行耗时
     */
    static TimeDelta timeDelta = new TimeDelta();

    /**
     * 测试 StringBuffer 写入性能及线程安全
     *
     * @throws InterruptedException
     */
    static void testStringBuffer() throws InterruptedException {
        timeDelta.renew();
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < THREAD_COUNT * 100; j++) {
                        buffer.append('a');
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("StringBuffer: 耗时 " + timeDelta.getDelta() + "ms");
        System.out.println("StringBuffer: 大小 " + buffer.length() + '\n');
    }

    /**
     * 测试 StringBuilder 写入性能及线程安全
     *
     * @throws InterruptedException
     */
    static void testStringBuilder() throws InterruptedException {
        timeDelta.renew();
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < THREAD_COUNT * 100; j++) {
                        builder.append('a');
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("StringBuilder: 耗时 " + timeDelta.getDelta() + "ms");
        System.out.println("StringBuilder: 大小 " + builder.length() + '\n');
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("测试StringBuffer、StringBuilder性能及线程安全\n写数据使用%s个线程，" +
                "每个线程写入%s个字符\n", THREAD_COUNT, THREAD_COUNT * 100));
        testStringBuffer();
        testStringBuilder();
    }
}