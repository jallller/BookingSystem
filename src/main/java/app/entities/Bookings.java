package app.entities;

import java.sql.Date;

public class Bookings {
    private int bookings_id;
    private Date booking_date;
    private int days;
    private String comment;
    private boolean booking_status;
    private int user_id;
    private int equipment_id;

    public Bookings(int bookings_id, Date booking_date, int days, String comment, boolean booking_status, int user_id, int equipment_id) {
        this.bookings_id = bookings_id;
        this.booking_date = booking_date;
        this.days = days;
        this.comment = comment;
        this.booking_status = booking_status;
        this.user_id = user_id;
        this.equipment_id = equipment_id;
    }
}
