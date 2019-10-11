package lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    static class CacheData {
        private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
        private Lock readLock = readWriteLock.readLock();
        private Lock writeLock = readWriteLock.writeLock();

        private Map<String, Object> cacheMap = new HashMap<>();

        <T> T getCache(String key) {
            try {
                readLock.lock();
                return (T) cacheMap.get(key);
            } catch (Exception e) {
                return null;
            } finally {
                readLock.unlock();
            }
        }

        <T> void putCache(String key, T value) {
            try {
                writeLock.lock();
                cacheMap.put(key, value);
            } finally {
                writeLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CacheData cacheData = new CacheData();
        Random random = new Random(System.currentTimeMillis());
        int readerCount = 5;
        long startTime = System.currentTimeMillis();
        Thread[] readThread = new Thread[readerCount];
        for (int i = 0; i < readerCount; i++) {
            readThread[i] = new Thread(() -> {
                for (int j = 0; j < readerCount; j++) {
                    String key = "cache_" + random.nextInt(5);
                    System.out.println(Thread.currentThread().getName() + " read " + key + ": " + cacheData.getCache(key));
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Reader_" + i);
            readThread[i].start();
        }
        Thread writeThread = new Thread(() -> {
            for (int i = 0; i < readerCount; i++) {
                cacheData.putCache("cache_" + i, i);
            }
        });
        writeThread.start();

        writeThread.join();
        for (int i = 0; i < readerCount; i++) {
            readThread[i].join();
        }
        System.out.println("cost " + (System.currentTimeMillis() - startTime));
    }
}
