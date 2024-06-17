package app.persistence;

import app.entities.Bookings;
import app.entities.User;
import app.exceptions.DatabaseException;
import io.javalin.http.Context;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingsMapper {

    public static List<Bookings> getAllBookingsPerUser(int user_id, ConnectionPool connectionPool) throws DatabaseException {
        List<Bookings> bookingsList = new ArrayList<>();
        String sql = "select * from bookings where user_id=? order by user_id";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("bookings_id");
                Date bookings_date = rs.getDate("booking_date");
                int days = rs.getInt("days");
                String comment = rs.getString("comment");
                Boolean bookings_status = rs.getBoolean("booking_status");
                int equipment_id = rs.getInt("equipment_id");
                bookingsList.add(new Bookings(id, bookings_date, days, comment, bookings_status, user_id, equipment_id));

            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return bookingsList;
    }

    public static Bookings addBookings(Bookings booking, ConnectionPool connectionPool) throws DatabaseException {
        Bookings newBooking = null;

        String sql = "insert into bookings (booking_date, days, comment, booking_status, user_id, equipment_id) values (?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setDate(1, booking.getBooking_date());
            ps.setInt(2, booking.getDays());
            ps.setString(3, booking.getComment());
            ps.setBoolean(4, booking.isBooking_status());
            ps.setInt(5, booking.getUser_id());
            ps.setInt(6, booking.getEquipment_id());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int newId = rs.getInt(1);
                    newBooking = new Bookings(newId, booking.getBooking_date(), booking.getDays(), booking.getComment(),
                            booking.isBooking_status(), booking.getUser_id(), booking.getEquipment_id());
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("An error occurred while creating the booking.", e.getMessage());
        }
        return newBooking;
    }

    public static List<Bookings> getAllBookings(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        List<Bookings> bookingList = new ArrayList<>();
        String sql = "select * from bookings order by booking_date";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bookings_id = rs.getInt("bookings_id");
                Date booking_date = rs.getDate("booking_date");
                int days = rs.getInt("days");
                String comment = rs.getString("comment");
                boolean bookings_status = rs.getBoolean("booking_status");
                int user_id = rs.getInt("user_id");
                int equipment_id = rs.getInt("equipment_id");
                bookingList.add(new Bookings(bookings_id, booking_date, days, comment, bookings_status, user_id, equipment_id));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return bookingList;
    }
//
//    public static boolean isBookingOverlap(int equipmentId, Date startDate, int days, ConnectionPool connectionPool) throws DatabaseException {
//        String sql = "SELECT * FROM bookings " +
//                "WHERE equipment_id = ? AND booking_date <= ? AND DATE_ADD(booking_date, INTERVAL days DAY) >= ?";
//
//        try (Connection connection = connectionPool.getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, equipmentId);
//            ps.setDate(2, startDate);
//            ps.setDate(3, startDate);
//
//            ResultSet rs = ps.executeQuery();
//            return rs.next(); // Returns true if there is an overlap
//        } catch (SQLException e) {
//            throw new DatabaseException("Error checking booking overlap");
//        }
//    }
}