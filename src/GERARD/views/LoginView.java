package views;

import constants.CommonConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class LoginView extends views.BaseForm {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel goToRegister;

    public LoginView() {
        super("Login");
        initComponents();
    }

    private void initComponents() {
        JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 36));
        title.setBounds(200, 50, 400, 50);
        title.setForeground(CommonConstants.TEXT_COLOR);
        add(title);

        JLabel userLabel = new JLabel("Username or Email:");
        userLabel.setBounds(200, 110, 400, 30);
        userLabel.setForeground(CommonConstants.TEXT_COLOR);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 140, 400, 40);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(200, 190, 400, 30);
        passLabel.setForeground(CommonConstants.TEXT_COLOR);
        passLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 220, 400, 40);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(275, 280, 250, 50);
        add(loginButton);

        goToRegister = new JLabel("Not registered? Click here", SwingConstants.CENTER);
        goToRegister.setBounds(275, 340, 250, 30);
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
