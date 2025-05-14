package business.entities;

public class Game {

    private int gameID;
    private String name;
    private String userName;
    private double numCoffee;

    private int numCoffeeMachine;
    private int numBarista;
    private int numCafe;

    private int numUpgradeCoffeeMachine;
    private int numUpgradeBarista;
    private int numUpgradeCafe;

    private String lastAccess;
    private boolean hasEnded;
    private int minDuration;

        // Constructor per a estadístiques
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

    // Constructor amb tots els camps
    public Game(int gameID, String name, int coffees, String lastAccess,
                int numCoffeeMachine, int numBarista, double numCoffee,
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

    // Terminar partida
    public void endGame() {
        this.hasEnded = true;
    }

    // Métodos para incrementar generadores
    public void increaseGeneratorCoffeeMachine() {
        numCoffeeMachine++;
    }

    public void increaseGeneratorBarista() {
        numBarista++;
    }

    public void increaseGeneratorCafe() {
        numCafe++;
    }

    // Métodos para añadir café
    public void addCoffee(double amount) {
        this.numCoffee += amount;
    }

    // Método para quitar cafes
    public void subtractCoffee(double amount) {this.numCoffee -= amount;}

    // Métodos para upgrades
    public void increaseUpgradeCoffeeMachine() {
        numUpgradeCoffeeMachine++;
    }

    public void increaseUpgradeBarista() {
        numUpgradeBarista++;
    }

    public void increaseUpgradeCafe() {
        numUpgradeCafe++;
    }

    // CANVIAR UNA VEZ ESTAN HECHOS BIEN LOS GENERADORES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public int getCoffeeMachinePrice() {
        return 100 + (numCoffeeMachine * 20);
        //return (int) Math.round(cursorPriceBase * Math.pow(1.07, cursorNumber));
    }

    public int getBaristaPrice() {
        return 150 + (numBarista * 30);
        // return (int) Math.round(grandpaPriceBase * Math.pow(1.15, grandpaNumber));
    }

    public int getCafePrice() {
        return 200 + (numCafe * 40);
    }

    public int getCoffeeMachineUpgradePrice() {
        return 75 + (numUpgradeCoffeeMachine * 25);
    }

    public int getBaristaUpgradePrice() {
        return 125 + (numUpgradeBarista * 35);
    }

    public int getCafeUpgradePrice() {
        return 175 + (numUpgradeCafe * 45);
    }

    // Setters for game state
    public void setNumCoffeeMachine(int numCoffeeMachine) {
        this.numCoffeeMachine = numCoffeeMachine;
    }

    public void setNumBarista(int numBarista) {
        this.numBarista = numBarista;
    }

    public void setNumCafe(int numCafe) {
        this.numCafe = numCafe;
    }

    public void setNumUpgradeCoffeeMachine(int numUpgradeCoffeeMachine) {
        this.numUpgradeCoffeeMachine = numUpgradeCoffeeMachine;
    }

    public void setNumUpgradeBarista(int numUpgradeBarista) {
        this.numUpgradeBarista = numUpgradeBarista;
    }

    public void setNumUpgradeCafe(int numUpgradeCafe) {
        this.numUpgradeCafe = numUpgradeCafe;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getGameID() {
        return gameID;
    }

    public double getNumCoffees() {
        return numCoffee;
    }

    public boolean hasEnded() {
        return hasEnded;
    }

    public int getNumCoffeeMachine() {
        return numCoffeeMachine;
    }
    public int getNumBarista() {
        return numBarista;
    }
    public int getNumCafe(){
        return numCafe;
    }

    public int getNumUpgradeCoffeeMachine() {
        return numUpgradeCoffeeMachine;
    }
    public int getNumUpgradeBarista() {
        return numUpgradeBarista;
    }
    public int getNumUpgradeCafe(){
        return numUpgradeCafe;
    }

    public String getLastAccess() {return lastAccess; }

    // Getter for userName
    public String getUserName() {
        return userName;
    }

    public int getIdPartida() { return this.gameID;}

    public int getMinDuration() { return this.minDuration; }

    public void increaseMinDuration() {
        this.minDuration++;
    }


}
