import java.util.concurrent.locks.ReentrantLock;

public class Fork{
    private final int id;
    private final ReentrantLock lock;

    public Fork(int id, boolean fair){
        this.id = id;
        this.lock = new ReentrantLock(fair);
    }

    // Functions to help to interact with lock
    public int getId(){return this.id;}
    public boolean tryToPickUp(){return this.lock.tryLock();}
    public void pickUp(){this.lock.lock();}
    public void putDown(){this.lock.unlock();}
    @Override
    public String toString(){return "Fork( " + this.id + " )";}

}
