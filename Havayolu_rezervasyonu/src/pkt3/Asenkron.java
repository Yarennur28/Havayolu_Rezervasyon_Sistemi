package pkt3;

public class Asenkron {
    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();

        Thread writer1 = new Thread(new Writer(system, 0, true));
        Thread writer2 = new Thread(new Writer(system, 2, true));
        Thread writer3 = new Thread(new Writer(system, 2, true));
        Thread writer4 = new Thread(new Writer(system, 2, true));

        Thread reader1 = new Thread(new Reader(system));
        Thread reader2 = new Thread(new Reader(system));

        writer1.start();
        writer2.start();
        writer3.start();
        writer4.start();
        reader1.start();
        reader2.start();
    }
}