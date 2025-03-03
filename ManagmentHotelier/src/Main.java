import exceptions.CantAffordRoomException;
import exceptions.GuestEmailNotFoundException;
import exceptions.InvalidGuestTypeException;
import models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<String[]> readItemsFromFile(String inputFile) {
        List<String[]> items = new ArrayList<>();
        try {
            // deschidem fisierul
            // https://www.w3schools.com/java/java_files_read.asp
            File file = new File(inputFile);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                // impart linia dupa spatii si fac un vector de elemnte
                String[] currentItems = line.split(" ");

                items.add(currentItems);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Eroare la citirea din fisier guests.in!");
        }
        return items;
    }

    public static List<Hotel> readHotelsFromFile() {
        List<Hotel> hotels = new ArrayList<>();
        for (String[] item : readItemsFromFile("input/hotels.in")) {
            Hotel hotel = new Hotel(Long.parseLong(item[0]), item[1], item[2], new ArrayList<>(), new ArrayList<>() );
            hotels.add(hotel);
        }
        return hotels;
    }

    public static List<Room> readRoomsFromFile(List<Hotel> hotels) {
        List<Room> rooms = new ArrayList<>();

        for (String[] item : readItemsFromFile("input/rooms.in")) {
            Long hotelId = Long.parseLong(item[5]);

            Room room = new Room(Long.parseLong(item[0]), item[1], item[2], Float.parseFloat(item[3]), Boolean.parseBoolean(item[4]));
            rooms.add(room);

            // legam camera la un hotel
            for (Hotel hotel: hotels) {
                if (hotel.getHotelId().equals(hotelId)) {
                    hotel.getRooms().add(room);
                }
            }
        }

        return rooms;
    }

    public static List<Guest> readGuestsFromFile() {
        List<Guest> guests = new ArrayList<>();

        for (String[] item : readItemsFromFile("input/guests.in")) {
            String guestType = item[5];

            try  {
                if (guestType.equals("normal")) {
                    Guest guest = new Guest(item[0] + " " + item[1], item[2], item[3], new ArrayList<>(), Float.parseFloat(item[4]));
                    guests.add(guest);
                } else if (guestType.equals("premium")) {
                    PremiumGuest premiumGuest = new PremiumGuest(Integer.parseInt(item[6]), item[0] + " " + item[1], item[2], item[3], new ArrayList<>(), Float.parseFloat(item[4]));
                    guests.add(premiumGuest);
                } else {
                    throw new InvalidGuestTypeException(String.format("Tip de oaspete invalid: %s", guestType));
                }
            } catch (InvalidGuestTypeException e) {
                System.out.println(e.getMessage());
            }

        }

        return guests;
    }

    public static void printHotels(List<Hotel> hotels) {
        for (Hotel hotel: hotels) {
            System.out.println("Hotel: " + hotel.getHotelName() + " at location: " + hotel.getLocation() + " with id " + hotel.getHotelName());

            System.out.println("Rooms: ");
            printRooms(hotel.getRooms());
        }
    }

    public static void printGuests(List<Guest> guests) {
        for (Guest guest: guests) {
            System.out.println("Guest: " + guest.getName() + " " + guest.getEmail() + " " + guest.getPhoneNumber());
        }
    }

    public static void printRooms(List<Room> rooms) {
        for (Room room: rooms) {
            System.out.println("Room " + room.getId() + " " + " number: " + room.getRoomNumber() + " type: " + room.getRoomType() + " price: " + room.getPricePerNight() + " isAvailable: " + room.isAvailable());
        }
    }

    public static Guest searchGuest(String email, List<Guest> guests) {
        for (Guest currentGuest: guests) {
            if (currentGuest.getEmail().equals(email)) {
                return currentGuest;
            }
        }

        throw new GuestEmailNotFoundException("Email ul nu a fost gasit in lista de oaspeti");
    }

    // interactiunea cu utilizatorul
    public static Guest selectCurentGuest(List<Guest> guests) {
        Scanner scanner = new Scanner(System.in);

        Guest currentGuest = null;

        while (currentGuest == null) {
            System.out.println("Introduceti email:");
            String email = scanner.nextLine();

            try {
                currentGuest = searchGuest(email, guests);
            } catch (GuestEmailNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Utilizatorul curent este: " + currentGuest.getName());
        System.out.println("Utilizatorul curent are bugetul: " + currentGuest.getBudget());
        return currentGuest;
    }

    public static void showAvailableRoomsAndSelectRoom(List<Hotel> hotels, Guest currentGuest) {
        Scanner scanner = new Scanner(System.in);

        for (Hotel hotel: hotels) {
            System.out.println("Camere disponibile pentru hotelul " + hotel.getHotelName());
            printRooms(hotel.getFreeRooms());

            System.out.println("Doriti sa faceti o rezervare la acest hotel?");
            String response = scanner.nextLine();
            if (response.equals("da") || response.equals("DA") || response.equals("Da")) {
                System.out.println("Va rugam introduceti numarul camerei pe care o doriti");


                Long roomNumber = Long.parseLong(scanner.nextLine());

                System.out.println("Cate nopti doriti sa va cazati?");
                Integer numberOfNights = Integer.parseInt(scanner.nextLine());

                try {
                     hotel.reserveRoom(roomNumber, currentGuest, numberOfNights);


                        System.out.println("Camera a fost rezervata cu succes!");

                        System.out.println("Doriti salvarea chitaneti intr-un fisier?");

                        response = scanner.nextLine();
                        if (response.equals("da") || response.equals("DA") || response.equals("Da")) {
                          //  writeBookingInFile("chitanta.txt", booking);
                        }


                } catch (CantAffordRoomException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Hotel> hotels = readHotelsFromFile();
        List<Room> rooms = readRoomsFromFile(hotels);
        List<Guest> guests = readGuestsFromFile();

        printHotels(hotels);
        printGuests(guests);

        Guest currentGuest = selectCurentGuest(guests);

        showAvailableRoomsAndSelectRoom(hotels, currentGuest);
    }
}