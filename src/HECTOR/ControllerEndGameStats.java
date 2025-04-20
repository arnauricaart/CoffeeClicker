package HECTOR;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerEndGameStats implements GameSelectionListener {

    private EndGameStatsGUI gui;

    public ControllerEndGameStats(List<Game> partidas) {
        List<Game> finalizadas = partidas.stream()
                .filter(Game::hasEnded)
                .collect(Collectors.toList());

        gui = new EndGameStatsGUI(finalizadas);
        gui.setGameSelectionListener(this);

        gui.setBackButtonListener(() -> {
            gui.dispose();
            // Aquí podrías lanzar tu clase de menú principal si la tienes
        });

        gui.setVisible(true);
    }

    @Override
    public void onGameSelected(Game game) {
        // Convertimos datos para la gráfica (int -> float)
        List<Integer> cafes = game.getCoffeePerMinute().stream()
                .map(Float::intValue)
                .collect(Collectors.toList());

        JDialog dialogo = new JDialog(gui, "Gráfica - " + game.getName(), true);
        CafeStatsChart chart = new CafeStatsChart(cafes);
        dialogo.setContentPane(chart.getContentPane());
        dialogo.pack();
        dialogo.setLocationRelativeTo(gui);
        dialogo.setVisible(true);
    }
}


