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
    private JLabel goToRegister;

    public NewGameView() {
        super("New Game");
        initComponents();
    }

    private void initComponents() {
        int formWidth = 400;
        int fieldWidth = 400;
        int fieldHeight = 40;
        int buttonWidth = 250;
        int buttonHeight = 50;
        int centerX = (1280 - fieldWidth) / 2;
        int topY = 120; // para centrar verticalmente

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


        newGameButton = new JButton("Start");
        newGameButton.setBounds(centerX + (fieldWidth - buttonWidth) / 2, topY + 240, buttonWidth, buttonHeight);
        newGameButton.setBackground(BUTTON_COLOR);
        newGameButton.setForeground(PRIMARY_COLOR);
        add(newGameButton);

    }

    public String getNewGameName() { return newGameName.getText(); }
    public void setNewGameButtonListener(ActionListener al) { newGameButton.addActionListener(al); }

}
