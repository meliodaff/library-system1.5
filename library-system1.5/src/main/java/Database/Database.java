package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private final String URL = "jdbc:mysql://localhost:3306/library_db";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    public Connection getConnection() throws Exception{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
