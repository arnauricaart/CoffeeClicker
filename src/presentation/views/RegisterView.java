package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
// No se necesita ImageIO ni URL si las imágenes se cargan directamente con ImageIcon
// y asumimos que tienen el tamaño correcto.

public class RegisterView extends JFrame {
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField repasswordField;
    private JButton registerButton; // Ahora usará imágenes
    private JLabel goToLogin;
    private JLabel backgroundLabel; // Para la imagen de fondo

    public RegisterView() {
        setTitle("Register");
        setSize(1280, 720);
        // setLayout(null); // No en el contentPane directamente si usamos JLayeredPane
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        // getContentPane().setBackground(Color.decode("#FFFFFF")); // El contentPane será transparente

        // Obtenemos el JLayeredPane
        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.setLayout(null); // El layeredPane sí puede tener layout null

        // --- Configuración de la imagen de fondo ---
        String backgroundRegisterPath = "res/register.jpg"; // Ruta de tu imagen de fondo
        try {
            ImageIcon backgroundImgIcon = new ImageIcon(backgroundRegisterPath);
            if (backgroundImgIcon.getIconWidth() > 0) {
                backgroundLabel = new JLabel(backgroundImgIcon);
                backgroundLabel.setBounds(0, 0, getWidth(), getHeight()); // Cubre todo el frame
                layeredPane.add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE)); // Capa más profunda
                System.out.println("Imagen de fondo register cargada OK: " + new java.io.File(backgroundRegisterPath).getAbsolutePath());
            } else {
                System.err.println("ERROR AL CARGAR imagen de fondo register (ancho <=0): " + new java.io.File(backgroundRegisterPath).getAbsolutePath());
                getContentPane().setBackground(Color.decode("#FFFFFF")); // Fallback
            }
        } catch (Exception e) {
            System.err.println("Excepción al cargar imagen de fondo register " + backgroundRegisterPath + ": " + e.getMessage());
            getContentPane().setBackground(Color.decode("#FFFFFF")); // Fallback
        }

        // Hacemos el contentPane transparente y con layout null
        ((JPanel)getContentPane()).setOpaque(false);
        getContentPane().setLayout(null);


        JLabel title = new JLabel("REGISTER", SwingConstants.CENTER);
        title.setFont(new Font("Pixeled", Font.BOLD, 25)); // Puedes cambiar a "Pixeled" si quieres
        title.setBounds(440, 60, 400, 50);
        title.setForeground(Color.decode("#000000")); // Asegura contraste
        getContentPane().add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(440, 120, 400, 30);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        userLabel.setForeground(Color.decode("#000000")); // Asegura contraste
        getContentPane().add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(440, 150, 400, 40);
        usernameField.setBackground(Color.decode("#D9D9D9"));
        getContentPane().add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(440, 200, 400, 30);
        emailLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        emailLabel.setForeground(Color.decode("#000000")); // Asegura contraste
        getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(440, 230, 400, 40);
        emailField.setBackground(Color.decode("#D9D9D9"));
        getContentPane().add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(440, 280, 400, 30);
        passLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        passLabel.setForeground(Color.decode("#000000")); // Asegura contraste
        getContentPane().add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(440, 310, 400, 40);
        passwordField.setBackground(Color.decode("#D9D9D9"));
        getContentPane().add(passwordField);

        JLabel repassLabel = new JLabel("Confirm Password:");
        repassLabel.setBounds(440, 360, 400, 30);
        repassLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        repassLabel.setForeground(Color.decode("#000000")); // Asegura contraste
        getContentPane().add(repassLabel);

        repasswordField = new JPasswordField();
        repasswordField.setBounds(440, 390, 400, 40);
        repasswordField.setBackground(Color.decode("#D9D9D9"));
        getContentPane().add(repasswordField);

        // --- Configuración del registerButton con imágenes ---
        registerButton = new JButton();
        Dimension registerButtonSize = new Dimension(300, 50); // Tamaño original del botón
        registerButton.setPreferredSize(registerButtonSize);
        registerButton.setMinimumSize(registerButtonSize);
        registerButton.setMaximumSize(registerButtonSize);
        registerButton.setBounds(490, 450, registerButtonSize.width, registerButtonSize.height); // Posición original

        String registerNormalPath = "res/button_register.png";
        String registerRolloverPath = "res/button_register2.png";
        boolean registerImageLoaded = false;

        try {
            System.out.println("Intentando cargar imagen normal register: " + new java.io.File(registerNormalPath).getAbsolutePath());
            ImageIcon icon = new ImageIcon(registerNormalPath);
            if (icon.getIconWidth() > 0) {
                registerButton.setIcon(icon);
                registerImageLoaded = true;
                System.out.println("Imagen normal register cargada OK: " + registerNormalPath);
            } else {
                System.err.println("ERROR AL CARGAR imagen normal register (ancho <=0): " + registerNormalPath);
            }
        } catch (Exception e) {
            System.err.println("Excepción al cargar imagen normal register " + registerNormalPath + ": " + e.getMessage());
        }

        if (registerRolloverPath != null) {
            try {
                System.out.println("Intentando cargar imagen rollover register: " + new java.io.File(registerRolloverPath).getAbsolutePath());
                ImageIcon rIcon = new ImageIcon(registerRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    registerButton.setRolloverIcon(rIcon);
                    registerButton.setRolloverEnabled(true);
                    System.out.println("Imagen rollover register cargada OK: " + registerRolloverPath);
                } else {
                    System.err.println("ERROR AL CARGAR imagen rollover register (ancho <=0): " + registerRolloverPath);
                }
            } catch (Exception e) {
                System.err.println("Excepción al cargar imagen rollover register " + registerRolloverPath + ": " + e.getMessage());
            }
        }

        if (registerImageLoaded) {
            registerButton.setBorder(null);
            registerButton.setBorderPainted(false);
            registerButton.setContentAreaFilled(false);
            registerButton.setOpaque(false);
        } else {
            // Fallback si la imagen normal no se cargó
            registerButton.setText("Register (Error)");
            registerButton.setBackground(Color.decode("#9E6B57")); // Color original de fallback
            registerButton.setForeground(Color.WHITE);
            registerButton.setOpaque(true);
            registerButton.setContentAreaFilled(true);
            registerButton.setBorder(BorderFactory.createEtchedBorder());
        }
        registerButton.setFocusPainted(false);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getContentPane().add(registerButton); // Añadir el botón al contentPane


        goToLogin = new JLabel("Already registered? Click here", SwingConstants.CENTER);
        goToLogin.setBounds(515, 510, 250, 30);
        goToLogin.setForeground(Color.decode("#000000")); // Asegura contraste
        goToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getContentPane().add(goToLogin);
    }

    public String getUsername() { return usernameField.getText(); }
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }
    public String getRepassword() { return new String(repasswordField.getPassword()); }

    public void setRegisterButtonListener(ActionListener al) {
        if (registerButton != null) { // Buena práctica verificar
            registerButton.addActionListener(al);
        }
    }
    public void setLoginLabelListener(MouseAdapter ma) { goToLogin.addMouseListener(ma); }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showRegisterResultMessage(boolean b) {
        // Podrías querer mostrar un JOptionPane aquí también para el éxito.
        // Por ejemplo:
        if (b) {
            JOptionPane.showMessageDialog(this, "Registration successful! You can now login.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // El error ya se muestra con showErrorMessage, así que este else podría no ser necesario
            // o podrías tener un mensaje de error genérico aquí si showErrorMessage no fue llamado antes.
        }
    }
}