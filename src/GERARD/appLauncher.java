import controllers.LoginController;
import model.UserModel;
import views.LoginView;

import javax.swing.*;

public class appLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserModel model = new UserModel();
            LoginView loginView = new LoginView();
            new LoginController(loginView, model);
            loginView.setVisible(true);
        });
    }
}
