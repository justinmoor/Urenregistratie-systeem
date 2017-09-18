package Database;
import java.sql.*;

public class DatabaseConnectie {

    public DatabaseConnectie() {

        try{
            Connection  connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB?user=root&password=ipsen123");
        } catch (Exception ex){
            System.out.println("Niet gevonden");
        }

    }
}
