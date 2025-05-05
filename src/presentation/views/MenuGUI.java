package presentation.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuGUI extends JFrame {

    private JButton newGameButton;
    private JButton statisticsButton;
    private JButton logoutButton;
    private JButton deleteAccountButton;

    public MenuGUI() {
        setTitle("Coffee Clicker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245)); // fondo claro

        // Panel lateral izquierdo con imagen rotada + escalada
        JLabel leftCoffee = createRotatedImageLabel("/coffee.png", 400, 400, 15, 1.0);
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(false);
        leftPanel.add(leftCoffee);

        // Imagen derecha: rotada -30°, escalada al 130%
        JLabel rightCoffee = createRotatedImageLabel("/coffee.png", 400, 400, -30, 1.3f);

        // Panel contenedor que posiciona la imagen manualmente
        JPanel rightContainer = new JPanel(null); // null layout: colocación absoluta
        rightContainer.setOpaque(false);
        rightContainer.setPreferredSize(new Dimension(300, 500)); // espacio visible limitado

        // Posicionamos la imagen parcialmente fuera del panel visible (positivo en X)
        int x = -20; // cuanto más negativo, más hacia la derecha está
        int y = 50;
        rightCoffee.setBounds(x, y, rightCoffee.getPreferredSize().width, rightCoffee.getPreferredSize().height);

        rightContainer.add(rightCoffee);

        // Panel central
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(80, 40, 80, 40));

        JLabel titleLabel = new JLabel("WELCOME TO COFFEE CLICKER", JLabel.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.DARK_GRAY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        centerPanel.add(titleLabel);

        newGameButton = createStyledButton("PLAY");
        statisticsButton = createStyledButton("STATISTICS");
        logoutButton = createStyledButton("LOGOUT");
        deleteAccountButton = createStyledButton("DELETE ACCOUNT");

        centerPanel.add(newGameButton);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(statisticsButton);

        // separación mayor entre grupos
        centerPanel.add(Box.createVerticalStrut(40));

        centerPanel.add(logoutButton);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(deleteAccountButton);


        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightContainer, BorderLayout.EAST);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBackground(new Color(220, 220, 220));
        button.setForeground(Color.BLACK);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 40));
        return button;
    }

    private JLabel createRotatedImageLabel(String path, int baseWidth, int baseHeight, double rotationDegrees, double scaleFactor) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResource(path));

            int scaledWidth = (int) (baseWidth * scaleFactor);
            int scaledHeight = (int) (baseHeight * scaleFactor);

            Image scaledImage = img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

            BufferedImage rotated = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = rotated.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            AffineTransform at = new AffineTransform();
            at.translate(scaledWidth / 2.0, scaledHeight / 2.0);
            at.rotate(Math.toRadians(rotationDegrees));
            at.translate(-scaledWidth / 2.0, -scaledHeight / 2.0);
            g2d.drawImage(scaledImage, at, null);
            g2d.dispose();

            JLabel label = new JLabel(new ImageIcon(rotated));
            label.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
            return label;
        } catch (IOException e) {
            System.err.println("⚠ No se pudo cargar la imagen: " + path);
            return new JLabel();
        }
    }

    // Métodos para el controlador
    public void setNewGameButtonListener(java.awt.event.ActionListener l) {
        newGameButton.addActionListener(l);
    }

    public void setStatisticsButtonListener(java.awt.event.ActionListener l) {
        statisticsButton.addActionListener(l);
    }

    public void setLogoutButtonListener(java.awt.event.ActionListener l) {
        logoutButton.addActionListener(l);
    }

    public void setDeleteAccountButtonListener(java.awt.event.ActionListener l) {
        deleteAccountButton.addActionListener(l);
    }

    public void showGameExists(){
        JOptionPane.showMessageDialog(this, "You have a game started", "Info", JOptionPane.INFORMATION_MESSAGE);

    }

}