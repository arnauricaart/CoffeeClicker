package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
// No se importan ImageIO ni URL ya que ImageIcon("ruta") las maneja internamente
// si la ruta es accesible desde el sistema de archivos.

public class MenuGUI extends JFrame {

    // --- Componentes UI ---
    private JButton newGameButton, statisticsButton, logoutButton, deleteAccountButton;
    private final Color TITLE_TEXT_COLOR = Color.BLACK; // Color para el título principal

    /**
     * Constructor de MenuGUI.
     * Configura la ventana principal del menú, incluyendo el título, los botones
     * y las imágenes decorativas. Todos los componentes se posicionan de forma absoluta.
     */
    public MenuGUI() {
        // --- Configuración General de la Ventana (JFrame) ---
        setTitle("Coffee Clicker - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar esta ventana
        setSize(1280, 720);                            // Tamaño fijo de la ventana
        setLocationRelativeTo(null);                   // Centra la ventana en la pantalla
        setResizable(false);                           // Impide que el usuario cambie el tamaño de la ventana

        // --- Panel Principal (Contenedor de todo) ---
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0)); // Usa BorderLayout para distribuir título y contenido
        mainPanel.setBackground(Color.WHITE);                  // Fondo blanco para toda la ventana

        // --- Panel para el Título ---
        JPanel titleHoldingPanel = new JPanel(new GridBagLayout()); // GridBagLayout para centrar fácilmente el JLabel del título
        titleHoldingPanel.setOpaque(false);                         // Hacer el panel transparente para que se vea el fondo de mainPanel
        int titlePanelHeight = 200;                                 // Altura designada para la sección del título
        titleHoldingPanel.setPreferredSize(new Dimension(1280, titlePanelHeight));
        int topMarginForTitle = 160;                                // Margen para empujar el título hacia abajo dentro de su panel
        titleHoldingPanel.setBorder(BorderFactory.createEmptyBorder(topMarginForTitle, 10, 0, 10));

