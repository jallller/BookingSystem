//package app.controllers;
//
//import app.entities.Bookings;
//import app.entities.User;
//import app.exceptions.DatabaseException;
//import app.persistence.ConnectionPool;
//import app.persistence.BookingsMapper;
//import app.persistence.EquipmentMapper;
//import app.persistence.UserMapper;
//import io.javalin.Javalin;
//import io.javalin.http.Context;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.List;
//
//public class BookingsController {
//    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
//        //addBooking formaction
////        app.post("createbooking ", ctx -> addBooking(ctx, connectionPool));
//        app.post("createbooking", ctx -> addBooking(ctx, connectionPool));
//
//        //    app.post("createbooking", ctx -> ctx.render("createbooking"));
////        app.post("createuser", ctx -> BookingsMapper.getAllBookingsPerUser(ctx, connectionPool));
//
//
//    }
//
//
////    private static void addBooking(Context ctx, ConnectionPool connectionPool) {
//////        //Hent formparametre
//////        int bookings_id = Integer.parseInt(ctx.formParam("bookings_id"));
//////        Date booking_date = Date.valueOf(ctx.formParam("booking_date"));
//////        int days = Integer.parseInt(ctx.formParam("days"));
//////        String comment = ctx.formParam("comment");
//////        boolean booking_status = Boolean.parseBoolean(ctx.formParam("booking_status"));
//////        int user_id = Integer.parseInt(ctx.formParam("user_id"));
//////        int equipment_id = Integer.parseInt(ctx.formParam("equipment_id"));
//////
//////
//////        BookingsMapper.addBookings(connectionPool);
//////        ctx.attribute("message", "Brugeren oprettet med brugernavn: ");
//////        ctx.render("createuser.html"); //Send til adminside
////
////        Date bookingName= Date.valueOf(ctx.formParam("booking_date"));
////        int days = Integer.parseInt(ctx.formParam("days"));
////        String comment = ctx.formParam("comment");
////        User user = ctx.sessionAttribute("currentUser");
////
////
////        List<Bookings> bookingsList = null;
////        try {
////            Bookings booking = BookingsMapper.addBookings(user,connectionPool); //try catch
////            bookingsList = BookingsMapper.getAllBookings(connectionPool);
////        } catch (DatabaseException e) {
////            ctx.attribute("message", "fejl fejl fejl");
////            ctx.render("booking.html");
////        }
////        ctx.attribute("bookingsList", bookingsList);
////        ctx.render("booking.html");
////    }
////
////    private static void addBooking(Context ctx, ConnectionPool connectionPool) {
////        String bookingDateStr = ctx.formParam("booking_date");
////        int days = Integer.parseInt(ctx.formParam("days"));
////        String comment = ctx.formParam("comment");
////        boolean booking_status = Boolean.parseBoolean(ctx.formParam("booking_status"));
////        User user = ctx.sessionAttribute("currentUser");
//////        int equipment_id = Integer.parseInt(ctx.formParam("equipment_id"));
////
////        if (user == null) {
////            ctx.attribute("message", "User is not logged in.");
////            ctx.render("booking.html");
////            return;
////        }
////
////        Date bookingDate;
////        try {
////            // Validate and parse the booking date
////            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////            dateFormat.setLenient(false);
////            bookingDate = new Date(dateFormat.parse(bookingDateStr).getTime());
////        } catch (ParseException e) {
////            ctx.attribute("message", "Invalid date format. Please use yyyy-MM-dd.");
////            ctx.render("booking.html");
////            return;
////        } catch (Exception e) {
////            ctx.attribute("message", "An error occurred while processing the date.");
////            ctx.render("booking.html");
////            return;
////        }
////
////        List<Bookings> bookingsList = null;
////        try {
////            // Create the booking
////            Bookings booking = new Bookings(0, bookingDate, days, comment, true, user.getUser_id(), 0); // Assuming equipment_id is 0
////            BookingsMapper.addBookings(user , connectionPool);
////            // Retrieve updated list of bookings
////            bookingsList = BookingsMapper.getAllBookings(connectionPool);
////        } catch (DatabaseException e) {
////            ctx.attribute("message", "An error occurred while creating the booking.");
////            ctx.render("booking.html");
////            return; // Ensure the rest of the code is not executed in case of an exception
////        }
////        ctx.attribute("bookingsList", bookingsList);
////        ctx.render("booking.html");
////    }
//
//
//    private static void addBooking(Context ctx, ConnectionPool connectionPool) {
//        String bookingDateStr = ctx.formParam("booking_date");
//        int days = Integer.parseInt(ctx.formParam("days"));
//        String comment = ctx.formParam("comment");
//        int equipmentId = Integer.parseInt(ctx.formParam("equipment_id"));
//        User user = ctx.sessionAttribute("currentUser");
//
//        if (user == null) {
//            ctx.attribute("message", "User is not logged in.");
//            ctx.render("booking.html");
//            return;
//        }
//
//        Date bookingDate;
//        try {
//            // Validate and parse the booking date
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            dateFormat.setLenient(false);
//            bookingDate = new Date(dateFormat.parse(bookingDateStr).getTime());
//        } catch (ParseException e) {
//            ctx.attribute("message", "Invalid date format. Please use yyyy-MM-dd.");
//            ctx.render("booking.html");
//            return;
//        } catch (Exception e) {
//            ctx.attribute("message", "An error occurred while processing the date.");
//            ctx.render("booking.html");
//            return;
//        }
//
//        List<Bookings> bookingsList = null;
//        try {
//            // Create the booking
//            Bookings booking = new Bookings(0, bookingDate, days, comment, true, user.getId(), equipmentId);
//            BookingsMapper.addBookings(booking, connectionPool);
//            // Retrieve updated list of bookings
//            bookingsList = BookingsMapper.getAllBookings(connectionPool);
//        } catch (DatabaseException e) {
//            ctx.attribute("message", "An error occurred while creating the booking.");
//            ctx.render("booking.html");
//            return; // Ensure the rest of the code is not executed in case of an exception
//        }
//        ctx.attribute("bookingsList", bookingsList);
//        ctx.render("booking.html");
//    }
//
//
////    private static void addBooking(Context ctx, ConnectionPool connectionPool) {
////        String bookingDateStr = ctx.formParam("booking_date");
////        int days = Integer.parseInt(ctx.formParam("days"));
////        String comment = ctx.formParam("comment");
////        boolean booking_status = Boolean.parseBoolean(ctx.formParam("booking_status"));
////        User user = ctx.sessionAttribute("currentUser");
//////        int equipment_id = Integer.parseInt(ctx.formParam("equipment_id"));
////
////        if (user == null) {
////            ctx.attribute("message", "User is not logged in.");
////            ctx.render("booking.html");
////            return;
////        }
////
////        Date bookingDate;
////        try {
////            // Validate and parse the booking date
////            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////            dateFormat.setLenient(false);
////            bookingDate = new Date(dateFormat.parse(bookingDateStr).getTime());
////            // Create the booking
////            List<Bookings> bookingsList = null;
////            Bookings booking = new Bookings(0, bookingDate, days, comment, true, user.getUser_id(), 0); // Assuming equipment_id is 0
////            BookingsMapper.addBookings(user , booking,connectionPool);
////            // Retrieve updated list of bookings
////            bookingsList = BookingsMapper.getAllBookings(connectionPool);
////        } catch (Exception e) {
////            ctx.attribute("message", "An error occurred while processing the date.");
////            ctx.render("booking.html");
////            return;
////        }
////    }
//
//    public static void createUser(String email, String password, String name, String phone, String roles, ConnectionPool connectionPool) throws DatabaseException {
//        String sql = "insert into users (email, password, name, phone, roles) values (?,?,?,?,?)";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        ) {
//            ps.setString(1, email);
//            ps.setString(2, password);
//            ps.setString(3, name);
//            ps.setString(4, phone);
//            ps.setString(5, roles);
//
//            int rowsAffected = ps.executeUpdate();
////            if (rowsAffected != 1) {
////                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
////
////            } else {
////                throw new DatabaseException("Fejl ved oprettelse af bruger. Bruger-ID blev ikke genereret");
////            }
//        } catch (SQLException e) {
//            throw new DatabaseException("Der er sket en fejl. Prøv igen", e.getMessage());
//        }
//    }
//
////    private static void createBooking(Context ctx, ConnectionPool connectionPool) {
////        //Hent formparametre
////        int bookings_id = Integer.parseInt(ctx.formParam("bookings_id"));
////        Date bookings_date = Date.valueOf(ctx.formParam("bookings_date"));
////        int days = Integer.parseInt(ctx.formParam("days"));
////        String comment = ctx.formParam("comment");
////        boolean booking_status = Boolean.parseBoolean(ctx.formParam("booking_status"));
////        int user_id = Integer.parseInt(ctx.formParam("user_id"));
////        int equipment_id = Integer.parseInt(ctx.formParam("equipment_id"));
////
////        try {
////            Bookings bookings = new Bookings(bookings_id, bookings_date, days, comment, booking_status, user_id, equipment_id);
////            BookingsMapper.addBookings(bookings, connectionPool);
//////            BookingsMapper.addBookings(bookings_id, bookings_date, days, comment, booking_status, user_id, equipment_id, connectionPool);
////            ctx.attribute("message", "Udstyr med udstyr id: " + equipment_id + ". Oprettet");
////            ctx.render("adminseeebookings.html"); //Send til adminside
////        } catch (DatabaseException e) {
////            ctx.attribute("message", "Udstyret findes allerede i systemet");
////            ctx.render("adminseeebookings.html");
////        }
////    }
////    private static void createBookings(Context ctx, ConnectionPool connectionPool) {
////        //Hent formparametre
////        Date bookings_date = Date.valueOf(ctx.formParam("bookings_date"));
////        int days = Integer.parseInt(ctx.formParam("days"));
////        String comment = ctx.formParam("comment");
////        boolean booking_status = Boolean.parseBoolean(ctx.formParam("booking_status"));
////        int user_id = Integer.parseInt(ctx.formParam("user_id"));
////
////            try {
////                User user = new User(); //
////                BookingsMapper.addBookings(user, bookings_date, days, comment, booking_status, connectionPool);
////                ctx.attribute("message", "Bookingen er oprettet den: " + bookings_date + ". I " + days + "dage");
////                ctx.render("frontpage.html");
////
////            } catch (DatabaseException e) {
////                ctx.attribute("message", "Brugernavnet er allerede i brug");
////                ctx.render("createuser.html");
////            }
//}
//


