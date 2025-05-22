package presentation.views;

import presentation.presentationExceptions.EmptyNameException;
import presentation.presentationExceptions.PresentationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * NewGameView class extends from BaseForm class. This class will generate a view where the user will be capable of introducing the new game's name.
 */
public class NewGameView extends BaseForm {
    // Estas constantes de color ya no se usarán para los botones si tienen imágenes,
    // pero las dejamos por si otros elementos las necesitan o para el fallback.
    /**
     * Color used by the JLabels.
     */
    private static final Color TEXT_COLOR = Color.decode("#000000"); // Usado por JLabels
    /**
     * Secondary color.
     */
    private static final Color SECONDARY_COLOR = Color.decode("#D9D9D9"); // Usado por newGameName
    // private static final Color BUTTON_COLOR = Color.decode("#9E6B57"); // No se usa para newGameButton con imagen
    // private static final Color PRIMARY_COLOR = Color.WHITE; // No se usa para texto de newGameButton con imagen

    /**
     * Text Field where the user will put the game's name.
     */
    private JTextField newGameName;
    /**
     * Button that will create the new game.
     */
    private JButton newGameButton;  // Usará imágenes
    /**
     * Button that will cancel the creation of the new game.
     */
    private JButton cancelButton;   // Usará imágenes

    /**
     * Class constructor. Sets the view's title and initiates the components.
     */
    public NewGameView() {
        super("New Game"); // Llama al constructor de BaseForm
        initComponents();
    }

