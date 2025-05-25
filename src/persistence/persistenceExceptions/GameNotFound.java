package persistence.persistenceExceptions;

/**
 * Represents the persistence exception when a game is not found.
 */
public class GameNotFound extends PersistenceException {
    /**
     * String that contains the message that will be shown when the exception gets activated.
     */
    private static String message = "The game does not exist.";

    /**
     * Constructs an instance of FileNotFound.
     */
    public GameNotFound() {}

    /**
     * This method allows us to get the Exception message.
     *
     * @return returns the message indicating the exception occurred.
     */
    public String getExceptionMessage() {
        return message;
    }
}
