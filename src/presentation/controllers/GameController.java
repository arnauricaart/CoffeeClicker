package presentation.controllers;

import business.businessExceptions.BusinessException;
import business.entities.Game;
import business.managers.GameManager;
import business.results.ButtonActionResult;
import presentation.views.GameView;
import presentation.views.PopUpView;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * Controller class for the game, implementing the main logic and event handling
 * between the view (UI) and the model.
 *
 * Implements ActionListener, MouseListener, and GameUpdateListener to react to
 * user input and game updates.
 */
public class GameController implements ActionListener, MouseListener, GameUpdateListener {

    /**
     * A GameManager instance.
     */
    private GameManager model;
    /**
     * A GameView instance.
     */
    private GameView view;
    /**
     * The menu navigator.
     */
    private MenuNavigator menuNavigator;

    /**
     * Constructs a new GameController with the given menu navigator.
     *
     * @param navigator the navigator used to switch between menu and game views
     */
    public GameController(MenuNavigator navigator) {
        model = new GameManager();
        this.menuNavigator = navigator;
    }

    /**
     * Initializes the view, registers listeners, and prepares the UI.
     */
    private void createView(){
        view = new GameView(); // Crea la instancia de la vista

        // Registra este controlador como listener para los botones de la vista
        view.addCoffeeButtonListener(this);
        view.addCoffeeMachineButtonListener(this);
        view.addBaristaButtonListener(this);
        view.addCafeButtonListener(this);

        view.addCoffeeMachineUpgradeButtonListener(this);
        view.addBaristaUpgradeButtonListener(this);
        view.addCafeUpgradeButtonListener(this);

        // Registra este controlador como MouseListener para mensajes contextuales
        view.addCoffeeMachineButtonMouseListener(this);
        view.addBaristaButtonMouseListener(this);
        view.addCafeButtonMouseListener(this);
        view.addCoffeeMachineUpgradeButtonMouseListener(this);
        view.addBaristaUpgradeButtonMouseListener(this);
        view.addCafeUpgradeButtonMouseListener(this);

        // Listeners para Pausa y Fin de Juego
        view.addPauseButtonListener(e -> togglePause());
        view.addEndGameButtonListener(e -> endGame());

        // Registra este controlador para escuchar actualizaciones del modelo (GameManager)
        model.setGameUpdateListener(this);

        updateLabels(); // Actualiza las etiquetas/botones inicialmente
    }

    /**
     * Starts a new game with the given game data, and opens the game view.
     *
     * @param game the game instance to start
     */
    public void playGame(Game game){
        try {
            model.playGame(game); // Le dice al modelo que inicie el juego
            createView(); // Crea la interfaz gráfica del juego
            view.open(); // Hace visible la ventana del juego

            onGameUpdated(); // Llama a onGameUpdated para asegurar que la UI esté sincronizada desde el inicio
        }catch(BusinessException e){
            new PopUpView(e.getExceptionMessage());
        }
    }

    /**
     * Ends the current game session, closes the view and returns to the main menu.
     */
    public void endGame(){
        try {
            model.endGame(); // Le dice al modelo que termine el juego (guardar, etc.)
            view.close(); // Cierra la ventana del juego
            menuNavigator.returnToMenu(); // Navega de vuelta al menú principal
        }catch(BusinessException e){
            new PopUpView(e.getExceptionMessage());
        }
    }

    /**
     * Toggles the game state between paused and running.
     * Closes the game view and returns to the main menu.
     * (Actualmente solo pausa y regresa al menú)
     */
    public void togglePause() {
        try {
            model.pauseGame(); // Le dice al modelo que pause el juego (guardar estado)
            view.close(); // Cierra la ventana del juego
            menuNavigator.returnToMenu(); // Navega de vuelta al menú principal
        } catch (BusinessException e){
            new PopUpView(e.getExceptionMessage());
        }
    }

    /**
     * Updates UI labels such as counters and upgrade buttons based on the current model state.
     * Este metodo es llamado por onGameUpdated o cuando se necesita refrescar la UI.
     */
    private void updateLabels() {
        SwingUtilities.invokeLater(() -> { // Asegura que las actualizaciones de UI se hagan en el Event Dispatch Thread
            // Actualiza el contador de cafés y cafés por segundo
            view.setCounterLableText((int) model.getCoffeeCounter() + " coffees");
            view.setPerSecLabelText("per second: " + String.format("%.1f", model.getPerSecond()));

            // Actualiza la apariencia de los botones de la tienda
            view.updateCoffeeMachineButtonAppearance();
            view.updateCoffeeMachineUpgradeButtonAppearance();

            view.updateBaristaButtonAppearance(model.isBaristaUnlocked());
            view.updateBaristaUpgradeButtonAppearance(model.isBaristaUpgradeUnlocked());

            view.updateCafeButtonAppearance(model.isCafeUnlocked());
            view.updateCafeUpgradeButtonAppearance(model.isCafeUpgradeUnlocked());
        });
    }


    /**
     * Plays a sound effect from the specified file path.
     *
     * @param path path to the audio file
     */
    private void playSound(String path) {
        try {
            File soundFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace(); // Manejo básico de errores de sonido
        }
    }

    /**
     * Handles button clicks and processes game actions based on the command.
     *
     * @param e the action event triggered by a UI component
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        ButtonActionResult result = model.handleButtonAction(command);
        
        if (result.getMessage() != null && !result.getMessage().isEmpty()) {
            view.setMessageText(result.getMessage());
        }
        
        if (result.shouldPlayErrorSound()) {
            playSound("res/error.wav");
        } else if (result.isSuccess()) {
            playSound("res/ding.wav");
        }
        
        updateLabels();
    }

    /**
     * Displays relevant information when the mouse enters a button.
     * (Muestra información sobre el ítem: precio, producción, etc.)
     * @param e the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        String name = ((Component) e.getSource()).getName();
        view.setMessageText(model.getButtonDescription(name));
    }

    /**
     * Erases text when the mouse exits a button.
     * (Limpia el área de mensajes cuando el ratón sale del botón)
     * @param e the mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {view.setMessageText("");}

    // Unused MouseListener methods (Métodos no utilizados de MouseListener, pero deben estar por la interfaz)
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}

    /**
     * Called whenever the game model is updated.
     * Refreshes the view labels and statistics table.
     * (Este metodo es llamado por el GameManager a través de la interfaz GameUpdateListener)
     */
    @Override
    public void onGameUpdated() {
        updateLabels(); // Actualiza todos los textos y apariencias de botones

        // Comnetar, para que se actialize la tabla (Comentario original mantenido)
        // Actualiza la tabla de generadores
        Object[][] statsData = model.getGeneratorStatsData(); // Obtiene los datos del modelo
        if (view != null) { // Verifica que la vista exista
            view.updateGeneratorStatsTable(statsData); // Le dice a la vista que actualice la tabla
        }
    }
}