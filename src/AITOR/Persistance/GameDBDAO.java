package Persistance;

import java.sql.ResultSet;
import java.util.ArrayList;
import Persistence.SQL_CRUD;

public class GameDBDAO {
    public GameDBDAO() {

    }

    public ResultSet getGameByUser(String userID){
        String query = "SELECT * FROM partida WHERE Nombre=?";
        ArrayList<String> values = new ArrayList<String>();
        values.add(userID);
        ArrayList<String> tipos = new ArrayList<String>();
        tipos.add("String");
        ResultSet res = SQL_CRUD.Select(query,values,tipos);
        return res;
    }

    public ResultSet getGameById(String gameID){
        String query = "SELECT * FROM partida WHERE IdPartida=?";
        ArrayList<String> values = new ArrayList<String>();
        values.add(gameID);
        ArrayList<String> tipos = new ArrayList<String>();
        tipos.add("int");
        ResultSet res = SQL_CRUD.Select(query,values,tipos);
        return res;
    }

    public ResultSet getGameByNameAndGame(String gameName, String userId){
        String query = "SELECT * FROM partida WHERE NombrePartida=? AND Nombre=?";
        ArrayList<String> values = new ArrayList<String>();
        values.add(gameName);
        values.add(userId);
        ArrayList<String> tipos = new ArrayList<String>();
        tipos.add("String");
        tipos.add("String");
        ResultSet res = SQL_CRUD.Select(query,values,tipos);
        return res;
    }

}
