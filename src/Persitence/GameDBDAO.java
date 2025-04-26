package Persitence;

import Business.Game;
import Business.GameData;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GameDBDAO {
    public GameDBDAO() {

    }
    public List<GameData> getGamesFinishedByUser(String correo){
        List<GameData> games = new ArrayList<>();

        String query = "SELECT * FROM partida  WHERE correo =? AND Terminada = 1";
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<String> tipos = new ArrayList<String>();

        values.add(correo);
        tipos.add("String");

        ResultSet res = SQL_CRUD.Select(query,values,tipos);
        while(true){
            try {
                if (!res.next()) break;
                Timestamp ultimoAcceso = res.getTimestamp("ultimoAcceso");
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formatedDate = formato.format(ultimoAcceso);
                games.add(new GameData(res.getInt("IdPartida"), res.getString("Nombre"), res.getInt("Cafes"), formatedDate));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return games;
    }

    public List<GameData> getGamesNotFinishedByUser(String correo){
        String query = "SELECT * FROM partida  WHERE correo =? AND Terminada = 0";
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<String> tipos = new ArrayList<String>();

        values.add(correo);
        tipos.add("String");

        ResultSet res = SQL_CRUD.Select(query,values,tipos);
        List<GameData> games = new ArrayList<>();
        while(true){
            try {
                if (!res.next()) break;
                Timestamp ultimoAcceso = res.getTimestamp("ultimoAcceso");
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formatedDate = formato.format(ultimoAcceso);
                games.add(new GameData(res.getInt("IdPartida"), res.getString("Nombre"), res.getInt("Cafes"), formatedDate));
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


    public void insertGame(String nombre, String correo) {
        String query = "INSERT INTO partida(Nombre, Cafes, Correo, Terminada, UltimoAcceso) VALUES(?,0,?,0,Now())";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        values.add(nombre); types.add("String");
        values.add(correo); types.add("String");

        int result = SQL_CRUD.CUD(query, values, types);
    }
}
