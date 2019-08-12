package com.task12;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class General {

   /* private static final String URL = "jdbc:postgresql://localhost:5432/wg_forge_db";
    //private static final String URL = "jdbc:postgresql://192.168.99.100:5432/wg_forge_db";
    private static final String USER = "wg_forge";
    private static final String PASSWORD = "42a";*/

    public static void main(String[] args) {
        System.out.println("Start....");
        String url = "";
        String user = "";
        String password = "";

        try (InputStream stream = ClassLoader.getSystemResourceAsStream("config.properties")){
            System.out.println(stream != null);
            Properties prop = new Properties();
            prop.load(stream);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        com.task12.DB db = new com.task12.DB(url, user, password);
        db.executeDB();
        System.out.println("Done!");
    }
}


