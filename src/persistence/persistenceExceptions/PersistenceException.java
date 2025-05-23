package persistence.persistenceExceptions;

/**
 * Represents all the Persistence Exceptions that may occur when running the code. It is abstract.
 */
public abstract class PersistenceException extends Exception {
    private static String message;

    /**
     * This method allows us to get the Exception message.
     *
     * @return returns the message indicating the exception occurred.
     */
    public String getExceptionMessage() {
        return message;
    }

}
