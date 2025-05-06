package MARIA;
import business.entities.Game;
import persistence.GameDAO;
import persistence.GameDBDAO;
import persistence.StatsDAO;
import persistence.StatsDBDAO;

import java.util.Timer;
import java.util.TimerTask;

public class GameManager implements Runnable{
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
    private StatsDAO statDAO;
    private business.entities.Game game;

    private int currentMinute;

    private Thread autoCoffeeThread;

    //Cuando se haga bien el Game en el codigo principal canviar a FALSE
    private boolean running = true;

    private GameUpdateListener listener;

    public GameManager(int ID) {
        gameDAO = new GameDBDAO();
        statDAO = new StatsDBDAO();
        coffeeCounter = 0;
        coffeeMachineNumber = 0;
        baristaNumber = 0;
        cafeNumber = 0;
        baristaUnlocked = false;
        cafeUnlocked = false;
        perSecond = 0.0;
        // no asignamos game aún

        //Deberíamos hacer que game almazene los minutos que ha durado
        currentMinute = 1;
    }

    public void setGameUpdateListener(GameUpdateListener listener) {
        this.listener = listener;
    }

    //Inicializar las variables dependiendo se si es unn juego nuevo o se continua
    public void preStartGame(Game game){

    }
    // la función se llama desde el controller cuando se clica "new game" o "continue game"
    public void startNewGame(String gameName, String email) {
        int gameID = gameDAO.insertGame(gameName, email);
        game = gameDAO.getGameById(String.valueOf(gameID)); //Esta función del DAO deberias ser un int no String
        running = true;
    }

    public void continueGame(String email) {
        //resultSet? game = getGameById(String gameID);
        // game =  getGamesNotFinishedByUser(email);
        running = true;
    }

    public void endGame() {
        running = false;
        game.endGame();
        //Falta hacer un updateGame en la DB
    }

    public void pauseGame(){
        running = false;
        //Falta hacer un updateGame en la DB
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
        perSecond = coffeeMachineNumber * 0.2 + baristaNumber * 0.5;
        //perSecond = coffeeMachineNumber * 0.2 * game.getNumCoffeeMachine() + baristaNumber * 0.5 * game.getNumBarista() ;
        // FALTA tener en cuenta los upgrades. Añadir cafe y sus num upgrades
    }

    @Override
    public void run() {
        autoCoffeeThread = new Thread(() -> {
            // Timer para guardar cafes cada minuto
            Timer statsTimer = new Timer();
            statsTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (running) {
                        statDAO.updateStats(game.getGameID(), getCafeNumber(), currentMinute);
                        currentMinute++;
                    }
                }
            }, 0, 60 * 1000); // 1min

            while (running) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                addCoffee(getPerSecond());

                if (canUnlockBarista()) {
                    unlockBarista();
                }

                if (canUnlockCafe()) {
                    unlockCafe();
                }

                if (listener != null) {
                    listener.onGameUpdated();
                }
            }

            statsTimer.cancel(); // Cierra el timer si se para el juego
        });

        autoCoffeeThread.setDaemon(true);
        autoCoffeeThread.start();
    }

}

