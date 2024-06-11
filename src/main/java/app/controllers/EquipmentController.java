package app.controllers;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.EquipmentMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class EquipmentController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("createequipment", ctx -> createEquipment(ctx, connectionPool));
        app.post("createroom", ctx -> createRoom(ctx, connectionPool));
    }

    private static void createEquipment(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametr
        int equipment_id = Integer.parseInt(ctx.formParam("equipment_id"));
        String equipment_name = ctx.formParam("equipment_name");
        String description = ctx.formParam("description");
        int room_number = Integer.parseInt(ctx.formParam("room_number"));

        try {
            EquipmentMapper.createEquipment(equipment_id, equipment_name, description, room_number, connectionPool);
            ctx.attribute("message", "Udstyr med udstyr id: " + equipment_id + ". Oprettet");
            ctx.render("adminseeequipment.html"); //Send til adminside

        } catch (DatabaseException e) {
            ctx.attribute("message", "Udstyret findes allerede i systemet");
            ctx.render("adminseeequipment.html");
        }

    }


    private static void createRoom(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametr
        int room_number = Integer.parseInt(ctx.formParam("room_number"));
        String description = ctx.formParam("description");

        try {
            EquipmentMapper.createRoom(room_number, description, connectionPool);
            ctx.attribute("message", "Rum nr.: " + room_number + ". Oprettet");
            ctx.render("adminseeequipment.html"); //Send til adminside

        } catch (DatabaseException e) {
            ctx.attribute("message", "Rummet er allerede i systemet");
            ctx.render("adminseeequipment.html");
        }

    }
}

