package Presentation.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RemoveAccountView extends BaseForm {
    private static final Color TEXT_COLOR = Color.decode("#000000");
    private static final Color SECONDARY_COLOR = Color.decode("#D9D9D9");
    private static final Color BUTTON_COLOR = Color.decode("#9E6B57");
    private static final Color PRIMARY_COLOR = Color.WHITE;
    private JTextField password;
    private JButton removeButton;


    public RemoveAccountView() {
        super("Remove Account");
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

        JLabel title = new JLabel("REMOVE ACCOUNT", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 36));
        title.setBounds(centerX, topY, formWidth, 50);
        title.setForeground(TEXT_COLOR);
        add(title);

        JLabel userLabel = new JLabel("password: ");
        userLabel.setBounds(centerX, topY + 70, formWidth, 30);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(userLabel);


        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(440, 280, 400, 40);
        passwordField.setBackground(Color.decode("#D9D9D9"));
        add(passwordField);


        removeButton = new JButton("Remove");
        removeButton.setBounds(centerX + (fieldWidth - buttonWidth) / 2, topY + 240, buttonWidth, buttonHeight);
        removeButton.setBackground(BUTTON_COLOR);
        removeButton.setForeground(PRIMARY_COLOR);
        add(removeButton);

    }

    public void setRemoveAccButtonListener(ActionListener al) { removeButton.addActionListener(al); }

}
