package business.managers;

import business.businessExceptions.BusinessException;
import business.entities.User;
import persistence.UserDAO;
import persistence.UserDBDAO;
import persistence.persistenceExceptions.DBGeneralException;
import persistence.persistenceExceptions.FileNotFound;
import persistence.persistenceExceptions.PersistenceException;

/**
 * Manages user-related operations in the business domain.
 * This class provides methods for user registration, authentication,
 * and data management using a persistence layer.
 */
public class UserManager {
    /**
     * The data access object responsible for user-related persistence operations.
     */
    private UserDAO userDAO;
    /**
     * The currently active user in the session.
     */
    private User activeUser;

    /**
     * Constructs a UserManager and initializes the userDAO with a UserDBDAO implementation.
     */
    public UserManager() throws business.businessExceptions.DBGeneralException {
        try {
            this.userDAO = new UserDBDAO();
        }catch(PersistenceException e){
            throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }
    }

    /**
     * Sets the currently active user.
     *
     * @param user the user to set as active
     */
    public void setActiveUser(User user){
        this.activeUser = user;
    }

    /**
     * Retrieves the currently active user.
     *
     * @return the active user
     */
    public User getActiveUser() {
        return activeUser;
    }

    /**
     * Registers a new user with the given username, email, and password.
     *
     * @param username the username for the new user
     * @param email    the email address for the new user
     * @param password the password for the new user
     * @return true if registration was successful, false otherwise
     */
    public boolean register(String username, String email, String password) throws BusinessException {
        try{
            return userDAO.registerUser(username, email, password);
        }catch (DBGeneralException e){
            throw new business.businessExceptions.DBGeneralException(e.getMessage());
        }catch(FileNotFound e){
            throw new business.businessExceptions.FileNotFound(e.getMessage());
        }
    }

    /**
     * Retrieves the email associated with the given login input and password.
     *
     * @param input    the username or email provided during login
     * @param password the password provided during login
     * @return the email of the user if login is successful, null otherwise
     */
    public String getCorreoFromLogin(String input, String password) throws BusinessException {
        try{
            return userDAO.getCorreoFromLogin(input, password);
        }catch (DBGeneralException e){
            throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }catch(FileNotFound e){
            throw new business.businessExceptions.FileNotFound(e.getExceptionMessage());
        }
    }

    /**
     * Checks whether a user with the given username exists in the database.
     *
     * @param username the username to check
     * @return true if the user exists, false otherwise
     */
    public boolean checkUserExists(String username) throws BusinessException{
        try {
            return userDAO.checkUserExists(username);
        }catch (DBGeneralException e){
            throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }catch(FileNotFound e){
            throw new business.businessExceptions.FileNotFound(e.getExceptionMessage());
        }
    }

    /**
     * Removes a user and all related data from the database using their email.
     *
     * @param email the email of the user to remove
     * @return true if the user was successfully removed, false otherwise
     */
    public boolean removeUserAndData(String email) throws BusinessException{
        try {
            return userDAO.removeUserAndData(email);
        }catch(PersistenceException e){
            throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }
    }

    /**
     * Checks whether a user with the given email exists in the database.
     *
     * @param email the email to check
     * @return true if the email exists, false otherwise
     */
    public boolean checkEmailExists(String email) throws BusinessException{
        try{
            return userDAO.checkEmailExists(email);
        }catch (DBGeneralException e){
            throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }catch(FileNotFound e){
            throw new business.businessExceptions.FileNotFound(e.getExceptionMessage());
        }
    }
}
