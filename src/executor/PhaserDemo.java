package executor;

import java.util.concurrent.Phaser;

public class PhaserDemo {
    // field
    private Phaser phaser;

    // constructor
    public PhaserDemo() {
        this.phaser = new Phaser(1);
    }

    public void test() {
        //Phaser phaser = new Phaser(1);
        System.out.println(" starting ...");
        new Worker(phaser, "waitor").start();
        new Worker(phaser, "chef").start();
        new Worker(phaser, "front-desk").start();

        for (int i = 1; i <= 3; i++) {
            phaser.arriveAndAwaitAdvance();
            System.out.println("Order " + i + " finished");
        }

        phaser.arriveAndDeregister();
        System.out.println("All done!");
    }
}

class Worker extends Thread {
    // field
    Phaser phaser;

    // constructor
    public Worker(Phaser phaser, String name) {
        setName(name);
        this.phaser = phaser;
        phaser.register();
    }

    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("current order is : " + i + " : " + getName());
            if (i == 3) {
                phaser.arriveAndDeregister();
            }
            else {
                phaser.arriveAndAwaitAdvance();
            }

            try {
                sleep(1000);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
