package Database;

import java.sql.*;

public class DatabaseConnectie {
    Connection connection;
    Statement statement;
    ResultSet results;

    public DatabaseConnectie() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/UrenregistratieDatabase?user=root&password=ipsen123");
            System.out.println("Database connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement = connection.createStatement();
            results = statement.executeQuery("INSERT INTO personeel VALUES ('lololol', 'loloooool', 'lmaoooo')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
