package persistence;

import persistence.persistenceExceptions.DBGeneralException;
import persistence.persistenceExceptions.FileNotFound;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the interface StatsDAO.
 */
public class StatsDBDAO implements StatsDAO {

    /**
     * Constructor of the class.
     */
    public StatsDBDAO() {

    }

    /**
     * This method gets a list of games finished with their user from the database.
     * @param gameID Number with the Game Id used in the search.
     * @return Returns a List object with the finished games.
     * @throws FileNotFound if the configuration file cannot be found.
     * @throws DBGeneralException if there is a database access error.
     */
    public List<Integer> getStatsByGameId(int gameID) throws FileNotFound, DBGeneralException {
        String query = "SELECT * FROM stats WHERE IdPartida = ? ORDER BY Minuto ASC";
        ArrayList<String> valores = new ArrayList<>();
        ArrayList<String> tipos = new ArrayList<>();
        valores.add(String.valueOf(gameID));
        tipos.add("int");

        ResultSet res = null;
        try {
            res = SQL_CRUD.Select(query, valores, tipos);
        } catch (Exception e) {
            throw new DBGeneralException();
        }

        List<Integer> cafeCounts = new ArrayList<>();

        try {
            while (res.next()) {
                cafeCounts.add(res.getInt("Cafes"));
            }
        } catch (SQLException e) {
            throw new DBGeneralException();
        }

        return cafeCounts;
    }

    /**
     * This method generates a new register in the Stats table.
     * @param gameId Number with the Game Id of the game of which the stats come from.
     * @param cafes Number of coffees of the game at the instant that the new register is made.
     * @param min Number with the minute of the game.
     * @throws DBGeneralException if there is a database access error.
     */
    public void updateStats(int gameId, int cafes, int min) throws DBGeneralException {
        String query = "INSERT INTO stats (Cafes, Minuto, IdPartida) VALUES (?, ?, ?)";
        ArrayList<String> valores = new ArrayList<>();
        ArrayList<String> tipos = new ArrayList<>();

        valores.add(String.valueOf(cafes));
        tipos.add("int");

        valores.add(String.valueOf(min));
        tipos.add("int");

        valores.add(String.valueOf(gameId));
        tipos.add("int");

        try {
            SQL_CRUD.CUD(query, valores, tipos);
        } catch (Exception e) {
            throw new DBGeneralException();
        }
    }
}
