package sychronizer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class SynchronizerDemo {
    public static void main(String[] args) {
        //SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        //semaphoreDemo.test();

        //CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
        //countDownLatchDemo.test();

        CountDownLatchDemo2 countDownLathDemo2 = new CountDownLatchDemo2(3);
        countDownLathDemo2.test();
    }
}



