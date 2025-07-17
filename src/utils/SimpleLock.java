package utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class SimpleLock {
    public Lock lock1 = new ReentrantLock(true);
    public Lock lock2 = new ReentrantLock(true);

    public void print(String message) {
        System.out.println(message);
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            print("Interrupted during sleep: " + e.getMessage());
        }
    }

    protected abstract void operation1();
    protected abstract void operation2();
}
