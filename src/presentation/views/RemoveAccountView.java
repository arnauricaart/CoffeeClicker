package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class extends from BaseForm. This class implements a view to let the user remove his account.
 */
public class RemoveAccountView extends BaseForm {

    /**
     * Instance of the Color class that will set the text color.
     */
    private static final Color TEXT_COLOR = Color.decode("#000000");

    /**
     * Instance of the Color class that will set the secondary Color.
     */
    private static final Color SECONDARY_COLOR = Color.decode("#D9D9D9");

    /**
     * JButton that will remove the account.
     */
    private JButton removeButton;

    /**
     * JButton that will cancel the account removing.
     */
    private JButton cancelButton;

    /**
     * JPasswordField where the user will have to put his password to be able to delete the account.
     */
    private JPasswordField passwordField;

    /**
     * Constructor of the class. It sets the view's title and initiates the components of the view.
     */
    public RemoveAccountView() {
        super("Remove Account");
        initComponents();
    }

    /**
     * This method will initiate the components of the view.
     */
    private void initComponents() {

        int formWidth = 400;
        int fieldWidth = 400;
        int fieldHeight = 40;
        int buttonWidth = 120;
        int buttonHeight = 50;
        int centerX = (1280 - fieldWidth) / 2;
        int topY = 120;

        // Title REMOVE ACCOUNT
        JLabel title = new JLabel("REMOVE ACCOUNT", SwingConstants.CENTER);
        title.setFont(new Font("Pixeled", Font.BOLD, 25));
        title.setBounds(centerX, topY, formWidth, 50);
        title.setForeground(TEXT_COLOR);
        add(title);

        JLabel userLabel = new JLabel("Password:");
        userLabel.setBounds(centerX, topY + 70, formWidth, 30);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(userLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(centerX, topY + 110, fieldWidth, fieldHeight);
        passwordField.setBackground(SECONDARY_COLOR);
        add(passwordField);

        int buttonY = topY + 170;
        int spacing = 20;
        int buttonsStartX = centerX + (fieldWidth - (buttonWidth * 2 + spacing)) / 2;

        cancelButton = new JButton();
        Dimension cancelBtnSize = new Dimension(buttonWidth, buttonHeight);
        cancelButton.setBounds(buttonsStartX, buttonY, cancelBtnSize.width, cancelBtnSize.height);

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
            new PopUpView("There was an error loading the image");        }

        if (cancelRolloverPath != null) {
            try {
                ImageIcon rIcon = new ImageIcon(cancelRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    cancelButton.setRolloverIcon(rIcon);
                    cancelButton.setRolloverEnabled(true);
                }
            } catch (Exception e) {
                new PopUpView("There was an error loading the image");            }
        }

        if (cancelImageLoaded) {
            cancelButton.setBorder(null);
            cancelButton.setBorderPainted(false);
            cancelButton.setContentAreaFilled(false);
            cancelButton.setOpaque(false);
        } else {
            cancelButton.setText("Cancel (Error)");
            cancelButton.setBackground(SECONDARY_COLOR);
            cancelButton.setForeground(TEXT_COLOR);
            cancelButton.setOpaque(true);
            cancelButton.setContentAreaFilled(true);
            cancelButton.setBorder(BorderFactory.createEtchedBorder());
        }
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(cancelButton);


        // Button IMAGES
        removeButton = new JButton();
        Dimension removeBtnSize = new Dimension(buttonWidth, buttonHeight);
        removeButton.setBounds(cancelButton.getX() + buttonWidth + spacing, buttonY, removeBtnSize.width, removeBtnSize.height);

        String removeNormalPath = "res/button_remove.png";
        String removeRolloverPath = "res/button_remove2.png";
        boolean removeImageLoaded = false;

        try {
            ImageIcon icon = new ImageIcon(removeNormalPath);
            if (icon.getIconWidth() > 0) {
                removeButton.setIcon(icon);
                removeImageLoaded = true;
            }
        } catch (Exception e) {
            new PopUpView("There was an error loading the image");        }

        if (removeRolloverPath != null) {
            try {
                ImageIcon rIcon = new ImageIcon(removeRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    removeButton.setRolloverIcon(rIcon);
                    removeButton.setRolloverEnabled(true);
                }
            } catch (Exception e) {
                new PopUpView("There was an error loading the image");            }
        }

        if (removeImageLoaded) {
            removeButton.setBorder(null);
            removeButton.setBorderPainted(false);
            removeButton.setContentAreaFilled(false);
            removeButton.setOpaque(false);
        } else {
            removeButton.setText("Remove (Error)");
            removeButton.setOpaque(true);
            removeButton.setContentAreaFilled(true);
            removeButton.setBorder(BorderFactory.createEtchedBorder());
        }
        removeButton.setFocusPainted(false);
        removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(removeButton);
    }

    /**
     * This method sets an action listener to the remove account button.
     * @param al Action Listener that tells the button what to do when pressed.
     */
    public void setRemoveAccButtonListener(ActionListener al) {
        if (removeButton != null) removeButton.addActionListener(al);
    }
    /**
     * This method sets an action listener to the cancel button.
     * @param al Action Listener that tells the button what to do when pressed.
     */
    public void setCancelButtonListener(ActionListener al) {
        if (cancelButton != null) cancelButton.addActionListener(al);
    }

    /**
     * Getter of the user's password, if it is superior to 150, we inform the user that it is capped.
     * @return String with the user's password.
     */
    public String getPassword() {
        char[] passwordChars = passwordField.getPassword();
        if (passwordChars.length > 150) {
            passwordChars = java.util.Arrays.copyOf(passwordChars, 150);
            new PopUpView("The password can only accept a maximum of 150characters.");
        }
        return new String(passwordChars);
    }

    /**
     * This method will show a message dialog that will say if the account removing went okey or not.
     * @param success Boolean that will tell the method if the account was deleted or not.
     */
    public void showRemoveUserMessage(boolean success) {
        JOptionPane.showMessageDialog(this, success ? "User removed successfully." : "Invalid password. User not removed.",
                success ? "Success" : "Error",
                success ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }
}