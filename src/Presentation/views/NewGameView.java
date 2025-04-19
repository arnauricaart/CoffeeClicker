package Presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewGameView extends BaseForm {
    private static final Color TEXT_COLOR = Color.decode("#000000");
    private static final Color SECONDARY_COLOR = Color.decode("#D9D9D9");
    private static final Color BUTTON_COLOR = Color.decode("#9E6B57");
    private static final Color PRIMARY_COLOR = Color.WHITE;

    private JTextField newGameName;
    private JButton newGameButton;
    private JButton cancelButton;

    public NewGameView() {
        super("New Game");
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

        JLabel title = new JLabel("NEW GAME", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 36));
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

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(buttonsStartX, buttonY, buttonWidth, buttonHeight);
        cancelButton.setBackground(SECONDARY_COLOR);
        cancelButton.setForeground(TEXT_COLOR);
        add(cancelButton);

        newGameButton = new JButton("Start");
        newGameButton.setBounds(buttonsStartX + buttonWidth + spacing, buttonY, buttonWidth, buttonHeight);
        newGameButton.setBackground(BUTTON_COLOR);
        newGameButton.setForeground(PRIMARY_COLOR);
        add(newGameButton);
    }

    public String getNewGameName() {
        return newGameName.getText();
    }

    public void setNewGameButtonListener(ActionListener al) {
        newGameButton.addActionListener(al);
    }

    public void setCancelButtonListener(ActionListener al) {
        cancelButton.addActionListener(al);
    }
}
