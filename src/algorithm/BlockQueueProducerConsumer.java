package algorithm;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockQueueProducerConsumer {

    private static int produceNumber = 0;
    private static int consumeNumber = 0;

    public static class Storage {
        final BlockingQueue<Object> productPool = new LinkedBlockingQueue<>(4);

        public void product(Object obi) {
            try {
                produceNumber++;
                productPool.put(obi);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public Object consume() {
            try {
                consumeNumber++;
                return productPool.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
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
