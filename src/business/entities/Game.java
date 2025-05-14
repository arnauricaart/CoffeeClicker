package business.entities;

/**
 * Represents a game in the business domain.
 * This class stores information about a game's ID, name,username and the amount of each generator and upgrade.
 * It provides methods to retrieve and display this information.
 */
public class Game {

    /**
     * The unique identifier of the game.
     */
    private int gameID;
    /**
     * The name of the game.
     */
    private String name;
    /**
     * The username of the player who owns the game.
     */
    private String userName;
    /**
     * The current amount of coffee the player has.
     */
    private double numCoffee;
    /**
     * The number of coffee machines owned.
     */
    private int numCoffeeMachine;
    /**
     * The number of baristas owned.
     */
    private int numBarista;
    /**
     * The number of cafes owned.
     */
    private int numCafe;
    /**
     * The number of coffee machine upgrades acquired.
     */
    private int numUpgradeCoffeeMachine;
    /**
     * The number of barista upgrades acquired.
     */
    private int numUpgradeBarista;
    /**
     * The number of cafe upgrades acquired.
     */
    private int numUpgradeCafe;
    /**
     * The timestamp of the last access to the game.
     */
    private String lastAccess;
    /**
     * Indicates whether the game has ended.
     */
    private boolean hasEnded;
    /**
     * The duration of the game in minutes.
     */
    private int minDuration;

    /**
     * Constructs a Game object with some parameters.
     *
     * @param gameID     the unique game identifier
     * @param userName   the username of the player
     * @param name       the name of the game
     * @param coffees    the amount of coffee
     * @param lastAccess the last access timestamp
     */
    public Game(int gameID, String userName, String name, int coffees, String lastAccess) {
        this.gameID = gameID;
        this.userName = userName;
        this.name = name;
        this.numCoffee = coffees;
        this.lastAccess = lastAccess;
        this.hasEnded = false;
        this.numCoffeeMachine = 0;
        this.numBarista = 0;
        this.numCafe = 0;
        this.numUpgradeCoffeeMachine = 0;
        this.numUpgradeBarista = 0;
        this.numUpgradeCafe = 0;
        this.minDuration = 1;
    }

    /**
     * Constructs a Game object with all parameters.
     *
     * @param gameID                  the unique game identifier
     * @param name                    the name of the game
     * @param coffees                 the amount of coffee
     * @param lastAccess              the last access timestamp
     * @param numCoffeeMachine        number of coffee machines
     * @param numBarista              number of baristas
     * @param numCafe                 number of cafes
     * @param numUpgradeCoffeeMachine number of coffee machine upgrades
     * @param numUpgradeBarista       number of barista upgrades
     * @param numUpgradeCafe          number of cafe upgrades
     * @param minDuration             the duration of the game in minutes
     */
    public Game(int gameID, String name, double coffees, String lastAccess,
                int numCoffeeMachine, int numBarista, int numCafe,
                int numUpgradeCoffeeMachine, int numUpgradeBarista, int numUpgradeCafe, int minDuration) {
        this.gameID = gameID;
        this.name = name;
        this.numCoffee = coffees;
        this.lastAccess = lastAccess;
        this.hasEnded = false;
        this.numCoffeeMachine = numCoffeeMachine;
        this.numBarista = numBarista;
        this.numCafe = numCafe;
        this.numUpgradeCoffeeMachine = numUpgradeCoffeeMachine;
        this.numUpgradeBarista = numUpgradeBarista;
        this.numUpgradeCafe = numUpgradeCafe;
        this.minDuration = minDuration;
    }

    /**
     * Marks the game as ended.
     */
    public void endGame() {
        this.hasEnded = true;
    }

    /**
     * Increases the number of coffee machines by one.
     */
    public void increaseGeneratorCoffeeMachine() {
        numCoffeeMachine++;
    }

    /**
     * Increases the number of baristas by one.
     */
    public void increaseGeneratorBarista() {
        numBarista++;
    }

    /**
     * Increases the number of cafes by one.
     */
    public void increaseGeneratorCafe() {
        numCafe++;
    }

    /**
     * Adds the specified amount of coffee.
     *
     * @param amount the amount of coffee to add
     */
    public void addCoffee(double amount) {
        this.numCoffee += amount;
    }

    /**
     * Subtracts the specified amount of coffee.
     *
     * @param amount the amount of coffee to subtract
     */
    public void subtractCoffee(double amount) {this.numCoffee -= amount;}

    /**
     * Increases the coffee machine upgrade level by one.
     */
    public void increaseUpgradeCoffeeMachine() {
        numUpgradeCoffeeMachine++;
    }

    /**
     * Increases the barista upgrade level by one.
     */
    public void increaseUpgradeBarista() {
        numUpgradeBarista++;
    }

    /**
     * Increases the cafe upgrade level by one.
     */
    public void increaseUpgradeCafe() {
        numUpgradeCafe++;
    }

