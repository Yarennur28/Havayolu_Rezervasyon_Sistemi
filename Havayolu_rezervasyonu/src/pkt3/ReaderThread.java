package pkt3;

public class ReaderThread implements Runnable {
    private ReservationSystemSenkron system;

    public ReaderThread(ReservationSystemSenkron system) {
        this.system = system;
    }

    @Override
    public void run() {
        system.queryReservation();
    }
}
