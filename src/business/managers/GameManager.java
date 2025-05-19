package business.managers;
import business.entities.Game;
import presentation.controllers.GameUpdateListener;
import persistence.GameDAO;
import persistence.GameDBDAO;
import persistence.StatsDAO;
import persistence.StatsDBDAO;
import java.text.DecimalFormat;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Manages the state and operations of the active game session.
 * Handles game progression, purchasing/upgrading generators, auto-saving,
 * and updating game statistics periodically.
 */
public class GameManager implements Runnable{
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
    // ToDo: Cuando se haga bien el Game en el codigo principal canviar a FALSE
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
        cafeUpgradeUnlocked = false;
        cafeUnlocked = false;
        perSecond = 0.0;
        // no asignamos game aún

        //Deberíamos hacer que game almazene los minutos que ha durado
        //currentMinute = 1;
        autoSaveTimer = new Timer(true); // Daemon timer
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
    public void playGame(Game game) {
        running = true;
        this.game = game;
        startAutoSave();

        run();
    }

    /**
     * Starts the automatic saving mechanism that persists game state at fixed intervals.
     */
    private void startAutoSave() {
        if (autoSaveTimer != null) {
            autoSaveTimer.cancel(); // Cancel existing timer if any
        }
        autoSaveTimer = new Timer(true); // Create a new daemon timer

        autoSaveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (running && game != null) {
                    saveGameState();
                }
            }
        }, AUTO_SAVE_INTERVAL, AUTO_SAVE_INTERVAL);
    }

    /**
     * Saves the current state of the game to the database.
     */
    private void saveGameState() {
        if (game != null) {
            gameDAO.updateGameState(game);
        }
    }

    /**
     * Ends the current game session and performs final save operations.
     */
    public void endGame() {
        running = false;
        game.endGame();
        saveGameState();
        autoSaveTimer.cancel();
    }

    /**
     * Pauses the game session and saves the current state.
     */
    public void pauseGame(){
        running = false;
        saveGameState();
    }

    /**
     * Adds the specified amount of coffee to the current game.
     *
     * @param amount the amount of coffee to add
     */
    public void addCoffee(double amount) {
        game.addCoffee(amount);
    }

    /**
     * Checks if the player can afford a new coffee machine.
     *
     * @return true if the player has enough coffee, false otherwise
     */
    public boolean canBuyCoffeeMachine() {
        return game.getNumCoffees() >= getCoffeeMachinePrice();
    }

    /**
     * Purchases a new coffee machine, deducting coffee and increasing the count.
     */
    public void buyCoffeeMachine() {
        game.subtractCoffee(getCoffeeMachinePrice());
        game.increaseGeneratorCoffeeMachine();
    }

    /**
     * Calculates the price of the next coffee machine based on current quantity.
     *
     * @return the price of the next coffee machine
     */
    public int getCoffeeMachinePrice() {
        return (int) Math.round(COFFEE_MACHINE_PRICE_BASE * Math.pow(1.07, game.getNumCoffeeMachine()));
    }

    /**
     * Checks if the player can afford a new barista.
     *
     * @return true if affordable, false otherwise
     */
    public boolean canBuyBarista() {
        return game.getNumCoffees() >= getBaristaPrice();
    }

    /**
     * Purchases the cafe upgrade and increases its level.
     */
    public void buyCafeUpgrade() {
        game.subtractCoffee(getCafeUpgradePrice());
        game.increaseUpgradeCafe();
    }

    /**
     * Purchases a new barista and increases its count.
     */
    public void buyBarista() {
        game.subtractCoffee(getBaristaPrice());
        game.increaseGeneratorBarista();
        baristaUnlocked = true;
    }

    /**
     * Purchases the barista upgrade and increases its level.
     */
    public void buyBaristaUpgrade(){
        game.subtractCoffee(getBaristaUpgradePrice());
        game.increaseUpgradeBarista();
    }

    /**
     * Purchases the coffee machine upgrade and increases its level.
     */
    public void buyCoffeeMachineUpgrade() {
        game.subtractCoffee(getCoffeeMachineUpgradePrice());
        game.increaseUpgradeCoffeeMachine();
    }

    /**
     * Calculates the price for the next coffee machine upgrade.
     *
     * @return the upgrade price
     */
    public int getCoffeeMachineUpgradePrice() {
        return COFFEE_MACHINE_PRICE_BASE * (getCoffeeMachineUpgradeNumber() + 1);
    }

    /**
     * Calculates the price for the next barista upgrade.
     *
     * @return the upgrade price
     */
    public int getBaristaUpgradePrice(){
        return BARISTA_PRICE_BASE * (getBaristaUpgradeNumber() + 1);
    }

    /**
     * Calculates the price for the next cafe upgrade.
     *
     * @return the upgrade price
     */
    public int getCafeUpgradePrice() {
        return CAFE_PRICE_BASE * (getCafeUpgradeNumber() + 1);
    }

    /**
     * Calculates the price of the next barista based on quantity owned.
     *
     * @return the barista price
     */
    public int getBaristaPrice() {
        return (int) Math.round(BARISTA_PRICE_BASE * Math.pow(1.15, game.getNumBarista()));
    }

    /**
     * Checks if the player can unlock baristas.
     *
     * @return true if requirements are met, false otherwise
     */
    public boolean canUnlockBarista() {
        return game.getNumCoffees() >= BARISTA_PRICE_BASE && !baristaUnlocked;
    }

    /**
     * Checks if the barista upgrade is unlocked.
     *
     * @return true if unlocked, false otherwise
     */
    public boolean isBaristaUpgradeUnlocked(){
        return baristaUpgradeUnlocked;
    }

    /**
     * Checks if the cafe upgrade is unlocked.
     *
     * @return true if unlocked, false otherwise
     */
    public boolean isCafeUpgradeUnlocked(){
        return cafeUpgradeUnlocked;
    }

    /**
     * Checks if the player can buy a cafe.
     *
     * @return true if the cafe is affordable, false otherwise
     */
    public boolean canBuyCafe(){
        return game.getNumCoffees() >= getCafePrice();
    }

    /**
     * Checks if the player can purchase the cafe upgrade.
     *
     * @return true if affordable, false otherwise
     */
    public boolean canBuyCafeUpgrade() {return game.getNumCoffees() >= getCafeUpgradePrice();}

    /**
     * Checks if the player can purchase the coffee machine upgrade.
     *
     * @return true if affordable, false otherwise
     */
    public boolean canBuyCoffeeMachineUpgrade() {return game.getNumCoffees() >= getCoffeeMachineUpgradePrice();}

    /**
     * Checks if the player can purchase the barista upgrade.
     *
     * @return true if affordable, false otherwise
     */
    public boolean canBuyBaristaUpgrade() {return game.getNumCoffees() >= getBaristaUpgradePrice();}

    /**
     * Purchases a cafe and increases its count.
     */
    public void buyCafe() {
        game.subtractCoffee(getCafePrice());
        game.increaseGeneratorCafe();
    }

    /**
     * Calculates the price of the next cafe based on quantity.
     *
     * @return the cafe price
     */
    public int getCafePrice() {
        return (int) Math.round(CAFE_PRICE_BASE * Math.pow(1.07, game.getNumCafe()));
    }

    /**
     * Returns the current coffee generation rate per second.
     *
     * @return coffee per second
     */
    public double getPerSecond() { return perSecond; }

    /**
     * Checks if baristas have been unlocked.
     *
     * @return true if unlocked, false otherwise
     */
    public boolean isBaristaUnlocked() { return baristaUnlocked; }

    /**
     * Unlocks baristas for the current game session.
     */
    public void unlockBarista() {
        baristaUnlocked = true;
    }

    /**
     * Checks if cafes have been unlocked.
     *
     * @return true if unlocked, false otherwise
     */
    public boolean isCafeUnlocked() { return cafeUnlocked; }

    /**
     * Checks if the player meets the conditions to unlock cafes.
     *
     * @return true if unlockable, false otherwise
     */
    public boolean canUnlockCafe() {
        return game.getNumCoffees() >= CAFE_PRICE_BASE && !cafeUnlocked;
    }

    /**
     * Unlocks cafes for the current game session.
     */
    public void unlockCafe(){
        cafeUnlocked = true;
    }

    /**
     * Unlocks the barista upgrade.
     */
    public void unlockBaristaUpgrade() { baristaUpgradeUnlocked = true;}

    /**
     * Unlocks the cafe upgrade.
     */
    public void unlockCafeUpgrade(){cafeUpgradeUnlocked = true;}

    /**
     * Updates the per-second coffee generation rate based on owned generators and upgrades.
     */
    public void updatePerSecond() {
        //perSecond = coffeeMachineNumber * 0.2 * game.getNumCoffeeMachine() + baristaNumber * 0.5 * game.getNumBarista() ;
        perSecond = (double)(game.getNumCoffeeMachine() * COFFEEMACHINE_PERSECOND * (game.getNumUpgradeCoffeeMachine() + 1)) +
                (game.getNumBarista() * BARISTA_PERSECOND * (game.getNumUpgradeBarista() + 1)) +
                (game.getNumCafe() * CAFE_PERSECOND * (game.getNumUpgradeCafe() + 1));
    }

    /**
     * Returns the current amount of coffee accumulated.
     *
     * @return the coffee count
     */
    public double getCoffeeCounter() {return game.getNumCoffees();}

    /**
     * Gets the number of baristas purchased
     *
     * @return number of baristas purchased
     */
    public int getBaristaNumber() {return game.getNumBarista();}

    /**
     * Gets the number of coffee machines purchased
     *
     * @return number of coffee machines purchased
     */
    public int getCoffeeMachineNumber() {return game.getNumCoffeeMachine();}

    /**
     * Gets the number of cafes purchased
     *
     * @return number of cafes purchased
     */
    public int getCafeNumber() {return game.getNumCafe();}

    /**
     * Gets the number of barista upgrades purchased
     *
     * @return number of barista upgrades purchased
     */
    public int getBaristaUpgradeNumber() {return game.getNumUpgradeBarista();}

    /**
     * Gets the number of coffee machine upgrades purchased
     *
     * @return number of coffee machine upgrades purchased
     */
    public int getCafeUpgradeNumber(){return game.getNumUpgradeCafe();}

    /**
     * Gets the number of barista upgrades purchased
     *
     * @return number of barista upgrades purchased
     */
    public int getCoffeeMachineUpgradeNumber() {return game.getNumUpgradeCoffeeMachine();}

    /**
     * Prepares statistical data on generators for display in a table.
     *
     * @return a 3x5 Object array containing name, quantity, unit production, total production, and % overall
     */
    public Object[][] getGeneratorStatsData() {
        if (game == null) {
            // Si no hay juego cargado, devuelve datos vacíos o nulos
            return new Object[0][0];
        }

        Object[][] data = new Object[3][5];
        DecimalFormat dfRate = new DecimalFormat("0.0"); // Formato para producción (ej: 1.5)
        DecimalFormat dfPercent = new DecimalFormat("0.0'%'"); // Formato para porcentaje (ej: 25.5%)

        // --- Datos Base ---
        String[] names = {"Coffee Machine", "Barista", "Cafe"};
        int[] quantities = {game.getNumCoffeeMachine(), game.getNumBarista(), game.getNumCafe()};
        int[] upgradeLevels = {game.getNumUpgradeCoffeeMachine(), game.getNumUpgradeBarista(), game.getNumUpgradeCafe()};
        double[] baseRates = {COFFEEMACHINE_PERSECOND, BARISTA_PERSECOND, CAFE_PERSECOND};

        // --- Cálculos ---
        double[] unitProductions = new double[3];
        double[] totalProductions = new double[3];
        double grandTotalProduction = getPerSecond(); // Usamos el total ya calculado

        for (int i = 0; i < 3; i++) {
            unitProductions[i] = baseRates[i] * (upgradeLevels[i] + 1);
            totalProductions[i] = unitProductions[i] * quantities[i];
        }

        // --- Llenar el array de datos para la tabla ---
        for (int i = 0; i < 3; i++) {
            data[i][0] = names[i]; // Name
            data[i][1] = quantities[i]; // Quantity

            // Unit Production (formateado)
            data[i][2] = dfRate.format(unitProductions[i]) + " c/s";

            // Total Production (formateado)
            data[i][3] = dfRate.format(totalProductions[i]) + " c/s";

            // % Overall (formateado, con cuidado de división por cero)
            double percentage = 0.0;
            if (grandTotalProduction > 0) {
                percentage = (totalProductions[i] / grandTotalProduction) * 100.0;
            }
            data[i][4] = dfPercent.format(percentage);
        }

        return data;
    }

    /**
     * The main game loop that runs on a separate thread.
     * Adds coffee per second, checks unlock conditions, and updates the UI.
     */
    @Override
    public void run() {
        autoCoffeeThread = new Thread(() -> {
            // Timer para guardar cafes cada minuto
            Timer statsTimer = new Timer();
            statsTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (running) {
                        statDAO.updateStats(game.getGameID(), (int)game.getNumCoffees(), game.getMinDuration());
                        game.increaseMinDuration();
                    }
                }
            }, 0, 60 * 1000); // 1min

            while (running) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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

            // Stops the timer if the game has ended.
            statsTimer.cancel();
        });

        autoCoffeeThread.setDaemon(true);
        autoCoffeeThread.start();
    }
}


