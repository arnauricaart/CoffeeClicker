package persistence.persistenceExceptions;

/**
 * Represents the persistence exception when a file is not found.
 */
public class FileNotFound extends PersistenceException {
  /**
   * String that contains the message that will be shown when the exception gets activated.
   */
  private static String message = "The file does not exist.";

  /**
   * Constructs an instance of FileNotFound.
   */
  public FileNotFound() {}

  /**
   * This method allows us to get the Exception message.
   *
   * @return returns the message indicating the exception occurred.
   */
  public String getExceptionMessage() {
    return message;
  }
}