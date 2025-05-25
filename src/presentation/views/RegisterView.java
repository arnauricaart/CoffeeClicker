package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * This class extends from JFrame, this class implements a view that will let the user register.
 */
public class RegisterView extends JFrame {
    /**
     * Text Field where the user will put his user name.
     */
    private JTextField usernameField;
    /**
     * Text Field where the user will put the user's mail.
     */
    private JTextField emailField;
    /**
     * Password Field where the user will put the password.
     */
    private JPasswordField passwordField;
    /**
     * Password field where the user will have to repeat the password.
     */
    private JPasswordField repasswordField;
    /**
     * Button that will let the user register himself.
     */
    private JButton registerButton;
    /**
     * Pressable label that will let the user go back to the login view.
     */
    private JLabel goToLogin;
    /**
     * Label that will have the background image.
     */
    private JLabel backgroundLabel;

    /**
     * Constructor of the class. Sets everything in the view, from the title to the background image.
     */
    public RegisterView() {
        setTitle("Register");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.setLayout(null);

        // Background IMAGE
        String backgroundRegisterPath = "res/register.jpg";
        try {
            ImageIcon backgroundImgIcon = new ImageIcon(backgroundRegisterPath);
            if (backgroundImgIcon.getIconWidth() > 0) {
                backgroundLabel = new JLabel(backgroundImgIcon);
                backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
                layeredPane.add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));
            }
        } catch (Exception e) {
            new PopUpView("There was an error loading the image");        }

        ((JPanel)getContentPane()).setOpaque(false);
        getContentPane().setLayout(null);

        JLabel title = new JLabel("REGISTER", SwingConstants.CENTER);
        title.setFont(new Font("Pixeled", Font.BOLD, 25));
        title.setBounds(440, 60, 400, 50);
        title.setForeground(Color.decode("#000000"));
        getContentPane().add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(440, 120, 400, 30);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        userLabel.setForeground(Color.decode("#000000"));
        getContentPane().add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(440, 150, 400, 40);
        usernameField.setBackground(Color.decode("#D9D9D9"));
        getContentPane().add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(440, 200, 400, 30);
        emailLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        emailLabel.setForeground(Color.decode("#000000"));
        getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(440, 230, 400, 40);
        emailField.setBackground(Color.decode("#D9D9D9"));
        getContentPane().add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(440, 280, 400, 30);
        passLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        passLabel.setForeground(Color.decode("#000000"));
        getContentPane().add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(440, 310, 400, 40);
        passwordField.setBackground(Color.decode("#D9D9D9"));
        getContentPane().add(passwordField);

        JLabel repassLabel = new JLabel("Confirm Password:");
        repassLabel.setBounds(440, 360, 400, 30);
        repassLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        repassLabel.setForeground(Color.decode("#000000"));
        getContentPane().add(repassLabel);

        repasswordField = new JPasswordField();
        repasswordField.setBounds(440, 390, 400, 40);
        repasswordField.setBackground(Color.decode("#D9D9D9"));
        getContentPane().add(repasswordField);

        // Button IMAGE
        registerButton = new JButton();
        Dimension registerButtonSize = new Dimension(300, 50);
        registerButton.setPreferredSize(registerButtonSize);
        registerButton.setMinimumSize(registerButtonSize);
        registerButton.setMaximumSize(registerButtonSize);
        registerButton.setBounds(490, 450, registerButtonSize.width, registerButtonSize.height);

        String registerNormalPath = "res/button_register.png";
        String registerRolloverPath = "res/button_register2.png";
        boolean registerImageLoaded = false;

        try {
            ImageIcon icon = new ImageIcon(registerNormalPath);
            if (icon.getIconWidth() > 0) {
                registerButton.setIcon(icon);
                registerImageLoaded = true;
            }
        } catch (Exception e) {
            new PopUpView("There was an error loading the image");
        }

        if (registerRolloverPath != null) {
            try {
                ImageIcon rIcon = new ImageIcon(registerRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    registerButton.setRolloverIcon(rIcon);
                    registerButton.setRolloverEnabled(true);
                }
            } catch (Exception e) {
                new PopUpView("There was an error loading the image");            }
        }

        if (registerImageLoaded) {
            registerButton.setBorder(null);
            registerButton.setBorderPainted(false);
            registerButton.setContentAreaFilled(false);
            registerButton.setOpaque(false);
        } else {
            registerButton.setText("Register (Error)");
            registerButton.setBackground(Color.decode("#9E6B57"));
            registerButton.setForeground(Color.WHITE);
            registerButton.setOpaque(true);
            registerButton.setContentAreaFilled(true);
            registerButton.setBorder(BorderFactory.createEtchedBorder());
        }
        registerButton.setFocusPainted(false);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getContentPane().add(registerButton);


        goToLogin = new JLabel("Already registered? Click here", SwingConstants.CENTER);
        goToLogin.setBounds(515, 510, 250, 30);
        goToLogin.setForeground(Color.decode("#000000"));
        goToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getContentPane().add(goToLogin);
    }

    /**
     * Getter of the user name.
     * @return String with the user name.
     */
    public String getUsername() { return usernameField.getText(); }

    /**
     * Getter of the user mail.
     * @return String with the user mail.
     */
    public String getEmail() { return emailField.getText(); }

    /**
     * Getter of the user's password.
     * @return String with the user's password.
     */
    public String getPassword() { return new String(passwordField.getPassword()); }

    /**
     * Getter of the user's repassword.
     * @return String with the user's repassword.
     */
    public String getRepassword() { return new String(repasswordField.getPassword()); }

    /**
     * This method sets an action listener to the register button.
     * @param al Action Listener that tells the button what to do when pressed.
     */
    public void setRegisterButtonListener(ActionListener al) {
        if (registerButton != null) {
            registerButton.addActionListener(al);
        }
    }
    /**
     * This method sets an action listener to the login label.
     * @param al Action Listener that tells the login label what to do when pressed.
     */
    public void setLoginLabelListener(MouseAdapter ma) { goToLogin.addMouseListener(ma); }

    /**
     * This method shows a message dialog for the error messages.
     * @param message String with the message to be shown.
     */
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method shows a message dialog for the register result.
     * @param b Boolean that will tell this method if the register went succesfully or not.
     */
    public void showRegisterResultMessage(boolean b) {
        if (b) {
            JOptionPane.showMessageDialog(this, "Registration successful! You can now login.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}