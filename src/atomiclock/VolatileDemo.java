package atomiclock;

public class VolatileDemo {
    // fields
    private VolatileData volatileData;

    // constructor
    public VolatileDemo() {
        this.volatileData = new VolatileData();
    }

    public void test() {
        new VolatileWorker(volatileData, "writer-thread1").start();
        new VolatileWorker(volatileData, "writer-thread2").start();
        new VolatileReader(volatileData, "reader-thread").start();
    }
}

class VolatileData {
    // field
    private volatile int value;

    // constructor
    public VolatileData() {
        this.value = 0;
    }

    public void increase() {
        value += 1;
    }

    public int getValue() {
        return value;
    }
}

class VolatileWorker extends Thread {
    // filed
    private VolatileData volatileData;

    // constructor
    public VolatileWorker(VolatileData volatileData, String name) {
        setName(name);
        this.volatileData = volatileData;
    }

    public void run() {
        while (true) {
            try {
                sleep(1000);
                volatileData.increase();
                System.out.println(getName() + " setValue() = " + volatileData.getValue());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

class VolatileReader extends Thread {
    // filed
    private VolatileData volatileData;

    // constructor
    public VolatileReader(VolatileData volatileData, String name) {
        setName(name);
        this.volatileData = volatileData;
    }

    public void run() {
        while(true) {
            try {
                sleep(1000);
                System.out.println(getName() + " getValue() = " + volatileData.getValue());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
