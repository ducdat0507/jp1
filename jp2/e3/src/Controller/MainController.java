package Controller;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Entity.Customer;
import Entity.Room;
import Entity.Booking;
import Entity.RoomType;
import Global.Global;
import Global.Prompt;

public class MainController {
    private BookingController bookings;
    private CustomerController customers;
    private RoomController rooms;

    public MainController() {
        bookings = new BookingController();
        customers = new CustomerController();
        rooms = new RoomController();
    }

    public BookingController bookings() {
        return bookings;
    }
    public CustomerController customers() {
        return customers;
    }
    public RoomController rooms() {
        return rooms;
    }

    public Booking promptBooking() {
        Booking booking = new Booking();
        int step = 1;
        while (step <= 4) {
            switch (step) {
                case 1:
                    booking.setCustomer(Prompt.search("Search customer:", customers, (q) -> Global.fuzzyMatch(q, Customer::getName)));
                    step++;
                    break;
            
                case 2:
                    booking.setRoom(Prompt.search("Search room:", rooms, (q) -> ((x) -> x.getRoomType().getLabel().equalsIgnoreCase(q))));
                    step++;
                    break;
            
                case 3:
                    booking.setCheckInTime(LocalDateTime.parse(Prompt.string("Enter check-in time: ", (x) -> {
                        try { LocalDateTime.parse(x); return null; }
                        catch (Exception e) { return "Invalid date time"; }
                    })));
                    step++;
                    break;
    
                case 4:
                    booking.setCheckOutTime(LocalDateTime.parse(Prompt.string("Enter check-out time: ", (x) -> {
                        try { LocalDateTime.parse(x); return null; }
                        catch (Exception e) { return "Invalid date time"; }
                    })));
                    Boolean isOverlap = bookings.stream().anyMatch(x -> x.getRoom().equals(booking.getRoom()) && 
                        (x.getCheckInTime().isBefore(booking.getCheckOutTime()) || booking.getCheckInTime().isBefore(x.getCheckOutTime())));
                    if (Boolean.TRUE.equals(isOverlap)) {
                        System.out.println("Room is occupied at given time");
                        step = 3;
                    } else {
                        step++;
                    }
                    break;
    
                default:
                    break;
            }
        }
        bookings.add(booking);
        System.out.println("Added booking with id " + booking.getId());
        return booking;
    }
    
    public Map<RoomType, Double> getRoomTypeRevenue() {
        return bookings.stream().collect(Collectors.groupingBy(
            x -> x.getRoom().getRoomType(), 
            Collectors.summingDouble(Booking::getPrice)
        ));
    }
    
    public Entry<RoomType, Double> getMostValuableRoomTypeInYear(int year) {
        return bookings.stream().filter(
            x -> x.getCheckInTime().getYear() == year
        ).collect(Collectors.groupingBy(
            x -> x.getRoom().getRoomType(), 
            Collectors.summingDouble(Booking::getPrice)
        )).entrySet().stream().max(
            Entry.comparingByValue()
        ).orElse(null);
    }
    
}
