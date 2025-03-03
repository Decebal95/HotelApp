package models;

import java.util.List;


public class Guest {
    private String name;
    private String email;
    private String phoneNumber;
    private List<Booking> bookings;
    private float budget;

    public Guest() {
    }

    public Guest(String name, String email, String phoneNumber, List<Booking> bookings, float budget) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bookings = bookings;
        this.budget = budget;
    }

    public boolean canAffordRoom(float roomPrice) {
        return roomPrice <= this.budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }
}
