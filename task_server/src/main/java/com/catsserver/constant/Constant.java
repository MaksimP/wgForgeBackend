package com.catsserver.constant;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constant {

    /*public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    //public static final String URL = "jdbc:postgresql://192.168.99.100:5432/wg_forge_db";
    public static final String USER = "wg_forge";
    public static final String PASSWORD = "42a";*/

    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream stream = ClassLoader.getSystemResourceAsStream("config.properties")){
            Properties prop = new Properties();
            prop.load(stream);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
