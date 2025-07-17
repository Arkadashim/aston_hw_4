import utils.SimpleLock;

public class Counter extends SimpleLock {
    private boolean isFirstTurn = true;
    public static void Run() {
        var counterlock = new Counter();
        new Thread(counterlock::operation1, "T1").start();
        new Thread(counterlock::operation2, "T2").start();
    }

    @Override
    protected void operation1() {
        while (true) {
            if (!isFirstTurn) {
                sleep(0);
                continue;
            }

            print("1");
            sleep(1);

            isFirstTurn = false;
        }
    }

    @Override
    protected void operation2() {
        while (true) {
            if (isFirstTurn) {
                sleep(0);
                continue;
            }

            print("2");
            sleep(2);

            isFirstTurn = true;
        }
    }
}
