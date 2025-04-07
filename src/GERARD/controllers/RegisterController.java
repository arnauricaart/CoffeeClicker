package controllers;

import model.UserModel;
import views.LoginView;
import views.RegisterView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterController {
    private RegisterView view;
    private UserModel model;

    public RegisterController(RegisterView view, UserModel model) {
        this.view = view;
        this.model = model;
        initController();
    }

    private void initController() {
        view.setRegisterButtonListener(e -> register());
        view.setLoginLabelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                LoginView loginView = new LoginView();
                new controllers.LoginController(loginView, model);
                loginView.setVisible(true);
            }
        });
    }

    private void register() {
        String user = view.getUsername();
        String email = view.getEmail();
        String pass = view.getPassword();
        String repass = view.getRepassword();

        boolean valid = user.length() >= 6 && pass.equals(repass) && !email.isEmpty();

        boolean success = valid && model.register(user, email, pass);
        view.showRegisterResultMessage(success);

        if (success) {
            view.dispose();
            LoginView loginView = new LoginView();
            new controllers.LoginController(loginView, model);
            loginView.setVisible(true);
        }
    }
}
