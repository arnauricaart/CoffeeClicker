package persistence;
import persistence.persistenceExceptions.DBGeneralException;
import persistence.persistenceExceptions.FileNotFound;

import java.sql.*;

/**
 * This class makes impossible to make more than one connection instance to the database.
 */
public final class Singleton {
    // The field must be declared volatile so that double check lock would work
    // correctly.
    /**
     * Instance of the class that will be given any time is needed.
     */
    private static volatile Singleton instance;
    /**
     * Connection to the database, only one will be posible to be made.
     */
    private Connection conn;

    /**
     * Constructor of the class, it will use the ConfigJSONDAO class to get the information to make the connection to the database.
     */
    private Singleton() {//throws FileNotFound {
        ConfigJSONDAO config = new ConfigJSONDAO();
        String url = "jdbc:mysql://" + config.getDatabaseHost() + ":" + config.getDatabasePort() + "/" + config.getDatabaseName();
        String driver = "com.mysql.cj.jdbc.Driver";
        String usuario = config.getDatabaseUser();
        String password = config.getDatabasePassword();
        try{
            Class.forName(driver);
            this.conn = DriverManager.getConnection(url, usuario, password);
        }
        catch(ClassNotFoundException | SQLException e){
            //throw new DBGeneralException("Hol");
            e.printStackTrace();
        }
    }

    /**
     * This method is the one used in any other class to get an instance of the Singleton class.
     * @return Returns an instance of the class Singleton.
     */
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

    /**
     * Getter for the database connection.
     * @return Returns an instance of the connection to the database.
     */
    public Connection getConn(){

        return this.conn;
    }

}