package persistence;

import persistence.persistenceExceptions.DBGeneralException;
import persistence.persistenceExceptions.FileNotFound;

/**
 * Interface that will be used to retrive the user table information from the database.
 */
public interface UserDAO {

    /**
     * This method will implement a way to remove an user and all of its information.
     * @param email String with the User Mail.
     * @return Returns a boolean that will tell if everything went okey.
     * @throws DBGeneralException if a general database error occurs
     */
    public boolean removeUserAndData(String email) throws DBGeneralException;

    /**
     * This method will implement a way to register a new User.
     * @param username String with the User Name that will be used in the insert query.
     * @param email String with the User Mail.
     * @param password String with the User Password.
     * @return Returns a boolean that will tell if everything went okey.
     * @throws FileNotFound if the data source file is not found
     * @throws DBGeneralException if a general database error occurs
     */
    public boolean registerUser(String username, String email, String password) throws FileNotFound, DBGeneralException;

    /**
     * This method will implment a way to check if a user exists searching it by the User Name.
     * @param username String with the User Name that will be used in the search.
     * @return Returns a boolean that will tell if it exists or not.
     * @throws FileNotFound if the data source file is not found
     * @throws DBGeneralException if a general database error occurs
     */
    public boolean checkUserExists(String username) throws FileNotFound, DBGeneralException;

    /**
     * This method will implement a way to get the User Mail from the login.
     * @param userOrEmail String with the User Name or User Mail.
     * @param password String with the User Password.
     * @return Returns a String with the User Mail.
     * @throws FileNotFound if the data source file is not found
     * @throws DBGeneralException if a general database error occurs
     */
    public String getCorreoFromLogin(String userOrEmail, String password) throws FileNotFound, DBGeneralException;

    /**
     * This method will implement a way to check if a User Mail exists.
     * @param email String with the User Mail.
     * @return Returns a Boolean that will tell if the User Mail exists or not.
     * @throws FileNotFound if the data source file is not found
     * @throws DBGeneralException if a general database error occurs
     */
    boolean checkEmailExists(String email) throws FileNotFound, DBGeneralException;
}
