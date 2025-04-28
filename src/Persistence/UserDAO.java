package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {

    public boolean removeUserAndData(String email);

    public boolean registerUser(String username, String email, String password);

    public boolean checkUserExists(String username);

    public String getCorreoFromLogin(String userOrEmail, String password);
}
