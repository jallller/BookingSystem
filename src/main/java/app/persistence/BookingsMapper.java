package app.persistence;

import app.entities.Bookings;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Bookings> getAllBookings(ConnectionPool connectionPool) throws DatabaseException {
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

    public int daysPer

//    public static void setOnLoanTo(boolean b, int bookingsId, ConnectionPool connectionPool) {
//
//    }

//
//    public static List<Bookings> getAllBookings(ConnectionPool connectionPool) throws DatabaseException
//    {
//        List<Bookings> bookingList = new ArrayList<>();
//        String sql = "select * from bookings order by user_id";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        )
//        {
//            ResultSet rs = ps.executeQuery();
//            while (rs.next())
//            {
//
//                int bookings_id = rs.getInt("bookings_id");
//                Date booking_date = Date.valueOf(rs.getString("booking_date"));
//                int days = rs.getInt("days");
//                String comment = rs.getString("comment");
//                boolean bookings_status = Boolean.parseBoolean(rs.getString("booking_status"));
//                int user_id = rs.getInt("user_id");
//                int equipment_id = rs.getInt("equipment_id");
//                bookingList.add(new Bookings(bookings_id,booking_date,days,comment,bookings_status,user_id,equipment_id));
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl!!!!", e.getMessage());
//        }
//        return bookingList;
//    }

}


//
//public static Bookings addBooking(Bookings booking, ConnectionPool connectionPool) throws DatabaseException {
//    String sql = "INSERT INTO orders (orderstatus_id, user_id, toolroom_width, toolroom_length, total_price, carport_width, carport_length) " +
//            "VALUES (?, ?, ?, ?, ?, ?, ?)";
//    try (Connection connection = connectionPool.getConnection()) {
//        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            ps.setInt(1, booking.getBookings_id());
//            ps.setDate(2, booking.getBooking_date());
//            ps.setInt(3, booking.getDays());
//            ps.setString(4, booking.getComment());
//            ps.setBoolean(5, booking.isBooking_status());
//            ps.setInt(6, booking.getUser_id());
//            ps.setInt(7, booking.getEquipment_id());
//            ps.executeUpdate();
//
//            ResultSet keySet = ps.getGeneratedKeys();
//            if (keySet.next()) {
//
//                Bookings newBooking = new Bookings(keySet.getInt(1), booking.getBooking_date(), booking.getDays(), booking.getComment(),
//                        booking.isBooking_status(), booking.getUser_id(), booking.getEquipment_id());
//                booking.setBookings_id((newBooking.getBookings_id());
//
//                return newBooking;
//            } else
//                return null;
//        }
//    } catch (SQLException e) {
//        throw new DatabaseException("Could not create booking in the database", e.getMessage());
//    }
//}

