package presentation.presentationExceptions;

import persistence.persistenceExceptions.PersistenceException;

/**
 * This class makes an exception in the case the user tries to make a game without writting a name.
 */
public class EmptyNameException extends PersistenceException {
    /**
     * String with the message that will be shown when the exception gets activated.
     */
    private static String message = "You need to enter a name for the Game!";

    /**
     * Method that will be called when the exception is needed.
     */
    public EmptyNameException() {}
}

