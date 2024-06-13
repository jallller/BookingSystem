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

import java.util.List;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        app.get("adminseeequipment.html", ctx -> ctx.render("adminseeequipment.html"));
        app.get("adminSeeStudents.html", ctx -> ctx.render("adminSeeStudents.html"));
        app.get("adminseebookings.html", ctx -> ctx.render("adminseebookings.html"));
        app.post("login", ctx -> login(ctx, connectionPool));
        app.get("logout", ctx -> logout(ctx));
//        app.post("login", ctx -> UserController.login(ctx));
//        app.get("goToInfo", ctx -> ctx.render("info.html"));
//        app.post("goToInfo", ctx -> goToInfo(ctx));
//        app.get("createuser", ctx -> ctx.render("createuser.html"));
//        app.post("createuser", ctx -> ctx.render("createuser.html"));
        //app.post("createuser", ctx -> ctx.render("createuser.html"));
//        app.post("createuser", ctx -> createUser(ctx, connectionPool));
        //app.post("getAllUsers", ctx -> getAllUsers(ctx, connectionPool));
//        app.get("adminSeeStudents.html", ctx -> ctx.render("adminSeeStudents.html"));
//        app.get("adminseeequipment.html", ctx -> ctx.render("adminseeequipment.html"));
//        app.get("adminSeeStudents.html", ctx -> ctx.render("adminSeeStudents.html"));
//        app.get("adminseebookings.html", ctx -> ctx.render("adminseebookings.html"));
        app.get("account.html", ctx -> ctx.render("account.html"));
        app.post("createuser", ctx -> createUser(ctx, connectionPool));
//        app.post("createuser", ctx -> getAllUsers(ctx, connectionPool));
        app.get("createuser.html", ctx -> ctx.render("createuser.html"));
        app.get("admin.html", ctx -> ctx.render("admin.html"));


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

            List <User> userList = UserMapper.getAllUsers(connectionPool);
            ctx.attribute("userList", userList);

            List <Equipment> equipmentList = EquipmentMapper.getAllEquipment(connectionPool);
            ctx.attribute("equipmentList", equipmentList);

            List <Bookings> bookingsList = BookingsMapper.getAllBookings(connectionPool);
            ctx.attribute("bookingsList", bookingsList);
//            ctx.render("booking.html");

            BookingsMapper.getAllBookingsPerUser(user.getUser_id(),connectionPool);

//            BookingsMapper.addBookings(connectionPool);

//
//            List <Room> roomList = EquipmentMapper.getAllRooms(connectionPool);
//            ctx.attribute("roomList", roomList);

//            List <Room> roomList = RoomMapper.getAllRoom(connectionPool);
//            ctx.attribute("roomList", roomList);


//            List<Bookings> bookingsList = BookingsMapper.getAllBookingsPerUser(user.getUser_id(),connectionPool);
//            ctx.attribute("bookingsList",bookingsList);

//            List<User> userList = UserMapper.getAllUsers(user.getUser_id(), connectionPool);
//            List<User> userList2 = UserMapper.getAllUsers2(connectionPool);
//            List<Equipment> equipmentList = EquipmentMapper.getAllEquipment(user.getUser_id(), connectionPool);
//            ctx.attribute("equipmentList", equipmentList);

            ctx.sessionAttribute("currentUser", user);
            //Hvis ja send videre til bookingside
            ctx.render("admin.html");

            if (user.getRoles().equals("admin")) {
                ctx.render("admin.html");
            } else {
                ctx.render("booking.html"); //Server error booking.html
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
            List<User> userList = UserMapper.getAllUsers(connectionPool);

            //Hvis ja send videre til bookingside
            List <User> userList2 = UserMapper.getAllUsers(connectionPool);
            ctx.attribute("userList2", userList2);
            ctx.render("adminSeeStudents.html");

        } catch (DatabaseException e) {
            //Hvis nej send tilbage til loginside med fejlbesked
            ctx.attribute("message", e.getMessage());
            ctx.render("adminSeeStudents.html");
        }
    }
//
//    public static void getAllEquipment(Context ctx, ConnectionPool connectionPool) {
//        //Hent formparametre
//        String email = ctx.formParam("email");
//        String password = ctx.formParam("password");
//
//
//        try {
//            List<Equipment> equipmentList = UserMapper.getAllUsers(connectionPool);
//
//            //Hvis ja send videre til bookingside
//            ctx.render("adminSeeStudents.html");
//
//        } catch (DatabaseException e) {
//            //Hvis nej send tilbage til loginside med fejlbesked
//            ctx.attribute("message", e.getMessage());
//            ctx.render("adminSeeStudents.html");
//        }
//    }

//    public static void getAllRooms(Context ctx, ConnectionPool connectionPool) {
//        //Hent formparametre
//        String email = ctx.formParam("email");
//        String password = ctx.formParam("password");
//
//
//        try {
//            List<User> userList = UserMapper.getAllUsers(connectionPool);
//
//            //Hvis ja send videre til bookingside
//            ctx.render("adminSeeStudents.html");
//
//        } catch (DatabaseException e) {
//            //Hvis nej send tilbage til loginside med fejlbesked
//            ctx.attribute("message", e.getMessage());
//            ctx.render("adminSeeStudents.html");
//        }
//    }

//    public static void getAllBookings(Context ctx, ConnectionPool connectionPool) {
//        //Hent formparametre
//        String email = ctx.formParam("email");
//        String password = ctx.formParam("password");
//
//
//        try {
//            List<User> userList = UserMapper.getAllUsers(connectionPool);
//
//            //Hvis ja send videre til bookingside
//            ctx.render("adminSeeStudents.html");
//
//        } catch (DatabaseException e) {
//            //Hvis nej send tilbage til loginside med fejlbesked
//            ctx.attribute("message", e.getMessage());
//            ctx.render("adminSeeStudents.html");
//        }
//    }


}

