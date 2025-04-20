package Business;
// Classe de dades temporal per representar una partida
public class GameData {
    private int id;
    private String nombre;
    private int coffees;
    private String ultimoAcceso;

    public GameData(int id, String nombre, int coffees, String ultimoAcceso) {
        this.id = id;
        this.nombre = nombre;
        this.coffees = coffees;
        this.ultimoAcceso = ultimoAcceso;
    }

    public int getId() {
        return id;
    }

    public int getCoffees() {
        return coffees;
    }

    public String getUltimoAcceso() {
        return ultimoAcceso;
    }

    public String getNombre() {
        return nombre;
    }
}
