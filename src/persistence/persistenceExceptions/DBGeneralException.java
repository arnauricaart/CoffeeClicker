package persistence.persistenceExceptions;
/**
 * Represents the persistence exception when there is an error related to the DB.
 */
public class DBGeneralException extends PersistenceException {
  /**
   * Variable containing the message of the exception.
   */
  private static String message;
  /**
   * Constructs an instance of DBGeneralException.
   *
   * @param message the message regarding the DB exception
   */
  public DBGeneralException(String message) {
    this.message = message;
  }
}
