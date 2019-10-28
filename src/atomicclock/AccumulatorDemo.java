package atomicclock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

public class AccumulatorDemo {
    // fields
    private final LongBinaryOperator operator1 = (x, y) -> x + y;//lambda expression functor
    private final LongBinaryOperator operator2 = (x, y) -> x * y;//lambda expression functor
    private final LongBinaryOperator operator3 = (x, y) -> Math.min(x, y);//lambda expression functor
    private final LongBinaryOperator operator4 = (x, y) -> Math.max(x, y);//lambda expression functor
    private final LongBinaryOperator operator5 = (x, y) -> x / y;//lambda expression functor
    private final int TOTAL_THREAD_COUNT = 100;
    private final int THREAD_POOL_SIZE = 16;
    private ExecutorService service;

    private LongAccumulator counter;

    // constructor
    public AccumulatorDemo() {
        initialize();
    }

    private void initialize() {
        service = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        counter = new LongAccumulator(operator2, 1);
    }

    public void test() {
        for (int i = 1; i <= TOTAL_THREAD_COUNT; i++) {
            service.submit(new AccumulatorTask(counter));
        }

        System.out.println(counter.get());

        service.shutdown();
    }
}

class AccumulatorTask implements Runnable {
    // field
    private final LongAccumulator counter;

    // constructor
    public AccumulatorTask(LongAccumulator counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        // do some processing
        counter.accumulate(2);
    }
}
