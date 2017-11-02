package Database;

import java.sql.*;

/**
 * De klasse die de connectie met de database tot stand brengt
 *
 * @author Ian Beemsterboer
 *
 * @version 3.0
 */
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
        return this.connection; //Geeft de connectie terug
    }
}
