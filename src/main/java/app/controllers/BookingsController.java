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

//import static app.persistence.BookingsMapper.totalDaysOnLoan;

public class BookingsController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("createbooking", ctx -> addBooking(ctx, connectionPool));
//        app.post("onloan", ctx -> onloan(ctx, connectionPool));
//        app.get("loanDaysPerEquipment", ctx -> totalDaysOnLoan(ctx, connectionPool));

    }

//
//    private static void onloan(Context ctx, ConnectionPool connectionPool) {
//        User user = ctx.sessionAttribute("currentUser");
//        int bookings_id = Integer.parseInt(ctx.formParam("bookings_id"));
//
//        try {
//            // Retrieve the current booking
//            Bookings booking = BookingsMapper.getBookingById(bookings_id, connectionPool);
//
//            if (booking != null) {
//                boolean newOnLoanStatus = !booking.isBooking_status();
//                BookingsMapper.setOnLoanTo(newOnLoanStatus, bookings_id, connectionPool);

//                List<Bookings> bookingsList = BookingsMapper.getAllBookingsPerUser(user.getUser_id(), connectionPool);
//                ctx.attribute("bookingsList", bookingsList);
//            } else {
//                ctx.attribute("message", "Booking not found.");
//            }
//
//        } catch (DatabaseException e) {
//            throw new RuntimeException(e);
//        }
//
//        ctx.render("booking.html");
//    }

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
            bookingsList = BookingsMapper.getAllBookings(ctx,connectionPool);
        } catch (DatabaseException e) {
            ctx.attribute("message", "An error occurred while creating the booking.");
            ctx.render("booking.html");
            return;
        }
        ctx.attribute("bookingsList", bookingsList);
        ctx.render("booking.html");
    }


}



