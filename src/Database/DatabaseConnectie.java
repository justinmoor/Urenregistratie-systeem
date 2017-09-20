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
    }

    public boolean wachtwoordJuist(String wachtwoord, String email){
        ResultSet results;
        try {
            this.statement = connection.createStatement();
            results = this.statement.executeQuery("SELECT Wachtwoord FROM Gebruiker WHERE Mail='"+email+"'");
            if(results.next()) {
                if (results.getNString(1).equals(wachtwoord)) {
                    System.out.println("String uit database voor kolom Wachtwoord is: " + wachtwoord + ".");
                    System.out.println("Wachtwoord voor user " + email + " goed");
                    return true;
                }
                else{
                    System.out.println("Voor het emailadres bestond een record, maar het wachtwoord was verkeerd.");
                    return false;
                }
            }

            else {
                System.out.println("Verkeerd e-mailadres...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException");
            return false;
        }
        System.out.println("Wachtwoord niet juist of connectie sucked.");
        return false;

    }
}