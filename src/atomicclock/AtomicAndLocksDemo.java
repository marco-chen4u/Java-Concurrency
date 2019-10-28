package atomicclock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class AtomicAndLocksDemo {
    public static void main(String[] args) throws InterruptedException {
        //LockDemo lockDemo = new LockDemo();
        //lockDemo.test();

        //AtomicDemo atomicDemo = new AtomicDemo();
        //atomicDemo.test();

        //VolatileDemo volatileDemo = new VolatileDemo();
        //volatileDemo.test();

        AdderDemo adderDemo = new AdderDemo();
        adderDemo.test();

        //AccumulatorDemo accumulatorDemo = new AccumulatorDemo();
        //accumulatorDemo.test();
    }
}
