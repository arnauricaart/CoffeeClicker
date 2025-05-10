package business.managers;

import business.entities.Game;
import persistence.GameDAO;
import persistence.GameDBDAO;

import java.util.List;

public class PartidaManager {
    private GameDAO gameDAO;


    public PartidaManager() {
        this.gameDAO = new GameDBDAO();
    }

    public List<Game> getGamesFinished(){
        return gameDAO.getGamesFinished();
    }

    public List<Game> searchGamesFinished(String userSearch, String gameSearch){
        return gameDAO.searchGamesFinished(userSearch, gameSearch);
    }

    public int insertGame(String userName, String correo){
        gameDAO.insertGame(userName, correo);
        return 0;
    }

    public Game getStartedGame(String userName){
        return gameDAO.getStartedGame(userName);
    }

    public Game getGameById(int id) {
        return gameDAO.getGameById(id);
    }
}