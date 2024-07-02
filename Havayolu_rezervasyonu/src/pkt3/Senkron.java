package pkt3;

public class Senkron {
    public static void main(String[] args) {
        ReservationSystemSenkron system = new ReservationSystemSenkron();

        Thread writer1 = new Thread(new WriterThread(system, 0), "Writer1");
        Thread writer2 = new Thread(new WriterThread(system, 0), "Writer2");
        Thread writer3 = new Thread(new WriterThread(system, 1), "Writer3");

        Thread reader1 = new Thread(new ReaderThread(system), "Reader1");
        Thread reader2 = new Thread(new ReaderThread(system), "Reader2");
        Thread reader3 = new Thread(new ReaderThread(system), "Reader3");

        writer1.start();
        reader1.start();
        reader2.start();
        writer2.start();
        reader3.start();
        writer3.start();
    }
}
