package Presentation.controllers;

import Business.UserManager;
import Presentation.views.LoginView;
import Presentation.views.RegisterView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterController {
    private RegisterView view;
    private UserManager model;

    public RegisterController(UserManager model) {
        this.model = model;
    }

    public void start() {
        view = new RegisterView();
        initController();
        view.setVisible(true);
    }

    private void initController() {
        view.setRegisterButtonListener(e -> register());
        view.setLoginLabelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                LoginController loginController = new LoginController();
                loginController.start();
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
            LoginController loginController = new LoginController();
            loginController.start();
        }
    }
}
