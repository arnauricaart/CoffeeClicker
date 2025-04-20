package Presentation.controllers;

import Business.UserManager;
import Presentation.views.LoginView;
import Presentation.views.MenuGUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginController {
    private LoginView view;
    private UserManager model;

    public LoginController() {
        this.model = new UserManager();
    }

    public void start() {
        view = new LoginView();
        initController();
        view.setVisible(true);
    }

    private void initController() {
        view.setLoginButtonListener(e -> login());
        view.setRegisterLabelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                new RegisterController(model).start();
            }
        });
    }

    private void login() {
        String usernameOrEmail = view.getUsername();
        String password = view.getPassword();

        String correo = model.getCorreoFromLogin(usernameOrEmail, password);
        if(correo == null) {
            view.showLoginErrorMessage();
        }

        if (correo != null) {
            view.dispose();
            MenuGUI menuView = new MenuGUI();
            MenuController menuController = new MenuController(menuView, correo);
            menuController.initController();
        }
    }
}
