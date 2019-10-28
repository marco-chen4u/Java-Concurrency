package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockBasicsDemo {
    private Lock lockA = new ReentrantLock();
    private Lock lockB = new ReentrantLock();

    public void test() {
        System.out.println(this.getClass().getName() + " getting started...");
        new Thread(this::processThis).start();
        new Thread(this::processThat).start();
        System.out.println(this.getClass().getName() + " done");
    }

    // deadlock result from circular dependency locks of multiple threads. that is inconsistent ordering of locks.
    //[A] to check deadlocks :
    // (1) use jstack commands(thread dump) for analysis;
    // (2) use ThreadMXBean for source code to check it.

    //[B] to tackle this issues :
    // (1) use try methods for (locks/ semaphore) by timeout setting;
    // (2) pay attention to the order of lock usages
    private void processThis() {
        lockA.lock();
        // process resource A

        lockB.lock();
        // process resource A and B

        lockA.unlock();
        lockB.unlock();
    }

    private void processThat() {
        lockB.lock();
        // process resource B

        lockA.lock();
        // process resource A and B

        lockA.unlock();
        lockB.unlock();
    }
}
