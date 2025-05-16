package persistence.persistenceExceptions;

/**
 * Represents the persistence exception when a game is not found.
 */
public class GameNotFound extends PersistenceException {
    private static String message = "The game does not exist.";

    /**
     * Constructs an instance of FileNotFound.
     */
    public GameNotFound() {}
}
