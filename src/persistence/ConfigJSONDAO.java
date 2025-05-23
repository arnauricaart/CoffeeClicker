package persistence;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import persistence.persistenceExceptions.FileNotFound;

/**
 * This class allows to get information for the connection to the database from a JSON file.
 */
public class ConfigJSONDAO {
    /**
     * String atribute with the route to the JSON file.
     */
    private final String CONFIG_PATH = "data/ConfigurationFile.json";
    /**
     * JsonObject used to call methods to retrive information from the JSON file.
     */
    private JsonObject config;

    /**
     * Constructor of the class.
     */
    public ConfigJSONDAO() throws FileNotFound {
        try (FileReader reader = new FileReader(CONFIG_PATH)) {
            Gson gson = new Gson();
            config = gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            throw new FileNotFound();
        }
    }

    /**
     * This method returns the database port that will be used in the connection.
     * @return Returns a number with the port used in the connection.
     */
    public int getDatabasePort() {
        return config.getAsJsonObject("database").get("port").getAsInt();
    }

    /**
     * This method returns the database ip that will be used in the connection.
     * @return Returns a string with the ip used in the connection.
     */
    public String getDatabaseHost() {
        return config.getAsJsonObject("database").get("host").getAsString();
    }

    /**
     * This method returns the database name that will be used in the connection.
     * @return Returns a string with the database name used in the connection.
     */
    public String getDatabaseName() {
        return config.getAsJsonObject("database").get("name").getAsString();
    }

    /**
     * This method returns the database user that will be used in the connection.
     * @return Returns a string with the user used in the connection.
     */
    public String getDatabaseUser() {
        return config.getAsJsonObject("database").get("user").getAsString();
    }

    /**
     * This method returns the database password that will be used in the connection.
     * @return Returns a string with the database password used in the connection.
     */
    public String getDatabasePassword() {
        return config.getAsJsonObject("database").get("password").getAsString();
    }
}