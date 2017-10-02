package DAO;

import Database.DatabaseConnectie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GebruikerDAO {



    Statement statement;
    ResultSet results;

//    public GebruikerDAO() {
//        this.db = db;
//    }

    public boolean query(String email, String wachtwoord) {

        try {
            this.statement = DatabaseConnectie.connection.createStatement();
            results = this.statement.executeQuery("SELECT Wachtwoord FROM Gebruiker WHERE Mail =" + "'" + email + "'");

            if (results.next()) {
                if (results.getNString(1).equals(wachtwoord)) {
                    System.out.println("Ingelogd");
                    return true;
                } else {
                    System.out.println("Fout");
                    return false;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
