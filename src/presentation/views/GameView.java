package presentation.views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;

/*
public class GameView {

    private JFrame window;
    private JLabel counterLabel, perSecLabel;
    private JButton coffeeButton, coffeeMachineButton, baristaButton, cafeButton, coffeeMachineUpgradeButton, baristaUpgradeButton, cafeUpgradeButton,pauseButton, endGameButton;
    private JTextArea messageText;
    private Font font1, font2;

    public void setMessageText(String message) {
        this.messageText.setText(message);
    }

    public void setPauseButtonText(String message) {
        this.pauseButton.setText(message);
    }

    public void setCounterLableText(String message) {
        this.counterLabel.setText(message);
    }

    public void setPerSecLabelText(String message) {
        this.perSecLabel.setText(message);
    }

    public void setCoffeeMachineButtonText(String message) {
        this.coffeeMachineButton.setText(message);
    }

    public void setBaristaButtonText(String message) {
        this.baristaButton.setText(message);
    }

    public void setCafeButtonText(String message) {
        this.cafeButton.setText(message);
    }

    ////////////UPGRADE BUTTON TEXT ////////////////////
    public void setCoffeeMachineUpgradeButtonText(String message) {
        this.coffeeMachineUpgradeButton.setText(message);
    }

    public void setBaristaUpgradeButtonText(String message) {
        this.baristaUpgradeButton.setText(message);
    }

    public void setCafeUpgradeButtonText(String message) {
        this.cafeUpgradeButton.setText(message);
    }
    ////////////////////////////////////////////////////


    public void addCoffeeMachineButtonMouseListener(MouseListener mouseListener){
        coffeeMachineButton.addMouseListener(mouseListener);
    }

    public void addCafeButtonMouseListener(MouseListener mouseListener){
        cafeButton.addMouseListener(mouseListener);
    }

    public void addBaristaButtonMouseListener(MouseListener mouseListener){
        baristaButton.addMouseListener(mouseListener);
    }

    public void addCoffeeButtonListener(ActionListener listener){
        coffeeButton.addActionListener(listener);
    }

    public void addCoffeeMachineButtonListener(ActionListener listener){
        coffeeMachineButton.addActionListener(listener);
    }

    public void addBaristaButtonListener(ActionListener listener){
        baristaButton.addActionListener(listener);
    }

    public void addCafeButtonListener(ActionListener listener){
        cafeButton.addActionListener(listener);
    }

    public void addPauseButtonListener(ActionListener listener) {
        pauseButton.addActionListener(listener);
    }

    public void addEndGameButtonListener(ActionListener listener) {
        endGameButton.addActionListener(listener);
    }

    ////////////////////Listener de Upgrades//////////////////////
    public void addCoffeeMachineUpgradeButtonListener(ActionListener listener){
        coffeeMachineUpgradeButton.addActionListener(listener);
    }

    public void addBaristaUpgradeButtonListener(ActionListener listener){
        baristaUpgradeButton.addActionListener(listener);
    }

    public void addCafeUpgradeButtonListener(ActionListener listener){
        cafeUpgradeButton.addActionListener(listener);
    }
    //////////////////////////////////////////////////////////////


    public GameView() {
        createFont();
        createUI();
    }

    public void createFont() {
        font1 = new Font("DePixel", Font.PLAIN, 25);
        font2 = new Font("DePixel", Font.PLAIN, 13);
    }

    public void createUI() {
        window = new JFrame("Coffee Clicker");
        window.setSize(1280, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        JPanel coffeePanel = new JPanel();
        coffeePanel.setBounds(100, 220, 350, 350);
        coffeePanel.setBackground(Color.black);
        window.add(coffeePanel);

        ImageIcon originalIcon = new ImageIcon("res/coffee.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        coffeeButton = new JButton();
        coffeeButton.setBackground(Color.black);
        coffeeButton.setFocusPainted(false);
        coffeeButton.setBorder(null);
        coffeeButton.setIcon(scaledIcon);
        coffeePanel.add(coffeeButton);

        JPanel counterPanel = new JPanel();
        counterPanel.setBounds(100, 100, 300, 100);
        counterPanel.setBackground(Color.black);
        counterPanel.setLayout(new GridLayout(2, 1));
        window.add(counterPanel);

        counterLabel = new JLabel("0 coffees");
        counterLabel.setForeground(Color.white);
        counterLabel.setFont(font1);
        counterPanel.add(counterLabel);

        perSecLabel = new JLabel("per second: 0.0");
        perSecLabel.setForeground(Color.white);
        perSecLabel.setFont(font2);
        counterPanel.add(perSecLabel);

        JPanel shopLabelPanel = new JPanel();
        shopLabelPanel.setBounds(930, 80, 300, 40);
        shopLabelPanel.setBackground(Color.black);
        shopLabelPanel.setLayout(new BorderLayout());
        JLabel shopLabel = new JLabel("SHOP", SwingConstants.LEFT);
        shopLabel.setForeground(Color.white);
        shopLabel.setFont(font1);
        shopLabelPanel.add(shopLabel, BorderLayout.NORTH);
        window.add(shopLabelPanel);

        JPanel itemPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        itemPanel.setBounds(930, 130, 300, 200);
        itemPanel.setBackground(Color.black);
        window.add(itemPanel);

        coffeeMachineButton = new JButton("Cursor (0)");
        coffeeMachineButton.setFont(font1);
        coffeeMachineButton.setFocusPainted(false);
        itemPanel.add(coffeeMachineButton);

        baristaButton = new JButton("?");
        baristaButton.setFont(font1);
        baristaButton.setFocusPainted(false);
        itemPanel.add(baristaButton);

        cafeButton = new JButton("?");
        cafeButton.setFont(font1);
        cafeButton.setFocusPainted(false);
        itemPanel.add(cafeButton);

        /////////////////////////BOTONES DE LAS UPGRADES////////////////////////////////////////
        coffeeMachineUpgradeButton = new JButton("U1 (0)");
        coffeeMachineUpgradeButton.setFont(font1);
        coffeeMachineUpgradeButton.setFocusPainted(false);
        itemPanel.add(coffeeMachineUpgradeButton);

        baristaUpgradeButton = new JButton("?");
        baristaUpgradeButton.setFont(font1);
        baristaUpgradeButton.setFocusPainted(false);
        itemPanel.add(baristaUpgradeButton);

        cafeUpgradeButton = new JButton("?");
        cafeUpgradeButton.setFont(font1);
        cafeUpgradeButton.setFocusPainted(false);
        itemPanel.add(cafeUpgradeButton);
        //////////////////////////////////////////////////////////////////////////////////////

        pauseButton = new JButton("Pause");
        itemPanel.add(pauseButton);

        endGameButton = new JButton("End Game");
        itemPanel.add(endGameButton);

        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(500, 70, 250, 150);
        messagePanel.setBackground(Color.black);
        window.add(messagePanel);

        messageText = new JTextArea();
        messageText.setBounds(500, 70, 250, 150);
        messageText.setForeground(Color.white);
        messageText.setBackground(Color.black);
        messageText.setFont(font2);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messagePanel.add(messageText);

        coffeeButton.setActionCommand("COFFEEBUTTON");

        coffeeMachineButton.setActionCommand("COFFEEMACHINEBUTTON");
        baristaButton.setActionCommand("BARISTABUTTON");
        cafeButton.setActionCommand("CAFEBUTTON");

        /////////////////UGRADES////////////////////
        coffeeMachineUpgradeButton.setActionCommand("COFFEEMACHINEUPGRADEBUTTON");
        baristaUpgradeButton.setActionCommand("BARISTAUPGRADEBUTTON");
        cafeUpgradeButton.setActionCommand("CAFEUPGRADEBUTTON");
        ////////////////////////////////////////////

        pauseButton.setActionCommand("PAUSEBUTTON");
        endGameButton.setActionCommand("ENDGAMEBUTTON");

        coffeeMachineButton.setName("COFFEEMACHINEBUTTON");
        baristaButton.setName("BARISTABUTTON");
        cafeButton.setName("CAFEBUTTON");

        /////////////////UGRADES////////////////////
        coffeeMachineUpgradeButton.setName("COFFEEMACHINEUPGRADEBUTTON");
        baristaUpgradeButton.setName("BARISTAUPGRADEBUTTON");
        cafeUpgradeButton.setName("CAFEUPGRADEBUTTON");
        ////////////////////////////////////////////

        window.setVisible(true);
    }
}
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The main graphical interface of the Coffee Clicker game.
 * Extends JFrame and manages all UI components and layouts.
 */
