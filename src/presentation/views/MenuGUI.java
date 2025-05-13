package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MenuGUI extends JFrame {

    // --- Componentes UI ---
    private JButton newGameButton, statisticsButton, logoutButton, deleteAccountButton;

    // --- Colores UI ---
    private final Color PLAY_STATS_BUTTON_BG = Color.decode("#9E6B57");
    private final Color LOGOUT_BUTTON_BG = Color.decode("#654338");
    private final Color DELETE_BUTTON_BG = Color.decode("#2F170E");
    private final Color BUTTON_TEXT_FG = Color.WHITE;
    private final Color TITLE_TEXT_COLOR = Color.BLACK;

    /** Botón personalizado con esquinas redondeadas. */
    protected static class RoundedButton extends JButton {
        private Color backgroundColor;
        private int arcWidth, arcHeight;

        public RoundedButton(String text, Color bgColor, Color fgColor, Dimension size, int arcW, int arcH) {
            super(text);
            this.backgroundColor = bgColor;
            this.arcWidth = arcW;
            this.arcHeight = arcH;
            setContentAreaFilled(false); // Permite pintado personalizado
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setFont(new Font("Arial", Font.BOLD, 15));
            setForeground(fgColor);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(size); // Establece tamaño
            setMinimumSize(size);
            setMaximumSize(size);
        }

        @Override // Pinta el botón
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Suaviza

            // Color según estado
            if (getModel().isArmed()) g2.setColor(backgroundColor.darker());
            else if (getModel().isRollover()) g2.setColor(backgroundColor.brighter());
            else g2.setColor(backgroundColor);

            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight); // Dibuja fondo
            super.paintComponent(g2); // Pinta texto
            g2.dispose();
        }
    }

    /** Constructor: configura la ventana del menú. */
    public MenuGUI() {
        // --- Configuración Ventana ---
        setTitle("Coffee Clicker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720); // Tamaño fijo
        setLocationRelativeTo(null); // Centrada
        setResizable(false);

        // --- Panel Principal ---
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(Color.WHITE);

        // --- Panel Título (Superior) ---
        JPanel titleHoldingPanel = new JPanel(new GridBagLayout()); // Para centrar título
        titleHoldingPanel.setOpaque(false); // Transparente

        int titlePanelHeight = 200; // Altura fija para esta sección
        titleHoldingPanel.setPreferredSize(new Dimension(1280, titlePanelHeight));

        // Márgenes para posicionar título (top empuja hacia abajo)
        int topMarginForTitle = 160;
        int bottomMarginForTitle = 0; // Fijado a 0
        // Espacio útil vertical para el título: titlePanelHeight - topMarginForTitle = 40px
        titleHoldingPanel.setBorder(BorderFactory.createEmptyBorder(topMarginForTitle, 10, bottomMarginForTitle, 10));

        // Etiqueta Título
        JLabel titleLabel = new JLabel("WELCOME TO COFFEE CLICKER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(TITLE_TEXT_COLOR);
        titleHoldingPanel.add(titleLabel);

        // --- Panel Contenido (Central) ---
        JPanel contentBelowTitlePanel = new JPanel();
        contentBelowTitlePanel.setLayout(null); // Posicionamiento manual
        contentBelowTitlePanel.setOpaque(false);
        int contentBelowTitlePanelHeight = 720 - titlePanelHeight; // Altura restante (520px)
        contentBelowTitlePanel.setPreferredSize(new Dimension(1280, contentBelowTitlePanelHeight));

        // --- Imágenes Decorativas ---
        JLabel leftCoffee = loadImage("/coffee1.png");
        JLabel rightCoffee = loadImage("/coffee2.png");

        // --- Dimensiones Botones ---
        Dimension playStatsButtonSize = new Dimension(300, 38);
        Dimension logoutButtonSize = new Dimension(300, 38);
        Dimension deleteButtonDim = new Dimension(200, 38);

        // --- Creación Botones ---
        newGameButton = createStyledButton("NEW/CONTINUE GAME", PLAY_STATS_BUTTON_BG, BUTTON_TEXT_FG, playStatsButtonSize);
        statisticsButton = createStyledButton("STATISTICS OF GAME", PLAY_STATS_BUTTON_BG, BUTTON_TEXT_FG, playStatsButtonSize);
        logoutButton = createStyledButton("LOGOUT", LOGOUT_BUTTON_BG, BUTTON_TEXT_FG, logoutButtonSize);
        deleteAccountButton = createStyledButton("DELETE ACCOUNT", DELETE_BUTTON_BG, BUTTON_TEXT_FG, deleteButtonDim);

        // --- Panel para agrupar botones principales ---
        JPanel mainButtonsPanel = new JPanel();
        mainButtonsPanel.setOpaque(false);
        mainButtonsPanel.setLayout(new BoxLayout(mainButtonsPanel, BoxLayout.Y_AXIS)); // Vertical
        mainButtonsPanel.add(newGameButton);
        mainButtonsPanel.add(Box.createVerticalStrut(16)); // Espacio
        mainButtonsPanel.add(statisticsButton);
        mainButtonsPanel.add(Box.createVerticalStrut(50)); // Espacio
        mainButtonsPanel.add(logoutButton);
        mainButtonsPanel.setSize(new Dimension(300, 180)); // Tamaño panel botones

        // --- Posicionamiento Manual en contentBelowTitlePanel ---
        // (X, Y, Ancho, Alto)
        if (leftCoffee != null) {
            leftCoffee.setBounds(10, 0, 345, 345);
            contentBelowTitlePanel.add(leftCoffee);
        }
        if (rightCoffee != null) {
            // Nota: La coordenada Y (51) de coffee2_Y parece un valor específico de tu diseño.
            rightCoffee.setBounds(1280 - 434 - 10, 51, 434, 434);
            contentBelowTitlePanel.add(rightCoffee);
        }
        mainButtonsPanel.setBounds((1280 - mainButtonsPanel.getWidth()) / 2, 80, mainButtonsPanel.getWidth(), mainButtonsPanel.getHeight());
        contentBelowTitlePanel.add(mainButtonsPanel);
        deleteAccountButton.setBounds(18, contentBelowTitlePanelHeight - deleteButtonDim.height - 50, deleteButtonDim.width, deleteButtonDim.height);
        contentBelowTitlePanel.add(deleteAccountButton);

        // --- Ensamblaje Final ---
        mainPanel.add(titleHoldingPanel, BorderLayout.NORTH);    // Título arriba
        mainPanel.add(contentBelowTitlePanel, BorderLayout.CENTER); // Contenido en centro

        setContentPane(mainPanel);
        setVisible(true); // Mostrar ventana
    }

    /** Crea un botón redondeado con estilo. */
    private JButton createStyledButton(String text, Color bgColor, Color fgColor, Dimension buttonSize) {
        RoundedButton button = new RoundedButton(text, bgColor, fgColor, buttonSize, 10, 10); // 10 = redondez
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    /** Carga imagen del classpath como JLabel. */
    private JLabel loadImage(String path) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl != null) {
            return new JLabel(new ImageIcon(imgUrl));
        } else { // Error al cargar
            System.err.println("⚠ Imagen no encontrada: " + path);
            JLabel errorLabel = new JLabel("Falta: " + (path.startsWith("/") ? path.substring(1) : path));
            errorLabel.setPreferredSize(new Dimension(100,50));
            return errorLabel;
        }
    }

    // --- Setters para Listeners (usados por el Controller) ---
    public void setNewGameButtonListener(java.awt.event.ActionListener l) { newGameButton.addActionListener(l); }
    public void setStatisticsButtonListener(java.awt.event.ActionListener l) { statisticsButton.addActionListener(l); }
    public void setLogoutButtonListener(java.awt.event.ActionListener l) { logoutButton.addActionListener(l); }
    public void setDeleteAccountButtonListener(java.awt.event.ActionListener l) { deleteAccountButton.addActionListener(l); }

    /** Muestra mensaje si ya hay partida iniciada. */
    public void showGameExists(){
        JOptionPane.showMessageDialog(this, "You have a game started", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

}