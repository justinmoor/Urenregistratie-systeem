package Database;

import java.sql.*;

public class DatabaseConnectie {
    private Connection connection;

    public DatabaseConnectie() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/UrenregistratieDatabase?user=root&password=ipsen123");
            System.out.println("Database connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        System.out.println("connected");
        return this.connection; //Geeft de connectie terug
    }
}