    /**
     * Calculates the price to buy a new coffee machine.
     *
     * @return the price for the next coffee machine
     */
    public int getCoffeeMachinePrice() {
        return 100 + (numCoffeeMachine * 20);
        //return (int) Math.round(cursorPriceBase * Math.pow(1.07, cursorNumber));
    }

    /**
     * Calculates the price to hire a new barista.
     *
     * @return the price for the next barista
     */
    public int getBaristaPrice() {
        return 150 + (numBarista * 30);
    }

    /**
     * Calculates the price to buy a new cafe.
     *
     * @return the price for the next cafe
     */
    public int getCafePrice() {
        return 200 + (numCafe * 40);
    }

    /**
     * Calculates the price for upgrading coffee machines.
     *
     * @return the coffee machine upgrade price
     */
    public int getCoffeeMachineUpgradePrice() {
        return 75 + (numUpgradeCoffeeMachine * 25);
    }

    /**
     * Calculates the price for upgrading baristas.
     *
     * @return the barista upgrade price
     */
    public int getBaristaUpgradePrice() {
        return 125 + (numUpgradeBarista * 35);
    }

    /**
     * Calculates the price for upgrading cafes.
     *
     * @return the cafe upgrade price
     */
    public int getCafeUpgradePrice() {
        return 175 + (numUpgradeCafe * 45);
    }

    /**
     * Sets the number of coffee machines.
     *
     * @param numCoffeeMachine the new number of coffee machines
     */
    public void setNumCoffeeMachine(int numCoffeeMachine) {
        this.numCoffeeMachine = numCoffeeMachine;
    }

    /**
     * Sets the number of baristas.
     *
     * @param numBarista the new number of baristas
     */
    public void setNumBarista(int numBarista) {
        this.numBarista = numBarista;
    }

    /**
     * Sets the number of cafes.
     *
     * @param numCafe the new number of cafes
     */
    public void setNumCafe(int numCafe) {
        this.numCafe = numCafe;
    }

    /**
     * Sets the number of coffee machine upgrades.
     *
     * @param numUpgradeCoffeeMachine the new number of coffee machine upgrades
     */
    public void setNumUpgradeCoffeeMachine(int numUpgradeCoffeeMachine) {
        this.numUpgradeCoffeeMachine = numUpgradeCoffeeMachine;
    }

    /**
     * Sets the number of barista upgrades.
     *
     * @param numUpgradeBarista the new number of barista upgrades
     */
    public void setNumUpgradeBarista(int numUpgradeBarista) {
        this.numUpgradeBarista = numUpgradeBarista;
    }

    /**
     * Sets the number of cafe upgrades.
     *
     * @param numUpgradeCafe the new number of cafe upgrades
     */
    public void setNumUpgradeCafe(int numUpgradeCafe) {
        this.numUpgradeCafe = numUpgradeCafe;
    }

    /**
     * Retrieves the name of the game.
     *
     * @return the name of the game
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the unique identifier of the game.
     *
     * @return the game ID
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * Retrieves the current number of coffees in the game.
     *
     * @return the number of coffees
     */
    public double getNumCoffees() {
        return numCoffee;
    }

    /**
     * Indicates whether the game has ended.
     *
     * @return true if the game has ended, false otherwise
     */
    public boolean hasEnded() {
        return hasEnded;
    }

    /**
     * Retrieves the number of coffee machines.
     *
     * @return the number of coffee machines
     */
    public int getNumCoffeeMachine() {
        return numCoffeeMachine;
    }

    /**
     * Retrieves the number of baristas.
     *
     * @return the number of baristas
     */
    public int getNumBarista() {
        return numBarista;
    }

    /**
     * Retrieves the number of cafes.
     *
     * @return the number of cafes
     */
    public int getNumCafe(){
        return numCafe;
    }

    /**
     * Retrieves the number of coffee machine upgrades.
     *
     * @return the number of coffee machine upgrades
     */
    public int getNumUpgradeCoffeeMachine() {
        return numUpgradeCoffeeMachine;
    }

    /**
     * Retrieves the number of barista upgrades.
     *
     * @return the number of barista upgrades
     */
    public int getNumUpgradeBarista() {
        return numUpgradeBarista;
    }

    /**
     * Retrieves the number of cafe upgrades.
     *
     * @return the number of cafe upgrades
     */
    public int getNumUpgradeCafe(){
        return numUpgradeCafe;
    }

    /**
     * Retrieves the timestamp of the last access to the game.
     *
     * @return the last access time as a string
     */
    public String getLastAccess() {return lastAccess; }

    /**
     * Retrieves the username of the player.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Retrieves the game ID (alternative getter).
     *
     * @return the game ID
     */
    public int getIdPartida() { return this.gameID;}

    /**
     * Retrieves the minimum duration of the game in minutes.
     *
     * @return the minimum duration in minutes
     */
    public int getMinDuration() { return this.minDuration; }

    /**
     * Increments the game's duration in minutes.
     */
    public void increaseMinDuration() {
        this.minDuration++;
    }

}
