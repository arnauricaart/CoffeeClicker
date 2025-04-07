package controllers;

import model.UserModel;
import views.LoginView;
import views.RegisterView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginController {
    private LoginView view;
    private UserModel model;

    public LoginController(LoginView view, UserModel model) {
        this.view = view;
        this.model = model;
        initController();
    }

    private void initController() {
        view.setLoginButtonListener(e -> login());
        view.setRegisterLabelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                RegisterView registerView = new RegisterView();
                new controllers.RegisterController(registerView, model);
                registerView.setVisible(true);
            }
        });
    }

    private void login() {
        String input = view.getUsername(); // nom o correu
        String password = view.getPassword();

        boolean success = model.validateLogin(input, password);
        view.showLoginResultMessage(success);
    }
}
