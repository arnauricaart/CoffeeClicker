package presentation.controllers;

import business.managers.UserManager;
import presentation.views.RegisterView;

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

        if (user.length() < 6) {
            view.showErrorMessage("Username must be at least 6 characters long.");
            return;
        }

        if (email.isEmpty() || !email.contains("@")) {
            view.showErrorMessage("Invalid email address.");
            return;
        }
        if (model.checkUserExists(user)) {
            view.showErrorMessage("Username already exists.");
            return;
        }

        if (model.checkEmailExists(email)) {
            view.showErrorMessage("Email is already registered.");
            return;
        }


        if (!pass.equals(repass)) {
            view.showErrorMessage("Passwords do not match.");
            return;
        }

        if (pass.length() < 8) {
            view.showErrorMessage("Password must be at least 8 characters long.");
            return;
        }

        if (!pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            view.showErrorMessage("Password must contain uppercase, lowercase, and a number.");
            return;
        }

        boolean success = model.register(user, email, pass);
        if (success) {
            view.showRegisterResultMessage(true);
            view.dispose();
            LoginController loginController = new LoginController();
            loginController.start();
        } else {
            view.showErrorMessage("Registration failed. User may already exist.");
        }
    }

}
