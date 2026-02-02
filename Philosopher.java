public class Philosopher extends Thread {
    private final int id;
    private final Table table;

    public Philosopher(int id, Table table) {
        this.id = id;
        this.table = table;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            think();

            try {
                table.obtainForks(id);
                try {
                    eat();
                } finally {
                    table.freeForks(id);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void think() {
        System.out.println("Philosopher " + id + " is thinking.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void eat() {
        System.out.println("Philosopher " + id + " is eating.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Philosopher " + id + " is done eating.");
    }
}