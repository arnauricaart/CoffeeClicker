package persistence.persistenceExceptions.business.entities;

/**
 * Represents a user in the business domain.
 * This class stores information about a user's name, email, password, and associated game IDs,
 * and provides methods to access and modify this information.
 */
public class User {
    /**
     * The name of the user.
     */
    private String name;
    /**
     * The email address of the user.
     */
    private String email;
    /**
     * The password of the user.
     */
    private String password;
    /**
     * An array of game IDs associated with the user.
     */
    private int[] gamesID;

    /**
     * Constructs a User with the specified name, email, and password.
     *
     * @param name     the name of the user
     * @param email    the email address of the user
     * @param password the password of the user
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the new email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the array of game IDs associated with the user.
     *
     * @return the user's game IDs
     */
    public int[] getGamesID() {
        return gamesID;
    }

    /**
     * Sets the array of game IDs associated with the user.
     *
     * @param gamesID the new array of game IDs
     */
    public void setGamesID(int[] gamesID) {
        this.gamesID = gamesID;
    }
}