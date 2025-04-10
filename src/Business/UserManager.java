package Business;

import Persitence.UserDAO;

public class UserManager {
    private UserDAO userDAO;

    public UserManager() {
        this.userDAO = new UserDAO();
    }

    public boolean register(String username, String email, String password) {
        return userDAO.registerUser(username, email, password);
    }

    public boolean validateLogin(String input, String password) {
        return userDAO.validateLogin(input, password);
    }
}