    /**
     * Method that initiates the components.
     */
    private void initComponents() {
        int formWidth = 400;
        int fieldWidth = 400;
        int fieldHeight = 40;
        int buttonWidth = 120;  // Ancho para las imágenes de los botones
        int buttonHeight = 50; // Alto para las imágenes de los botones
        int centerX = (1280 - fieldWidth) / 2; // Asumiendo que BaseForm es 1280 de ancho
        int topY = 120;

        JLabel title = new JLabel("NEW GAME", SwingConstants.CENTER);
        title.setFont(new Font("Pixeled", Font.BOLD, 25)); // Puedes cambiar a "Pixeled" si quieres
        title.setBounds(centerX, topY, formWidth, 50);
        title.setForeground(TEXT_COLOR);
        add(title);

        JLabel userLabel = new JLabel("Name game: ");
        userLabel.setBounds(centerX, topY + 70, formWidth, 30);
        userLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(userLabel);

        newGameName = new JTextField();
        newGameName.setBounds(centerX, topY + 100, fieldWidth, fieldHeight);
        newGameName.setBackground(SECONDARY_COLOR);
        add(newGameName);

        int buttonY = topY + 180;
        int spacing = 20;
        int totalButtonsWidth = buttonWidth * 2 + spacing;
        int buttonsStartX = centerX + (fieldWidth - totalButtonsWidth) / 2; // Para centrar el par de botones

        // --- Configuración del cancelButton con imágenes ---
        cancelButton = new JButton();
        Dimension cancelBtnSize = new Dimension(buttonWidth, buttonHeight);
        // No establecemos setPreferredSize/setMin/setMax aquí porque usamos setBounds
        cancelButton.setBounds(buttonsStartX, buttonY, cancelBtnSize.width, cancelBtnSize.height);

        String cancelNormalPath = "res/button_cancel.png"; // Nombre específico para esta vista
        String cancelRolloverPath = "res/button_cancel2.png";
        boolean cancelImageLoaded = false;

        try {
            System.out.println("Intentando cargar imagen normal cancel (NewGameView): " + new java.io.File(cancelNormalPath).getAbsolutePath());
            ImageIcon icon = new ImageIcon(cancelNormalPath);
            if (icon.getIconWidth() > 0) {
                // Si las imágenes no son exactamente buttonWidth x buttonHeight, descomenta y ajusta el escalado:
                // if (icon.getIconWidth() != cancelBtnSize.width || icon.getIconHeight() != cancelBtnSize.height) {
                //    Image scaledImg = icon.getImage().getScaledInstance(cancelBtnSize.width, cancelBtnSize.height, Image.SCALE_SMOOTH);
                //    icon = new ImageIcon(scaledImg);
                // }
                cancelButton.setIcon(icon);
                cancelImageLoaded = true;
                System.out.println("Imagen normal cancel (NewGameView) cargada OK: " + cancelNormalPath);
            } else {
                System.err.println("ERROR AL CARGAR imagen normal cancel (NewGameView) (ancho <=0): " + cancelNormalPath);
            }
        } catch (Exception e) {
            System.err.println("Excepción al cargar imagen normal cancel (NewGameView) " + cancelNormalPath + ": " + e.getMessage());
        }

        if (cancelRolloverPath != null) {
            try {
                System.out.println("Intentando cargar imagen rollover cancel (NewGameView): " + new java.io.File(cancelRolloverPath).getAbsolutePath());
                ImageIcon rIcon = new ImageIcon(cancelRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    // if (rIcon.getIconWidth() != cancelBtnSize.width || rIcon.getIconHeight() != cancelBtnSize.height) {
                    //    Image scaledRollover = rIcon.getImage().getScaledInstance(cancelBtnSize.width, cancelBtnSize.height, Image.SCALE_SMOOTH);
                    //    rIcon = new ImageIcon(scaledRollover);
                    // }
                    cancelButton.setRolloverIcon(rIcon);
                    cancelButton.setRolloverEnabled(true);
                    System.out.println("Imagen rollover cancel (NewGameView) cargada OK: " + cancelRolloverPath);
                } else {
                    System.err.println("ERROR AL CARGAR imagen rollover cancel (NewGameView) (ancho <=0): " + cancelRolloverPath);
                }
            } catch (Exception e) {
                System.err.println("Excepción al cargar imagen rollover cancel (NewGameView) " + cancelRolloverPath + ": " + e.getMessage());
            }
        }

        if (cancelImageLoaded) {
            makeButtonLookLikeImage(cancelButton);
            // Si el texto "Cancel" está en la imagen, no necesitas lo siguiente.
            // Si quieres texto SOBRE la imagen:
            // cancelButton.setText("Cancel");
            // cancelButton.setFont(new Font("Pixeled", Font.BOLD, 12));
            // cancelButton.setForeground(Color.BLACK_OR_WHITE);
            // cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
            // cancelButton.setVerticalTextPosition(SwingConstants.CENTER);
        } else {
            // Fallback si la imagen normal no se cargó
            cancelButton.setText("Cancel (Error)");
            cancelButton.setBackground(SECONDARY_COLOR); // Color original de fallback
            cancelButton.setForeground(TEXT_COLOR);      // Color de texto original
            applyTextButtonStyles(cancelButton);
        }
        commonButtonViewSetup(cancelButton);
        add(cancelButton);


        // --- Configuración del newGameButton ("Start") con imágenes ---
        newGameButton = new JButton();
        Dimension newGameBtnSize = new Dimension(buttonWidth, buttonHeight);
        newGameButton.setBounds(buttonsStartX + buttonWidth + spacing, buttonY, newGameBtnSize.width, newGameBtnSize.height);

        String newGameNormalPath = "res/button_start.png"; // Asumiendo estos nombres
        String newGameRolloverPath = "res/button_start2.png";
        boolean newGameImageLoaded = false;

        try {
            System.out.println("Intentando cargar imagen normal start: " + new java.io.File(newGameNormalPath).getAbsolutePath());
            ImageIcon icon = new ImageIcon(newGameNormalPath);
            if (icon.getIconWidth() > 0) {
                // if (icon.getIconWidth() != newGameBtnSize.width || icon.getIconHeight() != newGameBtnSize.height) {
                //    Image scaledImg = icon.getImage().getScaledInstance(newGameBtnSize.width, newGameBtnSize.height, Image.SCALE_SMOOTH);
                //    icon = new ImageIcon(scaledImg);
                // }
                newGameButton.setIcon(icon);
                newGameImageLoaded = true;
                System.out.println("Imagen normal start cargada OK: " + newGameNormalPath);
            } else {
                System.err.println("ERROR AL CARGAR imagen normal start (ancho <=0): " + newGameNormalPath);
            }
        } catch (Exception e) {
            System.err.println("Excepción al cargar imagen normal start " + newGameNormalPath + ": " + e.getMessage());
        }

        if (newGameRolloverPath != null) {
            try {
                System.out.println("Intentando cargar imagen rollover start: " + new java.io.File(newGameRolloverPath).getAbsolutePath());
                ImageIcon rIcon = new ImageIcon(newGameRolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    // if (rIcon.getIconWidth() != newGameBtnSize.width || rIcon.getIconHeight() != newGameBtnSize.height) {
                    //    Image scaledRollover = rIcon.getImage().getScaledInstance(newGameBtnSize.width, newGameBtnSize.height, Image.SCALE_SMOOTH);
                    //    rIcon = new ImageIcon(scaledRollover);
                    // }
                    newGameButton.setRolloverIcon(rIcon);
                    newGameButton.setRolloverEnabled(true);
                    System.out.println("Imagen rollover start cargada OK: " + newGameRolloverPath);
                } else {
                    System.err.println("ERROR AL CARGAR imagen rollover start (ancho <=0): " + newGameRolloverPath);
                }
            } catch (Exception e) {
                System.err.println("Excepción al cargar imagen rollover start " + newGameRolloverPath + ": " + e.getMessage());
            }
        }

        if (newGameImageLoaded) {
            makeButtonLookLikeImage(newGameButton);
            // Si el texto "Start" está en la imagen, no necesitas lo siguiente.
        } else {
            // Fallback si la imagen normal no se cargó
            newGameButton.setText("Start (Error)");
            //newGameButton.setBackground(BUTTON_COLOR);    // Color original de fallback
            //newGameButton.setForeground(PRIMARY_COLOR); // Color de texto original
            applyTextButtonStyles(newGameButton);
        }
        commonButtonViewSetup(newGameButton);
        add(newGameButton);
    }

    /**
     * Applies common styles to a button that uses an image as it's principal looks.
     */
    private void makeButtonLookLikeImage(JButton button) {
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
    }

    /**
     * Applies common styles to a text button (fallback).
     */
    private void applyTextButtonStyles(JButton button) {
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorder(BorderFactory.createEtchedBorder());
    }

    /**
     * Applies a common configuration to this view's buttons.
     */
    private void commonButtonViewSetup(JButton button) {
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // No se necesita setAlignmentX para botones posicionados con setBounds.
    }


    public String getNewGameName() {
        return newGameName.getText();
    }

    public void setNewGameButtonListener(ActionListener al) {
        if (newGameButton != null) newGameButton.addActionListener(al);
    }

    public void setCancelButtonListener(ActionListener al) {
        if (cancelButton != null) cancelButton.addActionListener(al);
    }

    public void showDuplicateGameMessage() {
        JOptionPane.showMessageDialog(this, "This game already exists.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showEmptyGameMessage() {
        JOptionPane.showMessageDialog(this, "You must enter the name for the game.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}