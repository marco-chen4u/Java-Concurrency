package topics.ProducerConsumerPattern;

import java.util.Random;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleProucerConsumerDemo {
    public static void main(String[] args) {
        //BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
        BlockingQueue<Integer> queue = new BlockingQueue<Integer>(40);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        threadPool.submit(new Producer(queue));
        threadPool.submit(new Consumer(queue));
    }
}

class Producer implements Runnable {
    // field
    private final BlockingQueue<Integer> queue;
    private final int COUNT = 20;
    private final int MAX_VALUE = 1000;//Integer.MAX_VALUE;
    private Random random;

    // constructor
    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
        this.random = new Random();
    }

    // helper method
    private String getName() {
        return Thread.currentThread().getName();
    }

    private void process() throws InterruptedException {
        for (int i = 1; i <= COUNT; i++) {
            int value = random.nextInt(MAX_VALUE);
            System.out.println(getName() + " [Producer] put "+ value);
            queue.put(value);// current thread blocks if queue is full
            System.out.println(getName() + "[Producer] Queue remaining capacity : " + queue.remainingCapacity());
            Thread.sleep(100);
        }
    }

    @Override
    public void run() {
        try {
            process();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    // fields
    private final BlockingQueue<Integer> queue;

    // constructor
    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    // helper method
    private void process(Integer item) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "[Consumer] take : " + item);
        Thread.sleep(500);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer item = queue.take();
                process(item);// current thread blocks if queue is empty
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
