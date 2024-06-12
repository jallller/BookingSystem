package app.entities;

public class Room {
    private int roomnumber;
    private String description;

    @Override
    public String toString() {
        return "Room{" +
                "roomnumber=" + roomnumber +
                ", description='" + description + '\'' +
                '}';
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room(int roomnumber, String description) {
        this.roomnumber = roomnumber;
        this.description = description;
    }
}
