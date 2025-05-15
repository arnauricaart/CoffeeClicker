package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * Represents the login window of the application.
 * Extends JFrame and provides fields for username and password input,
 * login button, and a link to register a new user.
 */
public class LoginView extends JFrame {
    /**
     * Text field for the user to input their username or email.
     */
    private JTextField usernameField;
    /**
     * Password field for the user to input their password securely.
     */
    private JPasswordField passwordField;
    /**
     * Button to trigger the login process.
     */
    private JButton loginButton;
    /**
     * Label acting as a clickable link to navigate to the registration screen.
     */
    private JLabel goToRegister;
    /**
     * ActionListener for login button and Enter key presses on input fields.
     */
    private ActionListener loginActionListener;


    /**
     * Constructs the LoginView, setting up the UI components and layout.
     */
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

        ActionListener enterKeyListener = e -> {
            if (loginActionListener != null) {
                loginActionListener.actionPerformed(e);
            }
        };
        
        usernameField.addActionListener(enterKeyListener);
        passwordField.addActionListener(enterKeyListener);
    }

    /**
     * Returns the text currently entered in the username field.
     *
     * @return the username or email input by the user
     */
    public String getUsername() { return usernameField.getText(); }

    /**
     * Returns the password currently entered in the password field.
     *
     * @return the password as a String
     */
    public String getPassword() { return new String(passwordField.getPassword()); }

    /**
     * Sets the ActionListener for the login button and Enter key on input fields.
     *
     * @param al the ActionListener to handle login actions
     */
    public void setLoginButtonListener(ActionListener al) { 
        loginActionListener = al;
        loginButton.addActionListener(al); 
    }

    /**
     * Sets the MouseListener for the register label to handle navigation to registration.
     *
     * @param ma the MouseAdapter to handle mouse events on the register label
     */
    public void setRegisterLabelListener(MouseAdapter ma) { goToRegister.addMouseListener(ma); }

    /**
     * Displays an error message dialog informing the user that login failed.
     */
    public void showLoginErrorMessage() {
        JOptionPane.showMessageDialog(this, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
