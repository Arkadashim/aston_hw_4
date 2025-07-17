public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: running with only param <deadlock|livelock|counter>");
            return;
        }

        String mode = args[0].toLowerCase();
        System.out.println("Running " + mode + "...");
        switch (mode) {
            case "deadlock":
                Deadlock.Run();
                break;
            case "livelock":
                Livelock.Run();
                break;
            case "counter":
                Counter.Run();
                break;
            default:
                System.out.println("Invalid argument. Use 'deadlock', 'livelock' or 'counter'.");
        }
    }
}