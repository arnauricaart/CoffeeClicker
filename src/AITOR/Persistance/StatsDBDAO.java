package Persistance;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StatsDBDAO {

    public StatsDBDAO(){

    }

    //HACEN FALTA CAMBIOS EN LA BBDD PARA QUE FUNCIONE, EJ: MINUTO EN STATS ESTA COMO MINUBO
    public ResultSet getStatsByGameId(int gameID){
        String query = "Select * FROM stats WHERE IdPartida=? ORDER BY Minuto ASC";
        ArrayList<String> valores = new ArrayList<String>();
        valores.add(String.valueOf(gameID));
        ArrayList<String> tipos = new ArrayList<String>();
        tipos.add("int");
        ResultSet res = SQL_CRUD.Select(query,valores,tipos);
        return res;
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
