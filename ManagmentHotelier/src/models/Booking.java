package models;

import java.util.Date;
import java.util.List;

public class Booking {
    private int bookingId;
    private Room room;
    private List<Guest> guests;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalCost;


    public Booking() {
    }

    public Booking(int bookingId, Room room, List<Guest> guests, Date checkInDate, Date checkOutDate, double totalCost) {
        this.bookingId = bookingId;
        this.room = room;
        this.guests = guests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
    }


    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuest(List<Guest> guests) {
        this.guests = guests;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
