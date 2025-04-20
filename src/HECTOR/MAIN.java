package HECTOR;

import javax.swing.*;
import java.util.List;


public class MAIN {
    public static void main(String[] args) {
        List<Game> juegos = SimuladorDePartidas.obtenerPartidasFinalizadas();

        SwingUtilities.invokeLater(() -> {
            new ControllerEndGameStats(juegos);
        });
    }
}