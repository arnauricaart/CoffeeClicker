package business.managers;

import persistence.StatsDAO;
import persistence.StatsDBDAO;

import java.util.List;

public class StatisticsManager {
    private StatsDAO statsDAO;

    public StatisticsManager() {
        this.statsDAO = new StatsDBDAO();
    }

    public List<Integer> getStatsByGameId(int gameId){
        return statsDAO.getStatsByGameId(gameId);
    }
}
