import java.io.IOException;

public class General {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    //private static final String URL = "jdbc:postgresql://192.168.99.100:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        DB db = new DB(URL, USER, PASSWORD);
        db.executeDB();
    }
}
