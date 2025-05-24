// Archivo: presentation/views/GameView.java
package presentation.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * The main graphical interface of the Coffee Clicker game.
 * Extends JFrame and manages all UI components and layouts.
 */
public class GameView extends JFrame {

    // UI components used in different parts of the interface
    private JLabel counterLabel, perSecLabel;
    private JButton coffeeButton, coffeeMachineButton, baristaButton, cafeButton;
    private JButton coffeeMachineUpgradeButton, baristaUpgradeButton, cafeUpgradeButton;
    private JButton pauseButton, endGameButton;
    private JTextArea messageText; // JTextArea to display contextual messages to the user

    // Fonts used for various UI elements
    private Font titleFont, subtitleFont, mainTextFont, smallTextFont, buttonShopFont, tableHeaderFont, tableContentFont;

    // Table and related components to show generator statistics
    private JTable generatorStatsTable;
    private DefaultTableModel generatorStatsTableModel;
    private JScrollPane generatorStatsScrollPane;
    private final String[] generatorTableColumns = { // Column names for the generator statistics table
            "Name", "Qty", "Unit Prod", "Total Prod", "% Overall"
    };
    // Labels for titles and sections
    private JLabel generatorTableTitleLabel; // Title label for the generator statistics table
    private JLabel coffeeMakersTitleLabel, upgradesTitleLabel; // Sub-titles for shop sections
    private JLabel shopMainTitleLabel; // Main title for the shop panel
    private JLabel itemDetailsTitleLabel; // Title for the item details/message area

    private JButton instructionsButton; // NUEVA DECLARACIÓN DEL BOTÓN DE INSTRUCCIONES


    /**
     * Constructs the main game view and initializes all fonts and UI components.
     */
    public GameView() {
        createFont();
        createUI();
    }

    /**
     * Makes the GameView window visible.
     */
    public void open() {
        this.setVisible(true);
    }

    /**
     * Closes the GameView window and disposes of its resources.
     */
    public void close() {
        this.dispose();
    }

    /**
     * Initializes all the font styles used throughout the UI.
     */
    private void createFont() {
        titleFont = new Font("Pixeled", Font.BOLD, 20);
        subtitleFont = new Font("Pixeled", Font.BOLD, 15);
        mainTextFont = new Font("Pixeled", Font.PLAIN, 22);
        smallTextFont = new Font("Pixeled", Font.PLAIN, 13);
        buttonShopFont = new Font("Pixeled", Font.BOLD, 12);

        tableHeaderFont = new Font("SansSerif", Font.BOLD, 12);
        tableContentFont = new Font("SansSerif", Font.PLAIN, 11);
    }

