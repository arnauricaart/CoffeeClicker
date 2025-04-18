package Persitence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    public boolean removeUserAndData(String email){
        System.out.println("Remove user");
        String queryPartidaGenerador = "DELETE FROM partida_generador WHERE IdPartida IN (SELECT p.IdPartida FROM partida p JOIN users u ON p.correo = u.correo WHERE u.correo = ? OR u.nombre = ?)";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        values.add(email); types.add("String");
        values.add(email);    types.add("String");

        int result = SQL_CRUD.CUD(queryPartidaGenerador, values, types);

        String queryStats = "DELETE FROM stats WHERE IdPartida IN (SELECT p.IdPartida FROM partida p JOIN users u ON p.correo = u.correo WHERE u.correo = ? OR u.nombre = ?)";
        values = new ArrayList<>();
        types = new ArrayList<>();

        values.add(email); types.add("String");
        values.add(email);    types.add("String");

        result = SQL_CRUD.CUD(queryStats, values, types);

        String queryPartida = "DELETE FROM partida WHERE correo IN (SELECT correo FROM users WHERE correo = ? OR nombre = ?)";
        values = new ArrayList<>();
        types = new ArrayList<>();

        values.add(email); types.add("String");
        values.add(email);    types.add("String");

        result = SQL_CRUD.CUD(queryPartida, values, types);

        String queryUsers = "DELETE FROM users WHERE correo = ? OR nombre = ?";
        values = new ArrayList<>();
        types = new ArrayList<>();

        values.add(email); types.add("String");
        values.add(email);    types.add("String");

        result = SQL_CRUD.CUD(queryUsers, values, types);
        if(result <= 0){
            System.out.println("error user");
        }
        return result > 0;
    }

    public boolean registerUser(String username, String email, String password) {
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

    public boolean checkUserExists(String username) {
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

    public boolean validateLogin(String userOrEmail, String password) {
        String query = "SELECT * FROM users WHERE (Nombre = ? OR Correo = ?) AND Contrasena = ?";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        values.add(userOrEmail); types.add("String");
        values.add(userOrEmail); types.add("String");
        values.add(password);    types.add("String");

        ResultSet rs = SQL_CRUD.Select(query, values, types);
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
