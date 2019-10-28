package forkjoin;

import java.util.concurrent.*;

public class FibonacciDemo {
    public static void main(String[] args){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int n = 50;//8 minutes on my MAC book(8-core cpu) to complete
        ForkJoinTask<Long> result = forkJoinPool.submit(new Fibonacci(n));

        try {
            //System.out.println(result.get());
            System.out.println(result.get(10, TimeUnit.MINUTES));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        forkJoinPool.shutdown();
    }
}

class Fibonacci extends RecursiveTask<Long> {
    // fields
    private final int n;

    // constructor
    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    public Long compute() {
        // check corner case
        if (n <= 1) {
            return (long)n;
        }
        // divide(split into sub-tasks, forking)
        Fibonacci f1 = new Fibonacci(n - 1);// fork into sub-task 1
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);// fork into sub-task 2
        f2.fork();

        // conquer(join the results)
        return f1.join() + f2.join();// joining
    }
}
