package DAO;

import Database.DatabaseConnectie;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngevuldeTijdDAO {


    private DatabaseConnectie db;

    public IngevuldeTijdDAO (DatabaseConnectie db) {
        this.db = db;
    }

    public ResultSet getAdminOverzicht(String begindatum, String einddatum){
        ResultSet results;
        results = null;

        try {
            PreparedStatement getResults = db.getConnection().prepareStatement("SELECT * FROM geregistreerdetijd WHERE begindatum >=? AND einddatum<=?");
            getResults.setString(1, begindatum);
            getResults.setString(2, einddatum);

            System.out.println(begindatum);
            System.out.println(einddatum);

            results = getResults.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return results;
    }
}
