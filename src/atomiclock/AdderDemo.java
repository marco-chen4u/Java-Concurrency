package atomiclock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class AdderDemo {
    // fields
    private final int TOTAL_THREAD_COUNT = 100;
    private final int THREAD_POOL_SIZE = 16;
    private ExecutorService service;
    //AtomicLong counter = new AtomicLong(0);
    private LongAdder counter;// = new LongAdder();

    // constructor
    public AdderDemo() {
        initialize();
    }

    private void initialize() {
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("How many cores of CPU are available ? " + coreCount);
        service = Executors.newFixedThreadPool(coreCount);//THREAD_POOL_SIZE
        counter = new LongAdder();
    }

    public void test() {
        for (int i = 1; i <= TOTAL_THREAD_COUNT; i++) {
            service.submit(new CounterTask(counter));
        }

        //System.out.println(counter.get());
        System.out.println(counter.sum());// finally synchronized across all threads, only this step has some contention.

        service.shutdown();
    }
}

class CounterTask implements Runnable {
    // field
    //private final AtomicLong counter;
    private final LongAdder counter;

    // constructors
    //public Task(AtomicLong counter) {
    //    this.counter = counter;
    //}

    public CounterTask(LongAdder counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        //counter.incrementAndGet();// every increment operation had to be synchronized across the other threads
        counter.increment();//local counter for these separate threads instead of synchronization upon each add operation
    }
}
