package Presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel goToRegister;
    private ActionListener loginActionListener;

    public LoginView() {
        setTitle("Login");
        setSize(1280, 720);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#FFFFFF"));

        JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 36));
        title.setBounds(440, 100, 400, 50);
        title.setForeground(Color.decode("#000000"));
        add(title);

        JLabel userLabel = new JLabel("Username or Email:");
        userLabel.setBounds(440, 170, 400, 30);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(440, 200, 400, 40);
        usernameField.setBackground(Color.decode("#D9D9D9"));
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(440, 250, 400, 30);
        passLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(440, 280, 400, 40);
        passwordField.setBackground(Color.decode("#D9D9D9"));
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(515, 340, 250, 50);
        loginButton.setBackground(Color.decode("#9E6B57"));
        loginButton.setForeground(Color.WHITE);
        add(loginButton);

        goToRegister = new JLabel("Not a user? Register Here", SwingConstants.CENTER);
        goToRegister.setBounds(515, 400, 250, 30);
        goToRegister.setForeground(Color.decode("#000000"));
        goToRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(goToRegister);

        // Add Enter key support
        ActionListener enterKeyListener = e -> {
            if (loginActionListener != null) {
                loginActionListener.actionPerformed(e);
            }
        };
        
        usernameField.addActionListener(enterKeyListener);
        passwordField.addActionListener(enterKeyListener);
    }

    public String getUsername() { return usernameField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }
    public void setLoginButtonListener(ActionListener al) { 
        loginActionListener = al;
        loginButton.addActionListener(al); 
    }
    public void setRegisterLabelListener(MouseAdapter ma) { goToRegister.addMouseListener(ma); }
    public void showLoginErrorMessage() {
        JOptionPane.showMessageDialog(this, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
