package atomicclock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    // filed
    private Data data;

    // constructor
    public LockDemo() {
        this.data = new Data();
    }

    public void test() {
        new OperateWorker(data).start();
        new OperateWorker(data).start();
        new OperateWorker(data).start();
        new OperateWorker(data).start();
        new OperateWorker(data).start();
    }
}

class Data {
    // field
    private static int value = 0;
    private static Lock lock = new ReentrantLock();

    public static void operate() {
        lock.lock();

        value++;
        System.out.println(value);

        lock.unlock();
    }
}

class OperateWorker extends Thread {
    // field
    private Data data;
    public OperateWorker(Data data) {
        this.data = data;
    }

    public void run() {
        while (true) {
            try {
                sleep(1000);
                data.operate();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
