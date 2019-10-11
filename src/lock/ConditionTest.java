package lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private ReentrantLock lock = new ReentrantLock();
    private Condition emptyCondition = lock.newCondition();
    private Condition fullCondition = lock.newCondition();
    private static final int poolSize = 3;
    private static List<Object> pool = new LinkedList<>();

    private void consume() {
        lock.lock();
        try {
            while (pool.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " pool is empty, wait for produce");
                emptyCondition.await();
            }
            pool.remove(0);
            System.out.println(Thread.currentThread().getName() + " consume one object");
            fullCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void produce() {
        lock.lock();
        try {
            while (pool.size() >= poolSize) {
                System.out.println(Thread.currentThread().getName() + " pool is full, wait for consume");
                fullCondition.await();
            }
            pool.add(new Object());
            System.out.println(Thread.currentThread().getName() + " produce one object");
            emptyCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest conditionTest = new ConditionTest();
        Thread consumer;
        for (int i = 0; i < 3; i++) {
            consumer = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    conditionTest.consume();
                }
            }, "consumer-" + i);
            consumer.join();
            consumer.start();
        }
        Thread producer;
        for (int i = 0; i < 3; i++) {
            producer = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    conditionTest.produce();
                }
            }, "producer-" + i);
            producer.join();
            producer.start();
        }
    }
}
