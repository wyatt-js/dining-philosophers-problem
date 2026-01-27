public class Philosopher extends Thread{
    private final int id;
    private final Fork[] forks;

    public Philosopher(int id, Fork[] forks){
        this.id = id;
        this.forks = forks;
    }

    @Override
    public void run(){
        System.out.println("Philosopher " + id + " is thinking.");
        while (true) {
            if(!think()){
                System.out.println("Philosopher " + id + " STARVED!");
                break;
            }
        }
    }

    public boolean think(){
        // 16 seconds before starving & try to eat every 2 seconds
        for (int i = 0; i < 8; i++){
            try {
                Thread.sleep(2000);
                if (tryEat()) return true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return false;
    }

    public boolean tryEat(){
        if (forks[(id + 1) % 5].tryLock()){
            if (forks[id].tryLock()){
                try{
                    eat();
                    return true;
                } finally {
                    putDownForks();
                }
            } else {
                forks[(id + 1) % 5].unlock();
            }
        }
        return false;
    }

    public void eat(){
        System.out.println("Philosopher " + id + " is eating.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void putDownForks(){
        System.out.println("Philosopher " + id + " is done eating and now thinking.");
        forks[(id + 1) % 5].unlock();
        forks[id].unlock();
    }
}