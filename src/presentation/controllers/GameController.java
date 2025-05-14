package presentation.controllers;

import business.entities.Game;
import business.managers.GameManager;
import presentation.views.GameView;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GameController implements ActionListener, MouseListener, GameUpdateListener {

    private GameManager model;
    private GameView view;
    private MenuNavigator menuNavigator;


    //cantidad de cada uno de los generadores

    public GameController(MenuNavigator navigator) {
        model = new GameManager();
        this.menuNavigator = navigator;
    }

    private void createView(){
        view = new GameView();

        view.addCoffeeButtonListener(this);
        view.addCoffeeMachineButtonListener(this);
        view.addBaristaButtonListener(this);
        view.addCafeButtonListener(this);

        view.addCoffeeMachineUpgradeButtonListener(this);
        view.addBaristaUpgradeButtonListener(this);
        view.addCafeUpgradeButtonListener(this);

        view.addCoffeeMachineButtonMouseListener(this);
        view.addBaristaButtonMouseListener(this);
        view.addCafeButtonMouseListener(this);
        view.addCoffeeMachineUpgradeButtonMouseListener(this);
        view.addBaristaUpgradeButtonMouseListener(this);
        view.addCafeUpgradeButtonMouseListener(this);
        view.addPauseButtonListener(e -> togglePause());
        view.addEndGameButtonListener(e -> endGame());

        model.setGameUpdateListener(this);

        updateLabels();
    }

    public void playGame(Game game){
        model.playGame(game);
        createView();
        view.open();

        // Llama a onGameUpdated una vez para llenar la tabla y etiquetas iniciales
        onGameUpdated();
        //No faltaria aqui poner el view del menu en invisible?
    }

    public void endGame(){
        model.endGame();
        view.close();
        menuNavigator.returnToMenu();
    }

    public void togglePause() {
        model.pauseGame();
        view.close();
        menuNavigator.returnToMenu();
    }

    // ToDo: Cambiar el tamaño de los botones y sustituir U2 por Barista Upgrade,...
    private void updateLabels() {
        SwingUtilities.invokeLater(() -> {
            view.setCounterLableText((int) model.getCoffeeCounter() + " coffees");
            view.setPerSecLabelText("per second: " + String.format("%.1f", model.getPerSecond()));
            view.setCoffeeMachineButtonText("Coffee Machine (" + model.getCoffeeMachineNumber() + ")");
            view.setCoffeeMachineUpgradeButtonText("U1 (" + model.getCoffeeMachineUpgradeNumber() +")");
            if (model.isBaristaUnlocked()) {
                view.setBaristaButtonText("Barista (" + model.getBaristaNumber() + ")");
                view.setBaristaUpgradeButtonText("U2 (" + model.getBaristaUpgradeNumber() + ")");
            }
            if (model.isCafeUnlocked()) {
                view.setCafeButtonText("Cafe (" + model.getCafeNumber() + ")");
                view.setCafeUpgradeButtonText("U3 (" + model.getCafeUpgradeNumber() + ")");
            }
        });
    }


    private void playSound(String path) {
        try {
            File soundFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getActionCommand();

        if (source.equals("COFFEEBUTTON")) {
            model.addCoffee(1);
            updateLabels();
        }

        if (source.equals("COFFEEMACHINEBUTTON")) {
            if (model.canBuyCoffeeMachine()) {
                model.buyCoffeeMachine();
                playSound("res/ding.wav");
            } else {
                view.setMessageText("You need more coffees!");
                playSound("res/error.wav");
            }
            updateLabels();
        }

        if (source.equals("COFFEEMACHINEUPGRADEBUTTON")) {
            if (model.canBuyCoffeeMachineUpgrade()) {
                model.buyCoffeeMachineUpgrade();
                playSound("res/ding.wav");
            } else {
                view.setMessageText("You need more coffees!");
                playSound("res/error.wav");
            }
            updateLabels();
        }

        if (source.equals("BARISTABUTTON")) {
            if (!model.isBaristaUnlocked()) {
                view.setMessageText("This item is currently locked!");
                playSound("res/error.wav");
                return;
            } else {
                if (model.canBuyBarista()) {
                    model.buyBarista();
                    playSound("res/ding.wav");
                } else {
                    view.setMessageText("You need more coffees!");
                    playSound("res/error.wav");
                }
            }
        }

        if (source.equals("BARISTAUPGRADEBUTTON")) {
            if (!model.isBaristaUpgradeUnlocked()) {
                view.setMessageText("This item is currently locked!");
                playSound("res/error.wav");
                return;
            } else {
                if (model.canBuyBaristaUpgrade()) {
                    model.buyBaristaUpgrade();
                    playSound("res/ding.wav");
                } else {
                    view.setMessageText("You need more coffees!");
                    playSound("res/error.wav");
                }
            }
        }

        if (source.equals("CAFEBUTTON")) {
            if (!model.isCafeUnlocked()) {
                view.setMessageText("This item is currently locked!");
                playSound("res/error.wav");
                return;
            }else{
                if (model.canBuyCafe()) {
                    model.buyCafe();
                    playSound("res/ding.wav");
                }else{
                    view.setMessageText("You need more coffees!");
                    playSound("res/error.wav");
                }
            }
        }

        if (source.equals("CAFEUPGRADEBUTTON")) {
            if (model.canBuyCafeUpgrade()) {
                model.buyCafeUpgrade();
                playSound("res/ding.wav");
            } else {
                view.setMessageText("You need more coffees!");
                playSound("res/error.wav");
            }
            updateLabels();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String name = ((Component) e.getSource()).getName();

        if ("COFFEEMACHINEBUTTON".equals(name)) {
            view.setMessageText("Coffee Machine\n[price: " + model.getCoffeeMachinePrice() + "]\nAutoclicks once every 10 seconds.");
        } else if ("BARISTABUTTON".equals(name)) {
            if (!model.isBaristaUnlocked()) {
                view.setMessageText("This item is currently locked!");
            } else {
                view.setMessageText("Barista\n[price: " + model.getBaristaPrice() + "]\nProduces 1 coffee every 2 seconds.");
            }
        } else if ("CAFEBUTTON".equals(name)) {
            if (!model.isCafeUnlocked()) {
                view.setMessageText("This item is currently locked!");
            } else {
                view.setMessageText("Cafe\n[price: " + model.getCafePrice() + "]\nProduces 1 coffee every second.");
            }
        } else if ("COFFEEMACHINEUPGRADEBUTTON".equals(name)) {
            view.setMessageText("Coffee Machine Upgrade\n[price: " + model.getCoffeeMachineUpgradePrice() + "]\nIncreases CoffeeMachine production by " + (model.getCoffeeMachineUpgradeNumber() + 1) + ".");
        } else if ("BARISTAUPGRADEBUTTON".equals(name)) {
            if (!model.isBaristaUpgradeUnlocked()) {
                view.setMessageText("This item is currently locked!");
            } else {
                view.setMessageText("Barista Upgrade\n[price: " + model.getBaristaUpgradePrice() + "]\nIncreases Barista production by " + (model.getBaristaUpgradeNumber() + 1) + ".");
            }
        } else if ("CAFEUPGRADEBUTTON".equals(name)) {
            if (!model.isCafeUpgradeUnlocked()) {
                view.setMessageText("This item is currently locked!");
            } else {
                view.setMessageText("Cafe Upgarde\n[price: " + model.getCafeUpgradePrice() + "]\nIncreases Cafe production by " + (model.getCafeUpgradeNumber() + 1) + ".");
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {view.setMessageText("");}

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}

    @Override
    public void onGameUpdated() {
        updateLabels();

        // Comnetar, para que se actialize la tabla
        Object[][] statsData = model.getGeneratorStatsData();
        if (view != null) {
            view.updateGeneratorStatsTable(statsData);
        }
    }
}