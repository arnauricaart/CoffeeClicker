package HECTOR;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EndGameStatsGUI extends JFrame {

    private GameSelectionListener listener;
    private JButton backButton;

    public EndGameStatsGUI(List<Game_Provisional> partidasFinalizadas) {
        setTitle("Partidas Finalizadas Graficas");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Boton de volver en la parte superior
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Volver");
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        // Botones para las partidas finalizadas
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        for (Game_Provisional partida : partidasFinalizadas) {
            JButton btn = new JButton(partida.getName());
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(300, 40));
            btn.addActionListener(e -> {
                if (listener != null) {
                    listener.onGameSelected(partida);
                }
            });
            buttonPanel.add(Box.createVerticalStrut(10)); // Espaciado
            buttonPanel.add(btn);
        }

        // Añado un scroll en caso de que hayan muchos botones
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setGameSelectionListener(GameSelectionListener listener) {
        this.listener = listener;
    }

    public void setBackButtonListener(Runnable callback) {
        backButton.addActionListener(e -> callback.run());
    }
}
