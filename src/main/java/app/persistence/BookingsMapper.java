package app.persistence;

import app.entities.Bookings;
import app.entities.Bookings;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingsMapper
{

    public static List<Bookings> getAllBookingsPerUser(int user_id, ConnectionPool connectionPool) throws DatabaseException
    {
        List<Bookings> bookingsList = new ArrayList<>();
        String sql = "select * from bookings where bookings_id=? order by bookings_id";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("bookings_id");
                Date bookings_date = Date.valueOf(rs.getString("booking_date"));
                int days = rs.getInt("days");
                String comment = rs.getString("comment");
                Boolean bookings_status = rs.getBoolean("booking_status");
                bookingsList.add(new Bookings(id, bookings_date, days,comment,bookings_status,user_id));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return bookingsList;
    }
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
}