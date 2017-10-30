package DAO;

import Database.DatabaseConnectie;
import Models.KlantModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KlantDAO {
    DatabaseConnectie db;

    public KlantDAO(DatabaseConnectie db){
        this.db = db;
        System.out.println(db);
    }
    public ArrayList<KlantModel> haalKlantenOp() throws SQLException {

        ArrayList<KlantModel> klant_namen = null;
        try {
            ResultSet results;
            PreparedStatement haalKlantenOp = db.getConnection().prepareStatement("SELECT klant_naam FROM klant;");
            results = haalKlantenOp.executeQuery();
            klant_namen = new ArrayList<KlantModel>();
            while (results.next()) {
                klant_namen.add(new KlantModel(results.getString("klant_naam")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return klant_namen;
    }
}
