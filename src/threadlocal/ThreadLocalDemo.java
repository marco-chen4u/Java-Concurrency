package threadlocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        //ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocalSimulator<String> threadLocalSimulator = new ThreadLocalSimulator<String>() {
            @Override
            public String initialValue() {
                return "No Value";
            }
        };

        Random random = new Random();

        //ThreadLocalTask task1 = new ThreadLocalTask(random, threadLocal, "Thread-test1");
        //ThreadLocalTask task2 = new ThreadLocalTask(random, threadLocal, "Thread-test2");

        ThreadLocalTask task1 = new ThreadLocalTask(random, threadLocalSimulator, "Thread-test1");
        ThreadLocalTask task2 = new ThreadLocalTask(random, threadLocalSimulator, "Thread-test2");

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(Thread.currentThread().getName() + " " + threadLocalSimulator.get());
    }
}

class ThreadLocalTask implements Runnable {
    // fields
    private final Random random;
    //private final ThreadLocal<String> threadLocal;
    private final ThreadLocalSimulator<String> threadLocalSimulator;
    private final String name;

    // constructors
    //public ThreadLocalTask(Random random, ThreadLocal<String> threadLocal, String name) {
    //    this.random = random;
    //    this.threadLocal = threadLocal;
    //    this.name = name;
    //}

    public ThreadLocalTask(Random random, ThreadLocalSimulator<String> threadLocalSimulator, String name) {
        this.random = random;
        this.threadLocalSimulator = threadLocalSimulator;
        this.name = name;
    }

    @Override
    public void run() {
        //threadLocal.set(name);
        threadLocalSimulator.set(name);

        try {
            Thread.sleep(random.nextInt(1000));
            //System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + " " + threadLocalSimulator.get());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
