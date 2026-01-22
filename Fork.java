public class Fork {
    private int id;
    private boolean isAvailable = true;

    public Fork(int id){
        this.id = id;
    }

    public void pickup(){
        if (!isAvailable) {
            System.out.println("Fork " + id + " is already in use!");
            return;
        }
        isAvailable = false;
        System.out.println("Fork " + id + " has been picked up.");
    }

    public void putdown(){
        if (isAvailable) {
            System.out.println("Fork " + id + " is already on the table!");
            return;
        }
        isAvailable = true;
        System.out.println("Fork " + id + " has been put down.");
    }
}
