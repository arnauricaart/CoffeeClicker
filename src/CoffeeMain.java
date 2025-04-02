import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class CoffeeMain {

    // Declaración de componentes de la interfaz gráfica
    JLabel counterLabel, perSecLabel;
    JButton button1, button2, button3;
    int coffeeCounter, timerSpeed, cursorNumber, cursorPrice, grandpaNumber, grandpaPrice, ugrandpa, cursorPriceBase, grandpaPriceBase;
    double perSecond, calculateCursorPrice, calculateGrandpaPrice;
    boolean timerOn, grandpaUnlocked;
    Font font1, font2;
    CoffeeHandler cHandler = new CoffeeHandler(); // Manejador de eventos para botones
    Timer timer; // Temporizador para las actualizaciones periódicas
    JTextArea messageText, shopText; // Área de texto para mostrar información
    MouseHandler mHandler = new MouseHandler(); // Manejador de eventos para el mouse

    public static void main(String[] arg) {
        new CoffeeMain(); // Llama al constructor de la clase
    }

    public CoffeeMain() {
        // Inicialización de variables
        timerOn = false;
        perSecond = 0;
        coffeeCounter = 0;
        cursorNumber = 0;
        grandpaUnlocked = false;
        grandpaNumber = 0;
        ugrandpa = 150; // Precio para desbloquear el abuelo

        calculateGrandpaPrice = 150;
        calculateCursorPrice = 10;
        cursorPriceBase = 10;
        grandpaPriceBase = 150;
        cursorPrice = cursorPriceBase;
        grandpaPrice = grandpaPriceBase;


        createFont();  // Método para crear las fuentes
        createUI();    // Método para crear la interfaz gráfica
    }

    public void createFont() {
        // Definición de las fuentes a usar
        font1 = new Font("DePixel", Font.PLAIN, 25);
        font2 = new Font("DePixel", Font.PLAIN, 13);
    }

    public void createUI() {
        // Configuración de la ventana principal
        JFrame window = new JFrame();
        window.setSize(1280, 720);                     // Medidas de la ventana
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);  // Layout personalizado para colocar componentes a mano

        // Panel donde se coloca el botón principal (coffee)
        JPanel coffeePanel = new JPanel();
        coffeePanel.setBounds(100, 220, 350, 350);
        coffeePanel.setBackground(Color.black);
        window.add(coffeePanel);

        // Cargar y redimensionar el ícono de la imagen
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/coffee.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Botón principal para hacer clic y generar coffee
        JButton coffeeButton = new JButton();
        coffeeButton.setBackground(Color.black);
        coffeeButton.setFocusPainted(false);
        coffeeButton.setBorder(null);
        coffeeButton.setIcon(scaledIcon);
        coffeeButton.addActionListener(cHandler);
        coffeeButton.setActionCommand("coffee");
        coffeePanel.add(coffeeButton);

        // Panel para mostrar el contador de coffee
        JPanel counterPanel = new JPanel();
        counterPanel.setBounds(100, 100, 300, 100);
        counterPanel.setBackground(Color.black);
        counterPanel.setLayout(new GridLayout(2, 1)); // Dos etiquetas en una columna
        window.add(counterPanel);

        // Etiqueta para mostrar el número de coffee
        counterLabel = new JLabel(coffeeCounter + " coffees");
        counterLabel.setForeground(Color.white);
        counterLabel.setFont(font1);
        counterPanel.add(counterLabel);

        // Etiqueta para mostrar los coffees generados por segundo
        perSecLabel = new JLabel();
        perSecLabel.setForeground(Color.white);
        perSecLabel.setFont(font2);
        counterPanel.add(perSecLabel);

        // Panel contenedor para SHOP y los botones
        JPanel shopContainer = new JPanel();
        shopContainer.setBounds(930, 80, 300, 40);
        shopContainer.setBackground(Color.black);
        shopContainer.setLayout(new BorderLayout());  // Usa BorderLayout para separar NORTH y CENTER
        window.add(shopContainer);

        // Etiqueta para el título SHOP con font1
        JLabel shopLabel = new JLabel("SHOP", SwingConstants.LEFT);
        shopLabel.setForeground(Color.white);
        shopLabel.setFont(font1);
        shopContainer.add(shopLabel, BorderLayout.NORTH);

        // Panel contenedor para UPGRADES y los botones
        JPanel upgradesContainer = new JPanel();
        upgradesContainer.setBounds(930, 380, 300, 200);
        upgradesContainer.setBackground(Color.black);
        upgradesContainer.setLayout(new BorderLayout());  // Usa BorderLayout para separar NORTH y CENTER
        window.add(upgradesContainer);

        // Etiqueta para el título UPGRADES con font1
        JLabel upgradeLabel = new JLabel("UPGRADES", SwingConstants.LEFT);
        upgradeLabel.setForeground(Color.white);
        upgradeLabel.setFont(font1);
        upgradesContainer.add(upgradeLabel, BorderLayout.NORTH);


        // Panel para los botones de objetos en la sección SHOP
        // Se agrega separación entre botones usando GridLayout con gaps (10 píxeles en horizontal y vertical)
        JPanel itemPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        itemPanel.setBounds(930, 130, 300, 200);
        itemPanel.setBackground(Color.black);
        window.add(itemPanel);

        // Botón de "Cursor"
        button1 = new JButton("Cursor (" + cursorNumber + ")");
        button1.setFont(font1);
        button1.setFocusPainted(false);
        button1.addActionListener(cHandler);
        button1.setActionCommand("Cursor");
        button1.addMouseListener(mHandler);
        itemPanel.add(button1);

        // Botón de "Grandpa"
        button2 = new JButton("?");
        button2.setFont(font1);
        button2.setFocusPainted(false);
        button2.addActionListener(cHandler);
        button2.setActionCommand("Grandpa");
        button2.addMouseListener(mHandler);
        itemPanel.add(button2);

        // Botón 3 (se deja como "locked" en el ejemplo)
        button3 = new JButton("?");
        button3.setFont(font1);
        button3.setFocusPainted(false);
        button3.addActionListener(cHandler);
        button3.setActionCommand("Cursor");
        button3.addMouseListener(mHandler);
        itemPanel.add(button3);

        // Panel para mostrar los mensajes
        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(500, 70, 250, 150);
        messagePanel.setBackground(Color.black);
        window.add(messagePanel);

        // Área de texto para mostrar el mensaje del botón seleccionado
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



    // Método para configurar el temporizador
    public void setTimer() {
        timer = new Timer(timerSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coffeeCounter++;
                counterLabel.setText(coffeeCounter + " coffees");
                // Verificamos si se debe desbloquear "Grandpa" (si llega a 25 coffees)
                updateGrandpaStatus();
            }
        });
    }

    // Método para actualizar el temporizador según el valor de perSecond
    public void timerUpdate() {
        if (timerOn == false) {
            timerOn = true; // Inicia el temporizador
        } else {
            timer.stop(); // Detiene el temporizador
        }

        // Actualiza la velocidad del temporizador según el valor de perSecond
        double speed = 1 / perSecond * 1000;
        timerSpeed = (int) Math.round(speed);
        String s = String.format("%.1f", perSecond);
        perSecLabel.setText("per second: " + s);

        // Inicia el temporizador con los nuevos valores
        setTimer();
        timer.start();
    }

    // Método auxiliar para actualizar el estado de "Grandpa" al alcanzar ugrandpa
    public void updateGrandpaStatus() {
        if (coffeeCounter >= ugrandpa && !grandpaUnlocked) {
            grandpaUnlocked = true;
            button2.setText("Grandpa (" + grandpaNumber + ")");
        }
    }

    // Método para reproducir el sonido de error
    public void playErrorSound() {
        try {
            // Cargar el archivo de sonido
            File soundFile = new File("res\\error.wav"); // Especifica la ruta correcta del archivo
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Obtener el Clip
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Reproducir el sonido
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();  // Maneja cualquier error al cargar o reproducir el sonido
        }
    }

    // Sonido de compra
    public void playPurchaseSound() {
        try {
            // Cargar el archivo de sonido para la compra
            File soundFile = new File("res\\ding.wav"); // Especifica la ruta correcta del archivo
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Obtener el Clip
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Reproducir el sonido
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();  // Maneja cualquier error al cargar o reproducir el sonido
        }
    }

    // Clase que maneja los eventos de los botones
    public class CoffeeHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String action = event.getActionCommand();

            // Switch para manejar las acciones de los botones
            switch (action) {
                case "coffee":
                    coffeeCounter++;
                    counterLabel.setText(coffeeCounter + " coffees");
                    // Verificamos inmediatamente si se debe desbloquear "Grandpa"
                    if (!grandpaUnlocked) {
                        updateGrandpaStatus();
                    }
                    break;
                case "Cursor":

                    // Comprar un cursor
                    if (coffeeCounter >= cursorPrice) {
                        coffeeCounter = coffeeCounter - cursorPrice;
                        counterLabel.setText(coffeeCounter + " coffees");
                        cursorNumber++;
                        calculateCursorPrice = cursorPriceBase * Math.pow(1.07, cursorNumber);
                        cursorPrice = (int) Math.round(calculateCursorPrice);
                        button1.setText("Cursor (" + cursorNumber + ")");
                        messageText.setText("Cursor\n[price: " + cursorPrice + "]\nAutoclicks once every 10 seconds.");
                        perSecond = perSecond + 0.2;
                        timerUpdate();
                        playPurchaseSound();  // Reproduce el sonido de compra
                    } else {
                        messageText.setText("You need more coffees!");
                        playErrorSound();  // Llama al sonido de error
                    }
                    break;
                case "Grandpa":


                    grandpaPrice = (int) Math.round(calculateGrandpaPrice);


                    // Comprar un abuelo
                    if (coffeeCounter >= grandpaPrice) {
                        coffeeCounter = coffeeCounter - grandpaPrice;
                        counterLabel.setText(coffeeCounter + " coffees");
                        grandpaNumber++;
                        calculateGrandpaPrice = grandpaPriceBase * Math.pow(1.15, grandpaNumber);
                        grandpaPrice = (int) Math.round(calculateGrandpaPrice);
                        button2.setText("Grandpa (" + grandpaNumber + ")");
                        messageText.setText("Grandpa\n[price: " + grandpaPrice + "]\nEach grandpa produces 1 coffee per second.");
                        perSecond = perSecond + 0.5;
                        timerUpdate();
                        playPurchaseSound();  // Reproduce el sonido de compra
                    } else {
                        messageText.setText("You need more coffees!");
                        playErrorSound();  // Llama al sonido de error
                    }
                    break;
            }
        }
    }

    // Clase que maneja los eventos del mouse sobre los botones
    public class MouseHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            // Actualiza el mensaje cuando el mouse entra en un botón
            if (button == button1) {
                messageText.setText("Cursor\n[price: " + cursorPrice + "]\nAutoclicks once every 10 seconds.");
            } else if (button == button2) {
                if (!grandpaUnlocked) {
                    messageText.setText("This item is currently locked!");
                } else {
                    messageText.setText("Grandpa\n[price: " + grandpaPrice + "]\nEach grandpa produces 1 coffee per second.");
                }
            } else if (button == button3) {
                messageText.setText("This item is currently locked!");
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            messageText.setText(null); // Borra el mensaje cuando el mouse sale del botón
        }
    }
}
