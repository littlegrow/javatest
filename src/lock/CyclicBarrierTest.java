package lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    static class Worker extends Thread {
        private CyclicBarrier cyclicBarrier;

        Worker(CyclicBarrier cyclicBarrier, String name) {
            super(null, null, name, 0);
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            super.run();
            System.out.println(getName() + " 开始等待其他工人");
            try {
                cyclicBarrier.await();
                System.out.println(getName() + " 开始执行");
                Thread.sleep(1000);
                System.out.println(getName() + " 执行完毕");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            Worker worker = new Worker(cyclicBarrier, "worker-" + i);
            worker.start();
        }
    }
}
