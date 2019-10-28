package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserDemo2 {
    // fields
    private ExecutorService threadPool;
    private Phaser phaser;
    private int count;

    // constructor
    public PhaserDemo2(int count) {
        this.count = count;
        this.threadPool = Executors.newFixedThreadPool(count + 1);
        this.phaser = new Phaser(count);
    }

    public void test() throws InterruptedException {
        for (int i = 1; i <= count; i++) {
            threadPool.submit(new SeparateTask(phaser, "task-" + i));
        }

        phaser.awaitAdvance(1);// similar to await() of CountDownLatch
        Thread.sleep(1000);

        System.out.println("All dependent services initialized");
        // program initialized, perform other operations

        threadPool.shutdown();
    }
}

class SeparateTask implements Runnable {
    // field
    private Phaser phaser;
    private String name;

    // constructor
    public SeparateTask(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            // startup task
            phaser.arrive();// similar to countDown() of CountDownLatch
            System.out.println(name + " has just arrived and be ready to advance");
            // continue w/ other operations
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
