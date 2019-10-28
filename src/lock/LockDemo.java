package lock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class LockDemo {
    public static void main(String[] args) {
        //ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        //readWriteLockDemo.test();

        DeadLockBasicsDemo deadLockBasicsDemo = new DeadLockBasicsDemo();
        deadLockBasicsDemo.test();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.findDeadlockedThreads();
        boolean isDeadLock = threadIds != null && threadIds.length > 0;
        System.out.println("Deadlocks found: " + isDeadLock);
    }
}
