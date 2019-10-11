package lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockReentrantTest {
    static class Test {
        private ReentrantLock reentrantLock = new ReentrantLock();

        private void methodA() {
            reentrantLock.lock();
            System.out.println("in methodA");
            methodB();
            reentrantLock.unlock();
            System.out.println("methodA release lock");
        }

        private void methodB() {
            reentrantLock.lock();
            System.out.println("in methodA");
            reentrantLock.unlock();
            System.out.println("methodB release lock");
        }

        public void test() {
            methodA();
        }
    }

    public static void main(String[] args) {
        new Test().test();
    }
}
