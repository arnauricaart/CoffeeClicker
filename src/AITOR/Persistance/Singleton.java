package Persistance;
import java.sql.*;

public final class Singleton {
    // The field must be declared volatile so that double check lock would work
    // correctly.
    private static volatile Singleton instance;

    private Connection conn;

    private Singleton() {
        ConfigJSONDAO conf = new ConfigJSONDAO();
        String url = "jdbc:mysql://" + conf.getDatabaseHost() + ":" + conf.getDatabasePort() + "/" + conf.getDatabaseName();
        String driver = "com.mysql.cj.jdbc.Driver";
        String usuario = conf.getDatabaseUser();
        String password = conf.getDatabasePassword();
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