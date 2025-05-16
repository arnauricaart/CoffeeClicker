package persistence.persistenceExceptions;

/**
 * Represents the persistence exception when a user is not found.
 */
public class UserNotFound extends PersistenceException {
    private static String message = "The user does not exist.";

    /**
     * Constructs an instance of FileNotFound.
     */
    public UserNotFound() {}
}
