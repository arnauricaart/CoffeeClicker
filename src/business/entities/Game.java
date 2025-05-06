package business.entities;

public class Game {

    private int gameID;
    private String name;
    private String userName;
    private int coffee;

    private int numCoffeeMachine;
    private int numBarista;
    private int numCafe;

    private int numUpgradeCoffeeMachine;
    private int numUpgradeBarista;
    private int numUpgradeCafe;

    private String lastAccess;
    private boolean hasEnded;


    // Constructor
    public Game(int gameID) {
        this.gameID = gameID;
        this.name = "Partida " + gameID;
        this.coffee = 0;
        this.numCoffeeMachine = 0;
        this.numBarista = 0;
        this.numCafe = 0;
        this.numUpgradeCoffeeMachine = 0;
        this.numUpgradeBarista = 0;
        this.numUpgradeCafe = 0;
        this.hasEnded = false;
    }

    public Game(int gameID, String name, int coffees, String lastAccess) {
        this.gameID = gameID;
        this.name = name;
        this.coffee = coffees;
        this.lastAccess = lastAccess;
    }

    // Constructor amb userName per poder pintar les partides de les estadístiques
    public Game(int gameID, String userName, String name, int coffees, String lastAccess) {
        this.gameID = gameID;
        this.userName = userName;
        this.name = name;
        this.coffee = coffees;
        this.lastAccess = lastAccess;
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
    public void addCoffee(int amount) {
        this.coffee += amount;
    }

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

    // Getters
    public String getName() {
        return name;
    }

    public int getGameID() {
        return gameID;
    }

    public int getCoffees() {
        return coffee;
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

    public String getLastAccess() {return lastAccess; }

    // Getter for userName
    public String getUserName() {
        return userName;
    }

    public int getIdPartida() { return this.gameID;}

}
