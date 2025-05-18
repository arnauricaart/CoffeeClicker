package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
// No se necesita ImageIO ni URL si las imágenes se cargan directamente con ImageIcon
// y asumimos que tienen el tamaño correcto o no necesitamos verificarlo antes.

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
     * Button to trigger the login process. NOW USES IMAGES.
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
        title.setFont(new Font("Pixeled", Font.BOLD, 25)); // Puedes cambiar a "Pixeled" si quieres consistencia
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

        // --- Configuración del loginButton con imágenes ---
        loginButton = new JButton();
        Dimension loginButtonSize = new Dimension(300, 50); // Tamaño original del botón
        loginButton.setPreferredSize(loginButtonSize);
        loginButton.setMinimumSize(loginButtonSize);
        loginButton.setMaximumSize(loginButtonSize);
        loginButton.setBounds(490, 340, loginButtonSize.width, loginButtonSize.height); // Posición original

        String loginNormalPath = "res/button_login.png";
        String loginRolloverPath = "res/button_login2.png";
        boolean loginImageLoaded = false;

        try {
            System.out.println("Intentando cargar imagen normal login: " + new java.io.File(loginNormalPath).getAbsolutePath());
            ImageIcon icon = new ImageIcon(loginNormalPath);
            if (icon.getIconWidth() > 0) {
                // Si las imágenes no son exactamente 250x50, descomenta y ajusta el escalado:
                // if (icon.getIconWidth() != loginButtonSize.width || icon.getIconHeight() != loginButtonSize.height) {
                //    Image scaledImg = icon.getImage().getScaledInstance(loginButtonSize.width, loginButtonSize.height, Image.SCALE_SMOOTH);
                //    icon = new ImageIcon(scaledImg);
                // }
                loginButton.setIcon(icon);
                loginImageLoaded = true;
                System.out.println("Imagen normal login cargada OK: " + loginNormalPath);
            } else {
                System.err.println("ERROR AL CARGAR imagen normal login (ancho <=0): " + loginNormalPath);
            }
        } catch (Exception e) {
            System.err.println("Excepción al cargar imagen normal login " + loginNormalPath + ": " + e.getMessage());
        }

        if (loginRolloverPath != null) {
            try {
                System.out.println("Intentando cargar imagen rollover login: " + new java.io.File(loginRolloverPath).getAbsolutePath());
                ImageIcon rIcon = new ImageIcon(loginRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    // if (rIcon.getIconWidth() != loginButtonSize.width || rIcon.getIconHeight() != loginButtonSize.height) {
                    //    Image scaledRollover = rIcon.getImage().getScaledInstance(loginButtonSize.width, loginButtonSize.height, Image.SCALE_SMOOTH);
                    //    rIcon = new ImageIcon(scaledRollover);
                    // }
                    loginButton.setRolloverIcon(rIcon);
                    loginButton.setRolloverEnabled(true);
                    System.out.println("Imagen rollover login cargada OK: " + loginRolloverPath);
                } else {
                    System.err.println("ERROR AL CARGAR imagen rollover login (ancho <=0): " + loginRolloverPath);
                }
            } catch (Exception e) {
                System.err.println("Excepción al cargar imagen rollover login " + loginRolloverPath + ": " + e.getMessage());
            }
        }

        if (loginImageLoaded) {
            loginButton.setBorder(null);
            loginButton.setBorderPainted(false);
            loginButton.setContentAreaFilled(false);
            loginButton.setOpaque(false);
            // Si el texto "Login" está en la imagen, no necesitas button.setText("Login");
            // Si quieres texto SOBRE la imagen (y no está en la imagen):
            // loginButton.setText("Login");
            // loginButton.setFont(new Font("Pixeled", Font.BOLD, 14)); // o la fuente que desees
            // loginButton.setForeground(Color.WHITE_OR_BLACK); // Color que contraste con tu imagen
            // loginButton.setHorizontalTextPosition(SwingConstants.CENTER);
            // loginButton.setVerticalTextPosition(SwingConstants.CENTER);
        } else {
            // Fallback si la imagen normal no se cargó
            loginButton.setText("Login (Error)");
            loginButton.setBackground(Color.decode("#9E6B57")); // Color original de fallback
            loginButton.setForeground(Color.WHITE);
            loginButton.setOpaque(true);
            loginButton.setContentAreaFilled(true);
            loginButton.setBorder(BorderFactory.createEtchedBorder());
        }
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(loginButton); // Añadir el botón al JFrame


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
        if (loginButton != null) { // Buena práctica verificar
            loginButton.addActionListener(al);
        }
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
        JOptionPane.showMessageDialog(this, "Login failed. Check credentials.", "Login Error", JOptionPane.ERROR_MESSAGE);
    }
}