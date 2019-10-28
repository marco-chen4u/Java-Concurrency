package callablefutrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableDemo {
    // fields
    private final int TOTAL_THREAD_COUNT = 100;
    private final int POOL_SIZE = 10;
    private ExecutorService service;

    // constructor
    public CallableDemo() {
        initialize();
    }

    private void initialize() {
        service = Executors.newFixedThreadPool(POOL_SIZE);
    }

    public void test() {

        List<Future> futureList = new ArrayList<Future>();
        for (int i = 1; i <= TOTAL_THREAD_COUNT; i++) {
            Future<Integer> currentFuture = service.submit(new CallableTask());
            futureList.add(currentFuture);
        }

        int index = 1;
        for(Future<Integer> current : futureList) {
            try {
                //Integer result = current.get();
                Integer result = current.get(1, TimeUnit.SECONDS);
                System.out.println("Result of future #" + index + "=" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }

            index++;
        }

        //Future<Integer> future = service.submit(new CallableTask());
        // perform some other unrelated operations

        //Integer result = null;// blocking call, it's blocking now, until it gets the result back.
        //try {
        //    result = future.get();
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //} catch (ExecutionException e) {
        //    e.printStackTrace();
        //}
        //System.out.println(result);

        service.shutdown();
    }
}

class CallableTask implements Callable<Integer> {
    // field
    private Random random;

    // constructor
    public CallableTask() {
        this.random = new Random();
    }

    @Override
    public Integer call() throws Exception{
        Thread.sleep(1000);
        return random.nextInt();
    }
}
