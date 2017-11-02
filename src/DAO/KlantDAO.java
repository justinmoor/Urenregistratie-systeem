package DAO;

import Database.DatabaseConnectie;
import Models.KlantModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Haalt alle data uit de database uit de "klant" tabel.
 *
 * @author Ian beemsterboer
 * @author Alex de Bruin
 *
 * @version 3.0
 */
public class KlantDAO {
    private DatabaseConnectie db;
    private ProjectDAO projectDao;

    public KlantDAO(DatabaseConnectie db){
        this.db = db;
        projectDao = new ProjectDAO(db);
        System.out.println(db);
    }

    /**
     * Haalt alle klanten uit de database.
     * @return
     * @throws SQLException
     */
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

    /**
     * Voegt nieuwe klant toe
     * @param klant
     * @param project
     * @param onderwerp
     */
    public void voegNieuweKlantToe(String klant, String project, String onderwerp) {

        try {
            PreparedStatement voegKlantToe = db.getConnection().prepareStatement("INSERT INTO klant (klant_naam) VALUES(?)");

            voegKlantToe.setString(1, klant);
            voegKlantToe.executeQuery();

            projectDao.voegNieuwProjectToe(klant, project, onderwerp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
