public class Philosopher{
    private int id;

    public Philosopher(int id){
        this.id = id;
    }

    public void think(){
        System.out.println("Philosopher " + id + " is thinking.");
    }

    public void eat(){
        System.out.println("Philosopher " + id + " is eating.");
    }

    public void pickupLeftFork(Fork fork){
        fork.pickup();
        System.out.println("Philosopher " + id + " picked up left fork.");
    }

    public void tryPickupRightFork(Fork fork){
        fork.pickup();
        System.out.println("Philosopher " + id + " picked up right fork.");
    }

    public void putDownLeftFork(Fork fork){
        fork.putdown();
        System.out.println("Philosopher " + id + " put down left fork.");
    }

    public void putDownRightFork(Fork fork){
        fork.putdown();
        System.out.println("Philosopher " + id + " put down right fork.");
    }
}