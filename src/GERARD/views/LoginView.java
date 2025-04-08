package views;
import views.BaseForm;
import constants.CommonConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class LoginView extends BaseForm {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel goToRegister;

    public LoginView() {
        super("Login");
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

        JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 36));
        title.setBounds(centerX, topY, formWidth, 50);
        title.setForeground(CommonConstants.TEXT_COLOR);
        add(title);

        JLabel userLabel = new JLabel("Username or Email:");
        userLabel.setBounds(centerX, topY + 70, formWidth, 30);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(centerX, topY + 100, fieldWidth, fieldHeight);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(centerX, topY + 150, formWidth, 30);
        passLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(centerX, topY + 180, fieldWidth, fieldHeight);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(centerX + (fieldWidth - buttonWidth) / 2, topY + 240, buttonWidth, buttonHeight);
        loginButton.setBackground(CommonConstants.BUTTON_COLOR);
        loginButton.setForeground(CommonConstants.PRIMARY_COLOR);
        add(loginButton);

        goToRegister = new JLabel("Not a user? Register Here", SwingConstants.CENTER);
        goToRegister.setBounds(centerX + (fieldWidth - buttonWidth) / 2, topY + 300, buttonWidth, 30);
        goToRegister.setForeground(CommonConstants.TEXT_COLOR);
        goToRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(goToRegister);
    }

    public String getUsername() { return usernameField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }
    public void setLoginButtonListener(ActionListener al) { loginButton.addActionListener(al); }
    public void setRegisterLabelListener(MouseAdapter ma) { goToRegister.addMouseListener(ma); }
    public void showLoginResultMessage(boolean success) {
        JOptionPane.showMessageDialog(this, success ? "Login successful" : "Login failed");
    }
}
