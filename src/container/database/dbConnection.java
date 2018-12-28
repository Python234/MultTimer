package container.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static final String CONN = "jdbc:sqlite::resource:multiTimerDB.db3";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(CONN);
        } catch (ClassNotFoundException ignored) {
            return null;
        }
    }
}
