package presentation.controllers;

import business.managers.UserManager;
import presentation.views.LoginView;
import presentation.views.MenuGUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controller responsible for handling login logic.
 * Connects the LoginView with the UserManager, processes user input,
 * and transitions to either the menu or registration view based on user actions.
 */
public class LoginController {
    /**
     * The view handling the login UI
     */
    private LoginView view;
    /**
     * The user manager responsible for authenticating users
     */
    private UserManager userManager;
    /**
     * Constructs a new LoginController and initializes the UserManager.
     */
    public LoginController() {
        this.userManager = new UserManager();
    }

    /**
     * Starts the login process by initializing the view and controller logic.
     */
    public void start() {
        view = new LoginView();
        initController();
        view.setVisible(true);
    }

    /**
     * Initializes listeners for the login view's components.
     * Handles login button and register label events.
     */
    private void initController() {
        view.setLoginButtonListener(e -> login());
        view.setRegisterLabelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                new RegisterController(userManager).start();
            }
        });
    }

    /**
     * Attempts to authenticate the user using the provided username/email and password.
     * If successful, opens the menu view; otherwise, shows an error message.
     */
    private void login() {
        String usernameOrEmail = view.getUsername();
        String password = view.getPassword();

        String correo = userManager.getCorreoFromLogin(usernameOrEmail, password);
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
