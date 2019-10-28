package executor;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;

public class ExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        //CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo(3);
        //cyclicBarrierDemo.test();

        //ExchangerDemo exchangerDemo = new ExchangerDemo();
        //exchangerDemo.test();

        //PhaserDemo phaserDemo = new PhaserDemo();
        //phaserDemo.test();

        //PhaserDemo2 phaserDemo2 = new PhaserDemo2(3);
        //phaserDemo2.test();

        //int testValue = test();
        //System.out.println(testValue);
    }

    public static int test() {
        try {
            return 1;
        }
        finally {
            System.out.println("finally called");
        }
    }
}





