package presentation.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
    private JTextArea messageText;

    // Fonts used for various UI elements
    private Font titleFont, subtitleFont, mainTextFont, smallTextFont, buttonShopFont, tableHeaderFont, tableContentFont;

    // Table and related components to show generator statistics
    private JTable generatorStatsTable;
    private DefaultTableModel generatorStatsTableModel;
    private JScrollPane generatorStatsScrollPane;
    private final String[] generatorTableColumns = {
            "Name", "Qty", "Unit Prod", "Total Prod", "% Overall"
    };
    // Labels for titles and sections
    private JLabel generatorTableTitleLabel;
    private JLabel coffeeMakersTitleLabel, upgradesTitleLabel;
    private JLabel shopMainTitleLabel;


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
     */
    private void createUI() {
        this.setTitle("Coffee Clicker");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // <--- LÍNEA AÑADIDA PARA CENTRAR LA VENTANA

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
        coffeeButton.setFocusPainted(false);
        coffeeButton.setBorder(null);
        leftPanel.add(coffeeButton);

        messageText = new JTextArea();
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
        int messageAreaHeight = 160;
        messageScrollPane.setBounds(5, 130, 410, messageAreaHeight);
        centerPanel.add(messageScrollPane);

        generatorTableTitleLabel = new JLabel("GENERATOR TABLE");
        styleTitleLabel(generatorTableTitleLabel, titleFont);
        generatorTableTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        int generatorTitleY = 130 + messageAreaHeight + 35;
        generatorTableTitleLabel.setBounds(5, generatorTitleY, 410, 30);
        centerPanel.add(generatorTableTitleLabel);

        generatorStatsTableModel = new DefaultTableModel(generatorTableColumns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        generatorStatsTable = new JTable(generatorStatsTableModel);
        generatorStatsTable.setFont(tableContentFont);
        generatorStatsTable.getTableHeader().setFont(tableHeaderFont);
        generatorStatsTable.setRowHeight(22);
        generatorStatsTable.setFillsViewportHeight(true);
        generatorStatsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        Color tableRowBg = new Color(35, 35, 35);
        Color tableHeaderBg = new Color(20, 20, 20);
        Color tableFg = Color.white;
        Color tableGrid = new Color(80, 80, 80);

        generatorStatsTable.setBackground(tableRowBg);
        generatorStatsTable.setForeground(tableFg);
        generatorStatsTable.getTableHeader().setBackground(tableHeaderBg);
        generatorStatsTable.getTableHeader().setForeground(tableFg);
        generatorStatsTable.setGridColor(tableGrid);
        generatorStatsTable.setOpaque(true);

        DefaultTableCellRenderer darkCenterRenderer = new DefaultTableCellRenderer();
        darkCenterRenderer.setHorizontalAlignment(JLabel.CENTER);
        darkCenterRenderer.setBackground(tableRowBg);
        darkCenterRenderer.setForeground(tableFg);

        DefaultTableCellRenderer darkLeftRenderer = new DefaultTableCellRenderer();
        darkLeftRenderer.setHorizontalAlignment(JLabel.LEFT);
        darkLeftRenderer.setBackground(tableRowBg);
        darkLeftRenderer.setForeground(tableFg);

        generatorStatsTable.getColumnModel().getColumn(0).setCellRenderer(darkLeftRenderer);
        for (int i = 1; i < generatorTableColumns.length; i++) {
            generatorStatsTable.getColumnModel().getColumn(i).setCellRenderer(darkCenterRenderer);
        }

        int numRowsInTable = 3;
        int dataRowHeight = generatorStatsTable.getRowHeight();
        int headerHeight = generatorStatsTable.getTableHeader().getPreferredSize().height;
        int tableActualHeight = headerHeight + (numRowsInTable * dataRowHeight);

        generatorStatsScrollPane = new JScrollPane(generatorStatsTable);
        generatorStatsScrollPane.getViewport().setBackground(tableRowBg);
        generatorStatsScrollPane.setOpaque(true);
        generatorStatsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        int tableY = generatorTitleY + 30 + 10;
        generatorStatsScrollPane.setBounds(5, tableY, 410, tableActualHeight + 2);
        centerPanel.add(generatorStatsScrollPane);

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
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        coffeeMachineButton = new JButton();
        configureImageButton(coffeeMachineButton, "res/button_coffee.machine.png", "res/button_coffee.machine2.png", "Coffee Machine (0)");
        shopItemsContainerPanel.add(coffeeMachineButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        baristaButton = new JButton();
        configureImageButton(baristaButton, "res/button_barista.png", "res/button_barista2.png", "???");
        shopItemsContainerPanel.add(baristaButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        cafeButton = new JButton();
        configureImageButton(cafeButton, "res/button_cafe.png", "res/button_cafe2.png", "???");
        shopItemsContainerPanel.add(cafeButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        upgradesTitleLabel = new JLabel("Upgrades");
        styleTitleLabel(upgradesTitleLabel, subtitleFont);
        upgradesTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        shopItemsContainerPanel.add(upgradesTitleLabel);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        coffeeMachineUpgradeButton = new JButton();
        configureImageButton(coffeeMachineUpgradeButton, "res/button_u1.png", "res/button_u1.2.png", "Upgrade Machine (0)");
        shopItemsContainerPanel.add(coffeeMachineUpgradeButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        baristaUpgradeButton = new JButton();
        configureImageButton(baristaUpgradeButton, "res/button_u2.png", "res/button_u2.2.png", "???");
        shopItemsContainerPanel.add(baristaUpgradeButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        cafeUpgradeButton = new JButton();
        configureImageButton(cafeUpgradeButton, "res/button_u3.png", "res/button_u3.2.png", "???");
        shopItemsContainerPanel.add(cafeUpgradeButton);

        shopItemsContainerPanel.add(Box.createVerticalGlue());

        pauseButton = new JButton();
        configureImageButton(pauseButton, "res/button_pause.png", "res/button_pause2.png", "Pause Game");
        shopItemsContainerPanel.add(pauseButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        endGameButton = new JButton();
        configureImageButton(endGameButton, "res/button_end.png", "res/button_end2.png", "End Game");
        shopItemsContainerPanel.add(endGameButton);

        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0,10)));

        coffeeButton.setActionCommand("COFFEEBUTTON");
        coffeeMachineButton.setActionCommand("COFFEEMACHINEBUTTON");
        baristaButton.setActionCommand("BARISTABUTTON");
        cafeButton.setActionCommand("CAFEBUTTON");
        coffeeMachineUpgradeButton.setActionCommand("COFFEEMACHINEUPGRADEBUTTON");
        baristaUpgradeButton.setActionCommand("BARISTAUPGRADEBUTTON");
        cafeUpgradeButton.setActionCommand("CAFEUPGRADEBUTTON");
        pauseButton.setActionCommand("PAUSEBUTTON");
        endGameButton.setActionCommand("ENDGAMEBUTTON");

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
        label.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    /**
     * Configures a button with image icons and fallback text in case of missing files.
     *
     * @param button        The JButton to configure.
     * @param normalPath    The path to the normal icon image.
     * @param rolloverPath  The path to the rollover (hover) icon image.
     * @param fallbackText  Text to display if the icon is missing.
     */
    private void configureImageButton(JButton button, String normalPath, String rolloverPath, String fallbackText) {
        button.setFont(this.buttonShopFont);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);

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
                button.setText("");
                button.setBorder(null);
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setOpaque(false);
            } else {
                button.setText(fallbackText);
                configureShopButtonLookAndFeel(button, true);
            }
        } catch (Exception e) {
            System.err.println("Error cargando imágenes para botón (" + fallbackText + "): " + e.getMessage());
            e.printStackTrace();
            button.setText(fallbackText);
            configureShopButtonLookAndFeel(button, true);
        }
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }


    /**
     * Configures the look and feel of shop-related buttons.
     *
     * @param button The JButton to configure.
     * @param isShopItem Determines the button's style: true for regular shop item, false for upgrade item.
     */
    private void configureShopButtonLookAndFeel(JButton button, boolean isShopItem) {
        button.setFont(this.buttonShopFont);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);

        Dimension buttonDim = new Dimension(300, 36);
        button.setPreferredSize(buttonDim);
        button.setMinimumSize(buttonDim);
        button.setMaximumSize(buttonDim);

        if (isShopItem) {
            button.setBackground(new Color(50, 50, 50));
            button.setForeground(Color.white);
            button.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        } else {
            button.setBackground(new Color(100, 30, 30));
            button.setForeground(Color.white);
            button.setBorder(BorderFactory.createLineBorder(new Color(70,10,10), 1));
        }
        button.setOpaque(true);

        Color originalBg = button.getBackground();
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(originalBg.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalBg);
            }
        });
    }

    /** Updates the main message text in the UI.
     *
     * @param message The message to display
     * */
    public void setMessageText(String message) { SwingUtilities.invokeLater(() -> this.messageText.setText(message)); }


    /** Updates the counter label text in the UI.
     *
     * @param message The message to display
     * */
    public void setCounterLableText(String message) { SwingUtilities.invokeLater(() -> this.counterLabel.setText(message)); }

    /** Updates the "per second" rate label in the UI.
     *
     * @param message The message to display
     * */
    public void setPerSecLabelText(String message) { SwingUtilities.invokeLater(() -> this.perSecLabel.setText(message)); }

    /** Updates the coffee machine button's text
     *
     * @param message The message to display
     * */
    public void setCoffeeMachineButtonText(String message) { SwingUtilities.invokeLater(() -> {
        if (coffeeMachineButton.getIcon() == null || !"".equals(coffeeMachineButton.getText())) coffeeMachineButton.setText(message);
    }); }

    /** Updates the barista button's text
     *
     * @param message The message to display
     * */
    public void setBaristaButtonText(String message) { SwingUtilities.invokeLater(() -> {
        if ("???".equals(message) || baristaButton.getIcon() == null) {
            baristaButton.setText(message);
        } else if (!"".equals(baristaButton.getText())) {
            baristaButton.setText(message);
        }
    }); }

    /** Updates the café button's text
     *
     * @param message The message to display
     * */
    public void setCafeButtonText(String message) { SwingUtilities.invokeLater(() -> {
        if ("???".equals(message) || cafeButton.getIcon() == null) {
            cafeButton.setText(message);
        } else if (!"".equals(cafeButton.getText())) {
            cafeButton.setText(message);
        }
    }); }

    /** Updates the coffee machine upgrade button's text
     *
     * @param message The message to display
     * */
    public void setCoffeeMachineUpgradeButtonText(String message) { SwingUtilities.invokeLater(() -> {
        if (coffeeMachineUpgradeButton.getIcon() == null || !"".equals(coffeeMachineUpgradeButton.getText())) coffeeMachineUpgradeButton.setText(message);
    }); }

    /** Updates the barista upgrade button's text
     *
     * @param message The message to display
     * */
    public void setBaristaUpgradeButtonText(String message) { SwingUtilities.invokeLater(() -> {
        if ("???".equals(message) || baristaUpgradeButton.getIcon() == null) {
            baristaUpgradeButton.setText(message);
        } else if (!"".equals(baristaUpgradeButton.getText())) {
            baristaUpgradeButton.setText(message);
        }
    }); }

    /** Updates the café upgrade button's text
     *
     * @param message The message to display
     * */
    public void setCafeUpgradeButtonText(String message) { SwingUtilities.invokeLater(() -> {
        if ("???".equals(message) || cafeUpgradeButton.getIcon() == null) {
            cafeUpgradeButton.setText(message);
        } else if (!"".equals(cafeUpgradeButton.getText())) {
            cafeUpgradeButton.setText(message);
        }
    }); }


    /**
     * Updates the generator statistics table with new data.
     * Clears the table and fills it row by row.
     */
    public void updateGeneratorStatsTable(Object[][] data) {
        SwingUtilities.invokeLater(() -> {
            generatorStatsTableModel.setRowCount(0);
            if (data != null) {
                for (Object[] rowData : data) {
                    generatorStatsTableModel.addRow(rowData);
                }
            }
        });
    }

    //  Methods to add Action Listeners

    /** Adds an ActionListener to the main coffee button.
     *
     * @param listener The action listener
     * */
    public void addCoffeeButtonListener(ActionListener listener) { coffeeButton.addActionListener(listener); }

    /** Adds an ActionListener to the coffee machine purchase button.
     *
     * @param listener The action listener
     * */
    public void addCoffeeMachineButtonListener(ActionListener listener) { coffeeMachineButton.addActionListener(listener); }

    /** Adds an ActionListener to the barista purchase button.
     *
     * @param listener The action listener
     * */
    public void addBaristaButtonListener(ActionListener listener) { baristaButton.addActionListener(listener); }

    /** Adds an ActionListener to the café purchase button.
     *
     * @param listener The action listener
     * */
    public void addCafeButtonListener(ActionListener listener) { cafeButton.addActionListener(listener); }

    /** Adds an ActionListener to the pause button.
     *
     * @param listener The action listener
     * */
    public void addPauseButtonListener(ActionListener listener) { pauseButton.addActionListener(listener); }

    /** Adds an ActionListener to the end game button.
     *
     * @param listener The action listener
     * */

    public void addEndGameButtonListener(ActionListener listener) { endGameButton.addActionListener(listener); }

    /** Adds an ActionListener to the coffee machine upgrade button.
     *
     * @param listener The action listener
     * */
    public void addCoffeeMachineUpgradeButtonListener(ActionListener listener) { coffeeMachineUpgradeButton.addActionListener(listener); }

    /** Adds an ActionListener to the barista upgrade button.
     *
     * @param listener The action listener
     * */
    public void addBaristaUpgradeButtonListener(ActionListener listener) { baristaUpgradeButton.addActionListener(listener); }

    /** Adds an ActionListener to the café upgrade button.
     *
     * @param listener The action listener
     * */
    public void addCafeUpgradeButtonListener(ActionListener listener) { cafeUpgradeButton.addActionListener(listener); }


    //  Methods to add Mouse Listeners
    /** Adds a MouseListener to the barista purchase button.
     *
     * @param mouseListener The mouse listener
     * */
    public void addCoffeeMachineButtonMouseListener(MouseListener mouseListener) { coffeeMachineButton.addMouseListener(mouseListener); }

    /** Adds a MouseListener to the barista purchase button.
     *
     * @param mouseListener The mouse listener
     * */
    public void addBaristaButtonMouseListener(MouseListener mouseListener) { baristaButton.addMouseListener(mouseListener); }

    /** Adds a MouseListener to the café purchase button.
     *
     * @param mouseListener The mouse listener
     * */
    public void addCafeButtonMouseListener(MouseListener mouseListener) { cafeButton.addMouseListener(mouseListener); }

    /** Adds a MouseListener to the coffee machine upgrade button.
     *
     * @param mouseListener The mouse listener
     * */
    public void addCoffeeMachineUpgradeButtonMouseListener(MouseListener mouseListener) { coffeeMachineUpgradeButton.addMouseListener(mouseListener); }

    /** Adds a MouseListener to the barista upgrade button.
     *
     * @param mouseListener The mouse listener
     * */
    public void addBaristaUpgradeButtonMouseListener(MouseListener mouseListener) { baristaUpgradeButton.addMouseListener(mouseListener); }

    /** Adds a MouseListener to the café upgrade button.
     *
     * @param mouseListener The mouse listener
     * */
    public void addCafeUpgradeButtonMouseListener(MouseListener mouseListener) { cafeUpgradeButton.addMouseListener(mouseListener); }


    //ToDo cambiar esta clase de lugar!!!!!!!!!!!
    // --- Clase interna WrappingCellRenderer ---
    /**
     * Custom cell renderer that allows text to wrap inside table cells.
     * Useful for displaying long descriptions or tooltips in a neat format.
     */
    private class WrappingCellRenderer extends JTextArea implements TableCellRenderer {

        /**
         * Constructor that configures the JTextArea for line wrapping and sets it to opaque.
         * Enables automatic wrapping of words to fit within the cell boundaries.
         */
        public WrappingCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }


        /**
         * Returns the component used for drawing the cell.
         * It adjusts appearance based on selection state and wraps long text.
         *
         * @param table The JTable that uses this renderer
         * @param value The value to assign to the cell
         * @param isSelected True if the cell is selected
         * @param hasFocus True if the cell has focus
         * @param row The row of the cell to render
         * @param column The column of the cell to render
         * @return The component used to render the cell
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            setFont(table.getFont());

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            return this;
        }
    }
}