package business.businessExceptions;

/**
 * Represents the business exception when a file is not found.
 */
public class FileNotFound extends BusinessException {
    private static String message;

    /**
     * Constructs an instance of FileNotFound.
     * @param message The message that the original persitence exception had.
     */
    public FileNotFound(String message) {
        this.message = message;
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
