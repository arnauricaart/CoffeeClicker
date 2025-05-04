package Persistence;

import Business.Entities.Game;

import java.sql.ResultSet;
import java.util.List;

public interface GameDAO {
    public List<Game> getGamesFinishedByUser(String correo);

    public List<Game> getGamesNotFinishedByUser(String correo);

    public Game getGameById(String gameID);

    public Game getGameByNameAndGame(String gameName, String userId);

    public boolean removeGame(int gameID);

    public int insertGame(String gameName, String correo);
}