package DAO;

import Database.DatabaseConnectie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GebruikerDAO {


    private DatabaseConnectie db;

    public GebruikerDAO(DatabaseConnectie db) {
        this.db = db;
    }

    public int query(String email, String wachtwoord) {

        int foutcode = 1; // 0 = succes, 1 = geen connectie, 2 = verkeerde gegevens

        PreparedStatement statement = null;
        ResultSet result;
        String loginSQL = "SELECT wachtwoord FROM personeel WHERE email =?;";

        try {
            if (db.getConnection() == null) {
                foutcode = 1;
                System.out.println("Connectie is NULL");
            } else {
                statement = db.getConnection().prepareStatement(loginSQL);
                statement.setString(1, email);

                result = statement.executeQuery();
                System.out.println("Fout1");

                if (result.next()) {
                    String ww = result.getString("wachtwoord");
                    System.out.println("Fout2");
                    if (ww.equals(wachtwoord)) {
                        foutcode = 0;
                        System.out.println("Gelukt");
                    } else {
                        System.out.println("Fout wachtwoord");
                        foutcode = 2;
                    }
                }


            }
        } catch (SQLException e) {
            foutcode = 1;
            e.printStackTrace();
        }
        return foutcode;
    }
}