package app.controllers;

import app.entities.Bookings;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.BookingsMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class BookingsController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("createbooking", ctx -> addBooking(ctx, connectionPool));
    }

    private static void addBooking(Context ctx, ConnectionPool connectionPool) {
        String bookingDateStr = ctx.formParam("booking_date");
        int days = Integer.parseInt(ctx.formParam("days"));
        String comment = ctx.formParam("comment");
        int equipmentId = Integer.parseInt(ctx.formParam("equipment_id"));
        User user = ctx.sessionAttribute("currentUser");

        if (user == null) {
            ctx.attribute("message", "User is not logged in.");
            ctx.render("booking.html");
            return;
        }

        Date bookingDate;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            bookingDate = new Date(dateFormat.parse(bookingDateStr).getTime());
        } catch (ParseException e) {
            ctx.attribute("message", "Invalid date format. Please use yyyy-MM-dd.");
            ctx.render("booking.html");
            return;
        }

        List<Bookings> bookingsList = null;
        try {
            Bookings booking = new Bookings(0, bookingDate, days, comment, true, user.getUser_id(), equipmentId);
            BookingsMapper.addBookings(booking, connectionPool);
            bookingsList = BookingsMapper.getAllBookings(connectionPool);
        } catch (DatabaseException e) {
            ctx.attribute("message", "An error occurred while creating the booking.");
            ctx.render("booking.html");
            return;
        }
        ctx.attribute("bookingsList", bookingsList);
        ctx.render("booking.html");
    }
}
