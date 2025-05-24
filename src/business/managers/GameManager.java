package business.managers;

import business.businessExceptions.BusinessException;
import business.businessExceptions.DBGeneralException;
import business.entities.Game;
import persistence.persistenceExceptions.PersistenceException;
import presentation.controllers.GameUpdateListener;
import persistence.GameDAO;
import persistence.GameDBDAO;
import persistence.StatsDAO;
import persistence.StatsDBDAO;
import presentation.views.PopUpView;
import business.results.ButtonActionResult;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Manages the state and operations of the active game session.
 * Handles game progression, purchasing/upgrading generators, auto-saving,
 * and updating game statistics periodically.
 */
public class GameManager implements Runnable {
    private final int COFFEE_MACHINE_PRICE_BASE = 15;
    private final int BARISTA_PRICE_BASE = 150;
    private final int CAFE_PRICE_BASE = 300;
    private final double COFFEEMACHINE_PERSECOND = 0.2;
    private final double BARISTA_PERSECOND = 1.0;
    private final double CAFE_PERSECOND = 5.0;
    private static final long AUTO_SAVE_INTERVAL = 60000; // To auto-save every minute

    private boolean baristaUnlocked;
    private boolean baristaUpgradeUnlocked;
    private boolean cafeUnlocked;
    private boolean cafeUpgradeUnlocked;
    private double perSecond;
    private GameDAO gameDAO;
    private StatsDAO statDAO;
    private Game game;
    private Thread autoCoffeeThread;
    private boolean running = false;
    private GameUpdateListener listener;
    private Timer autoSaveTimer;

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

    public void setGameUpdateListener(GameUpdateListener listener) {
        this.listener = listener;
    }

    /**
     * Starts the game loop for the specified game instance.
     *
     * @param game the game instance to be played
     * @throws BusinessException if there is an error initializing or running the game
     */
    public void playGame(Game game) throws BusinessException {
        this.game = game;

        this.baristaUnlocked = false;
        this.baristaUpgradeUnlocked = false;
        this.cafeUnlocked = false;
        this.cafeUpgradeUnlocked = false;

        if (this.game != null) {
            if (this.game.getNumBarista() > 0) {
                this.baristaUnlocked = true;
                this.baristaUpgradeUnlocked = true;
            }
            if (this.game.getNumCafe() > 0) {
                this.cafeUnlocked = true;
                this.cafeUpgradeUnlocked = true;
            }
        }

        this.running = true;
        startAutoSave();

        run();
    }

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
     *
     * @throws business.businessExceptions.DBGeneralException if a persistence error occurs while saving
     */
    private void saveGameState() throws business.businessExceptions.DBGeneralException {
        try {
            if (game != null) {
                gameDAO.updateGameState(game);
            }
        } catch (PersistenceException e) {
            throw new business.businessExceptions.DBGeneralException("Constraint DB error. Please try again later.");
        }
    }

    /**
     * Ends the current game session and performs final save operations.
     *
     * @throws BusinessException if saving or updating statistics fails
     */
    public void endGame() throws BusinessException {
        try {
            running = false;

            if (game != null && statDAO != null) {
                statDAO.updateStats(game.getGameID(), (int) game.getNumCoffees(), game.getMinDuration());
            }
            if (game != null) {
                game.endGame();
            }
            saveGameState();

            if (autoSaveTimer != null) {
                autoSaveTimer.cancel();
            }
        } catch (PersistenceException e) {
            throw new business.businessExceptions.DBGeneralException(e.getExceptionMessage());
        }
    }

    /**
     * Pauses the game session and saves the current state.
     *
     * @throws BusinessException if the game state could not be saved
     */
    public void pauseGame() throws BusinessException {
        running = false;
        saveGameState();
    }

    public void addCoffee(double amount) {
        game.addCoffee(amount);
    }

    public boolean canBuyCoffeeMachine() {
        return game.getNumCoffees() >= getCoffeeMachinePrice();
    }

    public void buyCoffeeMachine() {
        game.subtractCoffee(getCoffeeMachinePrice());
        game.increaseGeneratorCoffeeMachine();
    }

    public int getCoffeeMachinePrice() {
        return (int) Math.round(COFFEE_MACHINE_PRICE_BASE * Math.pow(1.07, game.getNumCoffeeMachine()));
    }

    public boolean canBuyBarista() {
        return game.getNumCoffees() >= getBaristaPrice();
    }

    public void buyBarista() {
        game.subtractCoffee(getBaristaPrice());
        game.increaseGeneratorBarista();
    }

    public int getBaristaPrice() {
        return (int) Math.round(BARISTA_PRICE_BASE * Math.pow(1.15, game.getNumBarista()));
    }

