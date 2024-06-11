package app.persistence;

import app.entities.Equipment;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentMapper {

    public static List<Equipment> getAllEquipment(int equipment_id, ConnectionPool connectionPool) throws DatabaseException
    {
        List<Equipment> equipmentList = new ArrayList<>();
        String sql = "select * from equipment where equipment_id=? order by equipment_name";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setInt(1, equipment_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {

                String equipment_name = rs.getString("equipment_name");
                String description = rs.getString("description");
                int room_number = Integer.parseInt(rs.getString("room_number"));

                equipmentList.add(new Equipment(equipment_id, equipment_name,description,room_number));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return equipmentList;
    }
}
