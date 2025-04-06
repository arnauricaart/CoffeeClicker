package bbdd;
import constants.CommonCostants;
import java.sql.*;

//connexió entre la bbdd --> java
public class MyJDBC {

    public static boolean register(String username, String email,String password) {
        //mirem si username existeix
        try{
            if(!checkUser(username)) {
                Connection connection = DriverManager.getConnection(CommonCostants.DB_URL,CommonCostants.DB_USERNAME,CommonCostants.DB_PASSWORD);

                //Insert
                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + CommonCostants.DB_USERS_TABLE_NAME + " (Nombre,Correo, Contrasena)" + "VALUES(?,?,?)"
                );

                insertUser.setString(1, username);
                insertUser.setString(2, email);
                insertUser.setString(3, password);

                insertUser.executeUpdate();
                connection.close();
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkUser(String username) {
        try{
            Connection connection = DriverManager.getConnection(CommonCostants.DB_URL,CommonCostants.DB_USERNAME,
                    CommonCostants.DB_PASSWORD);

            PreparedStatement checkUserExists = connection.prepareStatement(
                    "SELECT * FROM " + CommonCostants.DB_USERS_TABLE_NAME + " WHERE Nombre = ?"
            );
            checkUserExists.setString(1,username);

            ResultSet resultSet = checkUserExists.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public static boolean validateLogin(String username, String password) {
        try{
            Connection connection = DriverManager.getConnection(CommonCostants.DB_URL,CommonCostants.DB_USERNAME,CommonCostants.DB_PASSWORD);
            PreparedStatement validateUser = connection.prepareStatement("SELECT * FROM " + CommonCostants.DB_USERS_TABLE_NAME + " WHERE Nombre = ? AND Contrasena = ?");
            validateUser.setString(1, username);

            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();
            if(!resultSet.isBeforeFirst()) {
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
