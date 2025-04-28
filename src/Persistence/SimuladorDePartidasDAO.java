package Persistence;

import Business.Game;

import java.util.List;
import java.util.ArrayList;

public class SimuladorDePartidasDAO {
    public static List<Game> obtenerPartidasFinalizadas() {
        List<Game> juegos = new ArrayList<>();

        Game g1 = new Game(1);
        g1.endGame();
        g1.getCoffeePerMinute().addAll(List.of(10f, 20f, 50f, 100f, 200f));
        juegos.add(g1);

        Game g2 = new Game(2);
        g2.endGame();
        g2.getCoffeePerMinute().addAll(List.of(5f, 15f, 30f, 70f, 150f));
        juegos.add(g2);

        Game g3 = new Game(3);
        g3.endGame();
        g3.getCoffeePerMinute().addAll(List.of(500f, 15f, 100f, 70f, 150f));
        juegos.add(g3);

        Game g4 = new Game(4);
        g4.endGame();
        g4.getCoffeePerMinute().addAll(List.of(1000f, 105f, 370f, 70f, 500f));
        juegos.add(g4);

        Game g5 = new Game(5);
        g5.endGame();
        g5.getCoffeePerMinute().addAll(List.of(0f, 100f, 200f, 300f, 134f));
        juegos.add(g5);

        return juegos;
    }
}

