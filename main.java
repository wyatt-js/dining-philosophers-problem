public class Main {
    public static void main(String[] args) {
        System.out.println("Dining Philosophers Problem Simulation");

        Fork[] forks = new Fork[5];
        for (int i = 0; i < 5; i ++){
            forks[i] = new Fork(i);
        }

        Philosopher[] philosophers = new Philosopher[5];
        for (int i = 0; i < 5; i ++){
            philosophers[i] = new Philosopher(i, forks);
            philosophers[i].start();
        }
        System.out.println("Created 5 philosophers and forks.");
    }
}