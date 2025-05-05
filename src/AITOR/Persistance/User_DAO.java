package Persistance;

import java.sql.ResultSet;
import java.util.ArrayList;
import persistence.SQL_CRUD;

public class User_DAO {

    public ResultSet login(String val, String pass){
        String query = "Select * FROM users WHERE ";
        if (val.contains("@")){
            query = query + "Correo=? AND Contrasena=?";
        } else {
            query = query + "Nombre=? AND Contrasena=?";
        }
        ArrayList<String> values = new ArrayList<String>();
        values.add(val);
        values.add(pass);
        ArrayList<String> tipos = new ArrayList<String>();
        values.add("String");
        values.add("String");
        ResultSet res = SQL_CRUD.Select(query,values,tipos);
        return res;
    }
}
