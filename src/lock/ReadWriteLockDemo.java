package lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    // fields
    private ReentrantReadWriteLock lock;
    private ReentrantReadWriteLock.ReadLock readLock;
    private ReentrantReadWriteLock.WriteLock writeLock;

    // constructor
    public ReadWriteLockDemo() {
        this.lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    private void readResource() {
        readLock.lock();
        //view resource
        System.out.println("view resource");
        readLock.unlock();
    }

    private void writeResource() {
        writeLock.lock();
        // update the resource
        System.out.println("update the resource");
        writeLock.unlock();
    }

    public void test() {
        Thread t1 = new Thread(() -> this.readResource());
        Thread t3 = new Thread(() -> this.writeResource());
        Thread t2 = new Thread(() -> this.readResource());
        Thread t4 = new Thread(() -> this.writeResource());
        t1.start();
        t3.start();
        t2.start();
        t4.start();
    }
}
