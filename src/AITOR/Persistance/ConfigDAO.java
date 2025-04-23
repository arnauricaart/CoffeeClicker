package Persistance;

public interface ConfigDAO {

    int getDatabasePort();

    String getDatabaseHost();

    String getDatabaseName();

    String getDatabaseUser();

    String getDatabasePassword();

}