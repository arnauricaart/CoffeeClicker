package MARIA;


import javax.swing.*;
import java.awt.*;

public class GameView {

    JFrame window; // HAY QUE AÑADIR PRIVATE AQUÍ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    JLabel counterLabel, perSecLabel;
    JButton coffeeButton, cursorButton, grandpaButton, mysteryButton, pauseButton;
    JTextArea messageText;
    Font font1, font2;

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

        ImageIcon originalIcon = new ImageIcon("data/coffee.png");
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

        cursorButton = new JButton("Cursor (0)");
        cursorButton.setFont(font1);
        cursorButton.setFocusPainted(false);
        itemPanel.add(cursorButton);

        grandpaButton = new JButton("?");
        grandpaButton.setFont(font1);
        grandpaButton.setFocusPainted(false);
        itemPanel.add(grandpaButton);

        mysteryButton = new JButton("?");
        mysteryButton.setFont(font1);
        mysteryButton.setFocusPainted(false);
        itemPanel.add(mysteryButton);

        pauseButton = new JButton("Pause");
        itemPanel.add(pauseButton);

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

        window.setVisible(true);
    }
}


