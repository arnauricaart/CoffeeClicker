package MARIA;

import javax.sound.sampled.*;
import javax.swing.*;
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

        view.coffeeButton.addActionListener(this);
        view.cursorButton.addActionListener(this);
        view.grandpaButton.addActionListener(this);

        view.cursorButton.addMouseListener(this);
        view.grandpaButton.addMouseListener(this);
        view.mysteryButton.addMouseListener(this);

        view.pauseButton.addActionListener(e -> togglePause());

        updateLabels();
        run();
    }

    private void togglePause() {
        paused = !paused;
        view.pauseButton.setText(paused ? "Resume" : "Pause");
    }

    private void updateLabels() {
        SwingUtilities.invokeLater(() -> {
            view.counterLabel.setText(model.getCoffeeCounter() + " coffees");
            view.perSecLabel.setText("per second: " + String.format("%.1f", model.getPerSecond()));
            view.cursorButton.setText("Cursor (" + model.getCursorNumber() + ")");
            if (model.isGrandpaUnlocked()) {
                view.grandpaButton.setText("Grandpa (" + model.getGrandpaNumber() + ")");
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
        Object source = e.getSource();

        if (source == view.coffeeButton) {
            model.addCoffee(1);
            if (model.canUnlockGrandpa()) {
                model.unlockGrandpa();
            }
            updateLabels();
        }

        if (source == view.cursorButton) {
            if (model.canBuyCursor()) {
                model.buyCursor();
                playSound("res/ding.wav");
            } else {
                view.messageText.setText("You need more coffees!");
                playSound("res/error.wav");
            }
            updateLabels();
        }

        if (source == view.grandpaButton) {
            if (!model.isGrandpaUnlocked()) {
                view.messageText.setText("This item is currently locked!");
                playSound("res/error.wav");
                return;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object src = e.getSource();

        if (src == view.cursorButton) {
            view.messageText.setText("Cursor\n[price: " + model.getCursorPrice() + "]\nAutoclicks once every 10 seconds.");
        } else if (src == view.grandpaButton) {
            if (!model.isGrandpaUnlocked()) {
                view.messageText.setText("This item is currently locked!");
            } else {
                view.messageText.setText("Grandpa\n[price: " + model.getGrandpaPrice() + "]\nProduces 1 coffee per second.");
            }
        } else if (src == view.mysteryButton) {
            view.messageText.setText("This item is currently locked!");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        view.messageText.setText("");
    }

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

