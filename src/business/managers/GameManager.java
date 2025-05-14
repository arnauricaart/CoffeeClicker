package business.managers;
import presentation.controllers.GameUpdateListener;
import business.entities.Game;
import persistence.GameDAO;
import persistence.GameDBDAO;
import persistence.StatsDAO;
import persistence.StatsDBDAO;
import java.text.DecimalFormat;  //Nova

import java.util.Timer;
import java.util.TimerTask;

public class GameManager implements Runnable{
    private final int COFFEE_MACHINE_PRICE_BASE = 15;
    private final int BARISTA_PRICE_BASE = 150;
    private final int CAFE_PRICE_BASE = 300;
    private final double COFFEEMACHINE_PERSECOND = 0.2;
    private final double BARISTA_PERSECOND = 1;
    private final double CAFE_PERSECOND = 5;

    private boolean baristaUnlocked;
    private boolean baristaUpgradeUnlocked;
    private boolean cafeUnlocked;
    private boolean cafeUpgradeUnlocked;
    private double perSecond;
    private GameDAO gameDAO;
    private StatsDAO statDAO;
    private business.entities.Game game;

    //private int currentMinute;

    private Thread autoCoffeeThread;

    //Cuando se haga bien el Game en el codigo principal canviar a FALSE
    private boolean running = true;

    private GameUpdateListener listener;

    private Timer autoSaveTimer;
    private static final long AUTO_SAVE_INTERVAL = 60000; // Auto-save every minute

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

    public void setGameUpdateListener(GameUpdateListener listener) {
        this.listener = listener;
    }

    public void playGame(Game game) {
        running = true;
        this.game = game;
        startAutoSave();

        run();
        // Corrección: inicia un nuevo hilo para `run()`
        //new Thread(this).start();
    }

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

    private void saveGameState() {
        if (game != null) {
            gameDAO.updateGameState(game);
        }
    }

    public void endGame() {
        running = false;
        game.endGame();
        saveGameState();
        autoSaveTimer.cancel();
    }

    public void pauseGame(){
        running = false;
        saveGameState();
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
        return (int) Math.round(COFFEE_MACHINE_PRICE_BASE * Math.pow(1.07, game.getNumCoffeeMachine()));
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
        game.increaseUpgradeCoffeeMachine();
    }
    public int getCoffeeMachineUpgradePrice() {
        return COFFEE_MACHINE_PRICE_BASE * (getCoffeeMachineUpgradeNumber() + 1);
    }

    public int getBaristaUpgradePrice(){
        return BARISTA_PRICE_BASE * (getBaristaUpgradeNumber() + 1);
    }

    public int getCafeUpgradePrice() {
        return CAFE_PRICE_BASE * (getCafeUpgradeNumber() + 1);
    }

    // aqui no va esto, sino dentro de upgrade uno de los hijos
    public int getBaristaPrice() {
        return (int) Math.round(BARISTA_PRICE_BASE * Math.pow(1.15, game.getNumBarista()));
    }

    public boolean canUnlockBarista() {
        return game.getNumCoffees() >= BARISTA_PRICE_BASE && !baristaUnlocked;
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
        return (int) Math.round(CAFE_PRICE_BASE * Math.pow(1.07, game.getNumCafe()));
    }

    public double getPerSecond() { return perSecond; }

    public boolean isBaristaUnlocked() { return baristaUnlocked; }

    public void unlockBarista() {
        baristaUnlocked = true;
    }

    public boolean isCafeUnlocked() { return cafeUnlocked; }

    public boolean canUnlockCafe() {
        return game.getNumCoffees() >= CAFE_PRICE_BASE && !cafeUnlocked;
    }

    public void unlockCafe(){
        cafeUnlocked = true;
    }

    public void unlockBaristaUpgrade() { baristaUpgradeUnlocked = true;}

    public void unlockCafeUpgrade(){cafeUpgradeUnlocked = true;}

    public void updatePerSecond() {
        //perSecond = coffeeMachineNumber * 0.2 * game.getNumCoffeeMachine() + baristaNumber * 0.5 * game.getNumBarista() ;
        perSecond = (game.getNumCoffeeMachine() * COFFEEMACHINE_PERSECOND * (game.getNumUpgradeCoffeeMachine() + 1)) +
                    (game.getNumBarista() * BARISTA_PERSECOND * (game.getNumUpgradeBarista() + 1)) +
                    (game.getNumCafe() * CAFE_PERSECOND * (game.getNumUpgradeCafe() + 1));
    }

    public double getCoffeeCounter() {return game.getNumCoffees();}

    public int getBaristaNumber() {return game.getNumBarista();}

    public int getCoffeeMachineNumber() {return game.getNumCoffeeMachine();}

    public int getCafeNumber() {return game.getNumCafe();}


    public int getBaristaUpgradeNumber() {return game.getNumUpgradeBarista();}

    public int getCafeUpgradeNumber(){return game.getNumUpgradeCafe();}

    public int getCoffeeMachineUpgradeNumber() {return game.getNumUpgradeCoffeeMachine();}

    /**
     * Calcula y formatea los datos de estadísticas para la tabla de generadores.
     * @return Un array Object[3][5] con los datos para las 3 filas de la tabla.
     * Columnas: {"Name", "Quantity", "Unit Production", "Total Production", "% Overall"}
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

            statsTimer.cancel(); // Cierra el timer si se para el juego
        });

        autoCoffeeThread.setDaemon(true);
        autoCoffeeThread.start();
    }




}

