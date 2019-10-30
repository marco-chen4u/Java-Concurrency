package atomiclock;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    // field
    private Resource resource;

    // contructor
    public void AtomicDemo() {
        resource = new Resource();
    }

    public void test() {
        new AtomicOperateWorker(resource).start();
        new AtomicOperateWorker(resource).start();
        new AtomicOperateWorker(resource).start();
        new AtomicOperateWorker(resource).start();
    }
}

class Resource {
    // field
    private static AtomicInteger value = new AtomicInteger(0);

    public static void operate() {
        System.out.println(value.incrementAndGet());
    }
}

class AtomicOperateWorker extends Thread {
    // filed
    private Resource resource;

    // constructor
    public AtomicOperateWorker(Resource resource) {
        this.resource = resource;
    }

    public void run() {
        while(true) {
            try {
                sleep(1000);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            resource.operate();
        }
    }
}
