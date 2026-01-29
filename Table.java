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
        this.forks[left].pickUp();
        this.forks[right].pickUp();
    }

    private void putDownForks(int philospherIndex){
        int left = this.leftForkIndex(philospherIndex);
        int right = this.rightForkIndex(philospherIndex);
        this.forks[left].putDown();
        this.forks[right].putDown();
    }

    
}
