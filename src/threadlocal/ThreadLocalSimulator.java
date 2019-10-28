package threadlocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalSimulator<T> {
    // field
    private final Map<Thread, T> map = new HashMap<Thread, T>();

    public T initialValue() {
        return null;
    }

    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            map.put(key, t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            return map.containsKey(key) ? map.get(key) : initialValue();
        }
    }
}
