package persistence;

import business.entities.Game;

import java.util.List;

public interface GameDAO {
    public List<Game> getGamesFinished();

    public List<Game> searchGamesFinished(String userNameSearch, String gameNameSearch);

    public Game getGameById(String gameID);

    public Game getGameByNameAndGame(String gameName, String userId);

    public int insertGame(String gameName, String correo);

    public Game getStartedGame(String correo);
}