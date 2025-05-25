package persistence.persistenceExceptions;

/**
 * Represents all the Persistence Exceptions that may occur when running the code. It is abstract.
 */
public abstract class PersistenceException extends Exception {
    /**
     * String that contains the message that will be shown when the exception gets activated.
     */
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
