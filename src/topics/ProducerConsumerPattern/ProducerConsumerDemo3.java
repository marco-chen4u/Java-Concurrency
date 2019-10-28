package topics.ProducerConsumerPattern;

import java.util.concurrent.Semaphore;

public class ProducerConsumerDemo3 implements IConstants{
    public static void main(String[] args) {
        Semaphore mutex = new Semaphore(MUTEX);
        Semaphore empty = new Semaphore(EMPTY);
        Semaphore full = new Semaphore(FULL);

        Products theProducts = new Products();
        Producer3 producer = new Producer3(theProducts, mutex, empty, full);
        Consumer3 consumer = new Consumer3(theProducts, mutex, empty, full);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

interface IConstants {
    public final static int MAX_SIZE = 20;
    public final static int MUTEX = 2;
    public final static int EMPTY = MAX_SIZE;
    public final static int FULL = 0;
}

class Products implements IConstants{
    //fields
    private int[] products;
    private int index = 0;

    //constructor
    public Products() {
        this.products = new int[MAX_SIZE];
    }

    public synchronized void addItem(int number ){
        while (this.index == MAX_SIZE - 1){
            try{
                wait();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
        notifyAll();
        this.products[index++] = number;
    }

    public synchronized int remove(){
        while(this.index == 0){
            try
            {
                wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        notifyAll();
        return this.products[index --];
    }

    public int getSize(){
        return this.index;
    }
}

class Producer3 implements Runnable,IConstants{
    //fields
    private Products theResource = null;
    private Semaphore semaphoreMutex = null;
    private Semaphore semaphoreEmpty = null;
    private Semaphore semaphoreFull = null;

    //constructor
    public Producer3(Products theResource, Semaphore signalMutex,
                    Semaphore signalEmpty,
                    Semaphore signalFull)
    {
        this.theResource = theResource;
        this.semaphoreMutex = signalMutex;
        this.semaphoreFull = signalFull;
        this.semaphoreEmpty = signalEmpty;
    }

    public void run() {
        while (true) {
            try {
                int product = (int) (Math.random() * 100);
                this.semaphoreEmpty.acquire();
                this.semaphoreMutex.acquire();
                this.theResource.addItem(product);
                System.out.println("produced:" + product);
                this.semaphoreMutex.release();
                this.semaphoreFull.release();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class Consumer3 implements Runnable,IConstants{
    //fields
    private Products theResource = null;
    private Semaphore semaphoreMutex = null;
    private Semaphore semaphoreEmpty = null;
    private Semaphore semaphoreFull = null;

    //constructor
    public Consumer3(Products theResource,
                    Semaphore signalMutex,
                    Semaphore signalEmpty,
                    Semaphore signalFull
    )
    {
        this.theResource = theResource;
        this.semaphoreMutex = signalMutex;
        this.semaphoreFull = signalFull;
        this.semaphoreEmpty = signalEmpty;
    }

    public void run() {
        while (true) {
            try {
                this.semaphoreFull.acquire();
                this.semaphoreMutex.acquire();
                int product = this.theResource.remove();
                this.semaphoreMutex.release();
                this.semaphoreEmpty.release();

                System.out.println("constumed:" + product);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
