package applications;

import entities.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {

    private  static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Room Number: ");
        String roomNumber = sc.nextLine();
        System.out.println("Check-in-date (dd/MM/yyyy): ");
        Date checkin = format.parse(sc.nextLine());
        System.out.println("Check-out-date: (dd/MM/yyyy): ");
        Date checkout = format.parse(sc.nextLine());

        if(checkin.getTime() > checkout.getTime()){
            System.out.println("Check-in-date (DD/MM/yyyy): ");
            return;
        }

        Reservation reservation = new Reservation(Intenger.parseInt(roomNumber), checkin, checkout);
        System.out.println(reservation);

    }

}
