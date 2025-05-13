package persistence;

public interface UserDAO {

    public boolean removeUserAndData(String email);

    public boolean registerUser(String username, String email, String password);

    public boolean checkUserExists(String username);

    public String getCorreoFromLogin(String userOrEmail, String password);

    boolean checkEmailExists(String email);
}
