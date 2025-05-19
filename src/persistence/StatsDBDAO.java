package persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the interface StatsDAO.
 */
public class StatsDBDAO implements StatsDAO{

    /**
     * Constructor of the class.
     */
    public StatsDBDAO(){

    }

    /**
     * This method gets a list of games finished with their user from the database.
     * @param gameID Number with the Game Id used in the search.
     * @return Returns a List object with the finished games.
     */
    public List<Integer> getStatsByGameId(int gameID){
        String query = "Select * FROM stats WHERE IdPartida = ? ORDER BY Minuto ASC";
        ArrayList<String> valores = new ArrayList<>();
        ArrayList<String> tipos = new ArrayList<>();
        valores.add(String.valueOf(gameID));
        tipos.add("int");
        ResultSet res = SQL_CRUD.Select(query,valores,tipos);

        List<Integer> cafeCounts = new ArrayList<>();
        //cafeCounts.add(0);

        try {
            while (res.next()) {
                cafeCounts.add(res.getInt("Cafes"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cafeCounts;
    }

    /**
     * This method generates a new register in the Stats table.
     * @param gameId Number with the Game Id of the game of which the stats come from.
     * @param cafes Number of coffees of the game at the instant that the new register is made.
     * @param min Number with the minute of the game.
     */
    public void updateStats(int gameId, int cafes, int min) {
        String query = "INSERT INTO stats (Cafes, Minuto, IdPartida) VALUES (?, ?, ?)";
        ArrayList<String> valores = new ArrayList<>();
        ArrayList<String> tipos = new ArrayList<>();

        valores.add(String.valueOf(cafes));
        tipos.add("int");

        valores.add(String.valueOf(min));
        tipos.add("int");

        valores.add(String.valueOf(gameId));
        tipos.add("int");

        SQL_CRUD.CUD(query, valores, tipos);
    }
}
