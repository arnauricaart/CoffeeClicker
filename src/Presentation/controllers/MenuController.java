package Presentation.controllers;
import Business.Entities.Game;
import Persistence.*;
import Presentation.views.*;

import java.util.List;


public class MenuController {
    private MenuGUI menuView;
    private NewGameView newGameView;
    private ShowGamesView showGamesView;
    private RemoveAccountView removeAccountView;
    private UserDBDAO userDAO;
    private GameDBDAO gameDBDAO;
    private StatsDBDAO statsDBDAO;
    private String correo;


    public MenuController(MenuGUI menuView, String correo) {
        this.menuView = menuView;
        this.correo = correo;
    }

    public void initController() {
        menuView.setNewGameButtonListener(e -> startNewGame());
        menuView.setStatisticsButtonListener(e -> selectGameToShowStats());
        menuView.setLogoutButtonListener(e -> logout());
        menuView.setDeleteAccountButtonListener(e -> deleteAccount());
        userDAO = new UserDBDAO();
        gameDBDAO = new GameDBDAO();
        statsDBDAO = new StatsDBDAO();
    }

    private void startNewGame() {
        newGameView = new NewGameView();
        newGameView.setNewGameButtonListener(e -> newGame());
        newGameView.setCancelButtonListener(e -> newGameView.dispose());
        newGameView.setVisible(true);
    }

    private void selectGameToShowStats() {
        showGamesView = new ShowGamesView(gameDBDAO.getGamesFinished(), true);
        showGamesView.setShowStatsActionListener(e -> {
            int currentPartidaId = showGamesView.getCurrentPartidaId();
            if (currentPartidaId != -1) {
                openStatsForGame(currentPartidaId);
            }
        });
        showGamesView.setSearchActionListener(e -> {
            String userSearch = showGamesView.getUserSearchText();
            String gameSearch = showGamesView.getGameSearchText();
            List<Game> searchResults = gameDBDAO.searchGamesFinished(userSearch, gameSearch);
            showGamesView.updateTableData(searchResults);
        });
        showGamesView.setVisible(true);
    }

    private void openStatsForGame(int gameId) {
        List<Integer> cafesPorMinuto = statsDBDAO.getStatsByGameId(gameId);
        showGamesView.updateStatsChart(cafesPorMinuto);
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

    private void newGame() {
        String name = newGameView.getNewGameName();
        try{
            gameDBDAO.insertGame(name, correo);
        } catch (ConstraintException e){
            if(e.getMessage().contains("partida_nombre_uk")){
                newGameView.showDuplicateGameMessage();
                return;
            }
        }

        System.out.println("Name: " + name);
        //TODO cridar al joc
    }
    private void removeAccountFromDatabase() {
        String correo = userDAO.getCorreoFromLogin(this.correo, removeAccountView.getPassword());
        if(correo != null) {
            userDAO.removeUserAndData(this.correo);
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