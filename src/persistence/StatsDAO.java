package persistence;

import persistence.persistenceExceptions.DBGeneralException;
import persistence.persistenceExceptions.FileNotFound;

import java.util.List;

/**
 * Interface that will be used to retrive the stats table information from the database.
 */
public interface StatsDAO {
    /**
     * This method will implement a way to get a List with all stats from one Game, searching by the Game Id.
     * @param gameID Number with the Game Id used in the search.
     * @return List of numbers, those numbers are the coffes of the game at every minute.
     * @throws FileNotFound if the data source file is not found
     * @throws DBGeneralException if a general database error occurs
     */
    public List<Integer> getStatsByGameId(int gameID) throws FileNotFound, DBGeneralException;

    /**
     * This method will implement a way to generate a new register in the Stats table.
     * @param gameId Number with the Game Id of the game of which the stats come from.
     * @param cafes Number of coffees of the game at the instant that the new register is made.
     * @param min Number with the minute of the game.
     * @throws DBGeneralException if a general database error occurs
     */
    public void updateStats(int gameId, int cafes, int min) throws DBGeneralException;

}