package persistence;

import business.entities.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GameDBDAO implements GameDAO{
    public GameDBDAO() {

    }

    public List<Game> getGamesFinishedForStats(){
        List<Game> games = new ArrayList<>();

        String query = "SELECT p.*, u.Nombre as UserName FROM partida p JOIN users u ON p.Correo = u.Correo WHERE p.Terminada = 1";
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<String> tipos = new ArrayList<String>();

        ResultSet res = SQL_CRUD.Select(query,values,tipos);
        while(true){
            try {
                if (!res.next()) break;
                Timestamp ultimoAcceso = res.getTimestamp("ultimoAcceso");
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String formatedDate = formato.format(ultimoAcceso);
                games.add(new Game(res.getInt("IdPartida"), res.getString("UserName"), res.getString("Nombre"), res.getInt("Cafes"), formatedDate));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return games;
    }

    public List<Game> searchGamesFinished(String userNameSearch, String gameNameSearch) {
        List<Game> games = new ArrayList<>();

        String query = "SELECT p.*, u.Nombre as UserName FROM partida p " +
                      "JOIN users u ON p.Correo = u.Correo " +
                      "WHERE p.Terminada = 1 " +
                      "AND (u.Nombre LIKE ? OR ? IS NULL) " +
                      "AND (p.Nombre LIKE ? OR ? IS NULL)";
                      
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<String> tipos = new ArrayList<String>();

        values.add(userNameSearch == null || userNameSearch.isEmpty() ? null : "%" + userNameSearch + "%");
        values.add(userNameSearch == null || userNameSearch.isEmpty() ? null : "%" + userNameSearch + "%");
        values.add(gameNameSearch == null || gameNameSearch.isEmpty() ? null : "%" + gameNameSearch + "%");
        values.add(gameNameSearch == null || gameNameSearch.isEmpty() ? null : "%" + gameNameSearch + "%");
        
        tipos.add("String");
        tipos.add("String");
        tipos.add("String");
        tipos.add("String");

        ResultSet res = SQL_CRUD.Select(query, values, tipos);
        while(true) {
            try {
                if (!res.next()) break;
                Timestamp ultimoAcceso = res.getTimestamp("ultimoAcceso");
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String formatedDate = formato.format(ultimoAcceso);
                games.add(new Game(res.getInt("IdPartida"), res.getString("UserName"), res.getString("Nombre"), res.getInt("Cafes"), formatedDate));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return games;
    }

    @Override
    public Game getStartedGame(String correo) {
        String query = "SELECT * FROM partida WHERE Correo = ? AND Terminada = 0";
        ArrayList<String> values = new ArrayList<String>();
        values.add(correo);
        ArrayList<String> tipos = new ArrayList<String>();
        tipos.add("String");

        ResultSet rs = SQL_CRUD.Select(query, values, tipos);

        try {
            if(rs.next()) {
                return new Game(
                    rs.getInt("IdPartida"),
                    rs.getString("Nombre"),
                    rs.getInt("Cafes"),
                    rs.getString("UltimoAcceso"),
                    rs.getInt("numCoffeeMachine"),
                    rs.getInt("numBarista"),
                    rs.getDouble("numCafe"),
                    rs.getInt("numUpgradeCoffeeMachine"),
                    rs.getInt("numUpgradeBarista"),
                    rs.getInt("numUpgradeCafe"),
                    rs.getInt("minDuration")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Game getGameById(int gameID) {
        String query = "SELECT * FROM partida WHERE IdPartida=?";
        ArrayList<String> values = new ArrayList<String>();
        values.add(String.valueOf(gameID));
        ArrayList<String> tipos = new ArrayList<String>();
        tipos.add("int");

        ResultSet rs = SQL_CRUD.Select(query, values, tipos);

        try {
            if(rs.next()) {
                return new Game(
                    rs.getInt("IdPartida"),
                    rs.getString("Nombre"),
                    rs.getInt("Cafes"),
                    rs.getString("UltimoAcceso"),
                    rs.getInt("numCoffeeMachine"),
                    rs.getInt("numBarista"),
                    rs.getDouble("numCafe"),
                    rs.getInt("numUpgradeCoffeeMachine"),
                    rs.getInt("numUpgradeBarista"),
                    rs.getInt("numUpgradeCafe"),
                    rs.getInt("minDuration")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Game getGameByNameAndGame(String gameName, String userId) {
        String query = "SELECT * FROM partida WHERE NombrePartida=? AND Nombre=?";
        ArrayList<String> values = new ArrayList<String>();
        values.add(gameName);
        values.add(userId);
        ArrayList<String> tipos = new ArrayList<String>();
        tipos.add("String");
        tipos.add("String");

        ResultSet rs = SQL_CRUD.Select(query, values, tipos);
        try {
            if(rs.next()) {
                return new Game(
                    rs.getInt("IdPartida"),
                    rs.getString("Nombre"),
                    rs.getInt("Cafes"),
                    rs.getString("UltimoAcceso"),
                    rs.getInt("numCoffeeMachine"),
                    rs.getInt("numBarista"),
                    rs.getDouble("numCafe"),
                    rs.getInt("numUpgradeCoffeeMachine"),
                    rs.getInt("numUpgradeBarista"),
                    rs.getInt("numUpgradeCafe"),
                    rs.getInt("minDuration")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retorna el ID de la partida creada
    public int insertGame(String gameName, String correo) {
        String query = "INSERT INTO partida(Nombre, Cafes, Correo, Terminada, UltimoAcceso, " +
                      "numCoffeeMachine, numBarista, numCafe, " +
                      "numUpgradeCoffeeMachine, numUpgradeBarista, numUpgradeCafe, minDuration) " +
                      "VALUES(?, 0, ?, 0, Now(), 0, 0, 0.0, 0, 0, 0, 0)";
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        values.add(gameName); types.add("String");
        values.add(correo); types.add("String");

        return SQL_CRUD.CUDReturningNextval(query, values, types);
    }

    public void updateGameState(Game game) {
        String query = "UPDATE partida SET " +
                "Cafes = ?, " +
                "numCoffeeMachine = ?, " +
                "numBarista = ?, " +
                "numCafe = ?, " +
                "numUpgradeCoffeeMachine = ?, " +
                "numUpgradeBarista = ?, " +
                "numUpgradeCafe = ?, " +
                "UltimoAcceso = Now(), " +
                "Terminada = ?, " +
                "minDuration = ? " +
                "WHERE IdPartida = ?";

        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();

        values.add(String.valueOf(game.getNumCoffees())); types.add("int");
        values.add(String.valueOf(game.getNumCoffeeMachine())); types.add("int");
        values.add(String.valueOf(game.getNumBarista())); types.add("int");
        values.add(String.valueOf(game.getNumCafe())); types.add("double");
        values.add(String.valueOf(game.getNumUpgradeCoffeeMachine())); types.add("int");
        values.add(String.valueOf(game.getNumUpgradeBarista())); types.add("int");
        values.add(String.valueOf(game.getNumUpgradeCafe())); types.add("int");
        values.add(game.hasEnded() ? "1" : "0"); types.add("int");
        values.add(String.valueOf(game.getMinDuration())); types.add("int");
        values.add(String.valueOf(game.getGameID())); types.add("int");

        SQL_CRUD.CUD(query, values, types);

    }
}