public class GameView extends JFrame {

    /**
     * Labels displaying the total number of coffees and the amount of coffees produced per second.
     */
    private JLabel counterLabel, perSecLabel;
    /**
     * Buttons to click the coffee and all the generators.
     */
    private JButton coffeeButton, coffeeMachineButton, baristaButton, cafeButton;
    /**
     * Buttons to click the generator upgrades.
     */
    private JButton coffeeMachineUpgradeButton, baristaUpgradeButton, cafeUpgradeButton;
    /**
     * Buttons to pause or end the game.
     */
    private JButton pauseButton, endGameButton;
    /**
     * Text area to display messages to the player.
     */
    private JTextArea messageText;
    /**
     * Fonts used.
     */
    private Font font1, font2;

    /**
     * Table to display generator statistics.
     */
    private JTable generatorStatsTable;

    /**
     * Table model backing the generator statistics table.
     */
    private DefaultTableModel generatorStatsTableModel;

    /**
     * Scroll pane containing the generator statistics table.
     */
    private JScrollPane generatorStatsScrollPane;

    /**
     * Column headers used in the generator statistics table.
     */
    private final String[] generatorTableColumns = {
            "Name",      // O "Generator"
            "Qty",       // Abreviatura de Quantity
            "Unit Prod", // Abreviatura de Unit Production
            "Total Prod",// Abreviatura de Total Production
            "% Overall"  // O "% Total"
    };

