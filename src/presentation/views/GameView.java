
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

    /**
     * JLabel pertaining to the perSecLabel
     */
    private JLabel perSecLabel;

    /**
     * Label that displays the current count of the player's coffees.
     */
    private JLabel counterLabel;
    /**
     * JButton that lets us buy a coffee
     */
    private JButton coffeeButton;
    /**
     * JButton that lets us buy a coffee machine
     */
    private JButton coffeeMachineButton;
    /**
     * JButton that lets us buy a barista
     */
    private JButton baristaButton;
    /**
     * JButton that lets us buy a cafe
     */
    private JButton cafeButton;
    /**
     * JButton that lets us buy a coffee machine upgrade
     */
    private JButton coffeeMachineUpgradeButton;
    /**
     * JButton that lets us buy a barista upgrade
     */
    private JButton baristaUpgradeButton;
    /**
     * JButton that lets us buy a cafe upgrade
     */
    private JButton cafeUpgradeButton;
    /**
     * JButton that lets us pause the game
     */
    private JButton pauseButton;
    /**
     * JButton that lets us end the game
     */
    private JButton endGameButton;
    /**
     * JTextArea that sets the message text
     */
    private JTextArea messageText;

    // Fonts
    /**
     * JFont that sets the title font
     */
    private Font titleFont;
    /**
     * JFont that sets the subtitle font
     */
    private Font subtitleFont;
    /**
     * JFont that sets the main text font
     */
    private Font mainTextFont;
    /**
     * JFont that sets the small text font
     */
    private Font smallTextFont;
    /**
     * JFont that sets the button shop font
     */
    private Font buttonShopFont;
    /**
     * JFont that sets the table header font
     */
    private Font tableHeaderFont;
    /**
     * JFont that sets the table content font
     */
    private Font tableContentFont;
    
    // Table
    /**
     * JTable that creates the generator stats table
     */
    private JTable generatorStatsTable;
    /**
     * DefaultTableModel that sets the stats table model
     */
    private DefaultTableModel generatorStatsTableModel;
    /**
     * JScrollPane that sets the stats table scroll
     */
    private JScrollPane generatorStatsScrollPane;
    /**
     * String[] that creates the columns and sets them to: Name, Qty, Unit Prod, Total Prod and % Overall
     */
    private final String[] generatorTableColumns = {"Name", "Qty", "Unit Prod", "Total Prod", "% Overall"};

    // Titles
    /**
     * JLabel pertaining to the generator table title
     */
    private JLabel generatorTableTitleLabel;
    /**
     * JLabel pertaining to the coffee maker title
     */
    private JLabel coffeeMakersTitleLabel;
    /**
     * JLabel pertaining to the upgrades title
     */
    private JLabel upgradesTitleLabel;
    /**
     * JLabel pertaining to the main title
     */
    private JLabel shopMainTitleLabel;
    /**
     * JLabel pertaining to the item details title
     */
    private JLabel itemDetailsTitleLabel;

    /**
     * JButton that lets us view the game instructions
     */
    private JButton instructionsButton;


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
        this.setLocationRelativeTo(null);

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

        // Coffee counter, per second, and main coffee button.
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

        coffeeButton = new JButton();
        coffeeButton.setBounds(50, 180, 300, 300);
        coffeeButton.setOpaque(true);
        coffeeButton.setContentAreaFilled(true);
        coffeeButton.setBorderPainted(true);

        try {
            ImageIcon normalCoffeeIcon = new ImageIcon("res/click_coffee.png");
            ImageIcon rolloverCoffeeIcon = new ImageIcon("res/click_coffee2.png");

            if (normalCoffeeIcon.getIconWidth() > 0) {
                coffeeButton.setIcon(normalCoffeeIcon);
                if (rolloverCoffeeIcon.getIconWidth() > 0) {
                    coffeeButton.setRolloverIcon(rolloverCoffeeIcon);
                    coffeeButton.setRolloverEnabled(true);
                }
                coffeeButton.setText("");
                coffeeButton.setOpaque(false);
                coffeeButton.setContentAreaFilled(false);
                coffeeButton.setBorderPainted(false);
                coffeeButton.setBorder(null);
            } else {
                new PopUpView("Error: Main coffee image could not be loaded.");
            }
        } catch (Exception e) {
            new PopUpView("Exception loading main coffee image.");
        }
        leftPanel.add(coffeeButton);

        instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(this.buttonShopFont);
        instructionsButton.setFocusPainted(false);
        instructionsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        instructionsButton.setBounds(20, 595, 169, 36);
        instructionsButton.setBackground(Color.LIGHT_GRAY);
        instructionsButton.setForeground(Color.BLACK);
        instructionsButton.setOpaque(true);
        instructionsButton.setContentAreaFilled(true);
        instructionsButton.setBorderPainted(true);

        instructionsButton.addActionListener(e -> showInstructionsPopup());
        leftPanel.add(instructionsButton);

        //Item Details, Message Area, Generator Table
        itemDetailsTitleLabel = new JLabel("ITEM DETAILS");
        itemDetailsTitleLabel.setFont(titleFont);
        itemDetailsTitleLabel.setForeground(Color.black);
        itemDetailsTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        int itemDetailsTitleHeight = 30;
        int itemDetailsTitleY = 130;
        itemDetailsTitleLabel.setBounds(5, itemDetailsTitleY, 410, itemDetailsTitleHeight);
        centerPanel.add(itemDetailsTitleLabel);

        int messageScrollPaneY = itemDetailsTitleY + itemDetailsTitleHeight + 15;
        int messageAreaHeight = 170;

        messageText = new JTextArea();
        messageText.setForeground(Color.black);
        messageText.setBackground(new Color(235, 235, 235));
        messageText.setFont(smallTextFont);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messageText.setFocusable(false);

        JScrollPane messageScrollPane = new JScrollPane(messageText);
        messageScrollPane.setBorder(null);
        messageScrollPane.setBounds(5, messageScrollPaneY, 410, messageAreaHeight);
        centerPanel.add(messageScrollPane);

        generatorTableTitleLabel = new JLabel("GENERATOR TABLE");
        styleTitleLabel(generatorTableTitleLabel, titleFont);
        generatorTableTitleLabel.setBorder(new EmptyBorder(5, 0, 5, 10));
        generatorTableTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        int generatorTitleY = messageScrollPaneY + messageAreaHeight + 25;
        generatorTableTitleLabel.setBounds(5, generatorTitleY, 410, 30);
        centerPanel.add(generatorTableTitleLabel);

        // Generator Statistics Table
        generatorStatsTableModel = new DefaultTableModel(generatorTableColumns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        generatorStatsTable = new JTable(generatorStatsTableModel);
        generatorStatsTable.setFont(tableContentFont);
        generatorStatsTable.getTableHeader().setFont(tableHeaderFont);
        generatorStatsTable.setRowHeight(22);
        generatorStatsTable.setFillsViewportHeight(true);
        generatorStatsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        generatorStatsTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = generatorStatsTable.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setResizable(false);
        }

        generatorStatsTable.setRowSelectionAllowed(false);
        generatorStatsTable.setFocusable(false);


        // Colors
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


        DefaultTableCellRenderer themedLeftRenderer = new DefaultTableCellRenderer() {
            {
                setOpaque(true);
                setHorizontalAlignment(JLabel.LEFT);
            }
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
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

        DefaultTableCellRenderer themedCenterRenderer = new DefaultTableCellRenderer() {
            {
                setOpaque(true);
                setHorizontalAlignment(JLabel.CENTER);
            }
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
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

        int numRowsInTable = 3;
        int dataRowHeight = generatorStatsTable.getRowHeight();
        int headerHeight = generatorStatsTable.getTableHeader().getPreferredSize().height;
        int tableActualHeight = headerHeight + (numRowsInTable * dataRowHeight);

        generatorStatsScrollPane = new JScrollPane(generatorStatsTable);
        generatorStatsScrollPane.getViewport().setBackground(brownRowDark);
        generatorStatsScrollPane.setOpaque(true);
        generatorStatsScrollPane.setBorder(BorderFactory.createLineBorder(tableGrid));
        generatorStatsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        int tableY = generatorTitleY + 30 + 15;
        generatorStatsScrollPane.setBounds(5, tableY, 410, tableActualHeight + 2);
        centerPanel.add(generatorStatsScrollPane);

        // Title SHOP
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


        //Button IMAGES
        coffeeMachineButton = new JButton();
        configureImageButton(coffeeMachineButton, "res/button_coffee.machine.png", "res/button_coffee.machine2.png");
        shopItemsContainerPanel.add(coffeeMachineButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        baristaButton = new JButton();
        configureImageButton(baristaButton, "res/button_interrogant.png", "res/button_interrogant2.png");
        shopItemsContainerPanel.add(baristaButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        cafeButton = new JButton();
        configureImageButton(cafeButton, "res/button_interrogant.png", "res/button_interrogant2.png");
        shopItemsContainerPanel.add(cafeButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        upgradesTitleLabel = new JLabel("Upgrades");
        styleTitleLabel(upgradesTitleLabel, subtitleFont);
        upgradesTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        shopItemsContainerPanel.add(upgradesTitleLabel);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0,8)));

        coffeeMachineUpgradeButton = new JButton();
        configureImageButton(coffeeMachineUpgradeButton, "res/button_u1.png", "res/button_u1.2.png");
        shopItemsContainerPanel.add(coffeeMachineUpgradeButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        baristaUpgradeButton = new JButton();
        configureImageButton(baristaUpgradeButton, "res/button_interrogant.png", "res/button_interrogant2.png");
        shopItemsContainerPanel.add(baristaUpgradeButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        cafeUpgradeButton = new JButton();
        configureImageButton(cafeUpgradeButton, "res/button_interrogant.png", "res/button_interrogant2.png");
        shopItemsContainerPanel.add(cafeUpgradeButton);

        shopItemsContainerPanel.add(Box.createVerticalGlue());

        pauseButton = new JButton();
        configureImageButton(pauseButton, "res/button_pause.png", "res/button_pause2.png");
        shopItemsContainerPanel.add(pauseButton);
        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0,8)));

        endGameButton = new JButton();
        configureImageButton(endGameButton, "res/button_end.png", "res/button_end2.png");
        shopItemsContainerPanel.add(endGameButton);

        shopItemsContainerPanel.add(Box.createRigidArea(new Dimension(0,10)));

        // Action Commands
        coffeeButton.setActionCommand("COFFEEBUTTON");
        coffeeMachineButton.setActionCommand("COFFEEMACHINEBUTTON");
        baristaButton.setActionCommand("BARISTABUTTON");
        cafeButton.setActionCommand("CAFEBUTTON");
        coffeeMachineUpgradeButton.setActionCommand("COFFEEMACHINEUPGRADEBUTTON");
        baristaUpgradeButton.setActionCommand("BARISTAUPGRADEBUTTON");
        cafeUpgradeButton.setActionCommand("CAFEUPGRADEBUTTON");
        pauseButton.setActionCommand("PAUSEBUTTON");
        endGameButton.setActionCommand("ENDGAMEBUTTON");

        // MouseListener
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
        label.setBorder(new EmptyBorder(5,10,5,10));
    }

    /**
     * Configures a button with image icons.
     * If images are loaded, Java-rendered text is cleared.
     *
     * @param button        The JButton to configure.
     * @param normalPath    The path to the normal icon image.
     * @param rolloverPath  The path to the rollover (hover) icon image.
     */
    private void configureImageButton(JButton button, String normalPath, String rolloverPath) {
        button.setFont(this.buttonShopFont);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonDim = new Dimension(300, 36);
        button.setPreferredSize(buttonDim);
        button.setMinimumSize(buttonDim);
        button.setMaximumSize(buttonDim);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);

        try {
            ImageIcon iconNormal = null;
            File normalFile = new File(normalPath);
            if (normalFile.exists()) {
                iconNormal = new ImageIcon(normalPath);
            }

            ImageIcon iconRollover = null;
            if (rolloverPath != null) {
                File rolloverFile = new File(rolloverPath);
                if (rolloverFile.exists()) {
                    iconRollover = new ImageIcon(rolloverPath);
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
                new PopUpView("Error: Image could not be loaded for button.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            new PopUpView("Exception loading image for button.");
        }
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * Displays the game instructions in a pop-up dialog using a JTextArea.
     * Text is plain, and a JScrollPane is used for scrollability.
     */
    private void showInstructionsPopup() {
        String instructionsTitle = "How to Play Coffee Clicker";
        String instructionsMessage =
                        "\n 1. Make Coffees!\n" +
                        "   - Click the BIG COFFEE cup to earn coffees.\n\n" +
                        " 2. Buy to Grow!\n" +
                        "   - Use your coffees in the SHOP:\n" +
                        "     - Generators: They produce coffee for you automatically!\n" +
                        "     - Upgrades: Make your generators more powerful.\n" +
                        "   - Hint: Hover over a shop item to see its cost and details in ITEM DETAILS.\n\n" +
                        " 3. Check Your Empire!\n" +
                        "   - Your coffee count is at the top-left.\n" +
                        "   - The Generator Table shows what you own and its production.\n\n" +
                        " 4. Continue or Finish?\n" +
                        "   - Pause Game: Saves your current game and returns you to the main menu.\n" +
                        "   - End Game: Finishes your current game and saves your statistics.\n" +
                        " \n                                           ENJOY MAKING COFFEES!\n";


        JTextArea textArea = new JTextArea(instructionsMessage);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        textArea.setBackground(UIManager.getColor("Panel.background"));
        textArea.setBorder(null);
        textArea.setFocusable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 285));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

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


    /** Updates the appearance of the Coffee Machine button. */
    public void updateCoffeeMachineButtonAppearance() {
        configureImageButton(coffeeMachineButton, "res/button_coffee.machine.png", "res/button_coffee.machine2.png");
    }

    /** Updates the appearance of the Coffee Machine upgrade button. */
    public void updateCoffeeMachineUpgradeButtonAppearance() {
        configureImageButton(coffeeMachineUpgradeButton, "res/button_u1.png", "res/button_u1.2.png");
    }

    /** Updates the appearance of the Barista button based on its unlocked state.
     * @param unlocked True if the barista is unlocked, false otherwise.
     */
    public void updateBaristaButtonAppearance(boolean unlocked) {
        String normalIcon = unlocked ? "res/button_barista.png" : "res/button_interrogant.png";
        String rolloverIcon = unlocked ? "res/button_barista2.png" : "res/button_interrogant2.png";
        configureImageButton(baristaButton, normalIcon, rolloverIcon);
    }

    /** Updates the appearance of the Cafe button based on its unlocked state.
     * @param unlocked True if the cafe is unlocked, false otherwise.
     */
    public void updateCafeButtonAppearance(boolean unlocked) {
        String normalIcon = unlocked ? "res/button_cafe.png" : "res/button_interrogant.png";
        String rolloverIcon = unlocked ? "res/button_cafe2.png" : "res/button_interrogant2.png";
        configureImageButton(cafeButton, normalIcon, rolloverIcon);
    }

    /** Updates the appearance of the Barista upgrade button based on its unlocked state.
     * @param unlocked True if the barista upgrade is unlocked, false otherwise.
     */
    public void updateBaristaUpgradeButtonAppearance(boolean unlocked) {
        String normalIcon = unlocked ? "res/button_u2.png" : "res/button_interrogant.png";
        String rolloverIcon = unlocked ? "res/button_u2.2.png" : "res/button_interrogant2.png";
        configureImageButton(baristaUpgradeButton, normalIcon, rolloverIcon);
    }

    /** Updates the appearance of the Cafe upgrade button based on its unlocked state.
     * @param unlocked True if the cafe upgrade is unlocked, false otherwise.
     */
    public void updateCafeUpgradeButtonAppearance(boolean unlocked) {
        String normalIcon = unlocked ? "res/button_u3.png" : "res/button_interrogant.png";
        String rolloverIcon = unlocked ? "res/button_u3.2.png" : "res/button_interrogant2.png";
        configureImageButton(cafeUpgradeButton, normalIcon, rolloverIcon);
    }

    /**
     * Updates the generator statistics table with new data.
     * Clears the table and fills it row by row, ensuring it runs on the EDT.
     * @param data A 2D array of Objects representing the table data.
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

    /**
     * Adds an ActionListener to the main coffee button.
     * @param listener The ActionListener to be added.
     */
    public void addCoffeeButtonListener(ActionListener listener) { coffeeButton.addActionListener(listener); }

    /**
     * Adds an ActionListener to the coffee machine purchase button.
     * @param listener The ActionListener to be added.
     */
    public void addCoffeeMachineButtonListener(ActionListener listener) { coffeeMachineButton.addActionListener(listener); }

    /**
     * Adds an ActionListener to the barista purchase button.
     * @param listener The ActionListener to be added.
     */
    public void addBaristaButtonListener(ActionListener listener) { baristaButton.addActionListener(listener); }

    /**
     * Adds an ActionListener to the cafe purchase button.
     * @param listener The ActionListener to be added.
     */
    public void addCafeButtonListener(ActionListener listener) { cafeButton.addActionListener(listener); }

    /**
     * Adds an ActionListener to the pause button.
     * @param listener The ActionListener to be added.
     */
    public void addPauseButtonListener(ActionListener listener) { pauseButton.addActionListener(listener); }

    /**
     * Adds an ActionListener to the end game button.
     * @param listener The ActionListener to be added.
     */
    public void addEndGameButtonListener(ActionListener listener) { endGameButton.addActionListener(listener); }

    /**
     * Adds an ActionListener to the coffee machine upgrade button.
     * @param listener The ActionListener to be added.
     */
    public void addCoffeeMachineUpgradeButtonListener(ActionListener listener) { coffeeMachineUpgradeButton.addActionListener(listener); }

    /**
     * Adds an ActionListener to the barista upgrade button.
     * @param listener The ActionListener to be added.
     */
    public void addBaristaUpgradeButtonListener(ActionListener listener) { baristaUpgradeButton.addActionListener(listener); }

    /**
     * Adds an ActionListener to the cafe upgrade button.
     * @param listener The ActionListener to be added.
     */
    public void addCafeUpgradeButtonListener(ActionListener listener) { cafeUpgradeButton.addActionListener(listener); }



    /**
     * Adds a MouseListener to the coffee machine purchase button.
     * @param mouseListener The MouseListener to be added.
     */
    public void addCoffeeMachineButtonMouseListener(MouseListener mouseListener) { coffeeMachineButton.addMouseListener(mouseListener); }

    /**
     * Adds a MouseListener to the barista purchase button.
     * @param mouseListener The MouseListener to be added.
     */
    public void addBaristaButtonMouseListener(MouseListener mouseListener) { baristaButton.addMouseListener(mouseListener); }

    /**
     * Adds a MouseListener to the cafe purchase button.
     * @param mouseListener The MouseListener to be added.
     */
    public void addCafeButtonMouseListener(MouseListener mouseListener) { cafeButton.addMouseListener(mouseListener); }

    /**
     * Adds a MouseListener to the coffee machine upgrade button.
     * @param mouseListener The MouseListener to be added.
     */
    public void addCoffeeMachineUpgradeButtonMouseListener(MouseListener mouseListener) { coffeeMachineUpgradeButton.addMouseListener(mouseListener); }

    /**
     * Adds a MouseListener to the barista upgrade button.
     * @param mouseListener The MouseListener to be added.
     */
    public void addBaristaUpgradeButtonMouseListener(MouseListener mouseListener) { baristaUpgradeButton.addMouseListener(mouseListener); }

    /**
     * Adds a MouseListener to the cafe upgrade button.
     * @param mouseListener The MouseListener to be added.
     */
    public void addCafeUpgradeButtonMouseListener(MouseListener mouseListener) { cafeUpgradeButton.addMouseListener(mouseListener); }

}