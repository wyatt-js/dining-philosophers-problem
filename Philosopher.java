public class Philosopher extends Thread{
    private final int id;
    private final Table table;

    public Philosopher(int id, Table table){
        this.id = id;
        this.table = table;
    }

    @Override
    public void run(){
        System.out.println("Philosopher " + id + " is alive.");
    }

    public void think(){
        System.out.println("Philosopher " + id + " is thinking.");
        int i = 0;
        // Try to eat 10 times before starving (20 seconds before starving)
        while (i < 10) {
            try {
                Thread.sleep(2000);
                tryEat();
            } catch (InterruptedException e) {
                System.out.println("Philosopher " + id + " was interrupted while thinking.");
                Thread.currentThread().interrupt();
            }
            i++;
        }
        System.out.println("Philosopher " + id + " STARVED!");
    }

    public void tryEat(){
        if (table.requestToEat(id)){
            System.out.println("Philosopher " + id + " got permission to eat.");
            eat();
        }
    }

    public void eat(){
        System.out.println("Philosopher " + id + " is eating.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Philosopher " + id + " was interrupted while eating.");
            Thread.currentThread().interrupt();
        }
        think();
    }
}