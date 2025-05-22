package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
// No se necesitan ImageIO ni URL si las imágenes se cargan directamente con ImageIcon

public class MenuGUI extends JFrame {

    // --- Componentes UI ---
    private JButton newGameButton, statisticsButton, logoutButton, deleteAccountButton;
    private final Color TITLE_TEXT_COLOR = Color.BLACK;

    /**
     * Constructor of the MenuGUI Class.
     * It configures the principal menu screen. The component initialization and load of images is made here.
     */
    public MenuGUI() {
        // --- Configuración General de la Ventana (JFrame) ---
        setTitle("Coffee Clicker - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        // --- Panel Principal (Contenedor de todo) ---
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(Color.WHITE);

        // --- Panel para el Título ---
        JPanel titleHoldingPanel = new JPanel(new GridBagLayout());
        titleHoldingPanel.setOpaque(false);
        int titlePanelHeight = 200;
        titleHoldingPanel.setPreferredSize(new Dimension(1280, titlePanelHeight));
        int topMarginForTitle = 160;
        titleHoldingPanel.setBorder(BorderFactory.createEmptyBorder(topMarginForTitle, 10, 0, 10));

        JLabel titleLabel = new JLabel("WELCOME TO COFFEE CLICKER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Pixeled", Font.BOLD, 25));
        titleLabel.setForeground(TITLE_TEXT_COLOR);
        titleHoldingPanel.add(titleLabel);

        // --- Panel para el Contenido Debajo del Título ---
        JPanel contentBelowTitlePanel = new JPanel();
        contentBelowTitlePanel.setLayout(null);
        contentBelowTitlePanel.setOpaque(false);
        int contentBelowTitlePanelHeight = 720 - titlePanelHeight;
        contentBelowTitlePanel.setPreferredSize(new Dimension(1280, contentBelowTitlePanelHeight));

        // --- Carga de Imágenes Decorativas ---
        JLabel leftCoffee = null;
        String leftCoffeePath = "res/coffee1.png";
        try {
            ImageIcon coffee1Icon = new ImageIcon(leftCoffeePath);
            if (coffee1Icon.getIconWidth() > 0) {
                leftCoffee = new JLabel(coffee1Icon);
                // Si necesitas escalar:
                // Image scaledImg = coffee1Icon.getImage().getScaledInstance(345, 345, Image.SCALE_SMOOTH);
                // leftCoffee.setIcon(new ImageIcon(scaledImg));
            }
        } catch (Exception e) {
            // Error silencioso, leftCoffee permanecerá null
        }

        JLabel rightCoffee = null;
        String rightCoffeePath = "res/coffee2.png";
        try {
            ImageIcon coffee2Icon = new ImageIcon(rightCoffeePath);
            if (coffee2Icon.getIconWidth() > 0) {
                rightCoffee = new JLabel(coffee2Icon);
                // Si necesitas escalar:
                // Image scaledImg = coffee2Icon.getImage().getScaledInstance(434, 434, Image.SCALE_SMOOTH);
                // rightCoffee.setIcon(new ImageIcon(scaledImg));
            }
        } catch (Exception e) {
            // Error silencioso, rightCoffee permanecerá null
        }

        // --- Definición de Dimensiones para los Botones ---
        Dimension mainButtonSize = new Dimension(300, 38);
        Dimension deleteButtonSize = new Dimension(200, 38);

        // --- Creación y Configuración de los Botones del Menú (directamente) ---

        // 1. Botón New Game
        newGameButton = new JButton();
        newGameButton.setPreferredSize(mainButtonSize);
        newGameButton.setMinimumSize(mainButtonSize);
        newGameButton.setMaximumSize(mainButtonSize);
        newGameButton.setFocusPainted(false);
        newGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boolean ngImageLoaded = false;
        try {
            ImageIcon ngIcon = new ImageIcon("res/button_ng.png");
            if (ngIcon.getIconWidth() > 0) {
                // Asumimos que la imagen ya tiene el tamaño. Si no, escalar:
                // Image scaled = ngIcon.getImage().getScaledInstance(mainButtonSize.width, mainButtonSize.height, Image.SCALE_SMOOTH);
                // ngIcon = new ImageIcon(scaled);
                newGameButton.setIcon(ngIcon);
                ngImageLoaded = true;
            }
            ImageIcon ngIcon2 = new ImageIcon("res/button_ng2.png");
            if (ngIcon2.getIconWidth() > 0) {
                // Image scaled2 = ngIcon2.getImage().getScaledInstance(mainButtonSize.width, mainButtonSize.height, Image.SCALE_SMOOTH);
                // ngIcon2 = new ImageIcon(scaled2);
                newGameButton.setRolloverIcon(ngIcon2);
                newGameButton.setRolloverEnabled(true);
            }
        } catch (Exception e) { /* Error silencioso */ }
        if (ngImageLoaded) {
            newGameButton.setBorder(null);
            newGameButton.setBorderPainted(false);
            newGameButton.setContentAreaFilled(false);
            newGameButton.setOpaque(false);
        } else {
            newGameButton.setText("New Game (Error)");
            newGameButton.setBackground(Color.LIGHT_GRAY);
            newGameButton.setForeground(Color.BLACK);
            newGameButton.setOpaque(true);
            newGameButton.setContentAreaFilled(true);
        }

        // 2. Botón Statistics
        statisticsButton = new JButton();
        statisticsButton.setPreferredSize(mainButtonSize);
        statisticsButton.setMinimumSize(mainButtonSize);
        statisticsButton.setMaximumSize(mainButtonSize);
        statisticsButton.setFocusPainted(false);
        statisticsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        statisticsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boolean statImageLoaded = false;
        try {
            ImageIcon statIcon = new ImageIcon("res/button_statistics.png");
            if (statIcon.getIconWidth() > 0) {
                statisticsButton.setIcon(statIcon);
                statImageLoaded = true;
            }
            ImageIcon statIcon2 = new ImageIcon("res/button_statistics2.png");
            if (statIcon2.getIconWidth() > 0) {
                statisticsButton.setRolloverIcon(statIcon2);
                statisticsButton.setRolloverEnabled(true);
            }
        } catch (Exception e) { /* Error silencioso */ }
        if (statImageLoaded) {
            statisticsButton.setBorder(null);
            statisticsButton.setBorderPainted(false);
            statisticsButton.setContentAreaFilled(false);
            statisticsButton.setOpaque(false);
        } else {
            statisticsButton.setText("Statistics (Error)");
            statisticsButton.setBackground(Color.LIGHT_GRAY);
            statisticsButton.setForeground(Color.BLACK);
            statisticsButton.setOpaque(true);
            statisticsButton.setContentAreaFilled(true);
        }

        // 3. Botón Logout
        logoutButton = new JButton();
        logoutButton.setPreferredSize(mainButtonSize);
        logoutButton.setMinimumSize(mainButtonSize);
        logoutButton.setMaximumSize(mainButtonSize);
        logoutButton.setFocusPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boolean logoutImageLoaded = false;
        try {
            ImageIcon logoutIcon = new ImageIcon("res/button_logout.png");
            if (logoutIcon.getIconWidth() > 0) {
                logoutButton.setIcon(logoutIcon);
                logoutImageLoaded = true;
            }
            ImageIcon logoutIcon2 = new ImageIcon("res/button_logout2.png");
            if (logoutIcon2.getIconWidth() > 0) {
                logoutButton.setRolloverIcon(logoutIcon2);
                logoutButton.setRolloverEnabled(true);
            }
        } catch (Exception e) { /* Error silencioso */ }
        if (logoutImageLoaded) {
            logoutButton.setBorder(null);
            logoutButton.setBorderPainted(false);
            logoutButton.setContentAreaFilled(false);
            logoutButton.setOpaque(false);
        } else {
            logoutButton.setText("Logout (Error)");
            logoutButton.setBackground(Color.LIGHT_GRAY);
            logoutButton.setForeground(Color.BLACK);
            logoutButton.setOpaque(true);
            logoutButton.setContentAreaFilled(true);
        }

        // 4. Botón Delete Account
        deleteAccountButton = new JButton();
        deleteAccountButton.setPreferredSize(deleteButtonSize);
        deleteAccountButton.setMinimumSize(deleteButtonSize);
        deleteAccountButton.setMaximumSize(deleteButtonSize);
        deleteAccountButton.setFocusPainted(false);
        deleteAccountButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // No necesita setAlignmentX porque se posiciona con setBounds
        boolean delImageLoaded = false;
        try {
            ImageIcon delIcon = new ImageIcon("res/button_delete.png");
            if (delIcon.getIconWidth() > 0) {
                deleteAccountButton.setIcon(delIcon);
                delImageLoaded = true;
            }
            ImageIcon delIcon2 = new ImageIcon("res/button_delete2.png"); // o button_delete1.png
            if (delIcon2.getIconWidth() > 0) {
                deleteAccountButton.setRolloverIcon(delIcon2);
                deleteAccountButton.setRolloverEnabled(true);
            }
        } catch (Exception e) { /* Error silencioso */ }
        if (delImageLoaded) {
            deleteAccountButton.setBorder(null);
            deleteAccountButton.setBorderPainted(false);
            deleteAccountButton.setContentAreaFilled(false);
            deleteAccountButton.setOpaque(false);
        } else {
            deleteAccountButton.setText("Delete (Error)");
            deleteAccountButton.setBackground(Color.LIGHT_GRAY);
            deleteAccountButton.setForeground(Color.BLACK);
            deleteAccountButton.setOpaque(true);
            deleteAccountButton.setContentAreaFilled(true);
        }

        // --- Panel para Agrupar los Botones Principales ---
        JPanel mainButtonsPanel = new JPanel();
        mainButtonsPanel.setOpaque(false);
        mainButtonsPanel.setLayout(new BoxLayout(mainButtonsPanel, BoxLayout.Y_AXIS));
        mainButtonsPanel.add(newGameButton);
        mainButtonsPanel.add(Box.createVerticalStrut(16));
        mainButtonsPanel.add(statisticsButton);
        mainButtonsPanel.add(Box.createVerticalStrut(50));
        mainButtonsPanel.add(logoutButton);
        int mainButtonsPanelHeight = (3 * mainButtonSize.height) + 16 + 50;
        mainButtonsPanel.setSize(new Dimension(mainButtonSize.width, mainButtonsPanelHeight));

        // --- Posicionamiento Manual de Elementos ---
        if (leftCoffee != null) {
            leftCoffee.setBounds(10, 0, 345, 345);
            contentBelowTitlePanel.add(leftCoffee);
        }
        if (rightCoffee != null) {
            rightCoffee.setBounds(1280 - 345 - 10 - 89, 51, 434, 434);
            contentBelowTitlePanel.add(rightCoffee);
        }
        mainButtonsPanel.setBounds(
                (1280 - mainButtonsPanel.getWidth()) / 2, 80,
                mainButtonsPanel.getWidth(), mainButtonsPanel.getHeight()
        );
        contentBelowTitlePanel.add(mainButtonsPanel);
        deleteAccountButton.setBounds(
                18, contentBelowTitlePanelHeight - deleteButtonSize.height - 50,
                deleteButtonSize.width, deleteButtonSize.height
        );
        contentBelowTitlePanel.add(deleteAccountButton);

        // --- Ensamblaje Final ---
        mainPanel.add(titleHoldingPanel, BorderLayout.NORTH);
        mainPanel.add(contentBelowTitlePanel, BorderLayout.CENTER);
        setContentPane(mainPanel);
        setVisible(true);
    }

    // Ya no hay método configureButtonWithImages

    // Setters para Listeners

    /**
     * This method sets an action listener in the New Game button of the view.
     * @param l Action Listener that will be used when the button is pressed.
     */
    public void setNewGameButtonListener(ActionListener l) { if(newGameButton != null) newGameButton.addActionListener(l); }

    /**
     * This method sets an action listener in the Statistics button of the view.
     * @param l Action Listener that will be used when the button is pressed.
     */
    public void setStatisticsButtonListener(ActionListener l) { if(statisticsButton != null) statisticsButton.addActionListener(l); }
    /**
     * This method sets an action listener in the Logout button of the view.
     * @param l Action Listener that will be used when the button is pressed.
     */
    public void setLogoutButtonListener(ActionListener l) { if(logoutButton != null) logoutButton.addActionListener(l); }
    /**
     * This method sets an action listener in the Delete Account button of the view.
     * @param l Action Listener that will be used when the button is pressed.
     */
    public void setDeleteAccountButtonListener(ActionListener l) { if(deleteAccountButton != null) deleteAccountButton.addActionListener(l); }

    /**
     * This method shows a message dialog in the case that the user had a started a game.
     */
    public void showGameExists(){
        JOptionPane.showMessageDialog(this, "You have a game started", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

}