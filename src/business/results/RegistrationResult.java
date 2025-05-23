package business.results;

/**
 * Represents the result of a user registration operation.
 * Contains information about whether the registration was successful
 * and any error message if it wasn't.
 */
public class RegistrationResult {
    private final boolean success;
    private final String errorMessage;

    /**
     * Constructs a RegistrationResult with the given success status and error message.
     *
     * @param success whether the registration was successful
     * @param errorMessage the error message if registration failed, null otherwise
     */
    public RegistrationResult(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    /**
     * Returns whether the registration was successful.
     *
     * @return true if registration succeeded, false otherwise
     */
    public boolean isSuccess() { 
        return success; 
    }

    /**
     * Returns the error message if registration failed.
     *
     * @return the error message, or null if registration succeeded
     */
    public String getErrorMessage() { 
        return errorMessage; 
    }
} 