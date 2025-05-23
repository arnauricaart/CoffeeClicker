package persistence.persistenceExceptions;
/**
 * Represents the persistence exception when there is an error related to the DB.
 */
public class DBGeneralException extends PersistenceException {
  /**
   * Variable containing the message of the exception.
   */
  private static String message = "There was an error connecting to the database";
  /**
   * Constructs an instance of DBGeneralException.
   */
  public DBGeneralException() {
  }

  /**
   * This method allows us to get the Exception message.
   *
   * @return returns the message indicating the exception occurred.
   */
  public String getExceptionMessage() {
    return message;
  }
}

