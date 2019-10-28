package sychronizer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo2 {
    // fields
    private ExecutorService threadPool;
    private CountDownLatch countDownLatch;
    private int count;

    // constructor
    public CountDownLatchDemo2(int count) {
        this.count = count;
        this.threadPool = Executors.newFixedThreadPool(count + 1);
        this.countDownLatch = new CountDownLatch(count);
    }

    public void test() {
        for (int i = 1; i <= count; i++) {
            threadPool.submit(new DependentTask(countDownLatch, "Task-" + i));
        }

        try {
            countDownLatch.await();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("All dependent tasks initialized");
        // program initialized, perform other operations

        threadPool.shutdown();
    }
}

class DependentTask implements Runnable {
    // fields
    private CountDownLatch countDownLatch;
    private String name;

    // constructor
    public DependentTask(CountDownLatch countDownLatch, String name) {
        this.countDownLatch = countDownLatch;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            // startup task
            //Thread.sleep(1000);
            countDownLatch.countDown();
            System.out.println(name + " is ready.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        // continue w/ other operations
    }
}
