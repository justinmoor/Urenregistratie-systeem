package DAO;

import Database.DatabaseConnectie;
import Models.IngevuldeTijdModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngevuldeTijdDAO {


    private DatabaseConnectie db;

    public IngevuldeTijdDAO (DatabaseConnectie db) {
        this.db = db;
    }

    public ArrayList haalKlantenOp() throws SQLException {
        //Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/UrenregistratieDatabase?user=root&password=ipsen123");
        ArrayList<String> klant_namen = null;
        try {
            ResultSet results;
            PreparedStatement haalKlantenOp = db.getConnection().prepareStatement("SELECT klant_naam FROM klant;");
            results = haalKlantenOp.executeQuery();
            klant_namen = new ArrayList<String>();
            while (results.next()) {
                klant_namen.add(results.getString("klant_naam"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      //  conn.close();
        return klant_namen;
    }

    public ArrayList haalProjectenOp(String klant_naam) throws SQLException{
       // Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/UrenregistratieDatabase?user=root&password=ipsen123");
        ArrayList<String> projecten = null;
        try {
            ResultSet results;
            PreparedStatement haalProjectenOp = db.getConnection().prepareStatement("SELECT project_naam FROM project WHERE klant_naam =?;");
            haalProjectenOp.setString(1, klant_naam);
            results = haalProjectenOp.executeQuery();
            projecten = new ArrayList<String>();
            while(results.next()) {
                projecten.add(results.getString("project_naam"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
      //  conn.close();
        return projecten;
    }

    public ArrayList haalOnderwerpenOp(String project_naam) throws SQLException{
      //  Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/UrenregistratieDatabase?user=root&password=ipsen123");
        ArrayList<String> onderwerpen = null;
        try {
            ResultSet results;
            PreparedStatement haalOnderwerpenOp = db.getConnection().prepareStatement("SELECT onderwerp_naam FROM onderwerp WHERE project_naam =?;");
            haalOnderwerpenOp.setString(1, project_naam);
            results = haalOnderwerpenOp.executeQuery();
            onderwerpen = new ArrayList<String>();
            while (results.next()) {
                onderwerpen.add(results.getString("onderwerp_naam"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
      //  conn.close();
        return onderwerpen;
    }


    public void insertMetCommentaar(int getPersoonsID, String klant, String project, String onderwerp, String commentaar, String begindatum, String begintijd, String einddatum, String eindtijd) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/UrenregistratieDatabase?user=root&password=ipsen123");

        try {
            PreparedStatement insertMetCommentaar = db.getConnection().prepareStatement("INSERT INTO geregistreerdetijd (persoonID, klant_naam, project_naam, onderwerp_naam, commentaar, begindatum, begintijd, einddatum, eindtijd) VALUES(?,?,?,?,?,?,?,?,?)");

            insertMetCommentaar.setInt(1, getPersoonsID);
            insertMetCommentaar.setString(2, klant);
            insertMetCommentaar.setString(3, project);
            insertMetCommentaar.setString(4, onderwerp);
            insertMetCommentaar.setString(5, commentaar);
            insertMetCommentaar.setString(6, begindatum);
            insertMetCommentaar.setString(7, begintijd);
            insertMetCommentaar.setString(8, einddatum);
            insertMetCommentaar.setString(9, eindtijd);

            insertMetCommentaar.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }


    /**
     * Produceert een ResultSet wanneer wordt aangeroepen. Vereist de begin en einddatum als argument.
     * @param begindatum
     * @param einddatum
     * @return
     */
    public ResultSet getAdminOverzicht(String begindatum, String einddatum) {
        ResultSet results;
        results = null;
        try {
            PreparedStatement getResults = db.getConnection().prepareStatement("SELECT geregistreerdetijd.*, personeel.voornaam, personeel.tussenvoegsel, personeel.achternaam FROM geregistreerdetijd JOIN personeel ON geregistreerdetijd.persoonID = personeel.persoonID\n WHERE begindatum >=? AND einddatum<=?");
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

    /**
     * Ontvangt het model waarvan de goeddgekeurd boolean is veranderd en stuurt deze om weg te schrijven naar de database.
     * @param model
     */
    public void veranderGoedgekeurdKolom(IngevuldeTijdModel model){
        try {
            PreparedStatement goedkeurenQuery = db.getConnection().prepareStatement("UPDATE geregistreerdetijd SET goedgekeurd = TRUE WHERE uurid = ?");
            goedkeurenQuery.setInt(1, model.getUurId());
            goedkeurenQuery.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
