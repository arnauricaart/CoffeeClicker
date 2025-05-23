package persistence.persistenceExceptions;

/**
 * Represents the persistence exception when stats are not found.
 */
public class StatsNotFound extends PersistenceException {
  private static String message = "The stats do not exist.";

  /**
   * Constructs an instance of FileNotFound.
   */
  public StatsNotFound() {}

  /**
   * This method allows us to get the Exception message.
   *
   * @return returns the message indicating the exception occurred.
   */
  public String getExceptionMessage() {
    return message;
  }
}
