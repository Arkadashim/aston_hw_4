import utils.SimpleLock;

public class Deadlock extends SimpleLock {

    public static void Run() {
        var deadlock = new Deadlock();
        new Thread(deadlock::operation1, "T1").start();
        new Thread(deadlock::operation2, "T2").start();
    }

    public void operation1() {
        lock1.lock();
        print("lock1 acquired, waiting to acquire lock2.");
        sleep(50);

        lock2.lock();
        print("lock2 acquired");

        print("executing first operation.");

        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() {
        lock2.lock();
        print("lock2 acquired, waiting to acquire lock1.");
        sleep(50);

        lock1.lock();
        print("lock1 acquired");

        print("executing second operation.");

        lock1.unlock();
        lock2.unlock();
    }
}
