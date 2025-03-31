package Persitence;

import com.google.gson.Gson;

public interface ConfigJSONDAO {

    int getDatabasePort();

    String getDatabaseHost();

    String getDatabaseName();

    String getDatabaseUser();

    String getDatabasePassword();

}
