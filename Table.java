import java.util.*;
import java.util.concurrent.locks.*;
public class Table {
    private final int n;
    private final Fork[] forks;
    private final boolean[] forksAvailable;
    private final Queue<Integer> line;
    private final ReentrantLock waiterLock;
    private final Condition changed;

    public Table(int n){
        this.n = n;
        this.forks = new Fork[n];
        for(int i = 0; i < n; i++){
            forks[i] = new Fork(i, true);
        }

        this.forksAvailable = new boolean[n];
        for(int i = 0; i < n; i++){
            this.forksAvailable[i] = true;
        }

        this.line = new ArrayDeque<>();

        this.waiterLock = new ReentrantLock(true);
        this.changed = this.waiterLock.newCondition();
    }


    //Support functions for easier debugging and logic
    private int leftForkIndex(int philospherIndex){ return (philospherIndex % this.n);}
    private int rightForkIndex(int philospherIndex){
        int rightForkNumber = (philospherIndex % this.n) + 1;
        if(rightForkNumber == this.n){
            return 0;
        } else{
            return rightForkNumber;
        }
    }

    private boolean isHeadOfLine(int philospherIndex){
        if(line.isEmpty()){
            return false;
        } else{
            return line.peek() == philospherIndex;
        }
    }
    private boolean forksFree(int philospherIndex){
        int left = this.leftForkIndex(philospherIndex);
        int right = this.rightForkIndex(philospherIndex);
        return forksAvailable[left] && forksAvailable[right];
    }

    //Internal State updaters
    private void pickUpForks(int philospherIndex){
        int left = this.leftForkIndex(philospherIndex);
        int right = this.rightForkIndex(philospherIndex);
        if(left < right){
            this.forks[left].pickUp();
            this.forks[right].pickUp();
        } else{
            this.forks[right].pickUp();
            this.forks[left].pickUp();
        }
        
    }

    private void putDownForks(int philospherIndex){
        int left = this.leftForkIndex(philospherIndex);
        int right = this.rightForkIndex(philospherIndex);
        if(left < right){
            this.forks[left].putDown();
            this.forks[right].putDown();
        } else{
            this.forks[right].putDown();
            this.forks[left].putDown();
        }
    }

    private void addQueue(int philospherIndex){
        if(!this.line.contains(philospherIndex)){
            this.line.add(philospherIndex);
        }
    }

    //Main functions for which we created table class in the begining. Philosophers will use them to obtain and release forks. 
    public void obtainForks(int philosopherIndex) throws InterruptedException {
        int left = leftForkIndex(philosopherIndex);
        int right = rightForkIndex(philosopherIndex);
        waiterLock.lock();
        try {
            addQueue(philosopherIndex);

            while (!isHeadOfLine(philosopherIndex) || !forksFree(philosopherIndex)) {
                changed.await();
            }
            forksAvailable[left] = false;
            forksAvailable[right] = false;
        } finally {
            waiterLock.unlock();
        }
        this.pickUpForks(philosopherIndex);
    }

    public void freeForks(int philosopherIndex) throws InterruptedException{
        this.putDownForks(philosopherIndex);
        int left = leftForkIndex(philosopherIndex);
        int right = rightForkIndex(philosopherIndex);
        this.waiterLock.lock();
        try {
            forksAvailable[left] = true;
            forksAvailable[right] = true;
            this.line.poll();
            this.changed.signalAll();
        } finally {
            this.waiterLock.unlock();
        }

    }

}
