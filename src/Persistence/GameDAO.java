package Persistence;

import Business.GameData;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface GameDAO {
    public List<GameData> getGamesFinishedByUser(String correo);

    public List<GameData> getGamesNotFinishedByUser(String correo);

    public ResultSet getGameById(String gameID);

    public ResultSet getGameByNameAndGame(String gameName, String userId);

    public boolean removeGame(int gameID);

    public void insertGame(String nombre, String correo);
}