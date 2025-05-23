package presentation.controllers;

import business.businessExceptions.BusinessException;
import business.managers.UserManager;
import business.results.RegistrationResult;
import presentation.views.PopUpView;
import presentation.views.RegisterView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controller class responsible for handling the user registration logic.
 * Interacts with the RegisterView for UI and with the UserManager for data operations.
 */
public class RegisterController {
    /**
     * The registration view interface for user input
     */
    private RegisterView view;
    /**
     * The user manager
     */
    private UserManager model;

    /**
     * Constructs a RegisterController with the given user manager.
     *
     * @param model the user manager used for handling user registration logic
     */
    public RegisterController(UserManager model) {
        this.model = model;
    }

    /**
     * Starts the registration process by initializing the view and its listeners.
     */
    public void start() {
        view = new RegisterView();
        initController();
        view.setVisible(true);
    }

    /**
     * Initializes the listeners for registration actions and login label.
     */
    private void initController() {
        view.setRegisterButtonListener(e -> register());
        view.setLoginLabelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
                LoginController loginController = new LoginController();
            }
        });
    }

    /**
     * Performs input validation and attempts to register the user.
     * Displays error messages for invalid input and success messages when registration is successful.
     */
    private void register() {
        String user = view.getUsername();
        String email = view.getEmail();
        String pass = view.getPassword();
        String repass = view.getRepassword();

        try {
            RegistrationResult result = model.validateAndRegister(user, email, pass, repass);
            if (result.isSuccess()) {
                view.showRegisterResultMessage(true);
                view.dispose();
                LoginController loginController = new LoginController();
            } else {
                view.showErrorMessage(result.getErrorMessage());
            }
        } catch(BusinessException e) {
            new PopUpView(e.getExceptionMessage());
        }
    }

}
