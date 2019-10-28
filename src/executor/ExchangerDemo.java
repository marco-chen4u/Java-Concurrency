package executor;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    // field
    private Exchanger<String> exchanger;

    // constructor
    public void ExchangerDemo() {

    }

    public void test() {
        this.exchanger = new Exchanger<String>();
        new A(exchanger).start();
        new B(exchanger).start();
    }
}

class A extends Thread {

    // fields
    private Exchanger<String> exchanger;
    // constructor

    public A(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }
    public void run() {
        String msg = "";
        try {
            msg = exchanger.exchange("Hello there?");
            System.out.println(getName() + "A gets response :" + msg);

            msg = exchanger.exchange("A");
            System.out.println(getName() + "A gets response :" + msg);

            msg = exchanger.exchange("B");
            System.out.println(getName() + "A gets response :" + msg);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

class B extends Thread {
    // fields
    private Exchanger<String> exchanger;

    // constructor
    public B(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    public void run() {
        String msg = "";
        try {
            msg = exchanger.exchange("Hi friend!");
            System.out.println(getName() + "-B gets response :" + msg);

            msg = exchanger.exchange("1");
            System.out.println(getName() + "-B gets response :" + msg);

            msg = exchanger.exchange("2");
            System.out.println(getName() + "-B gets response :" + msg);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
