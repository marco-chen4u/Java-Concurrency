package forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Hello Fork/Join Framework!");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> result = forkJoinPool.submit(new WorkTask(1, 100000));
        System.out.println(result.get());
        forkJoinPool.shutdown();
    }
}

class WorkTask extends RecursiveTask<Long> {
    // fields
    private final int THRESH_HOLD = 10000;

    private int start;
    private int end;

    // constructor
    public WorkTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Long compute() {
        long sum = 0;

        if (end - start <= THRESH_HOLD) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }
        else {
            int mid = start + (end - start) / 2;

            // divide(forking)
            WorkTask left = new WorkTask(start, mid);// sub-task-1
            left.fork();
            WorkTask right = new WorkTask(mid + 1, end);// sub-task-2
            right.fork();

            // conquer(joining)
            Long leftValue = left.join();
            System.out.println(start + "->" + mid + " : " + leftValue);
            Long rightValue = right.join();
            System.out.println(mid + "->" + end + " : " + rightValue);

            sum = leftValue + rightValue; // join results
        }

        return sum;
    }
}
