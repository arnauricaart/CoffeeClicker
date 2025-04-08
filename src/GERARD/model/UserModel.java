package model;
import constants.CommonConstants;
import java.sql.*;

public class UserModel {

    public boolean register(String username, String email, String password) {
        try {
            if (!checkUser(username)) {
                Connection conn = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO " + CommonConstants.DB_USERS_TABLE_NAME + " (Nombre, Correo, Contrasena) VALUES (?, ?, ?)"
                );
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.executeUpdate();
                conn.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkUser(String username) {
        try {
            Connection conn = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME + " WHERE Nombre = ?"
            );
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public boolean validateLogin(String usernameOrEmail, String password) {
        try {
            Connection conn = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME + " WHERE (Nombre = ? OR Correo = ?) AND Contrasena = ?"
            );
            ps.setString(1, usernameOrEmail); // puede ser nombre o correo
            ps.setString(2, usernameOrEmail);
            ps.setString(3, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
