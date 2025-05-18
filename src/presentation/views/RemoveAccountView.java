package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RemoveAccountView extends BaseForm {
    // Estas constantes de color ya no se usarán para los botones si tienen imágenes,
    // pero las dejamos por si el título o el fondo del passwordField las necesitan
    // o para los botones de fallback si las imágenes no cargan.
    private static final Color TEXT_COLOR = Color.decode("#000000");
    private static final Color SECONDARY_COLOR = Color.decode("#D9D9D9"); // Usado para el fondo del passwordField
    // private static final Color BUTTON_COLOR = Color.decode("#9E6B57"); // No se usa para removeButton con imagen
    // private static final Color PRIMARY_COLOR = Color.WHITE; // No se usa para el texto de removeButton con imagen

    private JButton removeButton;
    private JButton cancelButton;
    private JPasswordField passwordField;

    public RemoveAccountView() {
        super("Remove Account"); // Llama al constructor de BaseForm
        initComponents();
    }

    private void initComponents() {
        // Las dimensiones y posiciones se mantienen como las tenías
        int formWidth = 400;
        int fieldWidth = 400;
        int fieldHeight = 40;
        int buttonWidth = 120;
        int buttonHeight = 50;
        int centerX = (1280 - fieldWidth) / 2; // Asumiendo que BaseForm tiene 1280 de ancho
        int topY = 120;

        JLabel title = new JLabel("REMOVE ACCOUNT", SwingConstants.CENTER);
        title.setFont(new Font("Pixeled", Font.BOLD, 25)); // Puedes cambiar a "Pixeled"
        title.setBounds(centerX, topY, formWidth, 50);
        title.setForeground(TEXT_COLOR);
        add(title);

        JLabel userLabel = new JLabel("Password:");
        userLabel.setBounds(centerX, topY + 70, formWidth, 30);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(userLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(centerX, topY + 110, fieldWidth, fieldHeight);
        passwordField.setBackground(SECONDARY_COLOR); // Fondo original para el campo de contraseña
        add(passwordField);

        int buttonY = topY + 170;
        int spacing = 20;
        int buttonsStartX = centerX + (fieldWidth - (buttonWidth * 2 + spacing)) / 2; // Para centrar el par de botones

        // --- Configuración del cancelButton con imágenes ---
        cancelButton = new JButton();
        Dimension cancelBtnSize = new Dimension(buttonWidth, buttonHeight);
        // No establecemos setPreferredSize/setMin/setMax aquí porque usamos setBounds
        cancelButton.setBounds(buttonsStartX, buttonY, cancelBtnSize.width, cancelBtnSize.height);

        String cancelNormalPath = "res/button_cancel.png";
        String cancelRolloverPath = "res/button_cancel2.png";
        boolean cancelImageLoaded = false;

        try {
            System.out.println("Intentando cargar imagen normal cancel: " + new java.io.File(cancelNormalPath).getAbsolutePath());
            ImageIcon icon = new ImageIcon(cancelNormalPath);
            if (icon.getIconWidth() > 0) {
                // Si las imágenes no son exactamente buttonWidth x buttonHeight, descomenta y ajusta el escalado:
                // if (icon.getIconWidth() != cancelBtnSize.width || icon.getIconHeight() != cancelBtnSize.height) {
                //    Image scaledImg = icon.getImage().getScaledInstance(cancelBtnSize.width, cancelBtnSize.height, Image.SCALE_SMOOTH);
                //    icon = new ImageIcon(scaledImg);
                // }
                cancelButton.setIcon(icon);
                cancelImageLoaded = true;
                System.out.println("Imagen normal cancel cargada OK: " + cancelNormalPath);
            } else {
                System.err.println("ERROR AL CARGAR imagen normal cancel (ancho <=0): " + cancelNormalPath);
            }
        } catch (Exception e) {
            System.err.println("Excepción al cargar imagen normal cancel " + cancelNormalPath + ": " + e.getMessage());
        }

        if (cancelRolloverPath != null) {
            try {
                System.out.println("Intentando cargar imagen rollover cancel: " + new java.io.File(cancelRolloverPath).getAbsolutePath());
                ImageIcon rIcon = new ImageIcon(cancelRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    // if (rIcon.getIconWidth() != cancelBtnSize.width || rIcon.getIconHeight() != cancelBtnSize.height) {
                    //    Image scaledRollover = rIcon.getImage().getScaledInstance(cancelBtnSize.width, cancelBtnSize.height, Image.SCALE_SMOOTH);
                    //    rIcon = new ImageIcon(scaledRollover);
                    // }
                    cancelButton.setRolloverIcon(rIcon);
                    cancelButton.setRolloverEnabled(true);
                    System.out.println("Imagen rollover cancel cargada OK: " + cancelRolloverPath);
                } else {
                    System.err.println("ERROR AL CARGAR imagen rollover cancel (ancho <=0): " + cancelRolloverPath);
                }
            } catch (Exception e) {
                System.err.println("Excepción al cargar imagen rollover cancel " + cancelRolloverPath + ": " + e.getMessage());
            }
        }

        if (cancelImageLoaded) {
            cancelButton.setBorder(null);
            cancelButton.setBorderPainted(false);
            cancelButton.setContentAreaFilled(false);
            cancelButton.setOpaque(false);
            // Si el texto "Cancel" está en la imagen, no necesitas button.setText("Cancel");
            // Si quieres texto SOBRE la imagen:
            // cancelButton.setText("Cancel");
            // cancelButton.setFont(new Font("Pixeled", Font.BOLD, 12)); // Ajusta fuente y tamaño
            // cancelButton.setForeground(Color.BLACK_OR_WHITE); // Ajusta color
            // cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
            // cancelButton.setVerticalTextPosition(SwingConstants.CENTER);
        } else {
            // Fallback si la imagen normal no se cargó
            cancelButton.setText("Cancel (Error)");
            cancelButton.setBackground(SECONDARY_COLOR); // Color original de fallback
            cancelButton.setForeground(TEXT_COLOR);      // Color de texto original
            cancelButton.setOpaque(true);
            cancelButton.setContentAreaFilled(true);
            cancelButton.setBorder(BorderFactory.createEtchedBorder());
        }
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(cancelButton);


        // --- Configuración del removeButton con imágenes ---
        removeButton = new JButton();
        Dimension removeBtnSize = new Dimension(buttonWidth, buttonHeight);
        removeButton.setBounds(cancelButton.getX() + buttonWidth + spacing, buttonY, removeBtnSize.width, removeBtnSize.height);

        String removeNormalPath = "res/button_remove.png";
        String removeRolloverPath = "res/button_remove2.png";
        boolean removeImageLoaded = false;

        try {
            System.out.println("Intentando cargar imagen normal remove: " + new java.io.File(removeNormalPath).getAbsolutePath());
            ImageIcon icon = new ImageIcon(removeNormalPath);
            if (icon.getIconWidth() > 0) {
                // if (icon.getIconWidth() != removeBtnSize.width || icon.getIconHeight() != removeBtnSize.height) {
                //    Image scaledImg = icon.getImage().getScaledInstance(removeBtnSize.width, removeBtnSize.height, Image.SCALE_SMOOTH);
                //    icon = new ImageIcon(scaledImg);
                // }
                removeButton.setIcon(icon);
                removeImageLoaded = true;
                System.out.println("Imagen normal remove cargada OK: " + removeNormalPath);
            } else {
                System.err.println("ERROR AL CARGAR imagen normal remove (ancho <=0): " + removeNormalPath);
            }
        } catch (Exception e) {
            System.err.println("Excepción al cargar imagen normal remove " + removeNormalPath + ": " + e.getMessage());
        }

        if (removeRolloverPath != null) {
            try {
                System.out.println("Intentando cargar imagen rollover remove: " + new java.io.File(removeRolloverPath).getAbsolutePath());
                ImageIcon rIcon = new ImageIcon(removeRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    // if (rIcon.getIconWidth() != removeBtnSize.width || rIcon.getIconHeight() != removeBtnSize.height) {
                    //    Image scaledRollover = rIcon.getImage().getScaledInstance(removeBtnSize.width, removeBtnSize.height, Image.SCALE_SMOOTH);
                    //    rIcon = new ImageIcon(scaledRollover);
                    // }
                    removeButton.setRolloverIcon(rIcon);
                    removeButton.setRolloverEnabled(true);
                    System.out.println("Imagen rollover remove cargada OK: " + removeRolloverPath);
                } else {
                    System.err.println("ERROR AL CARGAR imagen rollover remove (ancho <=0): " + removeRolloverPath);
                }
            } catch (Exception e) {
                System.err.println("Excepción al cargar imagen rollover remove " + removeRolloverPath + ": " + e.getMessage());
            }
        }

        if (removeImageLoaded) {
            removeButton.setBorder(null);
            removeButton.setBorderPainted(false);
            removeButton.setContentAreaFilled(false);
            removeButton.setOpaque(false);
            // Si el texto "Remove" está en la imagen, no necesitas button.setText("Remove");
        } else {
            // Fallback si la imagen normal no se cargó
            removeButton.setText("Remove (Error)");
            //removeButton.setBackground(BUTTON_COLOR);    // Color original de fallback
            //removeButton.setForeground(PRIMARY_COLOR); // Color de texto original
            removeButton.setOpaque(true);
            removeButton.setContentAreaFilled(true);
            removeButton.setBorder(BorderFactory.createEtchedBorder());
        }
        removeButton.setFocusPainted(false);
        removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(removeButton);
    }

    public void setRemoveAccButtonListener(ActionListener al) {
        if (removeButton != null) removeButton.addActionListener(al);
    }

    public void setCancelButtonListener(ActionListener al) {
        if (cancelButton != null) cancelButton.addActionListener(al);
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void showRemoveUserMessage(boolean success) {
        JOptionPane.showMessageDialog(this, success ? "User removed successfully." : "Invalid password. User not removed.",
                success ? "Success" : "Error",
                success ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }
}