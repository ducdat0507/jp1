import java.time.LocalDateTime;
import java.util.Set;

import Controller.BookingController;
import Controller.CustomerController;
import Controller.MainController;
import Controller.RoomController;
import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Global.Prompt;

public class Main {
    public static void main(String[] args) {
        MainController controller = new MainController();
        Prompt.init();

        RoomController rc = controller.rooms();
        rc.add(new Room("RS001", RoomType.SINGLE, 8));
        rc.add(new Room("RD001", RoomType.DOUBLE, 12));
        rc.add(new Room("RQ002", RoomType.QUEEN, 35));
        rc.add(new Room("RT001", RoomType.TRIPLE, 12.5));
        rc.add(new Room("RQ001", RoomType.QUAD, 20.5));

        CustomerController cc = controller.customers();
        cc.add(new Customer("001", "Mr.Linus Tovaldo", "84125325346457"));
        cc.add(new Customer("002", "Mr.Bill", "91124235346467"));
        cc.add(new Customer("003", "Mr.Turing", "911423534646"));

        BookingController bc = controller.bookings();
        bc.add(new Booking(cc.get("001"), rc.get("RS001"), LocalDateTime.parse("2023-03-15T09:30:15"), LocalDateTime.parse("2023-03-16T12:30:45")));
        bc.add(new Booking(cc.get("002"), rc.get("RS001"), LocalDateTime.parse("2023-06-09T19:30:25"), LocalDateTime.parse("2023-06-10T11:25:15")));
        bc.add(new Booking(cc.get("002"), rc.get("RD001"), LocalDateTime.parse("2023-03-11T10:10:05"), LocalDateTime.parse("2023-03-13T11:05:10")));
        bc.add(new Booking(cc.get("003"), rc.get("RT001"), LocalDateTime.parse("2023-11-11T11:11:15"), LocalDateTime.parse("2023-11-13T11:15:15")));
        bc.add(new Booking(cc.get("001"), rc.get("RT001"), LocalDateTime.parse("2023-10-25T09:20:25"), LocalDateTime.parse("2023-10-26T12:25:30")));
        bc.add(new Booking(cc.get("001"), rc.get("RQ001"), LocalDateTime.parse("2023-08-18T18:25:35"), LocalDateTime.parse("2023-08-19T11:35:20")));

        while (true) {
            String choice = (String)Prompt.prompt("Main menu", Prompt.Option.list(
                Prompt.Option.of("1", "Add booking"),
                Prompt.Option.of("2", "Search bookings"),
                Prompt.Option.of("3", "Show room type stats"),
                Prompt.Option.of("4", "Show most value room type in 2023"),
                Prompt.Option.of("0", "Exit")
            ));
            switch (choice) {
                case "1":
                    Booking booking = new Booking(
                        cc.get(Prompt.string("Enter customer ID: ", (x) -> {
                            if (cc.get(x) == null) return "Customer does not exist";
                            return null;
                        })),
                        rc.get(Prompt.string("Enter room ID: ", (x) -> {
                            if (rc.get(x) == null) return "Room does not exist";
                            return null;
                        })),
                        LocalDateTime.parse(Prompt.string("Enter check-in time: ", (x) -> {
                            try { LocalDateTime.parse(x); return null; }
                            catch (Exception e) { return "Invalid date time"; }
                        })),
                        LocalDateTime.parse(Prompt.string("Enter check-out time: ", (x) -> {
                            try { LocalDateTime.parse(x); return null; }
                            catch (Exception e) { return "Invalid date time"; }
                        }))
                    );
                    bc.add(booking);
                    System.out.println("Added booking with id " + booking.getId());
                    break;

                case "2":
                    Set<Booking> bookings = bc.search(Prompt.string("Enter search query (by customer name, phone, or room id): "));
                    long length = bookings.size();
                    if (length == 0) {
                        System.out.println("Found no results");
                    } else {
                        System.out.println("Found " + length + " results:");
                        int i = 0;
                        for (Booking b : bookings) System.out.println((++i) + ") " + b);
                    }
                    break;

                case "3":
                    var types = controller.getRoomTypeRevenue();
                    types.forEach((k, v) -> System.out.println(k + ": " + v));
                    break;

                case "4":
                    var type = controller.getMostValuableRoomTypeInYear(2023);
                    if (type == null) System.out.println("There are no bookings in 2023");
                    else System.out.println(type.getKey() + ": " + type.getValue());
                    break;

                case "0":
                    System.out.println("Goodbye");
                    return;
            }
        }
    }
}
