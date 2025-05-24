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
        // 1. Eliminar la instancia anterior de menuView si existe y es visible/mostrable.
        // Esto es crucial para evitar que la ventana anterior permanezca.
        if (this.menuView != null && this.menuView.isDisplayable()) {
            this.menuView.dispose();
        }

        // 2. Crear la nueva instancia de MenuGUI (como en tu código original).
        // Esta se convertirá en la menuView activa gestionada por este controlador.
        this.menuView = new MenuGUI();
        // El constructor de MenuGUI ya la hace visible con setVisible(true). [cite: 4]

        // 3. (Re)asignar todos los listeners necesarios a ESTA NUEVA instancia de menuView.
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

        // Nota: No necesitas llamar a menuView.setVisible(true) explícitamente aquí
        // si el constructor de MenuGUI ya lo hace, pero ser explícito no daña.
        // this.menuView.setVisible(true); // Opcional si el constructor ya lo maneja.
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
                newGameView = new NewGameView();
                //newGameView.setNewGameButtonListener(e -> newGame());

                //Parece que el error de la primera partida se encuetra aqui
                newGameView.setNewGameButtonListener(e -> {
                    Game nuevaPartida = newGame();
                    if (nuevaPartida != null) {
                        gameController.playGame(nuevaPartida);
                    }
                });

                newGameView.setCancelButtonListener(e -> newGameView.dispose());
                newGameView.setVisible(true);
            } else {
                //AQUI YA EXISTE
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
        // Lógica para cerrar sesión

            // tancar la finestra actual
            menuView.dispose();

            // obrir la pantalla de login

            LoginController loginController = new LoginController();

    }

    /**
     * Initiates the process of account deletion by opening a confirmation view.
     */
    private void deleteAccount() {
        // Lógica para eliminar la cuenta
        removeAccountView = new RemoveAccountView();
        removeAccountView.setRemoveAccButtonListener(e -> removeAccountFromDatabase());
        removeAccountView.setCancelButtonListener(e -> removeAccountView.dispose());
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
            // Usando tu MessageDialogs centralizado
            newGameView.showEmptyGameMessage();
            return null;
        }

        Game game = null;
        try {
            System.out.println("[DEBUG] Intentando crear partida: " + gameName + " para usuario: " + correo);
            int gameId = partidaManager.insertGame(gameName, correo);
            System.out.println("[DEBUG] Partida insertada con ID: " + gameId);

            if (gameId <= 0) { // Validar si el gameId es positivo
                System.err.println("[ERROR] partidaManager.insertGame devolvió un ID inválido: " + gameId);
                return null;
            }

            game = partidaManager.getGameById(gameId);
            System.out.println("[DEBUG] partidaManager.getGameById(gameId) devolvió: " + (game != null ? "Objeto Game" : "null"));

            if (game != null) {
                // Creación exitosa
                if (newGameView != null) {
                    newGameView.dispose();
                }
                if (menuView != null) {
                    menuView.dispose();
                }
            } else {
                // Si game es null aquí, significa que getGameById falló silenciosamente
                // o el insertGame devolvió un ID que no se pudo encontrar.
                System.err.println("[ERROR] No se pudo recuperar la partida (ID: " + gameId + ") después de la inserción.");
                // No establezcas game = null aquí porque ya lo es.
            }

        } catch (BusinessException e) {
            newGameView.showDuplicateGameMessage();
            System.err.println("[ERROR] ConstraintException: " + e.getMessage()); // Imprime el mensaje completo para depurar
            if (e.getMessage() != null && e.getMessage().contains("partida_nombre_usuario_uk")) {
                // Esto es para cuando EL MISMO usuario intenta crear una partida con un nombre que YA TIENE.
                //newGameView.showDuplicateGameMessage();
            } else {

            }
            game = null; // Importante: asegurar que game sea null si hay excepción
        } catch (RuntimeException re) { // Capturar otras RuntimeException que podrían venir de SQL_CRUD
            re.printStackTrace();
            game = null;
        } catch (Exception ex) { // Captura genérica por si acaso
            System.err.println("[ERROR] Exception inesperada durante la creación de la partida: " + ex.getMessage());
            ex.printStackTrace();
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