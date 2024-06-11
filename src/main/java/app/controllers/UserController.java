package app.controllers;

//import app.entities.Order;
import app.entities.Bookings;
import app.entities.Equipment;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.BookingsMapper;
import app.persistence.ConnectionPool;
//import app.persistence.OrderMapper;
import app.persistence.EquipmentMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class UserController {

    public static void addRoutes(Javalin app,ConnectionPool connectionPool) {
        app.post("login", ctx -> login(ctx, connectionPool));
        app.get("logout", ctx -> logout(ctx));
//        app.post("login", ctx -> UserController.login(ctx));
//        app.get("goToInfo", ctx -> ctx.render("info.html"));
//        app.post("goToInfo", ctx -> goToInfo(ctx));
//        app.get("createuser", ctx -> ctx.render("createuser.html"));
//        app.post("createuser", ctx -> ctx.render("createuser.html"));
        app.post("createuser", ctx -> ctx.render("createuser.html"));
//        app.post("createuser", ctx -> createUser(ctx, connectionPool));
        //app.post("getAllUsers", ctx -> getAllUsers(ctx, connectionPool));
//        app.get("adminSeeStudents.html", ctx -> ctx.render("adminSeeStudents.html"));
        app.get("adminseeequipment.html", ctx -> ctx.render("adminseeequipment.html"));
        app.get("adminSeeStudents.html", ctx -> ctx.render("adminSeeStudents.html"));
        app.get("adminseebookings.html", ctx -> ctx.render("adminseebookings.html"));
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
                ctx.attribute("message", "Brugeren oprettet med brugernavn: " + email + ". Log venligst på");
                ctx.render("frontpage.html");

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
            ctx.sessionAttribute("currentUser",user);
            List<Bookings> bookingsList = BookingsMapper.getAllBookingsPerUser(user.getUser_id(),connectionPool);
            ctx.attribute("bookingsList",bookingsList);
//            List<Equipment> equipmentList = EquipmentMapper.getAllEquipment(user.getUser_id(), connectionPool);
//            ctx.attribute("equipmentList", equipmentList);

            ctx.sessionAttribute("currentUser", user);
            //Hvis ja send videre til bookingside
            ctx.render("admin.html");

            if (user.getRoles().equals("admin")) {
                ctx.render("admin.html");
            } else {
                ctx.render("frontpage.html"); //Server error booking.html
            }


        } catch (DatabaseException e) {
            //Hvis nej send tilbage til loginside med fejlbesked
            ctx.attribute("message", e.getMessage());
            ctx.render("frontpage.html");
        }


    }


}

