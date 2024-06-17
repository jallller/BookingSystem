package app.controllers;

import app.entities.Equipment;
import app.entities.Room;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.EquipmentMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("createequipment", ctx -> createEquipment(ctx, connectionPool));
        app.post("createroom", ctx -> createRoom(ctx, connectionPool));
        app.get("adminAddEquimentRoom.html", ctx -> ctx.render("adminAddEquimentRoom.html"));
        app.get("allEquipment", ctx -> getAllEquipment(ctx, connectionPool));
        app.get("allRooms", ctx -> getAllRooms(ctx, connectionPool));


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
            ctx.render("adminAddEquimentRoom.html"); //Send til adminside

        } catch (DatabaseException e) {
            ctx.attribute("message", "Udstyret findes allerede i systemet");
            ctx.render("adminAddEquimentRoom.html");
        }
    }


    private static void createRoom(Context ctx, ConnectionPool connectionPool) {
        //Hent formparametr
        int room_number = Integer.parseInt(ctx.formParam("room_number"));
        String description = ctx.formParam("description");

        try {
            EquipmentMapper.createRoom(room_number, description, connectionPool);
            ctx.attribute("message", "Rum nr.: " + room_number + ". Oprettet");
            ctx.render("adminAddEquimentRoom.html"); //Send til adminside

        } catch (DatabaseException e) {
            ctx.attribute("message", "Rummet er allerede i systemet");
            ctx.render("adminAddEquimentRoom.html");
        }
    }


    //HER

    private static void getAllEquipment(Context ctx, ConnectionPool connectionPool) {
        try {
            List<Equipment> equipmentList = EquipmentMapper.getAllEquipment(connectionPool);
            ctx.attribute("equipmentList", equipmentList);
            ctx.render("adminseeequipment.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("error.html");
        }
    }
    private static void getAllRooms(Context ctx, ConnectionPool connectionPool) {
        try {
            List<Room> roomList = EquipmentMapper.getAllRooms(connectionPool);
            ctx.attribute("roomList", roomList);
            ctx.render("adminseeequipment.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("error.html");
        }
    }
}