//    public static void createBooking(int bookings_id, Date bookings_date, int days, String comment, boolean booking_status, int user_id, int equipment_id, ConnectionPool connectionPool) throws DatabaseException {
//        String sql = "insert into equipment (bookings_id, bookings_date, days, comment,booking_status,user_id,equipment_id) values (?,?,?,?,?,?,?)";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        ) {
//            ps.setInt(1, bookings_id);
//            ps.setDate(2, bookings_date);
//            ps.setInt(3, days);
//            ps.setString(4, comment);
//            ps.setBoolean(5, booking_status);
//            ps.setInt(6, user_id);
//            ps.setInt(7, equipment_id);
//
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected != 1) {
//                throw new DatabaseException("Fejl ved oprettelse af ny booking");
//
//
//            } else {
//                throw new DatabaseException("Fejl ved oprettelse af booking.");
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException("Der er sket en fejl. Prøv igen", e.getMessage());
//        }
//    }

//    public static Bookings createBooking(Bookings booking, ConnectionPool connectionPool) throws DatabaseException {
//        String sql = "INSERT INTO orders (orderstatus_id, user_id, toolroom_width, toolroom_length, total_price, carport_width, carport_length) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?)";
//        try (Connection connection = connectionPool.getConnection()) {
//            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//                ps.setInt(1, booking.getBookings_id());
//                ps.setInt(2, booking.getBooking_date());
//                ps.setInt(3, booking.getDays());
//                ps.setInt(4, booking.getComment());
//                ps.setInt(5, booking.getBoo);
//                ps.setInt(6, booking.getCarportWidth());
//                ps.setInt(7, booking.getCarportLength());
//                ps.executeUpdate();
//
//                ResultSet keySet = ps.getGeneratedKeys();
//                if (keySet.next()) {
//
//                    Order newOrder = new Order(keySet.getInt(1), order.getOrderStatusId(), order.getUserId(), order.getToolroomWidth(),
//                            order.getToolroomLength(), order.getTotalPrice(), order.getCarportWidth(), order.getCarportLength());
//                    order.setOrderId(newOrder.getOrderId());
//                    return newOrder;
//                } else
//                    return null;
//            }
//        } catch (SQLException e)
//        {
//            throw new DatabaseException("Could not create user in the database", e.getMessage());
//        }
//    }
//
//    public static Bookings addBookings(User user, Date bookings_date, int days, String comment, Boolean bookings_status,ConnectionPool connectionPool) throws DatabaseException
//    {
//        Bookings newBooking = null;
//
//        String sql = "insert into bookings (bookings_date,days,comment,bookings_status,user_id) values (?,?,?,?,?)";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
//        )
//        {
//            ps.setDate(1,bookings_date);
//            ps.setInt(2,days);
//            ps.setString(3,comment);
//            ps.setBoolean(4,bookings_status);
//            ps.setInt(5, user.getUser_id());
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected == 1)
//            {
//                ResultSet rs = ps.getGeneratedKeys();
//                rs.next();
//                int newId = rs.getInt(1);
//                newBooking = new Bookings(newId, bookings_date,days, false, bookings_status,user.getUser_id());
//            } else
//            {
//                throw new DatabaseException("Fejl under indsætning af booking d. : " + bookings_date);
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl i DB connection", e.getMessage());
//        }
//        return newBooking;
//    }

//    public static void setDoneTo(boolean done, int taskId, ConnectionPool connectionPool) throws DatabaseException
//    {
//        String sql = "update task set done = ? where task_id = ?";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        )
//        {
//            ps.setBoolean(1, done);
//            ps.setInt(2, taskId);
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected != 1)
//            {
//                throw new DatabaseException("Fejl i opdatering af en task");
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl i opdatering af en task");
//        }
//    }

//    public static void delete(int taskId, ConnectionPool connectionPool) throws DatabaseException
//    {
//        String sql = "delete from task where task_id = ?";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        )
//        {
//            ps.setInt(1, taskId);
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected != 1)
//            {
//                throw new DatabaseException("Fejl i opdatering af en task");
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl ved sletning af en task", e.getMessage());
//        }
//    }

//    public static Task getTaskById(int taskId, ConnectionPool connectionPool) throws DatabaseException
//    {
//        Task task = null;
//
//        String sql = "select * from task where task_id = ?";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        )
//        {
//            ps.setInt(1, taskId);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next())
//            {
//                int id = rs.getInt("task_id");
//                String name = rs.getString("name");
//                Boolean done = rs.getBoolean("done");
//                int userId = rs.getInt("user_id");
//                task = new Task(id, name, done, userId);
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl ved hentning af task med id = " + taskId, e.getMessage());
//        }
//        return task;
//    }
//
//    public static void update(int taskId, String taskName, ConnectionPool connectionPool) throws DatabaseException
//    {
//        String sql = "update task set name = ? where task_id = ?";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        )
//        {
//            ps.setString(1, taskName);
//            ps.setInt(2, taskId);
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected != 1)
//            {
//                throw new DatabaseException("Fejl i opdatering af en task");
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl i opdatering af en task", e.getMessage());
//        }
//    }