        JLabel titleLabel = new JLabel("WELCOME TO COFFEE CLICKER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Pixeled", Font.BOLD, 25));   // Fuente "Pixeled" (asegúrate que esté disponible)
        titleLabel.setForeground(TITLE_TEXT_COLOR);
        titleHoldingPanel.add(titleLabel);                          // Añadir el título a su panel contenedor

        // --- Panel para el Contenido Debajo del Título ---
        // Este panel usará posicionamiento absoluto (setLayout(null)) para los botones e imágenes
        JPanel contentBelowTitlePanel = new JPanel();
        contentBelowTitlePanel.setLayout(null);                     // Posicionamiento absoluto para los elementos dentro de este panel
        contentBelowTitlePanel.setOpaque(false);                    // Transparente
        int contentBelowTitlePanelHeight = 720 - titlePanelHeight;  // Altura restante del frame para este panel (520px)
        contentBelowTitlePanel.setPreferredSize(new Dimension(1280, contentBelowTitlePanelHeight));

        // --- Carga de Imágenes Decorativas ---
        // Se cargan directamente usando ImageIcon con la ruta relativa a la carpeta "res"
        // Es importante que la carpeta "res" esté en el directorio de trabajo de la aplicación.
        JLabel leftCoffee = null;
        String leftCoffeePath = "res/coffee1.png";
        try {
            ImageIcon coffee1Icon = new ImageIcon(leftCoffeePath);
            if (coffee1Icon.getIconWidth() > 0) { // Una comprobación básica de que la imagen se cargó
                leftCoffee = new JLabel(coffee1Icon);
                // Si las imágenes decorativas necesitan escalado, se haría aquí:
                // Image scaledImg = coffee1Icon.getImage().getScaledInstance(DESIRED_WIDTH, DESIRED_HEIGHT, Image.SCALE_SMOOTH);
                // leftCoffee.setIcon(new ImageIcon(scaledImg));
            } else {
                System.err.println("Error: No se pudo cargar la imagen decorativa: " + leftCoffeePath);
            }
        } catch (Exception e) {
            System.err.println("Excepción al cargar la imagen decorativa " + leftCoffeePath + ": " + e.getMessage());
        }

        JLabel rightCoffee = null;
        String rightCoffeePath = "res/coffee2.png";
        try {
            ImageIcon coffee2Icon = new ImageIcon(rightCoffeePath);
            if (coffee2Icon.getIconWidth() > 0) {
                rightCoffee = new JLabel(coffee2Icon);
            } else {
                System.err.println("Error: No se pudo cargar la imagen decorativa: " + rightCoffeePath);
            }
        } catch (Exception e) {
            System.err.println("Excepción al cargar la imagen decorativa " + rightCoffeePath + ": " + e.getMessage());
        }

        // --- Definición de Dimensiones para los Botones ---
        Dimension mainButtonSize = new Dimension(300, 38);    // Para NewGame, Statistics, Logout
        Dimension deleteButtonSize = new Dimension(200, 38);  // Para Delete Account

        // --- Creación y Configuración de los Botones del Menú ---
        // Se utiliza el método helper 'configureButtonWithImages' para reducir duplicación de código
        newGameButton = new JButton();
        configureButtonWithImages(newGameButton, "res/button_ng.png", "res/button_ng2.png", mainButtonSize, "New Game (Error)");

        statisticsButton = new JButton();
        configureButtonWithImages(statisticsButton, "res/button_statistics.png", "res/button_statistics2.png", mainButtonSize, "Statistics (Error)");

        logoutButton = new JButton();
        configureButtonWithImages(logoutButton, "res/button_logout.png", "res/button_logout2.png", mainButtonSize, "Logout (Error)");

        deleteAccountButton = new JButton();
        // Asegúrate que la imagen de hover es 'button_delete2.png' o 'button_delete1.png' según tu archivo
        configureButtonWithImages(deleteAccountButton, "res/button_delete.png", "res/button_delete2.png", deleteButtonSize, "Delete (Error)");


        // --- Panel para Agrupar los Botones Principales (New Game, Statistics, Logout) ---
        // Este panel usa BoxLayout para apilar los botones verticalmente
        JPanel mainButtonsPanel = new JPanel();
        mainButtonsPanel.setOpaque(false); // Transparente
        mainButtonsPanel.setLayout(new BoxLayout(mainButtonsPanel, BoxLayout.Y_AXIS)); // Layout vertical
        mainButtonsPanel.add(newGameButton);
        mainButtonsPanel.add(Box.createVerticalStrut(16)); // Espaciador vertical
        mainButtonsPanel.add(statisticsButton);
        mainButtonsPanel.add(Box.createVerticalStrut(50)); // Espaciador vertical más grande
        mainButtonsPanel.add(logoutButton);

        // Se establece un tamaño explícito para este panel, crucial cuando su contenedor tiene layout null
        int mainButtonsPanelHeight = (3 * mainButtonSize.height) + 16 + 50; // (3 botones * 38px) + 16px + 50px = 180px
        mainButtonsPanel.setSize(new Dimension(mainButtonSize.width, mainButtonsPanelHeight)); // 300x180


        // --- Posicionamiento Manual de Elementos en 'contentBelowTitlePanel' ---
        // Imágenes decorativas
        if (leftCoffee != null) {
            // Las dimensiones (345, 345) deben coincidir con el tamaño real de la imagen o escalarla.
            leftCoffee.setBounds(10, 0, 345, 345);
            contentBelowTitlePanel.add(leftCoffee);
        }
        if (rightCoffee != null) {
            // Las dimensiones (434, 434) deben coincidir o escalar.
            // La coordenada X (1280 - 345 - 10 - 89) es para posicionarla a la derecha.
            rightCoffee.setBounds(1280 - 345 - 10 - 89, 51, 434, 434);
            contentBelowTitlePanel.add(rightCoffee);
        }

        // Panel con los botones NewGame, Statistics, Logout
        mainButtonsPanel.setBounds(
                (1280 - mainButtonsPanel.getWidth()) / 2, // Centrado horizontalmente: (1280 - 300) / 2 = 490
                80,                                       // Posición Y desde el borde superior de contentBelowTitlePanel
                mainButtonsPanel.getWidth(),              // Ancho del panel (300)
                mainButtonsPanel.getHeight()              // Alto del panel (180)
        );
        contentBelowTitlePanel.add(mainButtonsPanel);

        // Botón Delete Account (posicionado de forma absoluta en la esquina inferior izquierda)
        deleteAccountButton.setBounds(
                18,                                                          // Posición X desde la izquierda
                contentBelowTitlePanelHeight - deleteButtonSize.height - 50, // Posición Y (calculada desde abajo)
                deleteButtonSize.width,                                      // Ancho del botón (200)
                deleteButtonSize.height                                      // Alto del botón (38)
        );
        contentBelowTitlePanel.add(deleteAccountButton);

        // --- Ensamblaje Final de Paneles en el JFrame ---
        mainPanel.add(titleHoldingPanel, BorderLayout.NORTH);     // Título en la parte superior
        mainPanel.add(contentBelowTitlePanel, BorderLayout.CENTER); // Resto del contenido en el centro
        setContentPane(mainPanel);                                  // Establecer mainPanel como el contenedor principal del JFrame
        setVisible(true);                                           // Hacer visible la ventana (¡importante que sea al final!)
    }

    /**
     * Método helper privado para configurar un JButton con una imagen normal y una de rollover.
     * Las imágenes se cargan desde rutas relativas (se espera que estén en la carpeta "res").
     * Si la carga de la imagen normal falla, se muestra un texto de error en el botón.
     *
     * @param button El JButton a configurar.
     * @param normalPath Ruta a la imagen para el estado normal del botón.
     * @param rolloverPath Ruta a la imagen para cuando el ratón está sobre el botón.
     * @param size Dimensiones deseadas para el botón.
     * @param errorText Texto a mostrar si la imagen normal no se puede cargar.
     */
    private void configureButtonWithImages(JButton button, String normalPath, String rolloverPath, Dimension size, String errorText) {
        // Establecer el tamaño preferido, mínimo y máximo para el botón.
        // Esto es importante para BoxLayout y para mantener un tamaño consistente.
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);

        boolean normalImageLoaded = false;
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(normalPath);
            if (icon.getIconWidth() > 0) { // Una forma simple de verificar si la imagen se cargó
                // NOTA: Si las imágenes NO tienen exactamente el tamaño 'size',
                // se deberían escalar aquí para evitar distorsión o tamaño incorrecto. Ejemplo:
                // if (icon.getIconWidth() != size.width || icon.getIconHeight() != size.height) {
                //    Image scaledImg = icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
                //    icon = new ImageIcon(scaledImg);
                // }
                button.setIcon(icon);
                normalImageLoaded = true;
            } else {
                // Este error se imprime si ImageIcon no pudo encontrar/leer el archivo
                System.err.println("Error al cargar (ImageIcon devolvió ancho <=0): " + normalPath);
            }
        } catch (Exception e) {
            // Este error captura otras posibles excepciones durante la carga
            System.err.println("Excepción durante la carga de la imagen normal " + normalPath + ": " + e.getMessage());
        }

        // Configurar imagen de rollover si se proporciona la ruta
        if (rolloverPath != null) {
            try {
                ImageIcon rIcon = new ImageIcon(rolloverPath);
                if (rIcon.getIconWidth() > 0) {
                    // Escalar si es necesario, igual que la imagen normal
                    // if (rIcon.getIconWidth() != size.width || rIcon.getIconHeight() != size.height) {
                    //    Image scaledRollover = rIcon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
                    //    rIcon = new ImageIcon(scaledRollover);
                    // }
                    button.setRolloverIcon(rIcon);
                    button.setRolloverEnabled(true); // Habilitar el efecto de rollover
                } else {
                    System.err.println("Error al cargar (ImageIcon devolvió ancho <=0) imagen rollover: " + rolloverPath);
                }
            } catch (Exception e) {
                System.err.println("Excepción durante la carga de la imagen rollover " + rolloverPath + ": " + e.getMessage());
            }
        }

        // Aplicar estilos si la imagen normal se cargó correctamente
        if (normalImageLoaded) {
            button.setBorder(null);              // Sin borde
            button.setBorderPainted(false);      // No pintar el borde estándar
            button.setContentAreaFilled(false);  // No pintar el área de contenido estándar del botón
            button.setOpaque(false);             // Hacer el componente del botón en sí transparente
        } else {
            // Fallback: Si la imagen normal no se cargó, mostrar texto de error
            button.setText(errorText);
            button.setBackground(Color.LIGHT_GRAY); // Fondo para el botón de error
            button.setForeground(Color.BLACK);      // Texto negro para contraste
            button.setOpaque(true);                 // El botón de error debe ser opaco para mostrar el fondo
            button.setContentAreaFilled(true);      // Pintar el área de contenido
            button.setBorder(BorderFactory.createEtchedBorder()); // Un borde simple
        }

        // Configuraciones comunes para todos los botones del menú
        button.setFocusPainted(false);                         // No mostrar el recuadro de foco al hacer clic
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));      // Cambiar cursor a mano sobre el botón

        // setAlignmentX es relevante para botones dentro de un panel con BoxLayout en eje Y
        // El botón deleteAccountButton se posiciona con setBounds, así que no lo necesita estrictamente.
        if (!(button == deleteAccountButton)) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar horizontalmente en BoxLayout
        }
    }

    // --- Setters para los ActionListeners de los botones (usados por MenuController) ---
    public void setNewGameButtonListener(ActionListener l) { if(newGameButton != null) newGameButton.addActionListener(l); }
    public void setStatisticsButtonListener(ActionListener l) { if(statisticsButton != null) statisticsButton.addActionListener(l); }
    public void setLogoutButtonListener(ActionListener l) { if(logoutButton != null) logoutButton.addActionListener(l); }
    public void setDeleteAccountButtonListener(ActionListener l) { if(deleteAccountButton != null) deleteAccountButton.addActionListener(l); }

    /**
     * Muestra un diálogo informativo si el usuario intenta iniciar un nuevo juego
     * pero ya tiene uno en curso.
     */
    public void showGameExists(){
        JOptionPane.showMessageDialog(this, "You have a game started", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

}