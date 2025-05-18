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

    public RegisterView() {
        setTitle("Register");
        setSize(1280, 720);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#FFFFFF"));

        JLabel title = new JLabel("REGISTER", SwingConstants.CENTER);
        title.setFont(new Font("Pixeled", Font.BOLD, 25)); // Puedes cambiar a "Pixeled" si quieres
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
                // Si las imágenes no son exactamente 250x50, descomenta y ajusta el escalado:
                // if (icon.getIconWidth() != registerButtonSize.width || icon.getIconHeight() != registerButtonSize.height) {
                //    Image scaledImg = icon.getImage().getScaledInstance(registerButtonSize.width, registerButtonSize.height, Image.SCALE_SMOOTH);
                //    icon = new ImageIcon(scaledImg);
                // }
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
                    // if (rIcon.getIconWidth() != registerButtonSize.width || rIcon.getIconHeight() != registerButtonSize.height) {
                    //    Image scaledRollover = rIcon.getImage().getScaledInstance(registerButtonSize.width, registerButtonSize.height, Image.SCALE_SMOOTH);
                    //    rIcon = new ImageIcon(scaledRollover);
                    // }
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
            // Si el texto "Register" está en la imagen, no necesitas button.setText("Register");
            // Si quieres texto SOBRE la imagen:
            // registerButton.setText("Register");
            // registerButton.setFont(new Font("Pixeled", Font.BOLD, 14)); // o la fuente que desees
            // registerButton.setForeground(Color.WHITE_OR_BLACK); // Color que contraste
            // registerButton.setHorizontalTextPosition(SwingConstants.CENTER);
            // registerButton.setVerticalTextPosition(SwingConstants.CENTER);
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
        add(registerButton); // Añadir el botón al JFrame


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