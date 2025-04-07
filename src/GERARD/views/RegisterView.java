package views;

import constants.CommonConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class RegisterView extends views.BaseForm {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField repasswordField;
    private JTextField emailField;
    private JButton registerButton;
    private JLabel goToLogin;

    public RegisterView() {
        super("Register");
        initComponents();
    }

    private void initComponents() {
        JLabel title = new JLabel("REGISTER", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 36));
        title.setBounds(200, 30, 400, 50);
        title.setForeground(CommonConstants.TEXT_COLOR);
        add(title);

        // Username
        add(label("Username:", 200, 90, 400, 30));
        usernameField = new JTextField();
        usernameField.setBounds(200, 120, 400, 40);
        add(usernameField);

        // Email
        add(label("Email:", 200, 170, 400, 30));
        emailField = new JTextField();
        emailField.setBounds(200, 200, 400, 40);
        add(emailField);

        // Password
        add(label("Password:", 200, 250, 400, 30));
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 280, 400, 40);
        add(passwordField);

        // Re-enter password
        add(label("Confirm Password:", 200, 330, 400, 30));
        repasswordField = new JPasswordField();
        repasswordField.setBounds(200, 360, 400, 40);
        add(repasswordField);

        // Register button
        registerButton = new JButton("Register");
        registerButton.setBounds(275, 420, 250, 50);
        add(registerButton);

        // Go to login
        goToLogin = new JLabel("Already registered? Click here", SwingConstants.CENTER);
        goToLogin.setBounds(275, 480, 250, 30);
        goToLogin.setForeground(CommonConstants.TEXT_COLOR);
        goToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(goToLogin);
    }

    private JLabel label(String text, int x, int y, int w, int h) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setForeground(CommonConstants.TEXT_COLOR);
        label.setFont(new Font("Dialog", Font.BOLD, 16));
        return label;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getRepassword() {
        return new String(repasswordField.getPassword());
    }

    public void setRegisterButtonListener(ActionListener al) {
        registerButton.addActionListener(al);
    }

    public void setLoginLabelListener(java.awt.event.MouseAdapter ma) {
        goToLogin.addMouseListener(ma);
    }

    public void showRegisterResultMessage(boolean success) {
        JOptionPane.showMessageDialog(this, success ? "Register successful" : "Register failed (username may exist)");
    }
}
