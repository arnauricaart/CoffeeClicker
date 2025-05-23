package business.managers;
import business.businessExceptions.BusinessException;
import business.businessExceptions.DBGeneralException;
import business.entities.Game;
//import com.sun.tools.javac.tree.JCTree;
import persistence.persistenceExceptions.PersistenceException;
import presentation.controllers.GameUpdateListener;
import persistence.GameDAO;
import persistence.GameDBDAO;
import persistence.StatsDAO;
import persistence.StatsDBDAO;
import presentation.views.PopUpView;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Manages the state and operations of the active game session.
 * Handles game progression, purchasing/upgrading generators, auto-saving,
 * and updating game statistics periodically.
 */
public class GameManager implements Runnable {
    // Constants
    private final int COFFEE_MACHINE_PRICE_BASE = 15;
    private final int BARISTA_PRICE_BASE = 150;
    private final int CAFE_PRICE_BASE = 300;
    private final double COFFEEMACHINE_PERSECOND = 0.2;
    private final double BARISTA_PERSECOND = 1.0;
    private final double CAFE_PERSECOND = 5.0;
    private static final long AUTO_SAVE_INTERVAL = 60000; // To auto-save every minute

    /**
     * Flag indicating whether baristas are unlocked.
     */
    private boolean baristaUnlocked;
    /**
     * Flag indicating whether the barista upgrade is unlocked.
     */
    private boolean baristaUpgradeUnlocked;
    /**
     * Flag indicating whether cafes are unlocked.
     */
    private boolean cafeUnlocked;
    /**
     * Flag indicating whether the cafe upgrade is unlocked.
     */
    private boolean cafeUpgradeUnlocked;
    /**
     * Total coffee generated per second across all generators.
     */
    private double perSecond;
    /**
     * Data access object for game-related database operations.
     */
    private GameDAO gameDAO;
    /**
     * Data access object for game statistics persistence.
     */
    private StatsDAO statDAO;
    /**
     * The current game.
     */
    private Game game;
    /**
     * The thread managing the per-second auto-coffee logic.
     */
    private Thread autoCoffeeThread;
    /**
     * Controls whether the game is actively running.
     */
    // ToDo: Cuando se haga bien el Game en el codigo principal canviar a FALSE (Comentario original)
    private boolean running = true;
    /**
     * Listener for UI updates related to game state changes.
     */
    private GameUpdateListener listener;
    /**
     * Timer for auto-saving the game state periodically.
     */
    private Timer autoSaveTimer;

    /**
     * Constructs a new GameManager with default game and stats DAOs.
     */
    public GameManager() {
        gameDAO = new GameDBDAO();
        statDAO = new StatsDBDAO();
        baristaUnlocked = false;
        baristaUpgradeUnlocked = false;
        cafeUnlocked = false;
        cafeUpgradeUnlocked = false;
        perSecond = 0.0;
        autoSaveTimer = new Timer(true);
    }

    /**
     * Sets the listener to receive updates on game state changes.
     *
     * @param listener the GameUpdateListener to notify
     */
    public void setGameUpdateListener(GameUpdateListener listener) {
        this.listener = listener;
    }

    /**
     * Starts the game loop for the specified game instance.
     *
     * @param game the game instance to be played
     */
    public void playGame(Game game) throws BusinessException {
        this.game = game;

        // --- INICIO DE LÍNEAS MODIFICADAS/AÑADIDAS PARA CORREGIR ESTADO EN NUEVA PARTIDA ---
        // (Esta parte es la corrección del bug y se mantiene)
        // Reiniciar los flags de desbloqueo a su estado base (bloqueado)
        this.baristaUnlocked = false;
        this.baristaUpgradeUnlocked = false;
        this.cafeUnlocked = false;
        this.cafeUpgradeUnlocked = false;

        // Si es una partida cargada que YA TENÍA generadores/mejoras,
        // actualizar los flags de desbloqueo basados en el estado de 'game'.
        // Para una partida NUEVA, esto no cambiará los flags de 'false'.
        if (this.game != null) { // Verificación de nulidad para seguridad
            if (this.game.getNumBarista() > 0) {
                this.baristaUnlocked = true;
                this.baristaUpgradeUnlocked = true; // Asumiendo desbloqueo conjunto
            }
            if (this.game.getNumCafe() > 0) {
                this.cafeUnlocked = true;
                this.cafeUpgradeUnlocked = true;    // Asumiendo desbloqueo conjunto
            }
        }
        // --- FIN DE LÍNEAS MODIFICADAS/AÑADIDAS PARA CORREGIR ESTADO EN NUEVA PARTIDA ---

        this.running = true;
        startAutoSave();

        run();
    }

