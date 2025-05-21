package presentation.presentationExceptions;

import persistence.persistenceExceptions.PersistenceException;

public class EmptyNameException extends PersistenceException {

    private static String message = "You need to enter a name for the Game!";

    public EmptyNameException() {}
}

