package Persistence;

import Business.Entities.Game;

import java.util.List;

public interface GameDAO {
    public List<Game> getGamesFinished();

    public List<Game> searchGamesFinished(String userNameSearch, String gameNameSearch);

    public Game getGameById(String gameID);

    public Game getGameByNameAndGame(String gameName, String userId);

    public int insertGame(String gameName, String correo);
}