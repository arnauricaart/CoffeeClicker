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
    private static final long AUTO_SAVE_INTERVAL = 60000;

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

    /**
     * GameManager constructor.
     * Initializes DAOs, game state variables, and the auto-save timer.
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
     * Sets the listener that will be notified of game updates.
     * The listener is typically the GameController.
     * @param listener The listener to be notified of game updates.
     */
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

    /**
     * Adds a specified amount of coffee to the current game's coffee count.
     * This method updates the coffee total within the active Game instance.
     *
     * @param amount The quantity of coffee to be added to the current total.
     */
    public void addCoffee(double amount) {
        game.addCoffee(amount);
    }

    /**
     * Checks if the player has enough coffee to buy a Coffee Machine.
     * @return true if affordable, false otherwise.
     */
    public boolean canBuyCoffeeMachine() {
        return game.getNumCoffees() >= getCoffeeMachinePrice();
    }

    /**
     * Purchases a Coffee Machine generator.
     * Deducts cost and increments the number of owned Coffee Machines.
     */
    public void buyCoffeeMachine() {
        game.subtractCoffee(getCoffeeMachinePrice());
        game.increaseGeneratorCoffeeMachine();
    }

    /**
     * Calculates the current price for the next Coffee Machine generator.
     * @return The price of the next Coffee Machine.
     */
    public int getCoffeeMachinePrice() {
        return (int) Math.round(COFFEE_MACHINE_PRICE_BASE * Math.pow(1.07, game.getNumCoffeeMachine()));
    }

    /**
     * Checks if the player has enough coffee to buy a Barista.
     * @return true if affordable, false otherwise.
     */
    public boolean canBuyBarista() {
        return game.getNumCoffees() >= getBaristaPrice();
    }

    /**
     * Purchases a Barista generator.
     * Deducts cost and increments the number of owned Baristas.
     */
    public void buyBarista() {
        game.subtractCoffee(getBaristaPrice());
        game.increaseGeneratorBarista();
    }

    /**
     * Calculates the current price for the next Barista generator.
     * @return The price of the next Barista.
     */
    public int getBaristaPrice() {
        return (int) Math.round(BARISTA_PRICE_BASE * Math.pow(1.15, game.getNumBarista()));
    }

    /**
     * Checks if the player has enough coffee to buy a Cafe.
     * @return true if affordable, false otherwise.
     */
    public boolean canBuyCafe() {
        return game.getNumCoffees() >= getCafePrice();
    }

    /**
     * Purchases a Cafe generator.
     * Deducts cost and increments the number of owned Cafes.
     */
    public void buyCafe() {
        game.subtractCoffee(getCafePrice());
        game.increaseGeneratorCafe();
    }

    /**
     * Calculates the current price for the next Cafe generator.
     * @return The price of the next Cafe.
     */
    public int getCafePrice() {
        return (int) Math.round(CAFE_PRICE_BASE * Math.pow(1.07, game.getNumCafe()));
    }

    /**
     * Checks if the player has enough coffee to buy a Coffee Machine.
     * @return true if affordable, false otherwise.
     */
    public boolean canBuyCoffeeMachineUpgrade() {
        return game.getNumCoffees() >= getCoffeeMachineUpgradePrice();
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
        return COFFEE_MACHINE_PRICE_BASE * (game.getNumUpgradeCoffeeMachine() + 1);
    }

    /**
     * Checks if the player can purchase the barista upgrade.
     *
     * @return true if affordable, false otherwise
     */
    public boolean canBuyBaristaUpgrade() {
        return game.getNumCoffees() >= getBaristaUpgradePrice();
    }

    /**
     * Purchases the barista upgrade and increases its level.
     */
    public void buyBaristaUpgrade(){
        game.subtractCoffee(getBaristaUpgradePrice());
        game.increaseUpgradeBarista();
    }

    /**
     * Calculates the price for the next barista upgrade.
     *
     * @return the upgrade price
     */
    public int getBaristaUpgradePrice(){
        return BARISTA_PRICE_BASE * (game.getNumUpgradeBarista() + 1);
    }

    /**
     * Checks if the player can purchase the cafe upgrade.
     *
     * @return true if affordable, false otherwise
     */
    public boolean canBuyCafeUpgrade() {
        return game.getNumCoffees() >= getCafeUpgradePrice();
}

    /**
     * Purchases the cafe upgrade and increases its level.
     */
    public void buyCafeUpgrade() {
        game.subtractCoffee(getCafeUpgradePrice());
        game.increaseUpgradeCafe();
    }

    /**
     * Calculates the price for the next cafe upgrade.
     *
     * @return the upgrade price
     */
    public int getCafeUpgradePrice() {
        return CAFE_PRICE_BASE * (game.getNumUpgradeCafe() + 1);
    }

    /**
     * Checks if the player meets the conditions to unlock baristas.
     *
     * @return true if requirements are met, false otherwise
     */
    public boolean canUnlockBarista() {
        return game.getNumCoffees() >= BARISTA_PRICE_BASE && !baristaUnlocked;
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
        return game.getNumCoffees() >= CAFE_PRICE_BASE && !cafeUnlocked;
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

    /**
     * Checks if Barista generators are unlocked for purchase.
     * @return true if Baristas are unlocked, false otherwise.
     */
    public boolean isBaristaUnlocked() { return baristaUnlocked; }

    /**
     * Checks if Barista upgrades are unlocked for purchase.
     * @return true if Barista upgrades are unlocked, false otherwise.
     */
    public boolean isBaristaUpgradeUnlocked(){ return baristaUpgradeUnlocked; }

    /**
     * Checks if Cafe generators are unlocked for purchase.
     * @return true if Cafes are unlocked, false otherwise.
     */
    public boolean isCafeUnlocked() { return cafeUnlocked; }

    /**
     * Checks if Cafe upgrades are unlocked for purchase.
     * @return true if Cafe upgrades are unlocked, false otherwise.
     */
    public boolean isCafeUpgradeUnlocked(){ return cafeUpgradeUnlocked; }


    /**
     * Updates the per-second coffee generation rate based on owned generators and upgrades.
     */
    public void updatePerSecond() {
        perSecond = (double)(game.getNumCoffeeMachine() * COFFEEMACHINE_PERSECOND * (game.getNumUpgradeCoffeeMachine() + 1)) +
                (game.getNumBarista() * BARISTA_PERSECOND * (game.getNumUpgradeBarista() + 1)) +
                (game.getNumCafe() * CAFE_PERSECOND * (game.getNumUpgradeCafe() + 1));
    }

    /**
     * Gets the current total number of coffees the player has.
     * @return The current coffee count.
     */
    public double getCoffeeCounter() { return game.getNumCoffees(); }

    /**
     * Gets the current rate of coffees generated automatically per second.
     * @return The coffees per second rate.
     */
    public double getPerSecond() { return perSecond; }

    /**
     * Gets the current number of Coffee Machine generators owned.
     * @return The number of Coffee Machines.
     */
    public int getCoffeeMachineNumber() { return game.getNumCoffeeMachine(); }


    /**
     * Gets the current number of Barista generators owned.
     * @return The number of Baristas.
     */
    public int getBaristaNumber() { return game.getNumBarista(); }


    /**
     * Gets the current number of Cafe generators owned.
     * @return The number of Cafes.
     */
    public int getCafeNumber() { return game.getNumCafe(); }

    /**
     * Gets the current upgrade level for Coffee Machine generators.
     * @return The Coffee Machine upgrade level.
     */
    public int getCoffeeMachineUpgradeNumber() { return game.getNumUpgradeCoffeeMachine(); }

    /**
     * Gets the current upgrade level for Barista generators.
     * @return The Barista upgrade level.
     */
    public int getBaristaUpgradeNumber() { return game.getNumUpgradeBarista(); }

    /**
     * Gets the current upgrade level for Cafe generators.
     * @return The Cafe upgrade level.
     */
    public int getCafeUpgradeNumber(){ return game.getNumUpgradeCafe(); }

    /**
     * Prepares statistical data on generators for display in a table.
     *
     * @return a 3x5 Object array
     */
    public Object[][] getGeneratorStatsData() {

        Object[][] data = new Object[3][5];
        DecimalFormat dfRate = new DecimalFormat("0.0");
        DecimalFormat dfPercent = new DecimalFormat("0.0");

        String[] names = {"Coffee Machine", "Barista", "Cafe"};
        int[] quantities = {game.getNumCoffeeMachine(), game.getNumBarista(), game.getNumCafe()};
        int[] upgradeLevels = {game.getNumUpgradeCoffeeMachine(), game.getNumUpgradeBarista(), game.getNumUpgradeCafe()};
        double[] baseRates = {COFFEEMACHINE_PERSECOND, BARISTA_PERSECOND, CAFE_PERSECOND};

        double[] unitProductions = new double[3];
        double[] totalProductionsByType = new double[3];

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
     * Gets the command clicked and handles it appropiately.
     * @param buttonCommand The command gotten from the action result
     * @return ButtonActionResult the action result of the button
     */
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

    /**
     * Gets the descriptive text for a game shop button.
     * Used to display item details like price and effect.
     * @param buttonName The identifier of the button.
     * @return A string description for the button.
     */
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
            statsTimer.cancel();
        });

        autoCoffeeThread.setDaemon(true);
        autoCoffeeThread.start();

    }
}