package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private  static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private int roomNumber;
    private Date checkin;
    private Date checkout;

    public Reservation(int roomNumber, Date checkin, Date checkout) {

        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;

    }

    private void updateDates(Date checkin, Date checkout) /*throws ReservationExceptions*/ {

        this.checkin = checkin;
        this.checkout = checkout;

    }

    public long duration() {
        long diff = checkout.getTime() - checkin.getTime();
        TimeUnit.DAYS.convert(diff, TimeUnit.MICROSECONDS);
        return diff;
    }

    @Override
    public String toString() {
        return "Reservation: Room" + this.roomNumber
                + " Checkin: " + sdf.format(checkin)
                + " Checkout: " + sdf.format(checkout)
                + " Duration: " + TimeUnit.DAYS.convert(duration(), TimeUnit.MICROSECONDS);
    }

}
