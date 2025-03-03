package threads;

import exceptions.CantAffordRoomException;
import models.*;
import java.util.Date;
import java.util.List;

public class CustomThread extends Thread {
    private final int threadId;
    private final Hotel hotel;
    private final long numberOfNights;
    private final Guest guest;
    private final long roomNumber;


    public CustomThread(int id, Hotel hotel, long numberOfNights, Guest guest, long roomNumber) {
        this.threadId = id;
        this.hotel = hotel;
        this.numberOfNights = numberOfNights;
        this.guest = guest;
        this.roomNumber = roomNumber;
    }

    @Override
    public void run() {
        double start = threadId * (double)hotel.getRooms().size() / hotel.getNumberOfThreads();
        double end = Math.min((threadId + 1) * (double)hotel.getRooms().size() / hotel.getNumberOfThreads(), hotel.getRooms().size());
        for (double i = start; i < end; i++) {
            Room room = hotel.getRooms().get((int) i);
            if (room.getId() == roomNumber) {
                int bookingId = hotel.getBookings().size() + 1;
                Date checkindDate = new Date();
                Date checkoutDate = new Date(checkindDate.getTime() + numberOfNights * 24 * 60 * 60 * 1000);

                float totalCost = room.getPricePerNight() * numberOfNights;

                if (guest.canAffordRoom(totalCost)) {

                    if (guest instanceof PremiumGuest) {
                        totalCost = totalCost - totalCost * ((PremiumGuest) guest).getPromoPercentage() / 100;
                    }

                    // salvam cazarea in hotel
                    Booking booking = new Booking(bookingId, room, List.of(guest), checkoutDate, checkoutDate, totalCost);
                    hotel.getBookings().add(booking);

                    // salvam cazarea oaspetelui
                    guest.getBookings().add(booking);
                    // calculam noul buget al oaspetelui
                    guest.setBudget(guest.getBudget() - totalCost);

                } else {
                    throw new CantAffordRoomException("Oaspetele nu are destui bani pentru camera.");
                }
            }
        }

    }
}
