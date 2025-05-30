package presentation.controllers;
import business.businessExceptions.BusinessException;
import business.entities.Game;
import business.managers.PartidaManager;
import business.managers.StatisticsManager;
import business.managers.UserManager;
import persistence.persistenceExceptions.GameNotFound;
import presentation.views.*;

import java.util.List;

/**
 * Controls the main menu of the application.
 * Handles navigation between views like New Game, Statistics, and Account Deletion.
 * Also manages session transitions such as logout or return from the game.
 */
public class MenuController implements MenuNavigator{
    /**
     * The main menu GUI view
     */
    private MenuGUI menuView;
    /**
     * View for starting a new game
     */
    private NewGameView newGameView;
    /**
     * View for displaying finished games and statistics
     */
    private ShowGamesView showGamesView;
    /**
     * View for handling account removal
     */
    private RemoveAccountView removeAccountView;
    /**
     * Logged-in user's email
     */
    private String correo;
    /**
     * Manages user-related operations like authentication and deletion
     */
    private UserManager userManager;
    /**
     * Handles game persistence and retrieval
     */
    private PartidaManager partidaManager;
    /**
     * Handles game-related statistics like coffees per minute
     */
    private StatisticsManager statisticsManager;
    /**
     * Controls the active game session
     */
    private GameController gameController;


    /**
     * Constructs a MenuController with the given menu view and user email.
     * Initializes the GameController with a reference to this class as a navigator.
     *
     * @param menuView the main menu view
     * @param correo the email of the logged-in user
     */
    public MenuController(MenuGUI menuView, String correo) {
        this.menuView = menuView;
        this.correo = correo;
        gameController = new GameController(this);
    }


    /**
     * Reinitializes the main menu view after returning from another screen.
     * Disposes the old menu window, creates a new one, and sets up all listeners again.
     */

    @Override
    public void returnToMenu() {

        if (this.menuView != null && this.menuView.isDisplayable()) {
            this.menuView.dispose();
        }

        this.menuView = new MenuGUI();

        this.menuView.setNewGameButtonListener(e -> {
            try {
                startNewGame();
            } catch (GameNotFound ex) {
                new PopUpView(ex.getExceptionMessage());
            }
        });
        this.menuView.setStatisticsButtonListener(e -> selectGameToShowStats());
        this.menuView.setLogoutButtonListener(e -> logout());
        this.menuView.setDeleteAccountButtonListener(e -> deleteAccount());

    }

    /**
     * Initializes the controller by setting up the listeners and creating managers.
     */
    public void initController() {
        try {
            menuView.setNewGameButtonListener(e -> {
                try {
                    startNewGame();
                } catch (GameNotFound ex) {
                    new PopUpView(ex.getExceptionMessage());
                }
            });
            menuView.setStatisticsButtonListener(e -> selectGameToShowStats());
            menuView.setLogoutButtonListener(e -> logout());
            menuView.setDeleteAccountButtonListener(e -> deleteAccount());
            userManager = new UserManager();
            partidaManager = new PartidaManager();
            statisticsManager = new StatisticsManager();
        }catch (BusinessException e){
            new PopUpView(e.getExceptionMessage());
            menuView.dispose();
        }
    }

    /**
     * Starts a new game if the user doesn't have one already in progress.
     * If an existing unfinished game is found, resumes it.
     */
    private void startNewGame() throws GameNotFound {
        try {
            Game partida = partidaManager.getStartedGame(correo);
            if (partida == null) {
                menuView.setVisible(false);
                newGameView = new NewGameView();

                newGameView.setNewGameButtonListener(e -> {
                    Game nuevaPartida = newGame();
                    if (nuevaPartida != null) {
                        gameController.playGame(nuevaPartida);
                    }
                });

                newGameView.setCancelButtonListener(e -> {newGameView.dispose(); menuView.setVisible(true);});
                newGameView.setVisible(true);
            } else {
                menuView.showGameExists();
                gameController.playGame(partida);
                menuView.dispose();
            }
        } catch (BusinessException e) {
            new PopUpView(e.getExceptionMessage());
        }
    }

    /**
     * Opens a view to select a finished game and show its statistics.
     */
    private void selectGameToShowStats() {
        try {
            menuView.setVisible(false);
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
                List<Game> searchResults = null;
                try {
                    searchResults = partidaManager.searchGamesFinished(userSearch, gameSearch);
                } catch (BusinessException ex) {
                    new PopUpView(ex.getExceptionMessage());
                }
                showGamesView.updateTableData(searchResults);
            });

            showGamesView.setReturnActionListener(e -> {showGamesView.dispose(); menuView.setVisible(true);});

            showGamesView.setVisible(true);
        }catch (BusinessException e){
            new PopUpView(e.getExceptionMessage());
        }
    }

    /**
     * Displays a statistics chart for the selected game.
     *
     * @param gameId the ID of the game whose statistics to show
     */
    private void openStatsForGame(int gameId) {
        try {
            List<Integer> cafesPorMinuto = statisticsManager.getStatsByGameId(gameId);
            showGamesView.updateStatsChart(cafesPorMinuto);
        }catch(BusinessException e) {
            new PopUpView(e.getExceptionMessage());
        }
    }

    /**
     * Logs the user out by closing the menu and launching the login screen.
     */
    private void logout() {
            menuView.dispose();
            LoginController loginController = new LoginController();
    }

    /**
     * Initiates the process of account deletion by opening a confirmation view.
     */
    private void deleteAccount() {
        removeAccountView = new RemoveAccountView();
        menuView.setVisible(false);
        removeAccountView.setRemoveAccButtonListener(e -> removeAccountFromDatabase());
        removeAccountView.setCancelButtonListener(e -> {removeAccountView.dispose();menuView.setVisible(true);});
        removeAccountView.setVisible(true);
    }

    /**
     * Creates a new game in the database and returns it.
     * Handles errors such as duplicate game names.
     *
     * @return the newly created Game object, or null if creation failed
     */
    private Game newGame() {
        String gameName = newGameView.getNewGameName();
        if (gameName == null || gameName.isEmpty()) {
            newGameView.showEmptyGameMessage();
            return null;
        }

        Game game = null;
        try {
            int gameId = partidaManager.insertGame(gameName, correo);

            if (gameId <= 0) {
                return null;
            }

            game = partidaManager.getGameById(gameId);

            if (game != null) {
                if (newGameView != null) {
                    newGameView.dispose();
                }
                if (menuView != null) {
                    menuView.dispose();
                }
            }

        } catch (BusinessException e) {
            newGameView.showDuplicateGameMessage();
            if (e.getMessage() != null && e.getMessage().contains("partida_nombre_usuario_uk")) {
            } else {

            }
            game = null;
        }
        return game;
    }

    /**
     * Validates the user's password and removes their account from the system.
     * If successful, logs them out and shows confirmation.
     */
    private void removeAccountFromDatabase() {
        try {
            String correo = userManager.getCorreoFromLogin(this.correo, removeAccountView.getPassword());
            if (correo != null) {
                userManager.removeUserAndData(this.correo);
                removeAccountView.showRemoveUserMessage(true);
                removeAccountView.dispose();
                menuView.dispose();
                LoginController loginController = new LoginController();
            } else {
                removeAccountView.showRemoveUserMessage(false);
            }
            return;
        }catch(BusinessException e) {
            new PopUpView(e.getExceptionMessage());
        }
    }
}