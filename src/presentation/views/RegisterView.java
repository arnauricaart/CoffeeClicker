package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class RegisterView extends JFrame {
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField repasswordField;
    private JButton registerButton;
    private JLabel goToLogin;

    public RegisterView() {
        setTitle("Register");
        setSize(1280, 720);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#FFFFFF"));

        JLabel title = new JLabel("REGISTER", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 36));
        title.setBounds(440, 60, 400, 50);
        title.setForeground(Color.decode("#000000"));
        add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(440, 120, 400, 30);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(440, 150, 400, 40);
        usernameField.setBackground(Color.decode("#D9D9D9"));
        add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(440, 200, 400, 30);
        emailLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(440, 230, 400, 40);
        emailField.setBackground(Color.decode("#D9D9D9"));
        add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(440, 280, 400, 30);
        passLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(440, 310, 400, 40);
        passwordField.setBackground(Color.decode("#D9D9D9"));
        add(passwordField);

        JLabel repassLabel = new JLabel("Confirm Password:");
        repassLabel.setBounds(440, 360, 400, 30);
        repassLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(repassLabel);

        repasswordField = new JPasswordField();
        repasswordField.setBounds(440, 390, 400, 40);
        repasswordField.setBackground(Color.decode("#D9D9D9"));
        add(repasswordField);

        registerButton = new JButton("Register");
        registerButton.setBounds(515, 450, 250, 50);
        registerButton.setBackground(Color.decode("#9E6B57"));
        registerButton.setForeground(Color.WHITE);
        add(registerButton);

        goToLogin = new JLabel("Already registered? Click here", SwingConstants.CENTER);
        goToLogin.setBounds(515, 510, 250, 30);
        goToLogin.setForeground(Color.decode("#000000"));
        goToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(goToLogin);
    }

    public String getUsername() { return usernameField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }
    public String getRepassword() { return new String(repasswordField.getPassword()); }

    public void setRegisterButtonListener(ActionListener al) { registerButton.addActionListener(al); }
    public void setLoginLabelListener(MouseAdapter ma) { goToLogin.addMouseListener(ma); }

    public void showRegisterResultMessage(boolean success) {
        JOptionPane.showMessageDialog(this, success ? "Register successful" : "Register failed (username or password may be incorrect)");
    }
}