    /**
     * Creates and arranges all the user interface elements in the window.
     * This method sets up the main JFrame, panels, labels, buttons, table, and shop area.
     */
    private void createUI() {
        this.setTitle("Coffee Clicker");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Main panels for layout
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBounds(20, 20, 400, 660);
        leftPanel.setBackground(Color.white);
        leftPanel.setOpaque(true);
        this.add(leftPanel);

        JPanel centerPanel = new JPanel(null);
        centerPanel.setBounds(440, 20, 420, 660);
        centerPanel.setBackground(Color.white);
        centerPanel.setOpaque(true);
        this.add(centerPanel);

        JPanel rightPanel = new JPanel(null);
        rightPanel.setBounds(860, 20, 380, 660);
        rightPanel.setBackground(Color.white);
        rightPanel.setOpaque(true);
        this.add(rightPanel);

        // Left Panel: Coffee counter, per second, and main coffee button
        counterLabel = new JLabel("0 coffees");
        counterLabel.setForeground(Color.black);
        counterLabel.setFont(mainTextFont);
        counterLabel.setBounds(20, 20, 360, 50);
        leftPanel.add(counterLabel);

        perSecLabel = new JLabel("per second: 0.0");
        perSecLabel.setForeground(Color.black);
        perSecLabel.setFont(smallTextFont);
        perSecLabel.setBounds(20, 70, 360, 30);
        leftPanel.add(perSecLabel);

        ImageIcon originalIcon = new ImageIcon("res/coffee.png");
        if (originalIcon.getIconWidth() == -1) {
            System.err.println("Error: Imagen 'res/coffee.png' no encontrada.");
            coffeeButton = new JButton("Click Coffee");
            coffeeButton.setFont(mainTextFont);
        } else {
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            coffeeButton = new JButton(new ImageIcon(scaledImage));
        }
        coffeeButton.setBounds(50, 180, 300, 300);
        coffeeButton.setOpaque(false);
        coffeeButton.setContentAreaFilled(false);
        coffeeButton.setBorderPainted(false);
        coffeeButton.setBorder(null); // Eliminado borde por defecto

        Color brownBorderColor = Color.decode("#9E6B57"); // Borde marrón para el botón de café
        int borderThickness = 3;
        coffeeButton.setBorder(BorderFactory.createLineBorder(brownBorderColor, borderThickness));

        leftPanel.add(coffeeButton);

        // --- INICIO: CÓDIGO AÑADIDO PARA EL BOTÓN DE INSTRUCCIONES ---
        instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(this.buttonShopFont);
        instructionsButton.setFocusPainted(false);
        instructionsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Dimension instructionsButtonDim = new Dimension(150, 36);
        instructionsButton.setPreferredSize(instructionsButtonDim);
        instructionsButton.setMinimumSize(instructionsButtonDim);
        instructionsButton.setMaximumSize(instructionsButtonDim);
        instructionsButton.setBounds(20, leftPanel.getHeight() - instructionsButtonDim.height - 20, instructionsButtonDim.width, instructionsButtonDim.height);

        String instructionsNormalPath = "res/button_instructions.png";
        String instructionsRolloverPath = "res/button_instructions2.png";
        boolean instructionImagesLoaded = false;
        try {
            ImageIcon iconNormalIns = new ImageIcon(instructionsNormalPath);
            ImageIcon iconRolloverIns = null;
            if (new File(instructionsRolloverPath).exists()) {
                iconRolloverIns = new ImageIcon(instructionsRolloverPath);
            }

            if (iconNormalIns.getIconWidth() > 0) {
                instructionsButton.setIcon(iconNormalIns);
                if (iconRolloverIns != null && iconRolloverIns.getIconWidth() > 0) {
                    instructionsButton.setRolloverIcon(iconRolloverIns);
                    instructionsButton.setRolloverEnabled(true);
                }
                instructionsButton.setText("");
                instructionsButton.setBorder(null);
                instructionsButton.setBorderPainted(false);
                instructionsButton.setContentAreaFilled(false);
                instructionsButton.setOpaque(false);
                instructionImagesLoaded = true;
            }
        } catch (Exception e) {
            // No se imprime error si la imagen es opcional o se prefiere manejarlo silenciosamente.
        }

        if (!instructionImagesLoaded) {
            instructionsButton.setText("Instructions");
            instructionsButton.setBackground(new Color(210, 210, 225));
            instructionsButton.setForeground(Color.BLACK);
            instructionsButton.setOpaque(true);
            instructionsButton.setContentAreaFilled(true);
            instructionsButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            final Color originalBgIns = instructionsButton.getBackground();
            instructionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (instructionsButton.isEnabled()) instructionsButton.setBackground(originalBgIns.brighter());
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    if (instructionsButton.isEnabled()) instructionsButton.setBackground(originalBgIns);
                }
            });
        }
        instructionsButton.addActionListener(e -> showInstructionsPopup());
        leftPanel.add(instructionsButton);
        // --- FIN: CÓDIGO AÑADIDO PARA EL BOTÓN DE INSTRUCCIONES ---

        // Center Panel: Item Details, Message Area, Generator Table
        itemDetailsTitleLabel = new JLabel("ITEM DETAILS");
        itemDetailsTitleLabel.setFont(titleFont);
        itemDetailsTitleLabel.setForeground(Color.black);
        itemDetailsTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        int itemDetailsTitleHeight = 30;
        int itemDetailsTitleY = 130;
        itemDetailsTitleLabel.setBounds(5, itemDetailsTitleY, 410, itemDetailsTitleHeight);
        centerPanel.add(itemDetailsTitleLabel);

        int messageScrollPaneY = itemDetailsTitleY + itemDetailsTitleHeight + 15; // Espaciado ajustado
        int messageAreaHeight = 170; // Altura ajustada

        messageText = new JTextArea(); // Área para mostrar mensajes contextuales
        messageText.setForeground(Color.black);
        messageText.setBackground(new Color(235, 235, 235));
        messageText.setFont(smallTextFont);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messageText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.lightGray, 1),
                new EmptyBorder(5, 8, 5, 8)
        ));
        JScrollPane messageScrollPane = new JScrollPane(messageText);
        messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messageScrollPane.setBorder(null);
        messageScrollPane.setBounds(5, messageScrollPaneY, 410, messageAreaHeight);
        centerPanel.add(messageScrollPane);

        generatorTableTitleLabel = new JLabel("GENERATOR TABLE");
        styleTitleLabel(generatorTableTitleLabel, titleFont);
        generatorTableTitleLabel.setBorder(new EmptyBorder(5, 0, 5, 10)); // Padding izquierdo a 0 para este título
        generatorTableTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        int generatorTitleY = messageScrollPaneY + messageAreaHeight + 25;
        generatorTableTitleLabel.setBounds(5, generatorTitleY, 410, 30);
        centerPanel.add(generatorTableTitleLabel);

        // Generator Statistics Table
        generatorStatsTableModel = new DefaultTableModel(generatorTableColumns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; } // Celdas no editables
        };
        generatorStatsTable = new JTable(generatorStatsTableModel);
        generatorStatsTable.setFont(tableContentFont);
        generatorStatsTable.getTableHeader().setFont(tableHeaderFont);
        generatorStatsTable.setRowHeight(22);
        generatorStatsTable.setFillsViewportHeight(true); // La tabla ocupa toda la altura del scrollpane
        generatorStatsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        generatorStatsTable.getTableHeader().setReorderingAllowed(false); // No permitir reordenar columnas

        TableColumnModel columnModel = generatorStatsTable.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setResizable(false); // No permitir redimensionar columnas
        }

        generatorStatsTable.setRowSelectionAllowed(false); // No permitir seleccionar filas
        generatorStatsTable.setFocusable(false); // Que la tabla no obtenga el foco


        // Paleta de colores marrón para la tabla
        final Color brownRowDark = new Color(0x5D4037);
        final Color brownRowMiddleLight = new Color(0x795548);
        final Color tableHeaderBg = new Color(0x4E342E);
        final Color tableFg = new Color(0xFFF8E1);
        final Color tableGrid = Color.decode("#9E6B57");
        final Color selectionBg = new Color(0x8D6E63);
        final Color selectionFg = Color.white;

        generatorStatsTable.getTableHeader().setBackground(tableHeaderBg);
        generatorStatsTable.getTableHeader().setForeground(tableFg);
        generatorStatsTable.setGridColor(tableGrid);
        // Los siguientes no tendrán efecto visual si la selección está deshabilitada, pero se definen.
        generatorStatsTable.setSelectionBackground(selectionBg);
        generatorStatsTable.setSelectionForeground(selectionFg);

        // Renderizadores como clases anónimas para colores de fila específicos
        DefaultTableCellRenderer themedLeftRenderer = new DefaultTableCellRenderer() {
            {
                setOpaque(true);
                setHorizontalAlignment(JLabel.LEFT);
            }
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                // isSelected y hasFocus se ignoran ya que la selección está deshabilitada
                super.getTableCellRendererComponent(table, value, false, false, row, column);
                if (row == 1) { // Fila del medio (Barista)
                    setBackground(brownRowMiddleLight);
                } else { // Filas exteriores (Coffee Machine y Cafe)
                    setBackground(brownRowDark);
                }
                setForeground(tableFg);
                return this;
            }
        };

        DefaultTableCellRenderer themedCenterRenderer = new DefaultTableCellRenderer() {
            {
                setOpaque(true);
                setHorizontalAlignment(JLabel.CENTER);
            }
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                super.getTableCellRendererComponent(table, value, false, false, row, column);
                if (row == 1) {
                    setBackground(brownRowMiddleLight);
                } else {
                    setBackground(brownRowDark);
                }
                setForeground(tableFg);
                return this;
            }
        };

        generatorStatsTable.getColumnModel().getColumn(0).setCellRenderer(themedLeftRenderer);
        for (int i = 1; i < generatorTableColumns.length; i++) {
            generatorStatsTable.getColumnModel().getColumn(i).setCellRenderer(themedCenterRenderer);
        }

        int numRowsInTable = 3; // Asumiendo 3 generadores fijos
        int dataRowHeight = generatorStatsTable.getRowHeight();
        int headerHeight = generatorStatsTable.getTableHeader().getPreferredSize().height;
        int tableActualHeight = headerHeight + (numRowsInTable * dataRowHeight);

        generatorStatsScrollPane = new JScrollPane(generatorStatsTable);
        generatorStatsScrollPane.getViewport().setBackground(brownRowDark);
        generatorStatsScrollPane.setOpaque(true);
        generatorStatsScrollPane.setBorder(BorderFactory.createLineBorder(tableGrid));
        generatorStatsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Sin scroll vertical
        int tableY = generatorTitleY + 30 + 15; // Espaciado igual al de itemDetails
        generatorStatsScrollPane.setBounds(5, tableY, 410, tableActualHeight + 2);
        centerPanel.add(generatorStatsScrollPane);

        // Right Panel: Shop
        shopMainTitleLabel = new JLabel("SHOP");
        styleTitleLabel(shopMainTitleLabel, titleFont);
        shopMainTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        shopMainTitleLabel.setBounds(30, 10, 320, 35);
        rightPanel.add(shopMainTitleLabel);

        JPanel shopItemsContainerPanel = new JPanel();
        shopItemsContainerPanel.setBounds(10, 55, 360, 595);
        shopItemsContainerPanel.setBackground(Color.white);
        shopItemsContainerPanel.setOpaque(true);
        shopItemsContainerPanel.setLayout(new BoxLayout(shopItemsContainerPanel, BoxLayout.Y_AXIS));
        shopItemsContainerPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        rightPanel.add(shopItemsContainerPanel);

        coffeeMakersTitleLabel = new JLabel("Coffee Makers");
        styleTitleLabel(coffeeMakersTitleLabel, subtitleFont);
        coffeeMakersTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        shopItemsContainerPanel.add(coffeeMakersTitleLabel);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0,8)));

        coffeeMachineButton = new JButton();
        configureImageButton(coffeeMachineButton, "res/button_coffee.machine.png", "res/button_coffee.machine2.png", "COFFEE MACHINE");
        shopItemsContainerPanel.add(coffeeMachineButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        baristaButton = new JButton();
        configureImageButton(baristaButton, "res/button_interrogant.png", "res/button_interrogant2.png", "???");
        shopItemsContainerPanel.add(baristaButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        cafeButton = new JButton();
        configureImageButton(cafeButton, "res/button_interrogant.png", "res/button_interrogant2.png", "???");
        shopItemsContainerPanel.add(cafeButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        upgradesTitleLabel = new JLabel("Upgrades");
        styleTitleLabel(upgradesTitleLabel, subtitleFont);
        upgradesTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        shopItemsContainerPanel.add(upgradesTitleLabel);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0,8)));

        coffeeMachineUpgradeButton = new JButton();
        configureImageButton(coffeeMachineUpgradeButton, "res/button_u1.png", "res/button_u1.2.png", "QUICK CLEAN");
        shopItemsContainerPanel.add(coffeeMachineUpgradeButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        baristaUpgradeButton = new JButton();
        configureImageButton(baristaUpgradeButton, "res/button_interrogant.png", "res/button_interrogant2.png", "???");
        shopItemsContainerPanel.add(baristaUpgradeButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        cafeUpgradeButton = new JButton();
        configureImageButton(cafeUpgradeButton, "res/button_interrogant.png", "res/button_interrogant2.png", "???");
        shopItemsContainerPanel.add(cafeUpgradeButton);

        shopItemsContainerPanel.add(Box.createVerticalGlue()); // Empuja los botones de pausa/fin hacia abajo

        pauseButton = new JButton();
        configureImageButton(pauseButton, "res/button_pause.png", "res/button_pause2.png", "Pause Game");
        shopItemsContainerPanel.add(pauseButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0,8)));

        endGameButton = new JButton();
        configureImageButton(endGameButton, "res/button_end.png", "res/button_end2.png", "End Game");
        shopItemsContainerPanel.add(endGameButton);

        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0,10))); // Pequeño margen inferior

        // Action Commands para identificación en el controller
        coffeeButton.setActionCommand("COFFEEBUTTON");
        coffeeMachineButton.setActionCommand("COFFEEMACHINEBUTTON");
        baristaButton.setActionCommand("BARISTABUTTON");
        cafeButton.setActionCommand("CAFEBUTTON");
        coffeeMachineUpgradeButton.setActionCommand("COFFEEMACHINEUPGRADEBUTTON");
        baristaUpgradeButton.setActionCommand("BARISTAUPGRADEBUTTON");
        cafeUpgradeButton.setActionCommand("CAFEUPGRADEBUTTON");
        pauseButton.setActionCommand("PAUSEBUTTON");
        endGameButton.setActionCommand("ENDGAMEBUTTON");

        // Nombres para identificación en eventos de ratón (MouseListener)
        coffeeMachineButton.setName("COFFEEMACHINEBUTTON");
        baristaButton.setName("BARISTABUTTON");
        cafeButton.setName("CAFEBUTTON");
        coffeeMachineUpgradeButton.setName("COFFEEMACHINEUPGRADEBUTTON");
        baristaUpgradeButton.setName("BARISTAUPGRADEBUTTON");
        cafeUpgradeButton.setName("CAFEUPGRADEBUTTON");
    }

    /**
     * Applies common styling to section title labels.
     *
     * @param label The JLabel to be styled.
     * @param font  The font to apply.
     */
    private void styleTitleLabel(JLabel label, Font font) {
        label.setFont(font);
        label.setForeground(Color.black);
        label.setBackground(Color.white);
        label.setOpaque(true);
        label.setBorder(new EmptyBorder(5,10,5,10)); // Padding por defecto para títulos
    }

    /**
     * Configures a button with image icons and fallback text.
     * If images are loaded, Java-rendered text is cleared.
     *
     * @param button        The JButton to configure.
     * @param normalPath    The path to the normal icon image.
     * @param rolloverPath  The path to the rollover (hover) icon image.
     * @param fallbackText  Text to display if the icon is missing or fails to load.
     */
    private void configureImageButton(JButton button, String normalPath, String rolloverPath, String fallbackText) {
        button.setFont(this.buttonShopFont);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonDim = new Dimension(300, 36);

        button.setPreferredSize(buttonDim);
        button.setMinimumSize(buttonDim);
        button.setMaximumSize(buttonDim);

        try {
            ImageIcon iconNormal = null;
            File normalFile = new File(normalPath);
            if (normalFile.exists()) {
                iconNormal = new ImageIcon(normalPath);
            } else {
                System.err.println("Imagen no encontrada (normal): " + normalPath + " (Abs: " + normalFile.getAbsolutePath() + ")");
            }

            ImageIcon iconRollover = null;
            if (rolloverPath != null) {
                File rolloverFile = new File(rolloverPath);
                if (rolloverFile.exists()) {
                    iconRollover = new ImageIcon(rolloverPath);
                } else {
                    System.err.println("Imagen no encontrada (rollover): " + rolloverPath + " (Abs: " + rolloverFile.getAbsolutePath() + ")");
                }
            }

            if (iconNormal != null && iconNormal.getIconWidth() > 0) {
                button.setIcon(iconNormal);
                if (iconRollover != null && iconRollover.getIconWidth() > 0) {
                    button.setRolloverIcon(iconRollover);
                    button.setRolloverEnabled(true);
                }
                button.setText(""); // No mostrar texto de Java si la imagen se carga (texto está en la imagen)
                button.setBorder(null);
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setOpaque(false);
            } else {
                button.setText(fallbackText); // Mostrar texto de fallback si la imagen no se carga
                //  CORREGIDO: El segundo parámetro de configureShopButtonLookAndFeel determina si es un item de tienda o de acción
                //  Antes se pasaba 'true' indiscriminadamente, ahora se ajusta.
                configureShopButtonLookAndFeel(button, !(fallbackText.equals("Pause Game") || fallbackText.equals("End Game")));
            }
        } catch (Exception e) {
            System.err.println("Error cargando imágenes para botón (" + fallbackText + "): " + e.getMessage());
            e.printStackTrace();
            button.setText(fallbackText); // Asegurar texto en caso de error
            configureShopButtonLookAndFeel(button, !(fallbackText.equals("Pause Game") || fallbackText.equals("End Game")));
        }
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }


    /**
     * Configures the look and feel of shop-related buttons when images are not used (fallback).
     *
     * @param button The JButton to configure.
     * @param isShopItem Determines the button's style: true for regular shop item, false for action buttons like Pause/End.
     */
    private void configureShopButtonLookAndFeel(JButton button, boolean isShopItem) {
        button.setFont(this.buttonShopFont);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);

        //  CORREGIDO: Se asegura que el tamaño sea el correcto (300x36) para los botones de texto de fallback
        //  ya que configureImageButton ya lo establece, pero es bueno ser explícito o consistente.
        Dimension buttonDim = new Dimension(300, 36);
        button.setPreferredSize(buttonDim);
        button.setMinimumSize(buttonDim);
        button.setMaximumSize(buttonDim);

        // Define colores de fallback para los botones de texto
        if (isShopItem) { // Para botones de compra/mejora de la tienda
            button.setBackground(new Color(50, 50, 50)); // Un gris oscuro
            button.setForeground(Color.white);
            button.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        } else { // Para botones de acción como Pausa, Fin de Juego
            button.setBackground(new Color(100, 30, 30)); // Un rojo oscuro
            button.setForeground(Color.white);
            button.setBorder(BorderFactory.createLineBorder(new Color(70,10,10), 1));
        }
        button.setOpaque(true); // Asegurar que el fondo se pinte para botones de texto

        // Efecto hover simple para botones de texto
        Color originalBg = button.getBackground();
        // Remueve listeners previos para evitar duplicados si este método es llamado múltiples veces.
        for(MouseListener ml : button.getMouseListeners()){
            if(ml.getClass().isAnonymousClass() && ml instanceof java.awt.event.MouseAdapter){
                button.removeMouseListener(ml);
            }
        }
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(button.isEnabled()) button.setBackground(originalBg.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(button.isEnabled()) button.setBackground(originalBg);
            }
        });
    }

    /**
     * Displays the game instructions in a pop-up dialog using a JTextArea.
     * Text is plain, and a JScrollPane is used for scrollability.
     */
    private void showInstructionsPopup() {
        String instructionsTitle = "How to Play Coffee Clicker";
        String instructionsMessage =
                "Welcome to Coffee Clicker!\n\n" +
                        "The main goal is to produce as many coffees as you can, as quickly as possible! \n" +
                        "The game saves your progress automatically every minute.\n\n" +
                        "Gameplay Basics:\n" +
                        "--------------------\n" +
                        "- Manual Clicks: Click the large coffee cup icon on the left to manually produce 1 coffee per click.\n" +
                        "- Coffee Counter: Your current coffee total is displayed at the top-left. \n" +
                        "  Below it, you'll see your 'coffees per second' rate from automatic generators.\n\n" +
                        "The Shop (Right Panel):\n" +
                        "--------------------------\n" +
                        "Use your coffees to buy items from the shop. Hover over an item to see its details \n" +
                        "(cost, production) in the 'ITEM DETAILS' area (center panel).\n\n" +
                        "Coffee Makers (Generators):\n" +
                        "These items automatically produce coffee for you over time.\n" +
                        "- Coffee Machine: Base cost 10 coffees, produces 0.2 c/s. Cost increases by 1.07x each.\n" +
                        "- Barista: Base cost 150 coffees, produces 1.0 c/s. Cost increases by 1.15x each.\n" +
                        "  Unlocks when affordable.\n" +
                        "- Cafe: Base cost 2000 coffees, produces 15 c/s. Cost increases by 1.12x each.\n" +
                        "  Unlocks when affordable.\n" +
                        "The cost for the next generator of a type increases with each purchase according to the formula: \n" +
                        "cost_next_generator = base_cost * increment_cost^number_generators_type.\n\n" +
                        "Upgrades:\n" +
                        "These enhance the production of your Coffee Makers. Upgrades affect both existing and \n" +
                        "future generators of that type.\n" +
                        "- Quick Clean (Coffee Machine Upgrade): Doubles Coffee Machine production.\n" +
                        "  Cost is (Base CM Price) * (Upgrades Owned + 1).\n" +
                        "- Swift Hands (Barista Upgrade): Doubles Barista production.\n" +
                        "  Cost is (Base Barista Price) * (Upgrades Owned + 1). Unlocks with Barista.\n" +
                        "- Happy Hour (Cafe Upgrade): Doubles Cafe production.\n" +
                        "  Cost is (Base Cafe Price) * (Upgrades Owned + 1). Unlocks with Cafe.\n\n" +
                        "Generator Table (Center Panel):\n" +
                        "----------------------------------\n" +
                        "This table shows real-time statistics about your purchased generators:\n" +
                        "- Name: Type of generator.\n" +
                        "- Qty: How many of that type you own.\n" +
                        "- Unit Prod: Coffees per second from one generator of this type (including upgrades).\n" +
                        "- Total Prod: Coffees per second from all generators of this type.\n" +
                        "- % Overall: This type's contribution to your total coffees per second.\n\n" +
                        "Game Controls (Bottom Right of Shop):\n" +
                        "------------------------------------------\n" +
                        "- Pause Game: Saves your current game progress and returns you to the main menu.\n" +
                        "  You can resume your game by selecting 'Start Game' again from the menu.\n" +
                        "- End Game: Finishes your current game. All game data (coffees, duration) is saved \n" +
                        "  for the statistics screen (accessible from the main menu).\n" +
                        "  After ending, you'll need to start a new game from scratch if you wish to play again.\n\n" +
                        "The game has no predefined end, play as long as you like!";

        JTextArea textArea = new JTextArea(instructionsMessage);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        textArea.setBackground(UIManager.getColor("Panel.background"));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(550, 450));

        JOptionPane.showMessageDialog(this, scrollPane, instructionsTitle, JOptionPane.INFORMATION_MESSAGE);
    }


    /** Updates the main message text in the UI, ensuring it runs on the EDT.
     * @param message The message to display.
     */
    public void setMessageText(String message) { SwingUtilities.invokeLater(() -> this.messageText.setText(message)); }

    /** Updates the counter label text in the UI, ensuring it runs on the EDT.
     * @param message The text to display for the counter.
     */
    public void setCounterLableText(String message) { SwingUtilities.invokeLater(() -> this.counterLabel.setText(message)); }

    /** Updates the "per second" rate label in the UI, ensuring it runs on the EDT.
     * @param message The text to display for the per second rate.
     */
    public void setPerSecLabelText(String message) { SwingUtilities.invokeLater(() -> this.perSecLabel.setText(message)); }

    // --- Métodos para actualizar la apariencia completa de los botones de la tienda ---
    // Estos métodos son llamados por GameController para cambiar icono y texto (o fallback)
    // basados en el estado del juego (ej. bloqueado/desbloqueado).

    /** Updates the appearance of the Coffee Machine button. */
    public void updateCoffeeMachineButtonAppearance() {
        configureImageButton(coffeeMachineButton, "res/button_coffee.machine.png", "res/button_coffee.machine2.png", "COFFEE MACHINE");
    }

    /** Updates the appearance of the Coffee Machine upgrade button. */
    public void updateCoffeeMachineUpgradeButtonAppearance() {
        configureImageButton(coffeeMachineUpgradeButton, "res/button_u1.png", "res/button_u1.2.png", "QUICK CLEAN");
    }

    /** Updates the appearance of the Barista button based on its unlocked state.
     * @param unlocked True if the barista is unlocked, false otherwise.
     */
    public void updateBaristaButtonAppearance(boolean unlocked) {
        String nameIfNoImage = unlocked ? "BARISTA" : "???";
        String normalIcon = unlocked ? "res/button_barista.png" : "res/button_interrogant.png";
        String rolloverIcon = unlocked ? "res/button_barista2.png" : "res/button_interrogant2.png";
        configureImageButton(baristaButton, normalIcon, rolloverIcon, nameIfNoImage);
    }

    /** Updates the appearance of the Cafe button based on its unlocked state.
     * @param unlocked True if the cafe is unlocked, false otherwise.
     */
    public void updateCafeButtonAppearance(boolean unlocked) {
        String nameIfNoImage = unlocked ? "CAFE" : "???";
        String normalIcon = unlocked ? "res/button_cafe.png" : "res/button_interrogant.png";
        String rolloverIcon = unlocked ? "res/button_cafe2.png" : "res/button_interrogant2.png";
        configureImageButton(cafeButton, normalIcon, rolloverIcon, nameIfNoImage);
    }

    /** Updates the appearance of the Barista upgrade button based on its unlocked state.
     * @param unlocked True if the barista upgrade is unlocked, false otherwise.
     */
    public void updateBaristaUpgradeButtonAppearance(boolean unlocked) {
        String nameIfNoImage = unlocked ? "SWIFT HANDS" : "???";
        String normalIcon = unlocked ? "res/button_u2.png" : "res/button_interrogant.png";
        String rolloverIcon = unlocked ? "res/button_u2.2.png" : "res/button_interrogant2.png";
        configureImageButton(baristaUpgradeButton, normalIcon, rolloverIcon, nameIfNoImage);
    }

    /** Updates the appearance of the Cafe upgrade button based on its unlocked state.
     * @param unlocked True if the cafe upgrade is unlocked, false otherwise.
     */
    public void updateCafeUpgradeButtonAppearance(boolean unlocked) {
        String nameIfNoImage = unlocked ? "HAPPY HOUR" : "???";
        String normalIcon = unlocked ? "res/button_u3.png" : "res/button_interrogant.png";
        String rolloverIcon = unlocked ? "res/button_u3.2.png" : "res/button_interrogant2.png";
        configureImageButton(cafeUpgradeButton, normalIcon, rolloverIcon, nameIfNoImage);
    }

    /**
     * Updates the generator statistics table with new data.
     * Clears the table and fills it row by row, ensuring it runs on the EDT.
     * @param data A 2D array of Objects representing the table data.
     */
    public void updateGeneratorStatsTable(Object[][] data) {
        SwingUtilities.invokeLater(() -> {
            generatorStatsTableModel.setRowCount(0); // Limpia filas existentes
            if (data != null) {
                for (Object[] rowData : data) {
                    generatorStatsTableModel.addRow(rowData); // Añade nuevas filas
                }
            }
        });
    }

    //  Methods to add Action Listeners
    /** Adds an ActionListener to the main coffee button. @param listener The action listener. */
    public void addCoffeeButtonListener(ActionListener listener) { coffeeButton.addActionListener(listener); }
    /** Adds an ActionListener to the coffee machine purchase button. @param listener The action listener. */
    public void addCoffeeMachineButtonListener(ActionListener listener) { coffeeMachineButton.addActionListener(listener); }
    /** Adds an ActionListener to the barista purchase button. @param listener The action listener. */
    public void addBaristaButtonListener(ActionListener listener) { baristaButton.addActionListener(listener); }
    /** Adds an ActionListener to the café purchase button. @param listener The action listener. */
    public void addCafeButtonListener(ActionListener listener) { cafeButton.addActionListener(listener); }
    /** Adds an ActionListener to the pause button. @param listener The action listener. */
    public void addPauseButtonListener(ActionListener listener) { pauseButton.addActionListener(listener); }
    /** Adds an ActionListener to the end game button. @param listener The action listener. */
    public void addEndGameButtonListener(ActionListener listener) { endGameButton.addActionListener(listener); }
    /** Adds an ActionListener to the coffee machine upgrade button. @param listener The action listener. */
    public void addCoffeeMachineUpgradeButtonListener(ActionListener listener) { coffeeMachineUpgradeButton.addActionListener(listener); }
    /** Adds an ActionListener to the barista upgrade button. @param listener The action listener. */
    public void addBaristaUpgradeButtonListener(ActionListener listener) { baristaUpgradeButton.addActionListener(listener); }
    /** Adds an ActionListener to the café upgrade button. @param listener The action listener. */
    public void addCafeUpgradeButtonListener(ActionListener listener) { cafeUpgradeButton.addActionListener(listener); }

    //  Methods to add Mouse Listeners for contextual help
    /** Adds a MouseListener to the coffee machine purchase button. @param mouseListener The mouse listener. */
    public void addCoffeeMachineButtonMouseListener(MouseListener mouseListener) { coffeeMachineButton.addMouseListener(mouseListener); }
    /** Adds a MouseListener to the barista purchase button. @param mouseListener The mouse listener. */
    public void addBaristaButtonMouseListener(MouseListener mouseListener) { baristaButton.addMouseListener(mouseListener); }
    /** Adds a MouseListener to the café purchase button. @param mouseListener The mouse listener. */
    public void addCafeButtonMouseListener(MouseListener mouseListener) { cafeButton.addMouseListener(mouseListener); }
    /** Adds a MouseListener to the coffee machine upgrade button. @param mouseListener The mouse listener. */
    public void addCoffeeMachineUpgradeButtonMouseListener(MouseListener mouseListener) { coffeeMachineUpgradeButton.addMouseListener(mouseListener); }
    /** Adds a MouseListener to the barista upgrade button. @param mouseListener The mouse listener. */
    public void addBaristaUpgradeButtonMouseListener(MouseListener mouseListener) { baristaUpgradeButton.addMouseListener(mouseListener); }
    /** Adds a MouseListener to the café upgrade button. @param mouseListener The mouse listener. */
    public void addCafeUpgradeButtonMouseListener(MouseListener mouseListener) { cafeUpgradeButton.addMouseListener(mouseListener); }

}