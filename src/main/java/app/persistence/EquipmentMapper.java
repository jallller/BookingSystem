package app.persistence;

import app.entities.Equipment;
import app.exceptions.DatabaseException;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentMapper {
//
//    public static List<Equipment> getAllEquipment(ConnectionPool connectionPool) throws DatabaseException
//    {
//        List<Equipment> equipmentList = new ArrayList<>();
//        String sql = "select * from equipment where equipment_id=? order by equipment_name";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        )
//        {
//            ps.setInt(1, equipment_id);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next())
//            {
//
//                String equipment_name = rs.getString("equipment_name");
//                String description = rs.getString("description");
//                int room_number = Integer.parseInt(rs.getString("room_number"));
//
//                equipmentList.add(new Equipment(equipment_id, equipment_name,description,room_number));
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl!!!!", e.getMessage());
//        }
//        return equipmentList;
//    }


    public static void createEquipment(int equipment_id, String equipment_name, String description,int room_number, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into equipment (equipment_id, equipment_name, description, room_number) values (?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, equipment_id);
            ps.setString(2, equipment_name);
            ps.setString(3, description);
            ps.setInt(4, room_number);

            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected != 1) {
//                throw new DatabaseException("Fejl ved oprettelse af nyt udstyr");
//
//
//            } else {
//                throw new DatabaseException("Fejl ved oprettelse af udstyr.");
//            }
        } catch (SQLException e) {
            throw new DatabaseException("Der er sket en fejl. Prøv igen", e.getMessage());
        }
    }


    public static void createRoom(int room_number1, String description, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into room (room_number, description) values (?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, room_number1);
            ps.setString(2, description);

            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected != 1) {
//                throw new DatabaseException("Fejl ved oprettelse af ny rum ");
//
//
//            } else {
//                throw new DatabaseException("Fejl ved oprettelse af rum.");
//            }
        } catch (SQLException e) {
            throw new DatabaseException("Der er sket en fejl. Prøv igen", e.getMessage());
        }
    }

//
//    private static void getAllEquipment(Context ctx, ConnectionPool connectionPool) {
//        try {
//            ConnectionPool connectionPool1 = new ConnectionPool();
//            List<Equipment> equipmentList = EquipmentMapper.getAllEquipment(connectionPool);
//            ctx.attribute("equipmentList", equipmentList);
//            ctx.render("allEquipment.html");
//        } catch (DatabaseException e) {
//            ctx.attribute("message", e.getMessage());
//            ctx.render("error.html");
//        }
//    }

    public static List<Equipment> getAllEquipment(ConnectionPool connectionPool) throws DatabaseException {
        List<Equipment> equipmentList = new ArrayList<>();
        String sql = "SELECT * FROM equipment ORDER BY equipment_name";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                int equipment_id = rs.getInt("equipment_id");
                String equipment_name = rs.getString("equipment_name");
                String description = rs.getString("description");
                int room_number = rs.getInt("room_number");



                equipmentList.add(new Equipment(equipment_id, equipment_name, description, room_number));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving equipment list", e.getMessage());
        }
        return equipmentList;
    }


}
