package models;

import threads.CustomThread;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private Long id;
    private String name;
    private String location;
    private List<Room> rooms;
    private List<Booking> bookings;
    private int numberOfThreads = 3;


    public Hotel() {
    }

    public Hotel(Long id, String name, String location, List<Room> rooms, List<Booking> bookings) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rooms = rooms;
        this.bookings = bookings;
    }

    // procesarea pararela
    public void reserveRoom(long roomNumber, Guest guest, long numberOfNights) {
        for (int i = 0; i < numberOfThreads; i++) {
            CustomThread t = new CustomThread(i,this, numberOfNights, guest, roomNumber);
            t.start();
        }
    }

    public List<Room> getFreeRooms() {
        List<Room> freeRooms = new ArrayList<>();

        for (Room room : rooms) {
            if (room.isAvailable()) {
                freeRooms.add(room);
            }
        }

        return freeRooms;
    }


    public Long getHotelId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return name;
    }

    public void setHotelName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

}
