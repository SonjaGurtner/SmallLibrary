package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String url = "jdbc:derby:dbdir/db;create=true";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
