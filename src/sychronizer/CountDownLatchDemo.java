package sychronizer;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    // field
    private CountDownLatch countDownLatch;

    // constructor
    public CountDownLatchDemo() {
        this.countDownLatch = new CountDownLatch(3);// 3 seconds/party-member(to arrive) to count down
    }

    public void test() {
        new Racer(countDownLatch, "A").start();
        new Racer(countDownLatch, "B").start();
        new Racer(countDownLatch, "C").start();

        System.out.println("Counting down ...");
        for (int i = 3; i >= 1; i--) {
            try {
                Thread.sleep(1000);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(i);
            countDownLatch.countDown();
        }
        System.out.println("Start!");
    }
}

class Racer extends Thread {
    // field
    private CountDownLatch countDownLatch;

    // constructor
    public Racer(CountDownLatch countDownLatch, String name) {
        setName(name);
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            countDownLatch.await();
            for (int i = 1; i <= 3; i++) {
                System.out.println(getName() + " : " + i);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
