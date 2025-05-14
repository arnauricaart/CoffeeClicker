package persistence;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class SQL_CRUD {
    public SQL_CRUD() {}

    public static ResultSet Select(String query, ArrayList<String> values, ArrayList<String> tipos) {
        PreparedStatement pst;
        Singleton s1 = Singleton.getInstance();
        ResultSet res;
        try {
            pst = s1.getConn().prepareStatement(query);
            for (int i = 0; i < values.size(); i++) {
                switch (tipos.get(i)) {
                    case "String":
                        pst.setString(i + 1, values.get(i));
                        break;
                    case "int":
                        pst.setInt(i + 1, Integer.parseInt(values.get(i)));
                        break;
                    case "float":
                    case "double": // <- añadido
                        pst.setDouble(i + 1, Double.parseDouble(values.get(i)));
                        break;
                    case "tinyint":
                        pst.setInt(i + 1, values.get(i).equals("true") ? 1 : 0);
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo no soportado en SELECT: " + tipos.get(i));
                }
            }
            res = pst.executeQuery();
            System.out.println(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    private static PreparedStatement CUDpreparedStament(String query, ArrayList<String> values, ArrayList<String> tipos, boolean isInsert) {
        PreparedStatement pst;
        Singleton s1 = Singleton.getInstance();
        try {
            System.out.println(query);
            if (isInsert) {
                pst = s1.getConn().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            } else {
                pst = s1.getConn().prepareStatement(query);
            }

            for (int i = 0; i < values.size(); i++) {
                switch (tipos.get(i)) {
                    case "String":
                        pst.setString(i + 1, values.get(i));
                        break;
                    case "int":
                        pst.setInt(i + 1, Integer.parseInt(values.get(i)));
                        break;
                    case "float":
                    case "double": // <- añadido
                        pst.setDouble(i + 1, Double.parseDouble(values.get(i)));
                        break;
                    case "tinyint":
                        pst.setInt(i + 1, values.get(i).equals("true") ? 1 : 0);
                        break;
                    case "datetime":
                        pst.setTimestamp(i + 1, Timestamp.valueOf(LocalDateTime.now()));
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo no soportado en CUD: " + tipos.get(i));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pst;
    }

    public static int CUD(String query, ArrayList<String> values, ArrayList<String> tipos) {
        int res;
        try {
            PreparedStatement pst = CUDpreparedStament(query, values, tipos, false);
            res = pst.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new ConstraintException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public static int CUDReturningNextval(String query, ArrayList<String> values, ArrayList<String> tipos) {
        try {
            PreparedStatement pst = CUDpreparedStament(query, values, tipos, true);
            int res = pst.executeUpdate();
            if (res > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new ConstraintException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Error insertando en la base de datos");
    }
}
