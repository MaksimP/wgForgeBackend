package com.catsserver.dao;

import com.catsserver.constant.Constant;
import com.catsserver.model.Cat;

import java.sql.*;

public class AddCatsDAOImpl implements AddCatsDAO{

    private final String URL = Constant.URL;
    private final String USER = Constant.USER;
    private final String PASSWORD = Constant.PASSWORD;

    @Override
    public void addCats(Cat cat) throws SQLException {
       /* try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            try (Statement statement = connection.createStatement()){
                String sql = "insert into cats values ('" + cat.getName() + "', '" + cat.getColor()
                        + "', " + cat.getTail_length() + ", " + cat.getWhiskers_length() + ");";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
