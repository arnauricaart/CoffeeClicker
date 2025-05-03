package MARIA;
import Business.Entities.Game;
import Persistence.GameDAO;
import Persistence.GameDBDAO;

public class GameManager{
    private double coffeeCounter;
    private int coffeeMachineNumber;
    private int baristaNumber;
    private int cafeNumber;
    private boolean baristaUnlocked;
    private boolean cafeUnlocked;
    private final int coffeeMachinePriceBase = 10;
    private final int baristaPriceBase = 150;
    private final int cafePriceBase = 150;
    private double perSecond;
    private GameDAO gameDAO;
    private Game game;

    public GameManager(int ID) {
        gameDAO = new GameDBDAO();
        coffeeCounter = 0;
        coffeeMachineNumber = 0;
        baristaNumber = 0;
        cafeNumber = 0;
        baristaUnlocked = false;
        cafeUnlocked = false;
        perSecond = 0.0;
        // no asignamos game aún

    }

    // la función se llama desde el controller cuando se clica "new game" o "continue game"
    public void startNewGame(String userName, String email) {
        int partidaID = gameDAO.insertGame(userName, email); // tendría que devolver el ID o que devuelva game
        // Falta asignar game
    }

    public void continueGame(String email) {
        //resultSet? game = getGameById(String gameID);
        // game =  getGamesNotFinishedByUser(email);

        }

    // cambiar
    public void addCoffee(double amount) {
        coffeeCounter += amount;
    }

    public boolean canBuyCoffeeMachine() {
        return coffeeCounter >= getCoffeeMachinePrice();
    }


    public void buyCoffeeMachine() {
        coffeeCounter -= getCoffeeMachinePrice();
        coffeeMachineNumber++;
        perSecond += 0.2;
    }

    // aqui no va esto, sino dentro de upgrade uno de los hijos
    public int getCoffeeMachinePrice() {
        return (int) Math.round(coffeeMachinePriceBase * Math.pow(1.07, coffeeMachineNumber));
    }

    public boolean canBuyBarista() {
        return coffeeCounter >= getBaristaPrice();
    }

    public void buyBarista() {
        coffeeCounter -= getBaristaPrice();
        baristaNumber++;
        perSecond += 0.5;
        baristaUnlocked = true;
    }

    // aqui no va esto, sino dentro de upgrade uno de los hijos
    public int getBaristaPrice() {
        return (int) Math.round(baristaPriceBase * Math.pow(1.15, baristaNumber));
    }

    public boolean canUnlockBarista() {
        return coffeeCounter >= baristaPriceBase && !baristaUnlocked;
    }

    public boolean canBuyCafe(){
        return coffeeCounter >= getCafePrice();
    }

    public void buyCafe() {
        coffeeCounter -= getCafePrice();
        cafeNumber++;
        perSecond += 1.0;
    }

    public int getCafePrice() {
        return (int) Math.round(cafePriceBase * Math.pow(1.07, cafeNumber));
    }

    public int getCoffeeCounter() { return (int) coffeeCounter; }
    public int getCoffeeMachineNumber() { return coffeeMachineNumber; }
    public int getBaristaNumber() { return baristaNumber; }
    public int getCafeNumber() { return cafeNumber; }
    public double getPerSecond() { return perSecond; }

    public boolean isBaristaUnlocked() { return baristaUnlocked; }

    public void unlockBarista() {
        baristaUnlocked = true;
    }

    public boolean isCafeUnlocked() { return cafeUnlocked; }

    public boolean canUnlockCafe() {
        return coffeeCounter >= cafePriceBase && !cafeUnlocked;
    }

    public void unlockCafe(){
        cafeUnlocked = true;
    }

    public void updatePerSecond() {
        perSecond = coffeeMachineNumber * 0.2 * game.getNumCoffeeMachine() + baristaNumber * 0.5 * game.getNumBarista() ;
        // tener en cuenta los upgrades. Añadir cafe y sus num upgrades
    }
}

