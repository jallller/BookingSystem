package app.entities;

public class Equipment {
    private int equipment_id;
    private String equipment_name;
    private String description;
    private int room_number;

    @Override
    public String toString() {
        return "Equipment{" +
                "equipment_id=" + equipment_id +
                ", equipment_name='" + equipment_name + '\'' +
                ", description='" + description + '\'' +
                ", room_number=" + room_number +
                '}';
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public Equipment() {
    }

    public Equipment(int equipment_id, String equipment_name, String description, int room_number) {
        this.equipment_id = equipment_id;
        this.equipment_name = equipment_name;
        this.description = description;
        this.room_number = room_number;
    }
}
