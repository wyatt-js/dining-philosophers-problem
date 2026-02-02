public class main {
    public static void main(String[] args) {
        int n = 5;
        Table table = new Table(n);

        Philosopher[] ps = new Philosopher[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new Philosopher(i, table);
            ps[i].start();
        }
    }
}