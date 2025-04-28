package Persistence;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class SQL_CRUD {
    public SQL_CRUD(){

    }
    public static ResultSet Select(String query, ArrayList<String> values, ArrayList<String> tipos){
        PreparedStatement pst;
        Singleton s1 = Singleton.getInstance();
        ResultSet res;
        try {
            pst = s1.getConn().prepareStatement(query);
            for (int i = 0; i < values.size(); i++){
                if (tipos.get(i).equals("String")){
                    pst.setString(i +1,values.get(i));
                } else if (tipos.get(i).equals("int")){
                    pst.setInt(i+1,Integer.parseInt(values.get(i)));
                } else if (tipos.get(i).equals("float")){
                    pst.setDouble(i+1, Double.parseDouble(values.get(i)));
                }
            }
            res = pst.executeQuery();
            System.out.println(query);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return res;
    }

    public static int CUD(String query, ArrayList<String> values, ArrayList<String> tipos){
        PreparedStatement pst;
        Singleton s1 = Singleton.getInstance();
        int res;
        try {
            System.out.println(query);
            pst = s1.getConn().prepareStatement(query);
            for (int i = 0; i < values.size(); i++){
                if (tipos.get(i).equals("String")){
                    pst.setString(i+1,values.get(i));
                } else if (tipos.get(i).equals("int")){
                    pst.setInt(i + 1,Integer.parseInt(values.get(i)));
                } else if (tipos.get(i).equals("float")){
                    pst.setDouble(i +1, Double.parseDouble(values.get(i)));
                }
            }
            res = pst.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new ConstraintException(e);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return res;
    }
}

