package executor;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    // fields
    private CyclicBarrier cyclicBarrier;
    private int threadCount;

    // constructor
    public CyclicBarrierDemo(int count) {
        this.threadCount = count;
        this.cyclicBarrier = new CyclicBarrier(threadCount, new Runnable() {
            @Override
            public void run() {
                System.out.println("Game start");
            }
        });
    }

    public void test() {
        String name = "Player-";
        for (int i = 1; i <= threadCount; i++) {
            new CyclicBarrierPlayer(cyclicBarrier, name + i).start();
        }
    }
}

class CyclicBarrierPlayer extends Thread {
    // field
    private CyclicBarrier cyclicBarrier;

    // constructor
    public CyclicBarrierPlayer(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        setName(name);
    }

    public void run() {
        System.out.println(getName() + " is waiting other players ...");
        try {
            cyclicBarrier.await();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

