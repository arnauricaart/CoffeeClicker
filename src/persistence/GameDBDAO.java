package persistence;

import business.businessExceptions.BusinessException;
import business.entities.Game;
import persistence.persistenceExceptions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements the interface GameDAO.
 */
public class GameDBDAO implements GameDAO{
    /**
     * Constructor of the class
     */
    public GameDBDAO() {

    }

    /**
     * This method gets a list of games finished with their user from the database.
     * @return Returns a List object with the finished games.
     */
    public List<Game> getGamesFinishedForStats() throws FileNotFound, DBGeneralException {// throws StatsNotFound {
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
                //throw new StatsNotFound();

            }
        }

        return games;
    }

    /**
     * This method gets a list of the games finished by a user name or game name from the database.
     * @param userNameSearch Name of the user that will be used in the search.
     * @param gameNameSearch Name of the game tha will be used in the search.
     * @return Returns a List object with the finished games.
     */
    public List<Game> searchGamesFinished(String userNameSearch, String gameNameSearch) throws FileNotFound, DBGeneralException {// throws GameNotFound {
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
                //throw new GameNotFound();
            }
        }

        return games;
    }

    /**
     * This method gets a Game instance of the started game from a user.
     * @param correo String with the User Mail that will be used in the search.
     * @return Returns a Game instance.
     */
    @Override
    public Game getStartedGame(String correo) throws FileNotFound, DBGeneralException { //throws GameNotFound
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
                    rs.getDouble("Cafes"),
                    rs.getString("UltimoAcceso"),
                    rs.getInt("numCoffeeMachine"),
                    rs.getInt("numBarista"),
                    rs.getInt("numCafe"),
                    rs.getInt("numUpgradeCoffeeMachine"),
                    rs.getInt("numUpgradeBarista"),
                    rs.getInt("numUpgradeCafe"),
                    rs.getInt("minDuration")
                );
            }
        } catch (SQLException e) {
            //throw new GameNotFound();
        }
        return null;
    }

    /**
     * This method gets a Game instance with its Game Id.
     * @param gameID Number of the Game Id that will be used in the search.
     * @return Returns a Game instance.
     */
    @Override
    public Game getGameById(int gameID) throws FileNotFound, DBGeneralException {// throws GameNotFound {
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
                    rs.getDouble("Cafes"),
                    rs.getString("UltimoAcceso"),
                    rs.getInt("numCoffeeMachine"),
                    rs.getInt("numBarista"),
                    rs.getInt("numCafe"),
                    rs.getInt("numUpgradeCoffeeMachine"),
                    rs.getInt("numUpgradeBarista"),
                    rs.getInt("numUpgradeCafe"),
                    rs.getInt("minDuration")
                );
            }
        } catch (SQLException e) {
            //throw new GameNotFound();
        }
        return null;
    }

    /**
     * This method gets a Game instance by the Game Name or the User Id.
     * @param gameName String of the Game Name that will be used in the search.
     * @param userId String of the User Id that will be used in the search.
     * @return Returns a Game instance.
     */
    @Override
    public Game getGameByNameAndGame(String gameName, String userId) throws FileNotFound, DBGeneralException {// throws GameNotFound {
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
                    rs.getDouble("Cafes"),
                    rs.getString("UltimoAcceso"),
                    rs.getInt("numCoffeeMachine"),
                    rs.getInt("numBarista"),
                    rs.getInt("numCafe"),
                    rs.getInt("numUpgradeCoffeeMachine"),
                    rs.getInt("numUpgradeBarista"),
                    rs.getInt("numUpgradeCafe"),
                    rs.getInt("minDuration")
                );
            }
        } catch (SQLException e) {
            //throw new GameNotFound();
        }
        return null;
    }

    /**
     * This method makes a register in the database to make a new game with a Game Name and the Users Mail.
     * @param gameName String of the Game Name that will be used in the register to the database.
     * @param correo Stirng of the User Mail that will be used in the register to the database.
     * @return Returns a number with the Game Id generated by the database.
     */
    public int insertGame(String gameName, String correo) throws DBGeneralException {
        try {
            String query = "INSERT INTO partida(Nombre, Cafes, Correo, Terminada, UltimoAcceso, " +
                    "numCoffeeMachine, numBarista, numCafe, " +
                    "numUpgradeCoffeeMachine, numUpgradeBarista, numUpgradeCafe, minDuration) " +
                    "VALUES(?, 0.0, ?, 0, Now(), 0, 0, 0, 0, 0, 0, 0)";
            ArrayList<String> values = new ArrayList<>();
            ArrayList<String> types = new ArrayList<>();

            values.add(gameName);
            types.add("String");
            values.add(correo);
            types.add("String");

            return SQL_CRUD.CUDReturningNextval(query, values, types);
        } catch (PersistenceException e){
            throw new DBGeneralException();
        }
    }

    /**
     * This methos updates the game state.
     * @param game Instance of the game that will be updated.
     */
    public void updateGameState(Game game) throws DBGeneralException {
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

        values.add(String.valueOf(game.getNumCoffees())); types.add("double");
        values.add(String.valueOf(game.getNumCoffeeMachine())); types.add("int");
        values.add(String.valueOf(game.getNumBarista())); types.add("int");
        values.add(String.valueOf(game.getNumCafe())); types.add("int");
        values.add(String.valueOf(game.getNumUpgradeCoffeeMachine())); types.add("int");
        values.add(String.valueOf(game.getNumUpgradeBarista())); types.add("int");
        values.add(String.valueOf(game.getNumUpgradeCafe())); types.add("int");
        values.add(game.hasEnded() ? "1" : "0"); types.add("int");
        values.add(String.valueOf(game.getMinDuration())); types.add("int");
        values.add(String.valueOf(game.getGameID())); types.add("int");

        try {
            SQL_CRUD.CUD(query, values, types);
        } catch (PersistenceException e){
            throw new DBGeneralException();
        }

    }
}