    public boolean canBuyCafe() {
        return game.getNumCoffees() >= getCafePrice();
    }

    public void buyCafe() {
        game.subtractCoffee(getCafePrice());
        game.increaseGeneratorCafe();
    }

    public int getCafePrice() {
        return (int) Math.round(CAFE_PRICE_BASE * Math.pow(1.07, game.getNumCafe()));
    }

    public boolean canBuyCoffeeMachineUpgrade() {
        return game.getNumCoffees() >= getCoffeeMachineUpgradePrice();
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

    public ButtonActionResult handleButtonAction(String buttonCommand) {
        switch (buttonCommand) {
            case "COFFEEBUTTON":
                addCoffee(1);
                return new ButtonActionResult(true, "", false);

            case "COFFEEMACHINEBUTTON":
                if (canBuyCoffeeMachine()) {
                    buyCoffeeMachine();
                    return new ButtonActionResult(true, "", false);
                }
                return new ButtonActionResult(false, "You need more coffees!", true);

            case "COFFEEMACHINEUPGRADEBUTTON":
                if (canBuyCoffeeMachineUpgrade()) {
                    buyCoffeeMachineUpgrade();
                    return new ButtonActionResult(true, "", false);
                }
                return new ButtonActionResult(false, "You need more coffees!", true);

            case "BARISTABUTTON":
                if (!isBaristaUnlocked()) {
                    return new ButtonActionResult(false, "This item is currently locked!", true);
                }
                if (canBuyBarista()) {
                    buyBarista();
                    return new ButtonActionResult(true, "", false);
                }
                return new ButtonActionResult(false, "You need more coffees!", true);

            case "BARISTAUPGRADEBUTTON":
                if (!isBaristaUpgradeUnlocked()) {
                    return new ButtonActionResult(false, "This item is currently locked!", true);
                }
                if (canBuyBaristaUpgrade()) {
                    buyBaristaUpgrade();
                    return new ButtonActionResult(true, "", false);
                }
                return new ButtonActionResult(false, "You need more coffees!", true);

            case "CAFEBUTTON":
                if (!isCafeUnlocked()) {
                    return new ButtonActionResult(false, "This item is currently locked!", true);
                }
                if (canBuyCafe()) {
                    buyCafe();
                    return new ButtonActionResult(true, "", false);
                }
                return new ButtonActionResult(false, "You need more coffees!", true);

            case "CAFEUPGRADEBUTTON":
                if (!isCafeUpgradeUnlocked()) {
                    return new ButtonActionResult(false, "This item is currently locked!", true);
                }
                if (canBuyCafeUpgrade()) {
                    buyCafeUpgrade();
                    return new ButtonActionResult(true, "", false);
                }
                return new ButtonActionResult(false, "You need more coffees!", true);

            default:
                return new ButtonActionResult(false, "", false);
        }
    }

    public String getButtonDescription(String buttonName) {
        switch (buttonName) {
            case "COFFEEMACHINEBUTTON":
                return String.format("Coffee Machine\n[price: %d]\nAutoclicks once every 5 seconds.", getCoffeeMachinePrice());
            case "BARISTABUTTON":
                if (!isBaristaUnlocked()) {
                    return "This item is currently locked!";
                }
                return String.format("Barista\n[price: %d]\nProduces 1 coffee every second.", getBaristaPrice());
            case "CAFEBUTTON":
                if (!isCafeUnlocked()) {
                    return "This item is currently locked!";
                }
                return String.format("Cafe\n[price: %d]\nProduces 5 coffees every second.", getCafePrice());
            case "COFFEEMACHINEUPGRADEBUTTON":
                return String.format("Coffee Machine Upgrade\n[price: %d]\nIncreases CoffeeMachine production by %d.", getCoffeeMachineUpgradePrice(), getCoffeeMachineUpgradeNumber() + 1);
            case "BARISTAUPGRADEBUTTON":
                if (!isBaristaUpgradeUnlocked()) {
                    return "This item is currently locked!";
                }
                return String.format("Barista Upgrade\n[price: %d]\nIncreases Barista production by %d.", getBaristaUpgradePrice(), getBaristaUpgradeNumber() + 1);
            case "CAFEUPGRADEBUTTON":
                if (!isCafeUpgradeUnlocked()) {
                    return "This item is currently locked!";
                }
                return String.format("Cafe Upgrade\n[price: %d]\nIncreases Cafe production by %d.", getCafeUpgradePrice(), getCafeUpgradeNumber() + 1);
            default:
                return "";
        }
    }

    /**
     * The main game loop that runs on a separate thread.
     * Adds coffee per second, checks unlock conditions, and updates the UI.
     * @throws RuntimeException if a database access error occurs while updating game statistics
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