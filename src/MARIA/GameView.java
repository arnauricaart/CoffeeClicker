package MARIA;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class GameView {

    private JFrame window; // HAY QUE AÑADIR PRIVATE AQUÍ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
        coffeeMachineButton.setActionCommand("COFFEEMACHINEUPGRADEBUTTON");
        baristaButton.setActionCommand("BARISTAUPGRADEBUTTON");
        cafeButton.setActionCommand("CAFEUPGRADEBUTTON");
        ////////////////////////////////////////////

        pauseButton.setActionCommand("PAUSEBUTTON");
        endGameButton.setActionCommand("ENDGAMEBUTTON");

        coffeeMachineButton.setName("COFFEEMACHINEBUTTON");
        baristaButton.setName("BARISTABUTTON");
        cafeButton.setName("CAFEBUTTON");

        /////////////////UGRADES////////////////////
        coffeeMachineButton.setName("COFFEEMACHINEUPGRADEBUTTON");
        baristaButton.setName("BARISTAUPGRADEBUTTON");
        cafeButton.setName("CAFEUPGRADEBUTTON");
        ////////////////////////////////////////////

        window.setVisible(true);
    }
}


