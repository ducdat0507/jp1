package Controller;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Entity.Booking;
import Entity.RoomType;

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
