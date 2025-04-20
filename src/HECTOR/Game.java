package HECTOR;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int gameID;
    private String name;
    private float coffee;

    private int numA;
    private int numB;
    private int numC;

    private int numUpgradeA;
    private int numUpgradeB;
    private int numUpgradeC;

    private boolean hasEnded;

    private List<Float> coffeePerMinute;

    // Constructor
    public Game(int gameID) {
        this.gameID = gameID;
        this.name = "Partida " + gameID;
        this.coffee = 0;
        this.numA = 0;
        this.numB = 0;
        this.numC = 0;
        this.numUpgradeA = 0;
        this.numUpgradeB = 0;
        this.numUpgradeC = 0;
        this.hasEnded = false;
        this.coffeePerMinute = new ArrayList<>();
    }

    // Terminar partida
    public void endGame() {
        this.hasEnded = true;
    }

    // Métodos para incrementar generadores
    public void increaseGeneratorCoffeeMachine() {
        numA++;
    }

    public void increaseGeneratorBarista() {
        numB++;
    }

    public void increaseGeneratorCafe() {
        numC++;
    }

    // Métodos para añadir café
    public double addCoffee(double amount) {
        this.coffee += amount;
        return this.coffee;
    }

    // Métodos para upgrades
    public void increaseUpgradeCoffeeMachine() {
        numUpgradeA++;
    }

    public void increaseUpgradeBarista() {
        numUpgradeB++;
    }

    public void increaseUpgradeCafe() {
        numUpgradeC++;
    }

    // Precios (puedes personalizar las fórmulas según el diseño del juego)
    public int getCoffeeMachinePrice() {
        return 100 + (numA * 20);
    }

    public int getBaristaPrice() {
        return 150 + (numB * 30);
    }

    public int getCafePrice() {
        return 200 + (numC * 40);
    }

    public int getCoffeeMachineUpgradePrice() {
        return 75 + (numUpgradeA * 25);
    }

    public int getBaristaUpgradePrice() {
        return 125 + (numUpgradeB * 35);
    }

    public int getCafeUpgradePrice() {
        return 175 + (numUpgradeC * 45);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getGameID() {
        return gameID;
    }

    public float getCoffees() {
        return coffee;
    }

    public boolean hasEnded() {
        return hasEnded;
    }

    // --- NUEVO: Registro de café por minuto para gráficas ---
    public void recordMinute() {
        coffeePerMinute.add(coffee);
    }

    public List<Float> getCoffeePerMinute() {
        return coffeePerMinute;
    }

    @Override
    public String toString() {
        return name + " (ID: " + gameID + ")";
    }
}
