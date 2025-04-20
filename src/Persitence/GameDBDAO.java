package Persitence;

import Business.GameData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDBDAO {
    public GameDBDAO() {

    }

    public List<GameData> getGamesNotFinishedByUser(String userID){
        String query = "SELECT p.* FROM partida p JOIN users u ON p.correo = u.correo WHERE (u.correo =? OR u.nombre =?) AND Terminada = 0";
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<String> tipos = new ArrayList<String>();

        values.add(userID);
        tipos.add("String");

        values.add(userID);
        tipos.add("String");

        ResultSet res = SQL_CRUD.Select(query,values,tipos);
        List<GameData> games = new ArrayList<>();
        while(true){
            try {
                if (!res.next()) break;
                games.add(new GameData(res.getInt("IdPartida"), res.getInt("Cafes")));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return games;
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
    public boolean removeGame(int gameID){
        System.out.println("Remove user");
        String queryPartidaGenerador = "DELETE FROM partida_generador WHERE IdPartida =?";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();


        values.add(Integer.toString(gameID)); types.add("int");


        int result = SQL_CRUD.CUD(queryPartidaGenerador, values, types);

        String queryStats = "DELETE FROM stats WHERE IdPartida =?";
        values = new ArrayList<>();
        types = new ArrayList<>();

        values.add(Integer.toString(gameID)); types.add("int");

        result = SQL_CRUD.CUD(queryStats, values, types);

        String queryPartida = "DELETE FROM partida WHERE idPartida=?";
        values = new ArrayList<>();
        types = new ArrayList<>();

        values.add(Integer.toString(gameID)); types.add("int");

        result = SQL_CRUD.CUD(queryPartida, values, types);
        return result > 0;
    }


}
