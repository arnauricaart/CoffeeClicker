package presentation.controllers;
import business.entities.Game;
import business.managers.GameManager;
import business.managers.PartidaManager;
import business.managers.StatisticsManager;
import business.managers.UserManager;
import persistence.*;
import presentation.views.*;

import java.util.List;


public class MenuController {
    private MenuGUI menuView;
    private NewGameView newGameView;
    private ShowGamesView showGamesView;
    private RemoveAccountView removeAccountView;
    private String correo;
    private UserManager userManager;
    private PartidaManager partidaManager;
    private StatisticsManager statisticsManager;

    public MenuController(MenuGUI menuView, String correo) {
        this.menuView = menuView;
        this.correo = correo;
    }

    public void initController() {
        menuView.setNewGameButtonListener(e -> startNewGame());
        menuView.setStatisticsButtonListener(e -> selectGameToShowStats());
        menuView.setLogoutButtonListener(e -> logout());
        menuView.setDeleteAccountButtonListener(e -> deleteAccount());
        userManager = new UserManager();
        partidaManager = new PartidaManager();
        statisticsManager = new StatisticsManager();
    }

    private void startNewGame() {
        Game partida = partidaManager.getStartedGame(correo);
        if (partida == null) {
            newGameView = new NewGameView();
            newGameView.setNewGameButtonListener(e -> newGame());
            newGameView.setCancelButtonListener(e -> newGameView.dispose());
            newGameView.setVisible(true);
        } else {
            new GameController();
            menuView.dispose();
        }
    }
    private void selectGameToShowStats() {
        showGamesView = new ShowGamesView(partidaManager.getGamesFinished(), true);
        showGamesView.setShowStatsActionListener(e -> {
            int currentPartidaId = showGamesView.getCurrentPartidaId();
            if (currentPartidaId != -1) {
                openStatsForGame(currentPartidaId);
            }
        });
        showGamesView.setSearchActionListener(e -> {
            String userSearch = showGamesView.getUserSearchText();
            String gameSearch = showGamesView.getGameSearchText();
            List<Game> searchResults = partidaManager.searchGamesFinished(userSearch, gameSearch);
            showGamesView.updateTableData(searchResults);
        });
        showGamesView.setVisible(true);
    }

    private void openStatsForGame(int gameId) {
        List<Integer> cafesPorMinuto = statisticsManager.getStatsByGameId(gameId);
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
        String gameName = newGameView.getNewGameName();
        try {
            int gameId = partidaManager.insertGame(gameName, correo);
            Game game = partidaManager.getGameById(gameId);
            new GameController();
            newGameView.dispose();
            menuView.dispose();
        } catch (ConstraintException e) {
            if (e.getMessage().contains("partida_nombre_uk")) {
                newGameView.showDuplicateGameMessage();
            }
        }
    }

    private void removeAccountFromDatabase() {
        String correo = userManager.getCorreoFromLogin(this.correo, removeAccountView.getPassword());
        if(correo != null) {
            userManager.removeUserAndData(this.correo);
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