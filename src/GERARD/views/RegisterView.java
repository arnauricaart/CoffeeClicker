package views;
import views.BaseForm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import constants.CommonConstants;

public class RegisterView extends BaseForm {
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField repasswordField;
    private JButton registerButton;
    private JLabel goToLogin;

    public RegisterView() {
        super("Register");
        initComponents();
    }

    private void initComponents() {
        int formWidth = 400;
        int fieldWidth = 400;
        int fieldHeight = 40;
        int buttonWidth = 250;
        int buttonHeight = 50;
        int centerX = (1280 - formWidth) / 2;
        int topY = 80; // margen superior ajustado para centrar verticalmente mejor

        JLabel title = new JLabel("REGISTER", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 36));
        title.setBounds(centerX, topY, formWidth, 50);
        add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(centerX, topY + 70, formWidth, 30);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(centerX, topY + 100, fieldWidth, fieldHeight);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(centerX, topY + 150, formWidth, 30);
        emailLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(centerX, topY + 180, fieldWidth, fieldHeight);
        emailField.setBackground(CommonConstants.SECONDARY_COLOR);
        add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(centerX, topY + 230, formWidth, 30);
        passLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(centerX, topY + 260, fieldWidth, fieldHeight);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        add(passwordField);

        JLabel repassLabel = new JLabel("Confirm Password:");
        repassLabel.setBounds(centerX, topY + 310, formWidth, 30);
        repassLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(repassLabel);

        repasswordField = new JPasswordField();
        repasswordField.setBounds(centerX, topY + 340, fieldWidth, fieldHeight);
        repasswordField.setBackground(CommonConstants.SECONDARY_COLOR);
        add(repasswordField);

        registerButton = new JButton("Register");
        registerButton.setBounds(centerX + (fieldWidth - buttonWidth) / 2, topY + 400, buttonWidth, buttonHeight);
        registerButton.setBackground(CommonConstants.BUTTON_COLOR);
        registerButton.setForeground(CommonConstants.PRIMARY_COLOR);
        add(registerButton);

        goToLogin = new JLabel("Already registered? Click here", SwingConstants.CENTER);
        goToLogin.setBounds(centerX + (fieldWidth - buttonWidth) / 2, topY + 460, buttonWidth, 30);
        goToLogin.setForeground(CommonConstants.TEXT_COLOR);
        goToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(goToLogin);
    }

    public String getUsername() { return usernameField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }
    public String getRepassword() { return new String(repasswordField.getPassword()); }

    public void setRegisterButtonListener(ActionListener al) { registerButton.addActionListener(al); }
    public void setLoginLabelListener(java.awt.event.MouseAdapter ma) { goToLogin.addMouseListener(ma); }

    public void showRegisterResultMessage(boolean success) {
        JOptionPane.showMessageDialog(this, success ? "Register successful" : "Register failed (username may exist)");
    }
}
