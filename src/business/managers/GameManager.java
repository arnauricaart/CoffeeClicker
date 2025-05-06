package business.managers;
import presentation.controllers.GameUpdateListener;
import business.entities.Game;
import persistence.GameDAO;
import persistence.GameDBDAO;
import persistence.StatsDAO;
import persistence.StatsDBDAO;

import java.util.Timer;
import java.util.TimerTask;

public class GameManager implements Runnable{
    private boolean baristaUnlocked;
    private boolean baristaUpgradeUnlocked;
    private boolean cafeUnlocked;
    private boolean cafeUpgradeUnlocked;
    private final int coffeeMachinePriceBase = 15;
    private final int baristaPriceBase = 150;
    private final int cafePriceBase = 300;
    private double perSecond;
    private GameDAO gameDAO;
    private StatsDAO statDAO;
    private business.entities.Game game;

    private int currentMinute;

    private Thread autoCoffeeThread;

    //Cuando se haga bien el Game en el codigo principal canviar a FALSE
    private boolean running = true;

    private GameUpdateListener listener;

    private final double COFFEEMACHINE_PERSECOND = 0.2;
    private final double BARISTA_PERSECOND = 1;
    private final double CAFE_PERSECOND = 5;

    public GameManager(int ID) {
        gameDAO = new GameDBDAO();
        statDAO = new StatsDBDAO();
        baristaUnlocked = false;
        baristaUpgradeUnlocked = false;
        cafeUpgradeUnlocked = false;
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

    // aqui no va esto, sino dentro de upgrade uno de los hijos
    public int getCoffeeMachinePrice() {
        return (int) Math.round(coffeeMachinePriceBase * Math.pow(1.07, game.getNumCoffeeMachine()));
    }

    public boolean canBuyBarista() {
        return game.getNumCoffees() >= getBaristaPrice();
    }

    public void buyCafeUpgrade() {
        game.subtractCoffee(getCafeUpgradePrice());
        game.increaseUpgradeCafe();
    }

    public void buyBarista() {
        game.subtractCoffee(getBaristaPrice());
        game.increaseGeneratorBarista();
        baristaUnlocked = true;
    }

    public void buyBaristaUpgrade(){
        game.subtractCoffee(getBaristaUpgradePrice());
        game.increaseUpgradeBarista();
    }

    public void buyCoffeeMachineUpgrade() {
        game.subtractCoffee(getCoffeeMachineUpgradePrice());
        game.increaseUpgradeCafe();
    }
    public int getCoffeeMachineUpgradePrice() {
        return coffeeMachinePriceBase * (getCoffeeMachineUpgradeNumber() + 1);
    }

    public int getBaristaUpgradePrice(){
        return baristaPriceBase * (getBaristaUpgradeNumber() + 1);
    }

    public int getCafeUpgradePrice() {
        return cafePriceBase * (getCafeUpgradeNumber() + 1);
    }

    // aqui no va esto, sino dentro de upgrade uno de los hijos
    public int getBaristaPrice() {
        return (int) Math.round(baristaPriceBase * Math.pow(1.15, game.getNumBarista()));
    }

    public boolean canUnlockBarista() {
        return game.getNumCoffees() >= baristaPriceBase && !baristaUnlocked;
    }

    public boolean isBaristaUpgradeUnlocked(){
        return baristaUpgradeUnlocked;
    }

    public boolean isCafeUpgradeUnlocked(){
        return cafeUpgradeUnlocked;
    }

    public boolean canBuyCafe(){
        return game.getNumCoffees() >= getCafePrice();
    }

    public boolean canBuyCafeUpgrade() {return game.getNumCoffees() >= getCafeUpgradePrice();}

    public boolean canBuyCoffeeMachineUpgrade() {return game.getNumCoffees() >= getCoffeeMachineUpgradePrice();}

    public boolean canBuyBaristaUpgrade() {return game.getNumCoffees() >= getBaristaUpgradePrice();}


    public void buyCafe() {
        game.subtractCoffee(getCafePrice());
        game.increaseGeneratorCafe();
    }

    public int getCafePrice() {
        return (int) Math.round(cafePriceBase * Math.pow(1.07, game.getNumCafe()));
    }

    public double getPerSecond() { return perSecond; }

    public boolean isBaristaUnlocked() { return baristaUnlocked; }

    public void unlockBarista() {
        baristaUnlocked = true;
    }

    public boolean isCafeUnlocked() { return cafeUnlocked; }

    public boolean canUnlockCafe() {
        return game.getNumCoffees() >= cafePriceBase && !cafeUnlocked;
    }

    public void unlockCafe(){
        cafeUnlocked = true;
    }

    public void unlockBaristaUpgrade() { baristaUpgradeUnlocked = true;}

    public void unlockCafeUpgrade(){cafeUpgradeUnlocked = true;}

    public void updatePerSecond() {
        //perSecond = coffeeMachineNumber * 0.2 * game.getNumCoffeeMachine() + baristaNumber * 0.5 * game.getNumBarista() ;
        perSecond = (game.getNumCoffeeMachine() * COFFEEMACHINE_PERSECOND * (game.getNumUpgradeCoffeeMachine() + 1)) +
                    (game.getNumUpgradeBarista() * BARISTA_PERSECOND * (game.getNumUpgradeBarista() + 1)) +
                    (game.getNumCafe() * CAFE_PERSECOND * (game.getNumUpgradeCafe() + 1));
    }

    public double getCoffeeCounter() {return game.getNumCoffees();}

    public int getBaristaNumber() {return game.getNumBarista();}

    public int getCoffeeMachineNumber() {return game.getNumCoffeeMachine();}

    public int getCafeNumber() {return game.getNumCafe();}


    public int getBaristaUpgradeNumber() {return game.getNumUpgradeBarista();}

    public int getCafeUpgradeNumber(){return game.getNumUpgradeCafe();}

    public int getCoffeeMachineUpgradeNumber() {return game.getNumUpgradeCoffeeMachine();}


    @Override
    public void run() {
        autoCoffeeThread = new Thread(() -> {
            // Timer para guardar cafes cada minuto
            Timer statsTimer = new Timer();
            statsTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (running) {
                        statDAO.updateStats(game.getGameID(), game.getNumCafe(), currentMinute);
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

            statsTimer.cancel(); // Cierra el timer si se para el juego
        });

        autoCoffeeThread.setDaemon(true);
        autoCoffeeThread.start();
    }

}

