package presentation.views;

import presentation.presentationExceptions.EmptyNameException;
import presentation.presentationExceptions.PresentationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * NewGameView class extends from BaseForm class. This class will generate a view where the user will be capable of introducing the new game's name.
 */
public class NewGameView extends BaseForm {
    /**
     * Color used by the JLabels.
     */
    private static final Color TEXT_COLOR = Color.decode("#000000");
    /**
     * Secondary color.
     */
    private static final Color SECONDARY_COLOR = Color.decode("#D9D9D9");
    /**
     * Text Field where the user will put the game's name.
     */
    private JTextField newGameName;
    /**
     * Button that will create the new game.
     */
    private JButton newGameButton;
    /**
     * Button that will cancel the creation of the new game.
     */
    private JButton cancelButton;

    /**
     * Class constructor. Sets the view's title and initiates the components.
     */
    public NewGameView() {
        super("New Game");
        initComponents();
    }

    /**
     * Method that initiates the components.
     */
    private void initComponents() {
        int formWidth = 400;

        int fieldWidth = 400;
        int fieldHeight = 40;
        int buttonWidth = 120;
        int buttonHeight = 50;
        int centerX = (1280 - fieldWidth) / 2;
        int topY = 120;

        // Title NEW GAME
        JLabel title = new JLabel("NEW GAME", SwingConstants.CENTER);
        title.setFont(new Font("Pixeled", Font.BOLD, 25));
        title.setBounds(centerX, topY, formWidth, 50);
        title.setForeground(TEXT_COLOR);
        add(title);

        JLabel userLabel = new JLabel("Name game: ");
        userLabel.setBounds(centerX, topY + 70, formWidth, 30);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(userLabel);

        newGameName = new JTextField();
        newGameName.setBounds(centerX, topY + 100, fieldWidth, fieldHeight);
        newGameName.setBackground(SECONDARY_COLOR);
        add(newGameName);

        int buttonY = topY + 180;
        int spacing = 20;
        int totalButtonsWidth = buttonWidth * 2 + spacing;
        int buttonsStartX = centerX + (fieldWidth - totalButtonsWidth) / 2;

        cancelButton = new JButton();
        Dimension cancelBtnSize = new Dimension(buttonWidth, buttonHeight);
        cancelButton.setBounds(buttonsStartX, buttonY, cancelBtnSize.width, cancelBtnSize.height);

        // Path
        String cancelNormalPath = "res/button_cancel.png";
        String cancelRolloverPath = "res/button_cancel2.png";
        boolean cancelImageLoaded = false;

        try {
            ImageIcon icon = new ImageIcon(cancelNormalPath);
            if (icon.getIconWidth() > 0) {
                cancelButton.setIcon(icon);
                cancelImageLoaded = true;
            }
        } catch (Exception e) {
            new PopUpView("There was an error loading the image");
        }

        if (cancelRolloverPath != null) {
            try {
                ImageIcon rIcon = new ImageIcon(cancelRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    cancelButton.setRolloverIcon(rIcon);
                    cancelButton.setRolloverEnabled(true);
                }
            } catch (Exception e) {
                new PopUpView("There was an error loading the image");
            }
        }

        if (cancelImageLoaded) {
            makeButtonLookLikeImage(cancelButton);
        } else {
            cancelButton.setText("Cancel (Error)");
            cancelButton.setBackground(SECONDARY_COLOR);
            cancelButton.setForeground(TEXT_COLOR);
            applyTextButtonStyles(cancelButton);
        }
        commonButtonViewSetup(cancelButton);
        add(cancelButton);


        newGameButton = new JButton();
        Dimension newGameBtnSize = new Dimension(buttonWidth, buttonHeight);
        newGameButton.setBounds(buttonsStartX + buttonWidth + spacing, buttonY, newGameBtnSize.width, newGameBtnSize.height);

        String newGameNormalPath = "res/button_start.png";
        String newGameRolloverPath = "res/button_start2.png";
        boolean newGameImageLoaded = false;

        try {
            ImageIcon icon = new ImageIcon(newGameNormalPath);
            if (icon.getIconWidth() > 0) {
                newGameButton.setIcon(icon);
                newGameImageLoaded = true;
            }
        } catch (Exception e) {
            new PopUpView("There was en error loading/finding the image.");
        }

        if (newGameRolloverPath != null) {
            try {
                ImageIcon rIcon = new ImageIcon(newGameRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    newGameButton.setRolloverIcon(rIcon);
                    newGameButton.setRolloverEnabled(true);
                }
            } catch (Exception e) {
                new PopUpView("There was an error loading the image");
            }
        }

        if (newGameImageLoaded) {
            makeButtonLookLikeImage(newGameButton);
        } else {
            newGameButton.setText("Start (Error)");
            applyTextButtonStyles(newGameButton);
        }
        commonButtonViewSetup(newGameButton);
        add(newGameButton);
    }

    /**
     * Applies common styles to a button that uses an image as it's principal looks.
     */
    private void makeButtonLookLikeImage(JButton button) {
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
    }

    /**
     * Applies common styles to a text button (fallback).
     */
    private void applyTextButtonStyles(JButton button) {
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorder(BorderFactory.createEtchedBorder());
    }

    /**
     * Applies a common configuration to this view's buttons.
     */
    private void commonButtonViewSetup(JButton button) {
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    /**
     * Getter method of the Game Name.
     * @return Returns a String with the Game Name.
     */
    public String getNewGameName() {
        return newGameName.getText();
    }

    /**
     * Setter of listeners in the new game button.
     * @param al Action Listener that will tell what to do when pressed.
     */
    public void setNewGameButtonListener(ActionListener al) {
        if (newGameButton != null) newGameButton.addActionListener(al);
    }

    /**
     * Setter of listeners in the cancel button.
     * @param al Action Listener that will tell what to do when pressed.
     */
    public void setCancelButtonListener(ActionListener al) {
        if (cancelButton != null) cancelButton.addActionListener(al);
    }

    public void showDuplicateGameMessage() {
        JOptionPane.showMessageDialog(this, "This game already exists.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showEmptyGameMessage() {
        JOptionPane.showMessageDialog(this, "You must enter the name for the game.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}