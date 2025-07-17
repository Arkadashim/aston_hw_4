import utils.SimpleLock;

public class Livelock extends SimpleLock {

    public static void Run() {
        var livelock = new Livelock();
        new Thread(livelock::operation1, "T1").start();
        new Thread(livelock::operation2, "T2").start();
    }

    public void operation1() {
        while (true) {
            lock1.lock();
            print("T1: lock1 acquired, trying to acquire lock2.");
            sleep(1);

            if (!lock2.tryLock()) {
                print("T1: cannot acquire lock2, releasing lock1.");
                lock1.unlock();
                continue;
            }

            print("T1: lock2 acquired");
            print("T1: executing first operation.");
            lock2.unlock();
            lock1.unlock();
            break;
        }
    }

    public void operation2() {
        while (true) {
            lock2.lock();
            print("T2: lock2 acquired, trying to acquire lock1.");
            sleep(1);

            if (!lock1.tryLock()) {
                print("T2: cannot acquire lock1, releasing lock2!");
                lock2.unlock();
                continue;
            }

            print("T2: lock1 acquired!");
            print("T2: executing second operation.");
            lock1.unlock();
            lock2.unlock();
            break;
        }
    }
}