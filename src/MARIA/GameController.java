package MARIA;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GameController implements ActionListener, MouseListener, Runnable {

    private GameManager model;
    private GameView view;
    private Thread autoCoffeeThread;
    private boolean running = true;
    private boolean paused = false;
    //cantidad de cada uno de los generadores

    public GameController(GameManager model, GameView view) {
        this.model = model;
        this.view = view;

        view.addCoffeeButtonListener(this);
        view.addCoffeeMachineButtonListener(this);
        view.addBaristaButtonListener(this);
        view.addCafeButtonListener(this);

        view.addCoffeeMachineButtonMouseListener(this);
        view.addBaristaButtonMouseListener(this);
        view.addCafeButtonMouseListener(this);
        view.addPauseButtonListener(e -> togglePause());

        updateLabels();
        run();
    }

    private void togglePause() {
        paused = !paused;
        view.setPauseButtonText(paused ? "Resume" : "Pause");
    }


    private void updateLabels() {
        SwingUtilities.invokeLater(() -> {
            view.setCounterLableText(model.getCoffeeCounter() + " coffees");
            view.setPerSecLabelText("per second: " + String.format("%.1f", model.getPerSecond()));
            view.setCoffeeMachineButtonText("Coffee Machine (" + model.getCoffeeMachineNumber() + ")");
            if (model.isBaristaUnlocked()) {
                view.setBaristaButtonText("Barista (" + model.getBaristaNumber() + ")");
            }
            if (model.isCafeUnlocked()) {
                view.setCafeButtonText("Cafe (" + model.getCafeNumber() + ")");
            }
        });
    }

    // cambiar a clase Generator
    @Override
    public void run() {
        autoCoffeeThread = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!paused) {
                    model.addCoffee(model.getPerSecond());

                    if (model.canUnlockBarista()) {
                        model.unlockBarista();
                    }

                    if (model.canUnlockCafe()) {
                        model.unlockCafe();
                    }

                    updateLabels();
                }
            }
        });

        autoCoffeeThread.setDaemon(true);
        autoCoffeeThread.start();
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
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {view.setMessageText("");}

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameManager model = new GameManager(12);
            GameView view = new GameView();
            new GameController(model, view);
        });
    }
}

