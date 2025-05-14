package persistence;

import java.util.List;

public interface StatsDAO {

    public List<Integer> getStatsByGameId(int gameID);

    public void updateStats(int gameId, int cafes, int min);

}