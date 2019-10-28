package topics.ProducerConsumerPattern;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {
    private LinkedList<T> queue;
    private int max = 20;
    private Lock lock = new ReentrantLock(true);
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BlockingQueue(int size) {
        queue = new LinkedList<T>();
        this.max = size;
    }

    // helper methods
    private boolean isEmpty() {
        return queue.size() == 0;
    }

    private boolean isFull() {
        return queue.size() == max;
    }

    public void put(T t) {
        lock.lock();

        try {
            if (isFull()) {
                notFull.await();// block the thread, wait for not full
            }
            queue.offer(t);
            notEmpty.signalAll();// notify consumers that are in block state
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();

        try {
            while (isEmpty()) {
                notEmpty.await();// block the thread, wait for not empty
            }
            T item = this.queue.poll();
            notFull.signalAll();// notify producers that are in block state
            return item;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

        return null;// default
    }

    public int remainingCapacity() {
        return this.queue.size();
    }
}
