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
     * Label to hold the background image.
     */
    private JLabel backgroundLabel; // Sigue siendo un JLabel


    /**
     * Constructs the LoginView, setting up the UI components and layout.
     */
    public LoginView() {
        setTitle("Login");
        setSize(1280, 720);
        // setLayout(null); // Ya no lo necesitamos en el contentPane directamente si usamos JLayeredPane para el fondo
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Obtenemos el JLayeredPane
        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.setLayout(null); // El layeredPane sí puede tener layout null para posicionar absoluto

        // --- Configuración de la imagen de fondo ---
        String backgroundLoginPath = "res/login.jpg"; // Ruta de tu imagen de fondo
        try {
            ImageIcon backgroundImgIcon = new ImageIcon(backgroundLoginPath);

            if (backgroundImgIcon.getIconWidth() > 0) {
                backgroundLabel = new JLabel(backgroundImgIcon);
                backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
                // Añadir la etiqueta de fondo a una capa inferior del JLayeredPane
                layeredPane.add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE)); // O JLayeredPane.FRAME_CONTENT_LAYER - 10
            } else {
                // Fallback: si la imagen no carga, usar el color de fondo blanco en el contentPane
                getContentPane().setBackground(Color.decode("#FFFFFF"));
            }
        } catch (Exception e) {
            // Fallback: si hay excepción, usar el color de fondo blanco en el contentPane
            getContentPane().setBackground(Color.decode("#FFFFFF"));
        }

        // Hacemos el contentPane transparente para que se vea el fondo del JLayeredPane
        ((JPanel)getContentPane()).setOpaque(false);
        // Y el contentPane también necesita layout null para posicionar los componentes encima del fondo
        getContentPane().setLayout(null);


        JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
        title.setFont(new Font("Pixeled", Font.BOLD, 25)); // Puedes cambiar a "Pixeled" si quieres consistencia
        title.setBounds(440, 100, 400, 50);
        title.setForeground(Color.decode("#000000")); // Asegúrate que este color contraste con tu fondo
        // add(title); // Se añade al contentPane
        getContentPane().add(title);


        JLabel userLabel = new JLabel("Username or Email:");
        userLabel.setBounds(440, 170, 400, 30);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setForeground(Color.decode("#000000")); // Asegúrate que este color contraste
        // add(userLabel);
        getContentPane().add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(440, 200, 400, 40);
        usernameField.setBackground(Color.decode("#D9D9D9"));
        // add(usernameField);
        getContentPane().add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(440, 250, 400, 30);
        passLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        passLabel.setForeground(Color.decode("#000000")); // Asegúrate que este color contraste
        // add(passLabel);
        getContentPane().add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(440, 280, 400, 40);
        passwordField.setBackground(Color.decode("#D9D9D9"));
        // add(passwordField);
        getContentPane().add(passwordField);

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
            ImageIcon icon = new ImageIcon(loginNormalPath);
            if (icon.getIconWidth() > 0) {
                loginButton.setIcon(icon);
                loginImageLoaded = true;
            }
        } catch (Exception e) {
            new PopUpView("There was an error loading the image");
        }

        if (loginRolloverPath != null) {
            try {
                ImageIcon rIcon = new ImageIcon(loginRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    loginButton.setRolloverIcon(rIcon);
                    loginButton.setRolloverEnabled(true);
                }
            } catch (Exception e) {
                new PopUpView("There was an error loading the image");
            }
        }

        if (loginImageLoaded) {
            loginButton.setBorder(null);
            loginButton.setBorderPainted(false);
            loginButton.setContentAreaFilled(false);
            loginButton.setOpaque(false);
        } else {
            loginButton.setText("Login (Error)");
            loginButton.setBackground(Color.decode("#9E6B57"));
            loginButton.setForeground(Color.WHITE);
            loginButton.setOpaque(true);
            loginButton.setContentAreaFilled(true);
            loginButton.setBorder(BorderFactory.createEtchedBorder());
        }
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // add(loginButton);
        getContentPane().add(loginButton);


        goToRegister = new JLabel("Not a user? Register Here", SwingConstants.CENTER);
        goToRegister.setBounds(515, 400, 250, 30);
        goToRegister.setForeground(Color.decode("#000000")); // Asegúrate que este color contraste
        goToRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // add(goToRegister);
        getContentPane().add(goToRegister);


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