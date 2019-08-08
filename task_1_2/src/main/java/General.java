public class General {

    //private static final String URL = "jdbc:postgresql://localhost:5432/wg_forge_db";
    private static final String URL = "jdbc:postgresql://192.168.99.100:5432/wg_forge_db";
    private static final String USER = "wg_forge";
    private static final String PASSWORD = "42a";

    public static void main(String[] args) {
        DB db = new DB(URL, USER, PASSWORD);
        db.executeDB();
    }
}


