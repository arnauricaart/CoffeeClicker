package MARIA;

public class GameManager {
    private double coffeeCounter;
    private int cursorNumber;
    private int grandpaNumber;
    private boolean grandpaUnlocked;
    private final int cursorPriceBase = 10;
    private final int grandpaPriceBase = 150;
    private double perSecond;

    public GameManager() {
        coffeeCounter = 0;
        cursorNumber = 0;
        grandpaNumber = 0;
        grandpaUnlocked = false;
        perSecond = 0.0;
    }

    public void addCoffee(double amount) {
        coffeeCounter += amount;
    }

    public boolean canBuyCursor() {
        return coffeeCounter >= getCursorPrice();
    }

    public void buyCursor() {
        coffeeCounter -= getCursorPrice();
        cursorNumber++;
        perSecond += 0.2;
    }

    public int getCursorPrice() {
        return (int) Math.round(cursorPriceBase * Math.pow(1.07, cursorNumber));
    }

    public boolean canBuyGrandpa() {
        return coffeeCounter >= getGrandpaPrice();
    }

    public void buyGrandpa() {
        coffeeCounter -= getGrandpaPrice();
        grandpaNumber++;
        perSecond += 0.5;
        grandpaUnlocked = true;
    }

    public int getGrandpaPrice() {
        return (int) Math.round(grandpaPriceBase * Math.pow(1.15, grandpaNumber));
    }

    public boolean canUnlockGrandpa() {
        return coffeeCounter >= grandpaPriceBase && !grandpaUnlocked;
    }

    public int getCoffeeCounter() { return (int) coffeeCounter; }
    public int getCursorNumber() { return cursorNumber; }
    public int getGrandpaNumber() { return grandpaNumber; }
    public double getPerSecond() { return perSecond; }
    public boolean isGrandpaUnlocked() { return grandpaUnlocked; }

    public void unlockGrandpa() {
        grandpaUnlocked = true;
    }

    public void updatePerSecond() {
        perSecond = cursorNumber * 0.2 + grandpaNumber * 0.5;
    }
}

