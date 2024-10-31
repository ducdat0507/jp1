package Entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Booking {
    private String id;
    private Customer customer;
    private Room room;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public Booking() {
    }
    public Booking(Customer customer, Room room, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        this.customer = customer;
        this.room = room;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }
    public Booking(String id, Customer customer, Room room, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        this.id = id;
        this.customer = customer;
        this.room = room;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        if (this.id != null) throw new RuntimeException("Can not rewrite id");
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }
    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }
    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }
    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public double getPrice() {
        return room.getPricePerHour() * ChronoUnit.HOURS.between(checkInTime, checkOutTime);
    }

    @Override
    public String toString() {
        return "Booking {id=" + id + ", customer=" + customer + ", room=" + room + ", checkInTime=" + checkInTime
                + ", checkOutTime=" + checkOutTime + "}";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Booking other = (Booking) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
