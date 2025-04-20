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
        view.addCursorButtonListener(this);
        view.addGrandpaButtonListener(this);

        view.addCursorButtonMouseListener(this);
        view.addCursorButtonMouseListener(this);
        view.addMysteryButtonMouseListener(this);
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
            view.setCursorButtonText("Cursor (" + model.getCursorNumber() + ")");
            if (model.isGrandpaUnlocked()) {
                view.setGrandpaButtonText("Grandpa (" + model.getGrandpaNumber() + ")");
            }
        });
    }

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

                    if (model.canUnlockGrandpa()) {
                        model.unlockGrandpa();
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
            if (model.canUnlockGrandpa()) {
                model.unlockGrandpa();
            }
            updateLabels();
        }

        if (source.equals("CURSORBUTTON")) {
            if (model.canBuyCursor()) {
                model.buyCursor();
                playSound("res/ding.wav");
            } else {
                view.setMessageText("You need more coffees!");
                playSound("res/error.wav");
            }
            updateLabels();
        }

        if (source.equals("GRANDPABUTTON")) {
            if (!model.isGrandpaUnlocked()) {
                view.setMessageText("This item is currently locked!");
                playSound("res/error.wav");
                return;
            }else{
                if (model.canBuyGrandpa()) {
                    model.buyGrandpa();
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
        Object src = e.getSource();
        String name = ((Component) e.getSource()).getName();

        if (src.equals("CURSORBUTTON")) {
            view.setMessageText("Cursor\n[price: " + model.getCursorPrice() + "]\nAutoclicks once every 10 seconds.");
        } else if (src.equals("GRANDPABUTTON")) {
            if (!model.isGrandpaUnlocked()) {
                view.setMessageText("This item is currently locked!");
            } else {
                view.setMessageText("Grandpa\n[price: " + model.getGrandpaPrice() + "]\nProduces 1 coffee per second.");
            }
        } else if (src.equals("MYSTERYBUTTON")) {
            view.setMessageText("This item is currently locked!");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {view.setMessageText("");}

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameManager model = new GameManager();
            GameView view = new GameView();
            new GameController(model, view);
        });
    }
}

