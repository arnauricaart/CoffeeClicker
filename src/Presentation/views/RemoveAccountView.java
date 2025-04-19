package Presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RemoveAccountView extends BaseForm {
    private static final Color TEXT_COLOR = Color.decode("#000000");
    private static final Color SECONDARY_COLOR = Color.decode("#D9D9D9");
    private static final Color BUTTON_COLOR = Color.decode("#9E6B57");
    private static final Color PRIMARY_COLOR = Color.WHITE;
    private JButton removeButton;
    private JButton cancelButton;
    private JPasswordField passwordField;

    public RemoveAccountView() {
        super("Remove Account");
        initComponents();
    }

    private void initComponents() {
        int formWidth = 400;
        int fieldWidth = 400;
        int fieldHeight = 40;
        int buttonWidth = 120;
        int buttonHeight = 50;
        int centerX = (1280 - fieldWidth) / 2;
        int topY = 120;

        JLabel title = new JLabel("REMOVE ACCOUNT", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 36));
        title.setBounds(centerX, topY, formWidth, 50);
        title.setForeground(TEXT_COLOR);
        add(title);

        JLabel userLabel = new JLabel("Password:");
        userLabel.setBounds(centerX, topY + 70, formWidth, 30);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(userLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(centerX, topY + 110, fieldWidth, fieldHeight);
        passwordField.setBackground(Color.decode("#D9D9D9"));
        add(passwordField);

        int buttonY = topY + 170;
        int spacing = 20;

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(centerX + (fieldWidth - (buttonWidth * 2 + spacing)) / 2, buttonY, buttonWidth, buttonHeight);
        cancelButton.setBackground(SECONDARY_COLOR);
        cancelButton.setForeground(TEXT_COLOR);
        add(cancelButton);

        removeButton = new JButton("Remove");
        removeButton.setBounds(cancelButton.getX() + buttonWidth + spacing, buttonY, buttonWidth, buttonHeight);
        removeButton.setBackground(BUTTON_COLOR);
        removeButton.setForeground(PRIMARY_COLOR);
        add(removeButton);
    }

    public void setRemoveAccButtonListener(ActionListener al) {
        removeButton.addActionListener(al);
    }

    public void setCancelButtonListener(ActionListener al) {
        cancelButton.addActionListener(al);
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void showRemoveUserMessage(boolean success) {
        JOptionPane.showMessageDialog(this, success ? "User removed" : "Invalid password");
    }
}
