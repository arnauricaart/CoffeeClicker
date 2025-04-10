package Persitence;
import java.sql.*;

public final class Singleton {
    // The field must be declared volatile so that double check lock would work
    // correctly.
    private static volatile Singleton instance;

    private Connection conn;

    private Singleton() {
        String url = "jdbc:mysql://localhost:3306/aitorgarcia";
        String driver = "com.mysql.cj.jdbc.Driver";
        String usuario = "root";
        String password = "";
        try{
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url, usuario, password);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public static Singleton getInstance() {

        Singleton result = instance;
        if (result != null) {
            return result;
        }
        synchronized(Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }

    public Connection getConn(){
        return this.conn;
    }

}