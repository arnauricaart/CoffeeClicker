package business.managers;

import business.businessExceptions.BusinessException;
import persistence.StatsDAO;
import persistence.StatsDBDAO;
import persistence.persistenceExceptions.DBGeneralException;
import persistence.persistenceExceptions.FileNotFound;

import java.util.List;

/**
 * Manages statistics-related operations in the business domain.
 * This class interacts with the data access layer to retrieve game statistics.
 */
public class StatisticsManager {
    /**
     * The data access object responsible for statistics-related persistence operations.
     */
    private StatsDAO statsDAO;

    /**
     * Constructs a StatisticsManager and initializes the statsDAO with a StatsDBDAO implementation.
     */
    public StatisticsManager() {
        this.statsDAO = new StatsDBDAO();
    }

    /**
     * Retrieves a list of statistics associated with a given game ID.
     *
     * @param gameId the ID of the game for which statistics are requested
     * @return a list of integers representing the game's statistics
     * @throws business.businessExceptions.DBGeneralException if a database error occurs during retrieval
     * @throws business.businessExceptions.FileNotFound if the requested statistics are not found
     */
    public List<Integer> getStatsByGameId(int gameId) throws BusinessException {
        try {
            return statsDAO.getStatsByGameId(gameId);
        }catch (DBGeneralException e){
            throw new business.businessExceptions.DBGeneralException(e.getMessage());
        }catch(FileNotFound e){
            throw new business.businessExceptions.FileNotFound(e.getMessage());
        }
    }
}
