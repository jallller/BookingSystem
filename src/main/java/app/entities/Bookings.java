package app.entities;

import java.sql.Date;

public class Bookings {
    private int bookings_id;
    private Date booking_date;
    private int days;
    private String comment;

    @Override
    public String toString() {
        return "Bookings{" +
                "bookings_id=" + bookings_id +
                ", booking_date=" + booking_date +
                ", days=" + days +
                ", comment='" + comment + '\'' +
                ", booking_status=" + booking_status +
                ", user_id=" + user_id +
                '}';
    }

    public int getBookings_id() {
        return bookings_id;
    }

    public void setBookings_id(int bookings_id) {
        this.bookings_id = bookings_id;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isBooking_status() {
        return booking_status;
    }

    public void setBooking_status(boolean booking_status) {
        this.booking_status = booking_status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Bookings(int bookings_id, Date booking_date, int days, String comment, boolean booking_status, int user_id) {
        this.bookings_id = bookings_id;
        this.booking_date = booking_date;
        this.days = days;
        this.comment = comment;
        this.booking_status = booking_status;
        this.user_id = user_id;
    }

    private boolean booking_status;
    private int user_id;
}
