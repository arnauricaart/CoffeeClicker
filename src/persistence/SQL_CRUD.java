package persistence;

import persistence.persistenceExceptions.DBGeneralException;
import persistence.persistenceExceptions.FileNotFound;
import persistence.persistenceExceptions.PersistenceException;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * This class will let the developers to have a set of instruccions to make CRUD petitions to the database.
 */
public class SQL_CRUD {
    /**
     * Constructor of the class.
     */
    public SQL_CRUD() {}

    /**
     * This method will let the developer to make a Read petition to search any information from the database.
     * @param query String with the query that will be executed.
     * @param values ArrayList with strings of the values used in the query.
     * @param tipos ArrayList with the data types of the values.
     * @return Returns a ResultSet object that lets the user retrive the information that the query gave.
     * @throws FileNotFound if the database connection file is not found.
     * @throws DBGeneralException if a general database error occurs.
     */
    public static ResultSet Select(String query, ArrayList<String> values, ArrayList<String> tipos) throws FileNotFound, DBGeneralException {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    /**
     * This method will let the developer to do any Create, Update or Delete petition to the database.
     * @param query String with the query that will create, update or delete something in the DDBB.
     * @param values ArrayList with strings of the values used in the query.
     * @param tipos ArrayList with the data types of the values.
     * @param isInsert Boolean that lets the user know if the petition is an insert.
     * @return Returns a PreparedStatement object.
     * @throws FileNotFound if the database connection file is not found.
     * @throws DBGeneralException if a general database error occurs.
     */
    private static PreparedStatement CUDpreparedStament(String query, ArrayList<String> values, ArrayList<String> tipos, boolean isInsert) throws FileNotFound, DBGeneralException {
        PreparedStatement pst;
        Singleton s1 = Singleton.getInstance();
        try {
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

    /**
     * This mehtod uses the CUDpreparedStament method.
     * @param query String with the query that will create, update or delete something in the DDBB.
     * @param values ArrayList with strings of the values used in the query.
     * @param tipos ArrayList with the data types of the values.
     * @return Returns a number with the status of the petition.
     * @throws DBGeneralException if a general database error occurs.
     */
    public static int CUD(String query, ArrayList<String> values, ArrayList<String> tipos) throws DBGeneralException {
        int res;
        try {
            PreparedStatement pst = CUDpreparedStament(query, values, tipos, false);
            res = pst.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DBGeneralException();
        } catch (SQLException | FileNotFound | DBGeneralException e) {
            throw new DBGeneralException();
        }
        return res;
    }

    /**
     * This method uses the CUDpreparedStament method to call the database.
     * @param query String with the query that will create, update or delete something in the DDBB.
     * @param values ArrayList with strings of the values used in the query.
     * @param tipos ArrayList with the data types of the values.
     * @return Returns a number with the id of the new register generated.
     * @throws PersistenceException if a persistence-level error occurs.
     */
    public static int CUDReturningNextval(String query, ArrayList<String> values, ArrayList<String> tipos) throws PersistenceException {
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
            throw new DBGeneralException();
        } catch (SQLException | FileNotFound | DBGeneralException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Error inserting the DataBase");
    }
}
