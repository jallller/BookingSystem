package app.controllers;

import app.entities.Bookings;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.BookingsMapper;
import app.persistence.EquipmentMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.Date;

public class BookingsController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("booking", ctx -> addBooking(ctx, connectionPool));
        //    app.post("createbooking", ctx -> ctx.render("createbooking"));
//        app.post("createuser", ctx -> BookingsMapper.getAllBookingsPerUser(ctx, connectionPool));


    }


    private static void addBooking(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametre
        int bookings_id = Integer.parseInt(ctx.formParam("bookings_id"));
        Date booking_date = Date.valueOf(ctx.formParam("booking_date"));
        int days = Integer.parseInt(ctx.formParam("days"));
        String comment = ctx.formParam("comment");
        boolean booking_status = Boolean.parseBoolean(ctx.formParam("booking_status"));
        int user_id = Integer.parseInt(ctx.formParam("user_id"));
        int equipment_id = Integer.parseInt(ctx.formParam("equipment_id"));


        BookingsMapper.addBookings(connectionPool);
        ctx.attribute("message", "Brugeren oprettet med brugernavn: ");
        ctx.render("createuser.html"); //Send til adminside


    }

//    private static void createBooking(Context ctx, ConnectionPool connectionPool) {
//        //Hent formparametre
//        int bookings_id = Integer.parseInt(ctx.formParam("bookings_id"));
//        Date bookings_date = Date.valueOf(ctx.formParam("bookings_date"));
//        int days = Integer.parseInt(ctx.formParam("days"));
//        String comment = ctx.formParam("comment");
//        boolean booking_status = Boolean.parseBoolean(ctx.formParam("booking_status"));
//        int user_id = Integer.parseInt(ctx.formParam("user_id"));
//        int equipment_id = Integer.parseInt(ctx.formParam("equipment_id"));
//
//        try {
//            Bookings bookings = new Bookings(bookings_id, bookings_date, days, comment, booking_status, user_id, equipment_id);
//            BookingsMapper.addBookings(bookings, connectionPool);
////            BookingsMapper.addBookings(bookings_id, bookings_date, days, comment, booking_status, user_id, equipment_id, connectionPool);
//            ctx.attribute("message", "Udstyr med udstyr id: " + equipment_id + ". Oprettet");
//            ctx.render("adminseeebookings.html"); //Send til adminside
//        } catch (DatabaseException e) {
//            ctx.attribute("message", "Udstyret findes allerede i systemet");
//            ctx.render("adminseeebookings.html");
//        }
//    }
//    private static void createBookings(Context ctx, ConnectionPool connectionPool) {
//        //Hent formparametre
//        Date bookings_date = Date.valueOf(ctx.formParam("bookings_date"));
//        int days = Integer.parseInt(ctx.formParam("days"));
//        String comment = ctx.formParam("comment");
//        boolean booking_status = Boolean.parseBoolean(ctx.formParam("booking_status"));
//        int user_id = Integer.parseInt(ctx.formParam("user_id"));
//
//            try {
//                User user = new User(); //
//                BookingsMapper.addBookings(user, bookings_date, days, comment, booking_status, connectionPool);
//                ctx.attribute("message", "Bookingen er oprettet den: " + bookings_date + ". I " + days + "dage");
//                ctx.render("frontpage.html");
//
//            } catch (DatabaseException e) {
//                ctx.attribute("message", "Brugernavnet er allerede i brug");
//                ctx.render("createuser.html");
//            }
}

