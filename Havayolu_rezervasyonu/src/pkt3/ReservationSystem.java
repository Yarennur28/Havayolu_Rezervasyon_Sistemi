package pkt3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

class ReservationSystem {
    private List<String> seats;
    private SimpleDateFormat sdf;

    public ReservationSystem() {
        seats = new ArrayList<>(Arrays.asList(new String[5]));
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Istanbul")); // Turkey's time zone
    }

    private String getCurrentTime() {
        return sdf.format(new Date());
    }

    public void makeReservation(int seatIndex) {
        if (seats.get(seatIndex) == null) {
            seats.set(seatIndex, "1");
            System.out.println("Time: " + getCurrentTime() + "\nKoltuk " + seatIndex + " rezerve edildi.");
        } else {
            System.out.println("Time: " + getCurrentTime() + "\nKoltuk " + seatIndex + " zaten rezerve edilmiş.");
        }
    }

    public void cancelReservation(int seatIndex) {
        if (seats.get(seatIndex) != null) {
            seats.set(seatIndex, null);
            System.out.println("Time: " + getCurrentTime() + "\nKoltuk " + seatIndex + " rezervasyonu iptal edildi.");
        } else {
            System.out.println("Time: " + getCurrentTime() + "\nKoltuk " + seatIndex + " rezerve değil.");
        }
    }

    public void queryReservation() {
        System.out.println("Time: " + getCurrentTime() + "\nMevcut Rezervasyonlar:");
        for (int i = 0; i < seats.size(); i++) {
            System.out.println("Koltuk " + i + ": " + (seats.get(i) == null ? "Mevcut" : "Rezerve"));
        }
        System.out.println("---------------------------------------------");
    }
}

class Writer implements Runnable {
    private final ReservationSystem system;
    private final int seatIndex;
    private final boolean isReservation;

    public Writer(ReservationSystem system, int seatIndex, boolean isReservation) {
        this.system = system;
        this.seatIndex = seatIndex;
        this.isReservation = isReservation;
    }

    @Override
    public void run() {
        if (isReservation) {
            system.makeReservation(seatIndex);
        } else {
            system.cancelReservation(seatIndex);
        }
    }
}

class Reader implements Runnable {
    private final ReservationSystem system;

    public Reader(ReservationSystem system) {
        this.system = system;
    }

    @Override
    public void run() {
        system.queryReservation();
    }
}


