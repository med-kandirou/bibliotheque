package helper;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    // Private constructor to prevent direct instantiation
    private DatabaseConnection() {
    }

    // Public method to get the single instance of DatabaseConnection
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    public Connection connect() {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String Url = prop.getProperty("DatabaseConnection.url");
        String Username = prop.getProperty("DatabaseConnection.username");
        String Password = prop.getProperty("DatabaseConnection.password");
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(Url, Username, Password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void disconnect() {
        if (this.connect() != null) {
            try {
                this.connect().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



