package business.businessExceptions;
/**
 * Represents the business exception when there is an error related to the DB.
 */
public class DBGeneralException extends BusinessException {
    /**
     * Variable containing the message of the exception.
     */
    private static String message;

    /**
     * Constructs an instance of DBGeneralException.
     * @param message The message that the original persitence exception had.
     */
    public DBGeneralException(String message) {this.message = message;}

    /**
     * This method allows us to get the Exception message.
     *
     * @return returns the message indicating the exception occurred.
     */
    public String getExceptionMessage() {
        return message;
    }
}
