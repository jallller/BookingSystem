package app;

import app.config.SessionConfig;
import app.config.ThymeleafConfig;
import app.controllers.UserController;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "BookingCph";

    private static ConnectionPool connectionPool = ConnectionPool.getInstance(USER,PASSWORD,URL,DB);

    public static void main(String[] args) {
        // Initializing Javalin and Jetty webserver

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.jetty.modifyServletContextHandler(handler ->  handler.setSessionHandler(SessionConfig.sessionConfig()));
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);


//        // Add routes from UserController
//        UserController.addRoutes(app, connectionPool);

        // Routing

        app.get("/", ctx ->  ctx.render("frontpage.html"));
        UserController.addRoutes(app,connectionPool);
//        app.post("login",ctx-> ctx.render("createuser.html")); //Video 5 Ftp - Tjek hvorfor serverfejl ved bookings.html
//        app.post("login", ctx -> UserController.login(ctx,connectionPool)); //^

//        app.post("login",ctx-> ctx.render("adminseebookings.html"));
//        app.post("createuser",ctx-> ctx.render("admin.html"));
    }
}