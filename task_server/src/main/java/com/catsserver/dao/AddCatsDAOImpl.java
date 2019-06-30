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
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            try (Statement statement = connection.createStatement()){
                String sql = "insert into cats values ('" + cat.getName() + "', '" + cat.getColor()
                        + "', " + cat.getTail_length() + ", " + cat.getWhiskers_length() + ");";
                System.out.println(sql);
                //System.out.println(checkColor(statement, cat.getColor()));
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw e;
        }

    }

    private String checkColor(Statement statement, String color) throws SQLException {
        System.out.println("Test**");
        String sql = "show columns from cat_color";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("Test");
        resultSet.next();
        System.out.println(resultSet.getString("Type"));
        /*while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }*/
        System.out.println("Test2");
        return "Test4";
    }
}
