package Presentation.controllers;
import Persitence.UserDAO;
import Presentation.views.*;

import java.util.List;


public class MenuController {
    private MenuGUI menuView;
    private NewGameView newGameView;
    private ContinueGameView continueGameView;
    private RemoveAccountView removeAccountView;
    private UserDAO userDAO;
    private String usernameOrEmail;


    public MenuController(MenuGUI menuView, String usernameOrEmail) {
        this.menuView = menuView;
        this.usernameOrEmail = usernameOrEmail;
    }

    public void initController() {
        menuView.setNewGameButtonListener(e -> startNewGame());
        menuView.setStatisticsButtonListener(e -> showStatistics());
        menuView.setLogoutButtonListener(e -> logout());
        menuView.setDeleteAccountButtonListener(e -> deleteAccount());
        menuView.setContinueGameButtonListener(e -> selectGameToContinue());
        userDAO = new UserDAO();
    }

    private void startNewGame() {
        // Lógica para iniciar o continuar el juego
        newGameView = new NewGameView();
        newGameView.setNewGameButtonListener(e -> newGame());
        newGameView.setVisible(true);
    }

    private void showStatistics() {
        // Lógica para mostrar las estadísticas del juego
    }

    private void logout() {
        // Lógica para cerrar sesión

            // tancar la finestra actual
            menuView.dispose();

            // obrir la pantalla de login

            LoginController loginController = new LoginController();
            loginController.start();

    }

    private void deleteAccount() {
        // Lógica para eliminar la cuenta
        removeAccountView = new RemoveAccountView();
        removeAccountView.setRemoveAccButtonListener(e -> removeAccountFromDatabase());
        removeAccountView.setCancelButtonListener(e -> removeAccountView.dispose());
        removeAccountView.setVisible(true);

    }

    private void selectGameToContinue() {
        continueGameView = new ContinueGameView(List.of(new GameData(1, 3), new GameData(2, 4)));
        //continueGameView.setNewGameButtonListener(e -> continueGame());
        continueGameView.setVisible(true);
    }
    private void newGame() {
        String name = newGameView.getNewGameName();
        System.out.println("Name: " + name);
        //TODO cridar al joc
    }
    private void removeAccountFromDatabase() {
        if(userDAO.validateLogin(this.usernameOrEmail, removeAccountView.getPassword())){
            userDAO.removeUserAndData(this.usernameOrEmail);
            removeAccountView.showRemoveUserMessage(true);
            removeAccountView.dispose();
            menuView.dispose();
            LoginController loginController = new LoginController();
            loginController.start();
        } else{
            removeAccountView.showRemoveUserMessage(false);
        }


        return;
    }
}