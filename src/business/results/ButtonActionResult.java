package business.results;

/**
 * Represents the result of a button action in the game.
 * Contains information about whether the action was successful,
 * any message to display, and whether an error sound should be played.
 */
public class ButtonActionResult {
    private final boolean success;
    private final String message;
    private final boolean shouldPlayErrorSound;

    /**
     * Constructs a ButtonActionResult with the given parameters.
     *
     * @param success whether the action was successful
     * @param message the message to display to the user, if any
     * @param shouldPlayErrorSound whether an error sound should be played
     */
    public ButtonActionResult(boolean success, String message, boolean shouldPlayErrorSound) {
        this.success = success;
        this.message = message;
        this.shouldPlayErrorSound = shouldPlayErrorSound;
    }

    /**
     * Returns whether the action was successful.
     *
     * @return true if the action succeeded, false otherwise
     */
    public boolean isSuccess() { 
        return success; 
    }

    /**
     * Returns the message to display to the user.
     *
     * @return the message, or null if no message should be displayed
     */
    public String getMessage() { 
        return message; 
    }

    /**
     * Returns whether an error sound should be played.
     *
     * @return true if an error sound should be played, false otherwise
     */
    public boolean shouldPlayErrorSound() { 
        return shouldPlayErrorSound; 
    }
} 