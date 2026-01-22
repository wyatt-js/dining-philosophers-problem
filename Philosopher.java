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

    public void pickupLeftFork(){
        System.out.println("Philosopher " + id + " picked up left fork.");
    }

    public void pickupRightFork(){
        System.out.println("Philosopher " + id + " picked up right fork.");
    }
}