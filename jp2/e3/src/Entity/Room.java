package Entity;

public class Room {
    private String id;
    private RoomType roomType;
    private double pricePerHour;
    
    public Room() {
    }
    public Room(String id, RoomType roomType, double pricePerHour) {
        this.id = id;
        this.roomType = roomType;
        this.pricePerHour = pricePerHour;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        if (this.id != null) throw new RuntimeException("Can not rewrite id");
        this.id = id;
    }
    public RoomType getRoomType() {
        return roomType;
    }
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
    public double getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @Override
    public String toString() {
        return "Room {id=" + id + ", roomType=" + roomType + ", pricePerHour=" + pricePerHour + "}";
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
        Room other = (Room) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
