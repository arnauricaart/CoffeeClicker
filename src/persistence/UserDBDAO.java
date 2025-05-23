package persistence;

import persistence.persistenceExceptions.DBGeneralException;
import persistence.persistenceExceptions.FileNotFound;
import persistence.persistenceExceptions.PersistenceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class implements the interface UserDAO.
 */
public class UserDBDAO implements UserDAO{
    /**
     * Constructor of the class.
     */
    public UserDBDAO() throws PersistenceException {
        Singleton.getInstance().getConn();
    }

    /**
     * This method removes an user and all of its information.
     * @param email String with the User Mail.
     * @return Returns a boolean that will tell if everything went okey.
     */
    public boolean removeUserAndData(String email) throws DBGeneralException {
        System.out.println("Remove user");
        String queryPartidaGenerador = "DELETE FROM partida_generador WHERE IdPartida IN (SELECT IdPartida FROM partida WHERE correo = ?)";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        values.add(email);
        types.add("String");

        int result = SQL_CRUD.CUD(queryPartidaGenerador, values, types);

        String queryStats = "DELETE FROM stats WHERE IdPartida IN (SELECT IdPartida FROM partida WHERE correo = ?)";
        values = new ArrayList<>();
        types = new ArrayList<>();

        values.add(email);
        types.add("String");

        result = SQL_CRUD.CUD(queryStats, values, types);

        String queryPartida = "DELETE FROM partida WHERE correo =?";
        values = new ArrayList<>();
        types = new ArrayList<>();

        values.add(email);
        types.add("String");

        result = SQL_CRUD.CUD(queryPartida, values, types);

        String queryUsers = "DELETE FROM users WHERE correo = ?";
        values = new ArrayList<>();
        types = new ArrayList<>();

        values.add(email);
        types.add("String");

        result = SQL_CRUD.CUD(queryUsers, values, types);
        if (result <= 0) {
            System.out.println("error user");
        }
        return result > 0;
    }

    /**
     * This method registers a new User.
     * @param username String with the User Name that will be used in the insert query.
     * @param email String with the User Mail.
     * @param password String with the User Password.
     * @return Returns a boolean that will tell if everything went okey.
     */
    public boolean registerUser(String username, String email, String password) throws FileNotFound, DBGeneralException {
        if (checkUserExists(username)) return false;

        String query = "INSERT INTO users (Nombre, Correo, Contrasena) VALUES (?, ?, ?)";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        values.add(username); types.add("String");
        values.add(email);    types.add("String");
        values.add(password); types.add("String");

        int result = SQL_CRUD.CUD(query, values, types);
        return result > 0;
    }

    /**
     * This method checks if a user exists searching it by the User Name.
     * @param username String with the User Name that will be used in the search.
     * @return Returns a boolean that will tell if it exists or not.
     */
    public boolean checkUserExists(String username) throws FileNotFound, DBGeneralException {
        String query = "SELECT * FROM users WHERE Nombre = ?";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        values.add(username); types.add("String");

        ResultSet rs = SQL_CRUD.Select(query, values, types);
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * This method checks if a User Mail exists.
     * @param email String with the User Mail.
     * @return Returns a Boolean that will tell if the User Mail exists or not.
     */
    public boolean checkEmailExists(String email) throws FileNotFound, DBGeneralException {
        String query = "SELECT COUNT(*) FROM users WHERE Correo = ?";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> tipos = new ArrayList<>();
        values.add(email); tipos.add("String");

        ResultSet rs = SQL_CRUD.Select(query, values, tipos);
        try {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method gets the User Mail from the login.
     * @param userOrEmail String with the User Name or User Mail.
     * @param password String with the User Password.
     * @return Returns a String with the User Mail.
     */
    public String getCorreoFromLogin(String userOrEmail, String password) throws FileNotFound, DBGeneralException {
        String query = "SELECT Correo FROM users WHERE (Nombre = ? OR Correo = ?) AND Contrasena = ?";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        values.add(userOrEmail); types.add("String");
        values.add(userOrEmail); types.add("String");
        values.add(password);    types.add("String");

        ResultSet rs = SQL_CRUD.Select(query, values, types);
        try {
            if(rs.next()){
                return rs.getString("Correo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