    /**
     * Label displaying the title above the generator statistics table.
     */
    private JLabel generatorTableTitleLabel;

    /**
     * Constructs a new GameView, initializing fonts and UI components.
     */
    public GameView() {
        createFont();
        createUI();
    }

    /**
     * Makes the game window visible.
     */
    public void open() {
        this.setVisible(true);
    }

    /**
     * Closes and disposes the game window.
     */
    public void close() {
        this.dispose();
    }

    /**
     * Initializes fonts used in the interface.
     */
    private void createFont() {
        font1 = new Font("DePixelg", Font.PLAIN, 25);
        font2 = new Font("DePixelg", Font.PLAIN, 13);
    }

    /**
     * Builds and configures all UI components, panels, and layouts.
     */
    private void createUI() {
        this.setTitle("Coffee Clicker");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        this.setLayout(null);

        JPanel coffeePanel = new JPanel();
        coffeePanel.setBounds(100, 220, 350, 350);
        coffeePanel.setBackground(Color.black);
        this.add(coffeePanel);

        ImageIcon originalIcon = new ImageIcon("res/coffee.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        coffeeButton = new JButton();
        coffeeButton.setBackground(Color.black);
        coffeeButton.setFocusPainted(false);
        coffeeButton.setBorder(null);
        coffeeButton.setIcon(scaledIcon);
        coffeePanel.add(coffeeButton);

        JPanel counterPanel = new JPanel();
        counterPanel.setBounds(100, 100, 300, 100);
        counterPanel.setBackground(Color.black);
        counterPanel.setLayout(new GridLayout(2, 1));
        this.add(counterPanel);

        counterLabel = new JLabel("0 coffees");
        counterLabel.setForeground(Color.white);
        counterLabel.setFont(font1);
        counterPanel.add(counterLabel);

        perSecLabel = new JLabel("per second: 0.0");
        perSecLabel.setForeground(Color.white);
        perSecLabel.setFont(font2);
        counterPanel.add(perSecLabel);

        JPanel shopLabelPanel = new JPanel();
        shopLabelPanel.setBounds(930, 80, 300, 40);
        shopLabelPanel.setBackground(Color.black);
        shopLabelPanel.setLayout(new BorderLayout());
        JLabel shopLabel = new JLabel("SHOP", SwingConstants.LEFT);
        shopLabel.setForeground(Color.white);
        shopLabel.setFont(font1);
        shopLabelPanel.add(shopLabel, BorderLayout.NORTH);
        this.add(shopLabelPanel);

        JPanel itemPanel = new JPanel(new GridLayout(9, 1, 10, 10));
        itemPanel.setBounds(930, 130, 300, 400);
        itemPanel.setBackground(Color.black);
        this.add(itemPanel);

        //Taula
        generatorStatsTableModel = new DefaultTableModel(generatorTableColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        generatorStatsTable = new JTable(generatorStatsTableModel);
        generatorStatsTable.setFont(font2);
        generatorStatsTable.getTableHeader().setFont(font2.deriveFont(Font.BOLD));

        generatorStatsTable.setRowHeight(30); // Altura

        generatorStatsTable.setFillsViewportHeight(true);
        generatorStatsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        generatorStatsTable.getColumnModel().getColumn(0).setPreferredWidth(120); // Name
        generatorStatsTable.getColumnModel().getColumn(1).setPreferredWidth(60);  // Qty
        generatorStatsTable.getColumnModel().getColumn(2).setPreferredWidth(95);  // Unit Prod
        generatorStatsTable.getColumnModel().getColumn(3).setPreferredWidth(95);  // Total Prod
        generatorStatsTable.getColumnModel().getColumn(4).setPreferredWidth(75);  // % Overall

        generatorStatsTable.setBackground(Color.decode("#B89C91"));
        generatorStatsTable.setForeground(Color.WHITE);
        generatorStatsTable.getTableHeader().setBackground(Color.decode("#9E6B57"));
        generatorStatsTable.getTableHeader().setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        generatorStatsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Centrar Qty
        generatorStatsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Centrar Unit Prod
        generatorStatsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Centrar Total Prod
        generatorStatsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Centrar % Overall

        int tableX = 460;
        int tableY = 400;
        int tableWidth = 450;

        int numRows = 3;
        int dataRowHeight = generatorStatsTable.getRowHeight();
        int headerHeight = generatorStatsTable.getTableHeader().getPreferredSize().height;
        int tableHeight = headerHeight + (numRows * dataRowHeight) + 2;

        generatorTableTitleLabel = new JLabel("GENERATOR TABLE");
        generatorTableTitleLabel.setFont(font1);
        generatorTableTitleLabel.setForeground(Color.WHITE);
        generatorTableTitleLabel.setBounds(tableX, tableY - 40, tableWidth, 30);
        this.add(generatorTableTitleLabel);

        generatorStatsScrollPane = new JScrollPane(generatorStatsTable);
        generatorStatsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        generatorStatsScrollPane.setBounds(tableX, tableY, tableWidth, tableHeight);

        this.add(generatorStatsScrollPane);

        //fide la taula

        coffeeMachineButton = new JButton("Cursor (0)");
        coffeeMachineButton.setFont(font1);
        coffeeMachineButton.setFocusPainted(false);
        itemPanel.add(coffeeMachineButton);

        baristaButton = new JButton("?");
        baristaButton.setFont(font1);
        baristaButton.setFocusPainted(false);
        itemPanel.add(baristaButton);

        cafeButton = new JButton("?");
        cafeButton.setFont(font1);
        cafeButton.setFocusPainted(false);
        itemPanel.add(cafeButton);

        coffeeMachineUpgradeButton = new JButton("U1 (0)");
        coffeeMachineUpgradeButton.setFont(font1);
        coffeeMachineUpgradeButton.setFocusPainted(false);
        itemPanel.add(coffeeMachineUpgradeButton);

        baristaUpgradeButton = new JButton("?");
        baristaUpgradeButton.setFont(font1);
        baristaUpgradeButton.setFocusPainted(false);
        itemPanel.add(baristaUpgradeButton);

        cafeUpgradeButton = new JButton("?");
        cafeUpgradeButton.setFont(font1);
        cafeUpgradeButton.setFocusPainted(false);
        itemPanel.add(cafeUpgradeButton);

        pauseButton = new JButton("Pause");
        itemPanel.add(pauseButton);

        endGameButton = new JButton("End Game");
        itemPanel.add(endGameButton);

        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(500, 70, 250, 150);
        messagePanel.setBackground(Color.black);
        this.add(messagePanel);

        messageText = new JTextArea();
        messageText.setBounds(500, 70, 250, 150);
        messageText.setForeground(Color.white);
        messageText.setBackground(Color.black);
        messageText.setFont(font2);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messagePanel.add(messageText);

        // ActionCommands y nombres
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
     * Renderer for table cells that enables text wrapping and automatic row height adjustment.
     */
    class WrappingCellRenderer extends JTextArea implements TableCellRenderer {

        /**
         * Constructs a WrappingCellRenderer with line wrap and padding enabled.
         */
        public WrappingCellRenderer() {
            setLineWrap(true);       // Activar ajuste de línea
            setWrapStyleWord(true);  // Ajustar por palabras completas
            setOpaque(true);
            setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5)); // Pequeño margen interno
        }

        /**
         * Returns the component used to render a table cell,
         * dynamically adjusting height based on content.
         *
         * @param table the JTable instance
         * @param value the value to assign to the cell
         * @param isSelected true if the cell is selected
         * @param hasFocus true if the cell has focus
         * @param row the row index of the cell
         * @param column the column index of the cell
         * @return the component used for rendering
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText((value == null) ? "" : value.toString());
            // Ajustar altura de fila dinámicamente (basado en el contenido)
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }

            // Colores (puedes copiarlos de cómo configuraste la tabla o usar otros)
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                // Puedes poner aquí los mismos colores que usaste para las filas normales
                setBackground(Color.decode("#D8BFD8")); // Fondo filas (Ej: Thistle claro - ¡igual que en createUI!)
                setForeground(Color.BLACK);             // Texto filas
                // O alternar colores si quieres:
                // setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                // setForeground(Color.BLACK);
            }
            setFont(table.getFont()); // Usar la fuente de la tabla
            return this;
        }
    }

    /**
     * Updates the text displayed in the message area.
     *
     * @param message the new message to show
     */
    public void setMessageText(String message) {
        this.messageText.setText(message);
    }

    /**
     * Updates the text of the pause button.
     *
     * @param message the new label text for the pause button
     */
    public void setPauseButtonText(String message) {
        this.pauseButton.setText(message);
    }

    /**
     * Updates the text of the coffee counter label.
     *
     * @param message the new label text for the coffee counter
     */
    public void setCounterLableText(String message) {
        this.counterLabel.setText(message);
    }

    /**
     * Updates the text of the per second label.
     *
     * @param message the new label text for coffees produced per second
     */
    public void setPerSecLabelText(String message) {
        this.perSecLabel.setText(message);
    }

    /**
     * Updates the text of the coffee machine purchase button.
     *
     * @param message the new label text for the coffee machine button
     */
    public void setCoffeeMachineButtonText(String message) {
        this.coffeeMachineButton.setText(message);
    }

    /**
     * Updates the text of the barista purchase button.
     *
     * @param message the new label text for the barista button
     */
    public void setBaristaButtonText(String message) {
        this.baristaButton.setText(message);
    }

    /**
     * Updates the text of the cafe purchase button.
     *
     * @param message the new label text for the cafe button
     */
    public void setCafeButtonText(String message) {
        this.cafeButton.setText(message);
    }

    /**
     * Updates the text of the coffee machine upgrade button.
     *
     * @param message the new label text for the coffee machine upgrade button
     */
    public void setCoffeeMachineUpgradeButtonText(String message) {
        this.coffeeMachineUpgradeButton.setText(message);
    }

    /**
     * Updates the text of the barista upgrade button.
     *
     * @param message the new label text for the barista upgrade button
     */
    public void setBaristaUpgradeButtonText(String message) {
        this.baristaUpgradeButton.setText(message);
    }

    /**
     * Updates the text of the cafe upgrade button.
     *
     * @param message the new label text for the cafe upgrade button
     */
    public void setCafeUpgradeButtonText(String message) {
        this.cafeUpgradeButton.setText(message);
    }


    /**
     * Adds the Coffee button listener.
     *
     * @param listener the ActionListener to notify
     */
    public void addCoffeeButtonListener(ActionListener listener) {
        coffeeButton.addActionListener(listener);
    }

    /**
     * Adds the Coffee machine button listener.
     *
     * @param listener the ActionListener to notify
     */
    public void addCoffeeMachineButtonListener(ActionListener listener) {
        coffeeMachineButton.addActionListener(listener);
    }

    /**
     * Adds the Barista button listener.
     *
     * @param listener the ActionListener to notify
     */
    public void addBaristaButtonListener(ActionListener listener) {
        baristaButton.addActionListener(listener);
    }

    /**
     * Adds the Cafe button listener.
     *
     * @param listener the ActionListener to notify
     */
    public void addCafeButtonListener(ActionListener listener) {
        cafeButton.addActionListener(listener);
    }

    /**
     * Adds the Pause button listener.
     *
     * @param listener the ActionListener to notify
     */
    public void addPauseButtonListener(ActionListener listener) {
        pauseButton.addActionListener(listener);
    }

    /**
     * Adds the End Game button listener.
     *
     * @param listener the ActionListener to notify
     */
    public void addEndGameButtonListener(ActionListener listener) {
        endGameButton.addActionListener(listener);
    }

    /**
     * Adds the Coffee machine upgrade button listener.
     *
     * @param listener the ActionListener to notify
     */
    public void addCoffeeMachineUpgradeButtonListener(ActionListener listener) {
        coffeeMachineUpgradeButton.addActionListener(listener);
    }

    /**
     * Adds the Barista upgrade button listener.
     *
     * @param listener the ActionListener to notify
     */
    public void addBaristaUpgradeButtonListener(ActionListener listener) {
        baristaUpgradeButton.addActionListener(listener);
    }

    /**
     * Adds the Cafe upgrade button listener.
     *
     * @param listener the ActionListener to notify
     */
    public void addCafeUpgradeButtonListener(ActionListener listener) {
        cafeUpgradeButton.addActionListener(listener);
    }

    /**
     * Adds the Coffee machine button mouse listener.
     *
     * @param mouseListener the MouseListener to notify
     */
    public void addCoffeeMachineButtonMouseListener(MouseListener mouseListener) {
        coffeeMachineButton.addMouseListener(mouseListener);
    }

    /**
     * Adds the Cafe button mouse listener.
     *
     * @param mouseListener the MouseListener to notify
     */
    public void addCafeButtonMouseListener(MouseListener mouseListener) {
        cafeButton.addMouseListener(mouseListener);
    }

    /**
     * Adds the Barista button mouse listener.
     *
     * @param mouseListener the MouseListener to notify
     */
    public void addBaristaButtonMouseListener(MouseListener mouseListener) {
        baristaButton.addMouseListener(mouseListener);
    }

    /**
     * Adds the Coffee machine upgrade button mouse listener.
     *
     * @param mouseListener the MouseListener to notify
     */
    public void addCoffeeMachineUpgradeButtonMouseListener(MouseListener mouseListener){
        coffeeMachineUpgradeButton.addMouseListener(mouseListener);
    }

    /**
     * Adds the Barista upgrade button mouse listener.
     *
     * @param mouseListener the MouseListener to notify
     */
    public void addBaristaUpgradeButtonMouseListener(MouseListener mouseListener) {
        baristaUpgradeButton.addMouseListener(mouseListener);
    }

    /**
     * Adds the Cafe upgrade button mouse listener.
     *
     * @param mouseListener the MouseListener to notify
     */
    public void addCafeUpgradeButtonMouseListener(MouseListener mouseListener) {
        cafeUpgradeButton.addMouseListener(mouseListener);
    }

    /**
     * Updates the generator statistics table with new data.
     *
     * @param data a 2D Object array where each row contains:
     *             {Name, Quantity, Unit Production, Total Production, Percentage Overall}
     */
    public void updateGeneratorStatsTable(Object[][] data) {
        // Limpia las filas anteriores
        generatorStatsTableModel.setRowCount(0);

        // Añade las nuevas filas que lleguen en 'data'
        if (data != null) {
            for (Object[] rowData : data) {
                generatorStatsTableModel.addRow(rowData);
            }
        }
    }

}