    /**
     * Starts the automatic saving mechanism that persists game state at fixed intervals.
     */
    private void startAutoSave() {

        if (autoSaveTimer != null) {
            autoSaveTimer.cancel();
        }
        autoSaveTimer = new Timer(true);

        autoSaveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (running && game != null) {
                    try {
                        saveGameState();
                    } catch (DBGeneralException e) {
                        System.out.println("Error saving the game progress: " + e.getExceptionMessage() + ". The game will be ended");
                        game.endGame();
                    }
                }
            }
        }, AUTO_SAVE_INTERVAL, AUTO_SAVE_INTERVAL);
    }

    /**
     * Saves the current state of the game to the database.
     */
    private void saveGameState() throws business.businessExceptions.DBGeneralException {
        try {
            if (game != null) { // Mantenida la verificación de nulidad aquí por seguridad al interactuar con DAO
                gameDAO.updateGameState(game);
            }
        }catch (PersistenceException e){
            throw new business.businessExceptions.DBGeneralException("Constraint DB error. Please try again later.");
        }
    }

    /**
     * Ends the current game session and performs final save operations.
     */
    public void endGame() throws BusinessException {
        try {
            running = false;

            if (game != null && statDAO != null) { // Mantenida la verificación
                statDAO.updateStats(game.getGameID(), (int) game.getNumCoffees(), game.getMinDuration());
            }
            if (game != null) { // Mantenida la verificación
                game.endGame();
            }
            saveGameState();

            if (autoSaveTimer != null) {
                autoSaveTimer.cancel();
            }
            // No se interrumpe explícitamente autoCoffeeThread, se dependerá de 'running = false'
        } catch(PersistenceException e){
            throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }
    }

    /**
     * Pauses the game session and saves the current state.
     */
    public void pauseGame() throws BusinessException {
        running = false;
        saveGameState();
        // No se interrumpe explícitamente autoCoffeeThread
    }

    /**
     * Adds the specified amount of coffee to the current game.
     *
     * @param amount the amount of coffee to add
     */
    public void addCoffee(double amount) {
        game.addCoffee(amount); // Revertido a acceso directo
    }

    /**
     * Checks if the player can afford a new coffee machine.
     *
     * @return true if the player has enough coffee, false otherwise
     */
    public boolean canBuyCoffeeMachine() {
        return game.getNumCoffees() >= getCoffeeMachinePrice(); // Revertido
    }

    /**
     * Purchases a new coffee machine, deducting coffee and increasing the count.
     */
    public void buyCoffeeMachine() {
        game.subtractCoffee(getCoffeeMachinePrice()); // Revertido
        game.increaseGeneratorCoffeeMachine(); // Revertido
    }

    /**
     * Calculates the price of the next coffee machine based on current quantity.
     *
     * @return the price of the next coffee machine
     */
    public int getCoffeeMachinePrice() {
        // Revertido a acceso directo, asumiendo 'game' no será null en flujo normal
        return (int) Math.round(COFFEE_MACHINE_PRICE_BASE * Math.pow(1.07, game.getNumCoffeeMachine()));
    }

    /**
     * Checks if the player can afford a new barista.
     *
     * @return true if affordable, false otherwise
     */
    public boolean canBuyBarista() {
        return game.getNumCoffees() >= getBaristaPrice(); // Revertido
    }

    /**
     * Purchases a new barista and increases its count.
     */
    public void buyBarista() {
        game.subtractCoffee(getBaristaPrice()); // Revertido
        game.increaseGeneratorBarista();        // Revertido
    }

    /**
     * Calculates the price of the next barista based on quantity owned.
     *
     * @return the barista price
     */
    public int getBaristaPrice() {
        return (int) Math.round(BARISTA_PRICE_BASE * Math.pow(1.15, game.getNumBarista())); // Revertido
    }

    /**
     * Checks if the player can buy a cafe.
     *
     * @return true if the cafe is affordable, false otherwise
     */
    public boolean canBuyCafe(){
        return game.getNumCoffees() >= getCafePrice(); // Revertido
    }

    /**
     * Purchases a cafe and increases its count.
     */
    public void buyCafe() {
        game.subtractCoffee(getCafePrice()); // Revertido
        game.increaseGeneratorCafe();        // Revertido
    }

    /**
     * Calculates the price of the next cafe based on quantity.
     *
     * @return the cafe price
     */
    public int getCafePrice() {
        return (int) Math.round(CAFE_PRICE_BASE * Math.pow(1.07, game.getNumCafe())); // Revertido
    }


    /**
     * Checks if the player can purchase the coffee machine upgrade.
     *
     * @return true if affordable, false otherwise
     */
    public boolean canBuyCoffeeMachineUpgrade() {
        return game.getNumCoffees() >= getCoffeeMachineUpgradePrice(); // Revertido
    }

    /**
     * Purchases the coffee machine upgrade and increases its level.
     */
    public void buyCoffeeMachineUpgrade() {
        game.subtractCoffee(getCoffeeMachineUpgradePrice()); // Revertido
        game.increaseUpgradeCoffeeMachine();                 // Revertido
    }

    /**
     * Calculates the price for the next coffee machine upgrade.
     *
     * @return the upgrade price
     */
    public int getCoffeeMachineUpgradePrice() {
        // REVERTIDO a la fórmula original sin el '* 5'
        return COFFEE_MACHINE_PRICE_BASE * (game.getNumUpgradeCoffeeMachine() + 1);
    }

    /**
     * Checks if the player can purchase the barista upgrade.
     *
     * @return true if affordable, false otherwise
     */
    public boolean canBuyBaristaUpgrade() {
        return game.getNumCoffees() >= getBaristaUpgradePrice(); // Revertido
    }

    /**
     * Purchases the barista upgrade and increases its level.
     */
    public void buyBaristaUpgrade(){
        game.subtractCoffee(getBaristaUpgradePrice()); // Revertido
        game.increaseUpgradeBarista();                 // Revertido
    }

    /**
     * Calculates the price for the next barista upgrade.
     *
     * @return the upgrade price
     */
    public int getBaristaUpgradePrice(){
        // REVERTIDO a la fórmula original sin el '* 5'
        return BARISTA_PRICE_BASE * (game.getNumUpgradeBarista() + 1);
    }

    /**
     * Checks if the player can purchase the cafe upgrade.
     *
     * @return true if affordable, false otherwise
     */
    public boolean canBuyCafeUpgrade() {
        return game.getNumCoffees() >= getCafeUpgradePrice(); // Revertido
    }

    /**
     * Purchases the cafe upgrade and increases its level.
     */
    public void buyCafeUpgrade() {
        game.subtractCoffee(getCafeUpgradePrice()); // Revertido
        game.increaseUpgradeCafe();                 // Revertido
    }

    /**
     * Calculates the price for the next cafe upgrade.
     *
     * @return the upgrade price
     */
    public int getCafeUpgradePrice() {
        // REVERTIDO a la fórmula original sin el '* 5'
        return CAFE_PRICE_BASE * (game.getNumUpgradeCafe() + 1);
    }

    /**
     * Checks if the player meets the conditions to unlock baristas.
     *
     * @return true if requirements are met, false otherwise
     */
    public boolean canUnlockBarista() {
        return game.getNumCoffees() >= BARISTA_PRICE_BASE && !baristaUnlocked; // Revertido
    }

    /**
     * Unlocks baristas for the current game session.
     */
    public void unlockBarista() {
        baristaUnlocked = true;
    }

    /**
     * Unlocks the barista upgrade.
     */
    public void unlockBaristaUpgrade() {
        baristaUpgradeUnlocked = true;
    }

    /**
     * Checks if the player meets the conditions to unlock cafes.
     *
     * @return true if unlockable, false otherwise
     */
    public boolean canUnlockCafe() {
        return game.getNumCoffees() >= CAFE_PRICE_BASE && !cafeUnlocked; // Revertido
    }

    /**
     * Unlocks cafes for the current game session.
     */
    public void unlockCafe(){
        cafeUnlocked = true;
    }

    /**
     * Unlocks the cafe upgrade.
     */
    public void unlockCafeUpgrade(){
        cafeUpgradeUnlocked = true;
    }

    public boolean isBaristaUnlocked() { return baristaUnlocked; }
    public boolean isBaristaUpgradeUnlocked(){ return baristaUpgradeUnlocked; }
    public boolean isCafeUnlocked() { return cafeUnlocked; }
    public boolean isCafeUpgradeUnlocked(){ return cafeUpgradeUnlocked; }


    /**
     * Updates the per-second coffee generation rate based on owned generators and upgrades.
     */
    public void updatePerSecond() {
        // Revertido a acceso directo, asumiendo 'game' no será null
        perSecond = (double)(game.getNumCoffeeMachine() * COFFEEMACHINE_PERSECOND * (game.getNumUpgradeCoffeeMachine() + 1)) +
                (game.getNumBarista() * BARISTA_PERSECOND * (game.getNumUpgradeBarista() + 1)) +
                (game.getNumCafe() * CAFE_PERSECOND * (game.getNumUpgradeCafe() + 1));
    }

    public double getCoffeeCounter() { return game.getNumCoffees(); } // Revertido
    public double getPerSecond() { return perSecond; }
    public int getCoffeeMachineNumber() { return game.getNumCoffeeMachine(); } // Revertido
    public int getBaristaNumber() { return game.getNumBarista(); }             // Revertido
    public int getCafeNumber() { return game.getNumCafe(); }                   // Revertido
    public int getCoffeeMachineUpgradeNumber() { return game.getNumUpgradeCoffeeMachine(); } // Revertido
    public int getBaristaUpgradeNumber() { return game.getNumUpgradeBarista(); }             // Revertido
    public int getCafeUpgradeNumber(){ return game.getNumUpgradeCafe(); }                     // Revertido

    /**
     * Prepares statistical data on generators for display in a table.
     *
     * @return a 3x5 Object array
     */
    public Object[][] getGeneratorStatsData() {
        // Revertido a la lógica que asume 'game' no es null aquí.
        // Si 'game' pudiera ser null y esto causara un error, necesitarías
        // la verificación o asegurar que no se llame en ese estado.

        Object[][] data = new Object[3][5];
        DecimalFormat dfRate = new DecimalFormat("0.0");
        DecimalFormat dfPercent = new DecimalFormat("0.0");

        String[] names = {"Coffee Machine", "Barista", "Cafe"};
        int[] quantities = {game.getNumCoffeeMachine(), game.getNumBarista(), game.getNumCafe()};
        int[] upgradeLevels = {game.getNumUpgradeCoffeeMachine(), game.getNumUpgradeBarista(), game.getNumUpgradeCafe()};
        double[] baseRates = {COFFEEMACHINE_PERSECOND, BARISTA_PERSECOND, CAFE_PERSECOND};

        double[] unitProductions = new double[3];
        double[] totalProductionsByType = new double[3];

        // Recalcular el total aquí para asegurar la precisión del porcentaje en este instante.
        double currentTotalPerSecond = (game.getNumCoffeeMachine() * COFFEEMACHINE_PERSECOND * (game.getNumUpgradeCoffeeMachine() + 1)) +
                (game.getNumBarista() * BARISTA_PERSECOND * (game.getNumUpgradeBarista() + 1)) +
                (game.getNumCafe() * CAFE_PERSECOND * (game.getNumUpgradeCafe() + 1));


        for (int i = 0; i < 3; i++) {
            unitProductions[i] = baseRates[i] * (upgradeLevels[i] + 1);
            totalProductionsByType[i] = unitProductions[i] * quantities[i];
        }

        for (int i = 0; i < 3; i++) {
            data[i][0] = names[i];
            data[i][1] = quantities[i];
            data[i][2] = dfRate.format(unitProductions[i]) + " c/s";
            data[i][3] = dfRate.format(totalProductionsByType[i]) + " c/s";

            double percentage = 0.0;
            if (currentTotalPerSecond > 0) {
                percentage = (totalProductionsByType[i] / currentTotalPerSecond) * 100.0;
            }
            data[i][4] = dfPercent.format(percentage) + "%";
        }
        return data;
    }

    /**
     * The main game loop that runs on a separate thread.
     * Adds coffee per second, checks unlock conditions, and updates the UI.
     */
    @Override
    public void run() throws RuntimeException{
        autoCoffeeThread = new Thread(() -> {
            Timer statsTimer = new Timer(true);
            statsTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (running && game != null) {
                        try {
                            statDAO.updateStats(game.getGameID(), (int) game.getNumCoffees(), game.getMinDuration());
                        } catch (persistence.persistenceExceptions.DBGeneralException e) {
                            throw new RuntimeException(e);
                        }
                        game.increaseMinDuration();
                    } else {
                        this.cancel();
                    }
                }
            }, 0, AUTO_SAVE_INTERVAL);

            while (running) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Revertido a como estaba originalmente
                    e.printStackTrace();
                    // Si el hilo se interrumpe, el bucle podría continuar si 'running' sigue true.
                    // La interrupción por sí sola no detiene el bucle 'while(running)'.
                    // Se dependerá de que 'running' se ponga a 'false' desde fuera (ej. endGame, pauseGame).
                }

                // Se asume que 'game' no será null dentro de este bucle si 'running' es true
                updatePerSecond();
                addCoffee(getPerSecond());

                if (canUnlockBarista()) {
                    unlockBarista();
                    unlockBaristaUpgrade();
                }

                if (canUnlockCafe()) {
                    unlockCafe();
                    unlockCafeUpgrade();
                }

                if (listener != null) {
                    listener.onGameUpdated();
                }
            }
            statsTimer.cancel();
        });

        autoCoffeeThread.setDaemon(true);
        autoCoffeeThread.start();

    }
}