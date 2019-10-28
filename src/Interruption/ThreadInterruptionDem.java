package Interruption;

import utils.StopWatch;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadInterruptionDem {
    private final static int FIVE_MINUTES = 300000;
    private final static int TWO_MINUTES = 120000;
    private final static int ONE_MINUTES = 60000;
    private final static int FIVE_SECONDS = 5000;
    private final static int TWO_SECOND = 2000;
    private final static int ONE_SECOND = 1000;
    private static ExecutorService threadPool;
    private static ScheduledExecutorService scheduledExecutorService;

    public static void main(String[] args) throws InterruptedException {
        threadPool = Executors.newFixedThreadPool(2);
        scheduledExecutorService = Executors.newScheduledThreadPool(2);

        test1();

        //test2();

        //test3();

        //test4();
    }

    private static void test1() {
        InterruptedTask task = new InterruptedTask();
        final Future<?> future = threadPool.submit(task);

        System.out.println("Starting running and get future...");
        try {
            future.get(20, TimeUnit.SECONDS);
        }
        catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        catch (TimeoutException e) {
            future.cancel(true);
        }
        System.out.println("Complete running and get future...");
        threadPool.shutdown();
    }

    private static void test2() throws InterruptedException {
        RunnableTask task = new RunnableTask();
        //Thread thread2 = new Thread(task);
        //thread2.start();
        //Thread.sleep(FIVE_SECONDS);

        threadPool.submit(task);
        Thread.sleep(FIVE_SECONDS);

        task.stop();
        Thread.sleep(TWO_SECOND);

        threadPool.shutdown();
    }

    private static void test3() throws InterruptedException {
        RunnableTask task = new RunnableTask();
        //StopWatch stopWatch = new StopWatch();
        System.out.println("task is starting");
        //stopWatch.start();
        scheduledExecutorService.schedule(()-> {task.stop();}, 5 , TimeUnit.SECONDS);
        //Thread.sleep(TWO_MINUTES);
        System.out.println("Running task....");

        scheduledExecutorService.shutdown();
        //stopWatch.stop();
        System.out.println("ScheduledExecutorService is shutting down.");
        //stopWatch.prettyPrint();
    }

    private static void  test4() {
        RunnableTask task = new RunnableTask();
        final Future<?> future = threadPool.submit(task);

        System.out.println("Starting running and get future...");
        try {
            future.get(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        catch (TimeoutException ex) {
            //future.cancel(true);// if using interrupts
            task.stop();// if using volatile or atomic variable to flag
        }
        System.out.println("Stop running and get future!");
        threadPool.shutdown();
    }
}

class InterruptedTask extends Thread {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // do some process
        }

        System.out.println(Thread.currentThread().getName() + "has been interrupted!");
    }
}

class RunnableTask implements Runnable {
    //private volatile boolean keepingRunning = true;
    private AtomicBoolean keepingRunning = new AtomicBoolean(true);

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running now...");

        while(keepingRunning.get()) {
            // do some processes
        }

        if (!keepingRunning.get()) {
            System.out.println(Thread.currentThread().getName() +" task is getting stopped.");
        }

        System.out.println(Thread.currentThread().getName() + " has completed!");
    }

    public void stop() {
        //this.keepingRunning = false;
        this.keepingRunning.set(false);
    }
}

class CallableTask implements Callable {

    @Override
    public Object call() throws Exception {
        return null;
    }
}
