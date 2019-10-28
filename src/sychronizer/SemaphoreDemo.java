package sychronizer;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    // field
    private Semaphore semaphore;

    // constructor
    public SemaphoreDemo() {
        this.semaphore = new Semaphore(2);// only 2 limited 'token-license' resource to share
    }

    public void test() {
        new Person(semaphore, "A").start();
        new Person(semaphore, "B").start();
        new Person(semaphore, "C").start();
    }
}

class Person extends Thread {
    // fields
    private Semaphore semaphore;

    // constructor
    public Person(Semaphore semaphore, String name) {
        setName(name);
        this.semaphore = semaphore;
    }

    public void run() {
        System.out.println(getName() + " is waiting ...");
        try {
            semaphore.acquire();
            System.out.println(getName() + " is servicing ...");
            sleep(1000);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(getName() + " is done.");
        semaphore.release();
    }
}
