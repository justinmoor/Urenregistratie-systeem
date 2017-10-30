package DAO;

import Database.DatabaseConnectie;
import Models.OnderwerpModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Is verantwoordelijk voor het halen van onderwerpen uit de database.
 */
public class OnderwerpDAO {
    DatabaseConnectie db;

    public OnderwerpDAO(DatabaseConnectie db){
        this.db = db;
    }

    /**
     * Haalt projecten uit de dataabse.
     * @param project_naam
     * @return
     * @throws SQLException
     */
    public ArrayList<OnderwerpModel> haalOnderwerpenOp(String project_naam) throws SQLException {

        ArrayList<OnderwerpModel> onderwerpen = null;
        try {
            ResultSet results;
            PreparedStatement haalOnderwerpenOp = db.getConnection().prepareStatement("SELECT onderwerp_naam FROM onderwerp WHERE project_naam =?;");
            haalOnderwerpenOp.setString(1, project_naam);
            results = haalOnderwerpenOp.executeQuery();
            onderwerpen = new ArrayList<OnderwerpModel>();
            while (results.next()) {
                onderwerpen.add(new OnderwerpModel(results.getString("onderwerp_naam")));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        return onderwerpen;
    }
}
