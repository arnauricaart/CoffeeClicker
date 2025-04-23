package Persistance;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConfigJSONDAO implements ConfigDAO{
    private final String CONFIG_PATH = "data/ConfigurationFile.json";
    private JsonObject config;

    public ConfigJSONDAO() {
        try (FileReader reader = new FileReader(CONFIG_PATH)) {
            Gson gson = new Gson();
            config = gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the configuration file");
        }
    }

    public int getDatabasePort() {
        return config.getAsJsonObject("database").get("port").getAsInt();
    }

    public String getDatabaseHost() {
        return config.getAsJsonObject("database").get("host").getAsString();
    }

    public String getDatabaseName() {
        return config.getAsJsonObject("database").get("name").getAsString();
    }

    public String getDatabaseUser() {
        return config.getAsJsonObject("database").get("user").getAsString();
    }

    public String getDatabasePassword() {
        return config.getAsJsonObject("database").get("password").getAsString();
    }
}