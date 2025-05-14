package business.managers;

import persistence.StatsDAO;
import persistence.StatsDBDAO;

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
     */
    public List<Integer> getStatsByGameId(int gameId){
        return statsDAO.getStatsByGameId(gameId);
    }
}
