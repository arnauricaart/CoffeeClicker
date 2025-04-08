package constants;

import java.awt.*;


public class CommonConstants {
    public static final Color PRIMARY_COLOR = Color.decode("#FFFFFF");
    public static final Color SECONDARY_COLOR = Color.decode("#D9D9D9");
    public static final Color BUTTON_COLOR = Color.decode("#9E6B57");
    public static final Color TEXT_COLOR = Color.decode("#000000");

    //SQL credecials

    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/cofeeclicker_schema";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "hola";
    public static final String DB_USERS_TABLE_NAME = "users";
    public static final String DB_STATS_TABLE_NAME = "stats";
    public static final String DB_PARTIDA_TABLE_NAME = "partida";
    public static final String DB_GENERADOR_TABLE_NAME = "generador";
    public static final String DB_PARTIDA_GENERADOR_TABLE_NAME = "partida_generador";

}
