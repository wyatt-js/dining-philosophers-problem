public class Fork {
    private int id;

    public Fork(int id){
        this.id = id;
    }

    public synchronized void pickup(){
        System.out.println("Fork " + id + " has been picked up.");
    }

    public void putdown(){
        System.out.println("Fork " + id + " has been put down.");
    }
}
