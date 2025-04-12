package Presentation.views;
//TODO ha d'anar a business o DAO
// Classe de dades temporal per representar una partida
public class GameData {
    private int id;
    private int coffees;

    public GameData(int id, int coffees) {
        this.id = id;
        this.coffees = coffees;
    }

    public int getId() {
        return id;
    }

    public int getCoffees() {
        return coffees;
    }
}
