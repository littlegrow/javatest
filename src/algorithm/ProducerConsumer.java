package algorithm;


import java.util.LinkedList;
import java.util.List;

public class ProducerConsumer {

    private static int produceNumber = 0;
    private static int consumeNumber = 0;

    public static class Storage {
        private static final int maxSize = 4;
        final List<Object> productPool = new LinkedList<>();

        public void product(Object obi) {
            synchronized (productPool) {
                while (productPool.size() > maxSize) {
                    try {
                        productPool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                productPool.add(obi);
                productPool.notify();
                produceNumber++;
            }
        }

        public Object consume() {
            Object result;
            synchronized (productPool) {
                while (productPool.isEmpty()) {
                    try {
                        productPool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                result = productPool.remove(0);
                productPool.notify();
                consumeNumber++;
            }
            return result;
        }
    }

    public static class Producer extends Thread {
        private Storage storage;

        public Producer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                Object obj = new Object();
                storage.product(obj);
                System.out.println("Producer-" + Thread.currentThread().getName() + ": produce " + i);
            }
        }
    }

    public static class Consumer extends Thread {
        private Storage storage;

        public Consumer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 6; i++) {
                storage.consume();
                System.out.println("Consumer-" + Thread.currentThread().getName() + ": consume " + i);
            }
        }
    }

    public static void main(String[] args) {
        Storage storage = new Storage();

        Producer producer1 = new Producer(storage);
        Producer producer2 = new Producer(storage);
        Producer producer3 = new Producer(storage);

        Consumer consumer1 = new Consumer(storage);
        Consumer consumer2 = new Consumer(storage);

        producer1.start();
        producer2.start();
        producer3.start();


        consumer1.start();
        consumer2.start();

        try {
            // 主线程等子线程执行完毕后再继续执行
            // 当我们调用某个线程的 join() 方法后，挂起调用线程，直到被调用线程结束完毕，调用线程才会继续执行
            producer1.join();
            producer2.join();
            producer3.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finish" + " produce: " + produceNumber + " consume: " + consumeNumber);
    }
}
