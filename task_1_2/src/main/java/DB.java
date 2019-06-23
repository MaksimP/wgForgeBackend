import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private String url;
    private String user;
    private String password;

    public DB(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void executeDB() {

        try (Connection connection = DriverManager.getConnection(url, user, password)){
            calculateColor(connection);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            calculateAvg(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void calculateColor(Connection connection)  {
        String SQL_QUERY = "select cast(color as cat_color), count(cast(color as cat_color))  from cats group by color";
        //String SQL_INSERT = "insert into cat_colors_info (color, count) values (cast(? as cat_color), ?)";
        String SQL_INSERT = "insert into cat_colors_info (color, count) values ('";

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);
            while (resultSet.next()) {
                statement.addBatch(SQL_INSERT + resultSet.getString(1)+
                        "', " + resultSet.getInt(2) + ")");
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void calculateAvg(Connection connection) {

        String insertSQL = "insert into cats_stat  values (?, ?, ?, ?, ?, ?)";

        String columnTailLength = "tail_length";
        String columnWhiskersLength = "whiskers_length";

        float meanTailLength = 0;
        float meanWhiskersLength = 0;
        float medianTailLength = 0;
        float medianWhiskersLength = 0;
        Array arrayModeTailLength = null;
        Array arrayModeWhiskersLength = null;

        try (Statement statement = connection.createStatement()){
            meanTailLength = calculateMean(columnTailLength, statement);
            meanWhiskersLength = calculateMean(columnWhiskersLength, statement);

            medianTailLength = calculateMedian(columnTailLength, statement);
            medianWhiskersLength = calculateMedian(columnWhiskersLength, statement);

            arrayModeTailLength = connection.createArrayOf("INTEGER", calculateMode(columnTailLength, statement));
            arrayModeWhiskersLength = connection.createArrayOf("INTEGER", calculateMode(columnWhiskersLength, statement));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){
            preparedStatement.setFloat(1, meanTailLength);
            preparedStatement.setFloat(2, medianTailLength);
            preparedStatement.setArray(3, arrayModeTailLength);
            preparedStatement.setFloat(4, meanWhiskersLength);
            preparedStatement.setFloat(5, medianWhiskersLength);
            preparedStatement.setArray(6, arrayModeWhiskersLength);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private float calculateMean(String column, Statement statement) throws SQLException {
        String querySQL = "select avg(" + column + ") from cats ";
        ResultSet resultSet = statement.executeQuery(querySQL);
        resultSet.next();
        return resultSet.getFloat(1);
    }

    private float calculateMedian(String column, Statement statement) throws SQLException {
        String querySQL = "select percentile_cont(0.5) within group (order by " + column + ") from cats";
        ResultSet resultSet = statement.executeQuery(querySQL);
        resultSet.next();
        return resultSet.getFloat(1);
    }

    private Object[] calculateMode(String column, Statement statement) throws SQLException {
        String querySQL = "select * from (select count(*) as all_mode from cats group by " + column +
                " order by all_mode desc) as mode where 1 < all_mode";
        List<Object> resultArray = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(querySQL);
        while (resultSet.next()) {
            resultArray.add(resultSet.getInt(1));
        }
        return resultArray.toArray();
    }
}