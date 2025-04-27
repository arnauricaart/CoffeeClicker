package Persitence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatsDBDAO {

    public StatsDBDAO(){

    }

    public List<Integer> getStatsByGameId(int gameID){
        String query = "Select * FROM stats WHERE IdPartida = ? ORDER BY Minuto ASC";
        ArrayList<String> valores = new ArrayList<>();
        ArrayList<String> tipos = new ArrayList<>();
        valores.add(String.valueOf(gameID));
        tipos.add("int");
        ResultSet res = SQL_CRUD.Select(query,valores,tipos);

        List<Integer> cafeCounts = new ArrayList<>();
        try {
            while (res.next()) {
                cafeCounts.add(res.getInt("Cafes"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cafeCounts;
    }

    public int updateStats(int gameId, int cafes, int min){
        String query = "INSERT INTO stats VALUES (Cafes, Minuto, IdPartida) VALUES (?,?,?)";
        ArrayList<String> valores = new ArrayList<String>();
        valores.add(String.valueOf(cafes));
        valores.add(String.valueOf(min));
        valores.add(String.valueOf(gameId));

        return 0;
    }
}
