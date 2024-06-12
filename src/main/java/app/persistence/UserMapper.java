package app.persistence;

import app.entities.Bookings;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper
{
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        //Der udføres
        String sql = "select * from users where email=? and password=?";

        try (
                //oprettes en forbindelse til databasen
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            //Det bliver sat som parametre
            ps.setString(1, email);
            ps.setString(2, password);


            //sql bliver udført og gemme resultatet
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int user_id = rs.getInt("user_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String roles = rs.getString("roles");

                return new User(user_id, email, password, name, phone, roles);
            } else
            {
                throw new DatabaseException("Fejl i login. Prøv igen");
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("DB fejl!", e.getMessage());
        }
    }


    public static void createUser(String email, String password, String name, String phone, String roles, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into users (email, password, name, phone, roles) values (?,?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, phone);
            ps.setString(5, roles);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");


            } else {
                throw new DatabaseException("Fejl ved oprettelse af bruger. Bruger-ID blev ikke genereret");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Der er sket en fejl. Prøv igen", e.getMessage());
        }
    }


    public static List<User> getAllUsers(int user_id, ConnectionPool connectionPool) throws DatabaseException
    {
        List<User> userList = new ArrayList<>();
        String sql = "select * from users where user_id=? order by email";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {

                String email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String roles = rs.getString("roles");

//                userList.add(new User(email,password,name,phone,roles));
                userList.add(new User(user_id,email,password,name,phone,roles));
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return userList;
    }


//
//    public static List<User> getAllUsers2(ConnectionPool connectionPool) throws DatabaseException
//    {
//        List<User> userList = new ArrayList<>();
//        String sql = "select * from users where user_id=? order by email";
//
//        try (
//                Connection connection = connectionPool.getConnection();
//                PreparedStatement ps = connection.prepareStatement(sql)
//        )
//        {
//            ResultSet rs = ps.executeQuery();
//            while (rs.next())
//            {
//
//                int user_id = rs.getInt("user_id");
//                String email = rs.getString("email");
//                String password = rs.getString("password");
//                String name = rs.getString("name");
//                String phone = rs.getString("phone");
//                String roles = rs.getString("roles");
////
//////                userList.add(new User(email,password,name,phone,roles));
////                userList.add(new User(user_id,email,password,name,phone,roles));
//            }
//        }
//        catch (SQLException e)
//        {
//            throw new DatabaseException("Fejl!!!!", e.getMessage());
//        }
//        return userList;
//    }

}