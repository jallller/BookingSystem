package app.controllers;

//import app.entities.Order;

import app.entities.Bookings;
import app.entities.Equipment;
import app.entities.Room;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.BookingsMapper;
import app.persistence.ConnectionPool;
//import app.persistence.OrderMapper;
import app.persistence.EquipmentMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import app.entities.User;

import java.util.List;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        app.get("adminseeequipment.html", ctx -> ctx.render("adminseeequipment.html"));
        app.get("adminSeeStudents.html", ctx -> ctx.render("adminSeeStudents.html"));
//        app.get("adminseebookings.html", ctx -> ctx.render("adminseebookings.html"));
        app.post("login", ctx -> login(ctx, connectionPool));
        app.get("logout", ctx -> logout(ctx));
        app.get("account.html", ctx -> ctx.render("account.html"));
        app.post("createuser", ctx -> createUser(ctx, connectionPool));
        app.get("createuser.html", ctx -> ctx.render("createuser.html"));
        app.get("admin.html", ctx -> ctx.render("admin.html"));
        app.post("/updatePassword", ctx -> updatePassword(ctx, connectionPool));
        app.post("/updatePhone", ctx -> updatePhone(ctx, connectionPool));

    }

    private static void updatePassword(Context ctx, ConnectionPool connectionPool) {
//        String email = ctx.sessionAttribute("currentUser").getEmail();
        String password = ctx.formParam("password");
        String email = ctx.formParam("email");
        String repeatPassword = ctx.formParam("repeatPassword");

        if (password.equals(repeatPassword)) {
            try {
                UserMapper.updatePassword(email, password, connectionPool);
                ctx.redirect("/account.html"); // Redirect to account page
            } catch (DatabaseException e) {
                ctx.attribute("message", "Failed to update password");
                ctx.render("account.html");
            }
        } else {
            ctx.attribute("message", "Passwords do not match");
            ctx.render("account.html");
        }
    }

    private static void updatePhone(Context ctx, ConnectionPool connectionPool) {
//        String email = ctx.sessionAttribute("currentUser").getEmail();
        String email = ctx.formParam("email");

        String phone = ctx.formParam("phone");

        try {
            UserMapper.updatePhone(email, phone, connectionPool);
            ctx.redirect("/account.html"); // Redirect to account page
        } catch (DatabaseException e) {
            ctx.attribute("message", "Failed to update phone number");
            ctx.render("account.html");
        }
    }


    private static void createUser(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametre
        String email = ctx.formParam("email");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");
        String name = ctx.formParam("name");
        String phone = ctx.formParam("phone");
        String roles = ctx.formParam("roles");


        if (password1.equals(password2)) {
            try {
                UserMapper.createUser(email, password1, name, phone, roles, connectionPool);
                ctx.attribute("message", "Brugeren oprettet med brugernavn: " + email);
                ctx.render("createuser.html"); //Send til adminside

            } catch (DatabaseException e) {
                ctx.attribute("message", "Brugernavnet er allerede i brug");
                ctx.render("createuser.html");
            }

        } else {
            ctx.attribute("message", "Kodeordende matcher ikke");
            ctx.render("createuser.html");
        }
    }

    private static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametre
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");



        //Tjek om bruger findes i DB
        try {

            User user = UserMapper.login(email, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);

            List<User> userList = UserMapper.getAllUsers(connectionPool);
            ctx.attribute("userList", userList);
            //adminseestudents


            //adminSeeequipment
            List<Equipment> equipmentList = EquipmentMapper.getAllEquipment(connectionPool);
            ctx.attribute("equipmentList", equipmentList);


            //adminseebookings
            List<Bookings> bookingsList = BookingsMapper.getAllBookings(ctx, connectionPool);
//            List<Bookings> bookingsList = BookingsMapper.getAllBookings(user.getUser_id(), connectionPool);
            ctx.attribute("bookingsList", bookingsList);
//            ctx.render("booking.html");


            ctx.sessionAttribute("currentUser", user);
            //Hvis ja send videre til bookingside
            ctx.render("admin.html");

            if (user.getRoles().equals("admin")) {
                List<Bookings> bookingsList2 = BookingsMapper.getAllBookings(ctx,connectionPool);
                ctx.attribute("bookingsList2", bookingsList2);
                ctx.render("admin.html");
            } else {
                ctx.render("booking.html");
            }


        } catch (DatabaseException e) {
            //Hvis nej send tilbage til loginside med fejlbesked
            ctx.attribute("message", e.getMessage());
            ctx.render("frontpage.html");
        }
    }

    public static void getAllUsers(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametre
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        try {

            //Hvis ja send videre til bookingside
            List<User> userList = UserMapper.getAllUsers(connectionPool);
            ctx.attribute("userList", userList);
            ctx.render("adminSeeStudents.html");

        } catch (DatabaseException e) {
            //Hvis nej send tilbage til loginside med fejlbesked
            ctx.attribute("message", e.getMessage());
            ctx.render("adminSeeStudents.html");
        }


    }
}

