package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.BookingsMapper;
import io.javalin.http.Context;

import java.sql.Date;

public class BookingsController {
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
//                BookingsMapper.addBookings(user,bookings_date,days,comment,booking_status, connectionPool);
//                ctx.attribute("message", "Bookingen er oprettet den: " + bookings_date + ". I " + days + "dage");
//                ctx.render("frontpage.html");
//
//            } catch (DatabaseException e) {
//                ctx.attribute("message", "Brugernavnet er allerede i brug");
//                ctx.render("createuser.html");
//            }
//    }
}
