package presentation.controllers;
import persistence.persistenceExceptions.business.entities.Game;
import persistence.persistenceExceptions.business.managers.PartidaManager;
import persistence.persistenceExceptions.business.managers.StatisticsManager;
import persistence.persistenceExceptions.business.managers.UserManager;
import persistence.persistenceExceptions.ConstraintException;
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
        this.menuView.setNewGameButtonListener(e -> startNewGame());
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
        menuView.setNewGameButtonListener(e -> startNewGame());
        menuView.setStatisticsButtonListener(e -> selectGameToShowStats());
        menuView.setLogoutButtonListener(e -> logout());
        menuView.setDeleteAccountButtonListener(e -> deleteAccount());
        userManager = new UserManager();
        partidaManager = new PartidaManager();
        statisticsManager = new StatisticsManager();
    }

    /**
     * Starts a new game if the user doesn't have one already in progress.
     * If an existing unfinished game is found, resumes it.
     */
    private void startNewGame() {
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
    }

    /**
     * Opens a view to select a finished game and show its statistics.
     */
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

    /**
     * Displays a statistics chart for the selected game.
     *
     * @param gameId the ID of the game whose statistics to show
     */
    private void openStatsForGame(int gameId) {
        List<Integer> cafesPorMinuto = statisticsManager.getStatsByGameId(gameId);
        showGamesView.updateStatsChart(cafesPorMinuto);
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
            loginController.start();

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
        String gameName = newGameView.getNewGameName(); // Obtiene el nombre del juego desde la vista
        Game game = null;
        try {
            // Intenta insertar y obtener la nueva partida
            int gameId = partidaManager.insertGame(gameName, correo);
            game = partidaManager.getGameById(gameId);

            // Si la creación de la partida y la obtención fueron exitosas (game no es null)
            if (game != null) {
                // Cierra la ventana de "Nueva Partida"
                if (newGameView != null) { // Buena práctica verificar que no sea null
                    newGameView.dispose();
                }
                // Cierra también la ventana del menú principal para dar foco al juego
                if (menuView != null) { // Buena práctica verificar que no sea null
                    menuView.dispose();
                }
            }
            // La línea //new GameController(this); probablemente no sea necesaria aquí,
            // ya que gameController ya es un miembro de la clase MenuController.

        } catch (ConstraintException e) {
            // Si ocurre un error (ej. nombre de partida duplicado), se muestra un mensaje
            // y NewGameView permanece abierta para que el usuario corrija el error.
            if (e.getMessage() != null && e.getMessage().contains("partida_nombre_uk")) {
                newGameView.showDuplicateGameMessage();
            }
            game = null; // Asegúrate de que game sea null si hubo un error
        }
        return game; // Devuelve la partida creada o null si falló
    }

    /**
     * Validates the user's password and removes their account from the system.
     * If successful, logs them out and shows confirmation.
     */
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