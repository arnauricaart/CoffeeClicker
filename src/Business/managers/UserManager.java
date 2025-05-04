package Business.managers;

import Business.Entities.User;
import Persistence.UserDAO;
import Persistence.UserDBDAO;

public class UserManager {
    private UserDAO userDAO;
    private User activeUser;


    public UserManager() {
        this.userDAO = new UserDBDAO();
    }

    public void setActiveUser(User user){
        this.activeUser = user;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public boolean register(String username, String email, String password) {
        return userDAO.registerUser(username, email, password);
    }

    public String getCorreoFromLogin(String input, String password) {
        return userDAO.getCorreoFromLogin(input, password);
    }

    public boolean checkUserExists(String username){
        return userDAO.checkUserExists(username);
    }

    public boolean removeUserAndData(String email){
        return userDAO.removeUserAndData(email);
    }

}
