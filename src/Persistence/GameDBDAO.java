package Persistence;

import Business.Entities.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GameDBDAO implements GameDAO{
    public GameDBDAO() {

    }

    public List<Game> getGamesFinishedByUser(String correo){
        List<Game> games = new ArrayList<>();

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
                games.add(new Game(res.getInt("IdPartida"), res.getString("Nombre"), res.getInt("Cafes"), formatedDate));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return games;
    }



    public Game getGameById(String gameID){
        String query = "SELECT * FROM partida WHERE IdPartida=?";
        ArrayList<String> values = new ArrayList<String>();
        values.add(gameID);
        ArrayList<String> tipos = new ArrayList<String>();
        tipos.add("int");

        ResultSet rs = SQL_CRUD.Select(query,values,tipos);

        try {
            if(rs.next()){
                return new Game(rs.getInt("IdPartida"), rs.getString("Nombre"), rs.getInt("Cafes"), rs.getString("UltimoAcceso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Game getGameByNameAndGame(String gameName, String userId){
        String query = "SELECT * FROM partida WHERE NombrePartida=? AND Nombre=?";
        ArrayList<String> values = new ArrayList<String>();
        values.add(gameName);
        values.add(userId);
        ArrayList<String> tipos = new ArrayList<String>();
        tipos.add("String");
        tipos.add("String");

        ResultSet rs = SQL_CRUD.Select(query,values,tipos);
        try {
            if(rs.next()){
                return new Game(rs.getInt("IdPartida"), rs.getString("Nombre"), rs.getInt("Cafes"), rs.getString("UltimoAcceso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Retorna el ID de la partida creada
    public int insertGame(String gameName, String correo) {
        String query = "INSERT INTO partida(Nombre, Cafes, Correo, Terminada, UltimoAcceso) VALUES(?,0,?,0,Now())";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        values.add(gameName); types.add("String");
        values.add(correo); types.add("String");

        int result = SQL_CRUD.CUDReturningNextval(query, values, types);
        return result;
    }
}
