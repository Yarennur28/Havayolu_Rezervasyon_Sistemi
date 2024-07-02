package pkt3;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReservationSystemSenkron {
    private List<String> seats = new ArrayList<>(Arrays.asList(new String[5]));
    private SimpleDateFormat sdf;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public ReservationSystemSenkron() {
        sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Istanbul")); // Turkey's time zone
    }

    private String getCurrentTime() {
        return sdf.format(new Date());
    }

    public void makeReservation(int seatIndex) {
        lock.writeLock().lock();
        try {
            System.out.println("Time: " + getCurrentTime() + "\n" + Thread.currentThread().getName() + " yer ayırtmaya çalışıyor " + (seatIndex + 1));
            if (seats.get(seatIndex) == null) {
                seats.set(seatIndex, "1");
                System.out.println("Time: " + getCurrentTime() + "\n" + Thread.currentThread().getName() + " rezerve edilen koltuk numarası " + (seatIndex + 1) + " başarılı.");
            } else {
                System.out.println("Time: " + getCurrentTime() + "\n" + Thread.currentThread().getName() + " koltuk numarası rezerve edilemedi " + (seatIndex + 1) + " zaten rezerve edilmiş.");
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void queryReservation() {
        lock.readLock().lock();
        try {
            System.out.println("Time: " + getCurrentTime() + "\n" + Thread.currentThread().getName() + " mevcut koltukları arar. Koltukların durumu şöyle: ");
            System.out.println("Koltuk No 1 : " + (seats.get(0) == null ? "0" : "1") + " Koltuk No 2 : " + (seats.get(1) == null ? "0" : "1") + 
                               " Koltuk No 3 : " + (seats.get(2) == null ? "0" : "1") + " Koltuk No 4 : " + (seats.get(3) == null ? "0" : "1") + 
                               " Koltuk No 5 : " + (seats.get(4) == null ? "0" : "1"));
            System.out.println("--------------------------------------------------------------------");
        } finally {
            lock.readLock().unlock();
        }
    }
}






