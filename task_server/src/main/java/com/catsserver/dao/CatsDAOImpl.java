package com.catsserver.dao;

import com.google.common.primitives.Ints;

import java.sql.*;
import java.util.*;

public class CatsDAOImpl implements CatsDAO {

    private final String URL = "jdbc:postgresql://localhost:5432/postgres";
    //private final String URL = "jdbc:postgresql://192.168.99.100:5432/postgres";
    private final String USER = "postgres";
    private final String PASSWORD = "1234";

    public List<Map<String, String>> getAll(String... param) {

        List<Map<String, String>> allCats = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            try (Statement statement = connection.createStatement()){
                String attribute = checkAttribute(param[0], statement) ? " order by " + param[0] + " " : "";
                String order = checkOrder(param[1]) && !attribute.isEmpty() ? param[1] : "";
                String limit = checkLimit(param[2]) ? " limit " + param[2] : "";
                String offset = checkOffset(param[3], statement) ? " offset " + param[3] : "";
                String SQL = "select * from cats" + attribute + order + limit + offset;

                ResultSet resultSet = statement.executeQuery(SQL);
                while (resultSet.next()) {
                    Map<String, String> resultRow = new LinkedHashMap<>();
                    resultRow.put("name", resultSet.getString(1));
                    resultRow.put("color", resultSet.getString(2));
                    resultRow.put("tail_length", resultSet.getString(3));
                    resultRow.put("whiskers_length", resultSet.getString(4));
                    allCats.add(resultRow);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCats;
    }

    private boolean checkAttribute(String attribute, Statement statement) throws SQLException {
        String sql = "select column_name from information_schema.columns where table_name='cats'";
        ResultSet resultSet = statement.executeQuery(sql);
        boolean result = false;
        while (resultSet.next()) {
            if (resultSet.getString(1).equals(attribute)) {
                result = true;
                break;
            }
        }
        return attribute != null && result;
    }

    private boolean checkOrder(String order) {
        return order != null && (order.equals("asc") || order.equals("desc"));
    }

    private boolean checkLimit(String limit) {
        return limit != null && Ints.tryParse(limit) != null;
    }

    private boolean checkOffset(String offset, Statement statement) throws SQLException {
        String sql = "select count(*) from cats";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return offset != null && Ints.tryParse(offset) != null
                && Integer.valueOf(offset) < resultSet.getInt(1);
    }
}