package business.managers;

import business.businessExceptions.BusinessException;
import business.entities.Game;
//import com.sun.tools.jdeps.Dependencies;
import persistence.GameDAO;
import persistence.GameDBDAO;
import persistence.persistenceExceptions.DBGeneralException;
import persistence.persistenceExceptions.FileNotFound;
import persistence.persistenceExceptions.PersistenceException;

import java.util.List;

/**
 * Manages game-related operations in the business domain.
 * This class interacts with the data access layer to manage game sessions, such as retrieving,
 * inserting, and searching for games.
 */
public class PartidaManager {
    /**
     * The data access object responsible for game-related persistence operations.
     */
    private GameDAO gameDAO;

    /**
     * Constructs a PartidaManager and initializes the gameDAO with a GameDBDAO implementation.
     */
    public PartidaManager() {
        this.gameDAO = new GameDBDAO();
    }

    /**
     * Retrieves a list of games that have finished.
     *
     * @return a list of finished {@link Game} instances
     */
    public List<Game> getGamesFinished() throws BusinessException {
        try {
            return gameDAO.getGamesFinishedForStats();
        }catch (DBGeneralException e){
            throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }catch(FileNotFound e){
            throw new business.businessExceptions.FileNotFound(e.getExceptionMessage());
        }
    }

    /**
     * Searches for finished games based on username and/or game name.
     *
     * @param userSearch the username to filter the search
     * @param gameSearch the game name to filter the search
     * @return a list of {@link Game} objects matching the search criteria
     */
    public List<Game> searchGamesFinished(String userSearch, String gameSearch) throws BusinessException {
        try{
            return gameDAO.searchGamesFinished(userSearch, gameSearch);
        }catch (DBGeneralException e){
            throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }catch(FileNotFound e){
            throw new business.businessExceptions.FileNotFound(e.getExceptionMessage());
        }
    }

    /**
     * Inserts a new game into the database.
     *
     * @param userName the username associated with the new game
     * @param correo the email of the user
     * @return the ID of the newly created game
     */
    public int insertGame(String userName, String correo) throws business.businessExceptions.DBGeneralException {
        try {
            return gameDAO.insertGame(userName, correo);
        }catch(PersistenceException e){
               throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }
    }

    /**
     * Retrieves the game currently started for a given user.
     *
     * @param userName the username of the player
     * @return the {@link Game} that is currently started for the user, or null if none is found
     */
    public Game getStartedGame(String userName) throws BusinessException {
        try {
            return gameDAO.getStartedGame(userName);
        }catch (DBGeneralException e){
            throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }catch(FileNotFound e){
            throw new business.businessExceptions.FileNotFound(e.getExceptionMessage());
        }
    }

    /**
     * Retrieves a game by its ID.
     *
     * @param id the ID of the game
     * @return the {@link Game} with the specified ID, or null if not found
     */
    public Game getGameById(int id) throws BusinessException{
        try {
            return gameDAO.getGameById(id);
        }catch (DBGeneralException e){
            throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }catch(FileNotFound e){
            throw new business.businessExceptions.FileNotFound(e.getExceptionMessage());
        }
    }
}