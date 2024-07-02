package pkt3;

public class WriterThread implements Runnable {
    private ReservationSystemSenkron system;
    private int seatIndex;

    public WriterThread(ReservationSystemSenkron system, int seatIndex) {
        this.system = system;
        this.seatIndex = seatIndex;
    }

    @Override
    public void run() {
        system.makeReservation(seatIndex);
    }
}
