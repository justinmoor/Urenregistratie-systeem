package DAO;

import Database.DatabaseConnectie;
import Models.IngevuldeTijdModel;
import Models.KlantModel;
import Models.OnderwerpModel;
import Models.ProjectModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngevuldeTijdDAO {


    private DatabaseConnectie db;

    public IngevuldeTijdDAO (DatabaseConnectie db) {
        this.db = db;
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

    public ArrayList<ProjectModel> haalProjectenOp(String klant_naam) throws SQLException{

        ArrayList<ProjectModel> projecten = null;
        try {
            ResultSet results;
            PreparedStatement haalProjectenOp = db.getConnection().prepareStatement("SELECT project_naam FROM project WHERE klant_naam =?;");
            haalProjectenOp.setString(1, klant_naam);
            results = haalProjectenOp.executeQuery();
            projecten = new ArrayList<ProjectModel>();
            while(results.next()) {
                projecten.add(new ProjectModel(results.getString("project_naam")));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        return projecten;
    }

    public ArrayList<OnderwerpModel> haalOnderwerpenOp(String project_naam) throws SQLException{

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


    public void insertMetCommentaar(int getPersoonsID, String klant, String project, String onderwerp, String commentaar, String begindatum, String begintijd, String einddatum, String eindtijd) throws SQLException{

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
    public ResultSet getAdminOverzicht(String begindatum, String einddatum, String klant, String project) {
        ResultSet results;
        results = null;
        try {
            PreparedStatement getResults = db.getConnection().prepareStatement("SELECT geregistreerdetijd.*, personeel.voornaam, personeel.tussenvoegsel, personeel.achternaam FROM geregistreerdetijd JOIN personeel ON geregistreerdetijd.persoonID = personeel.persoonID\n WHERE begindatum >=? AND einddatum<=?"+ "AND (klant_naam = ? OR klant_naam LIKE ?)"+ "AND (project_naam = ? OR project_naam LIKE ?)");
            getResults.setString(1, begindatum);
            getResults.setString(2, einddatum);

            getResults.setString(3, klant);
            getResults.setString(4, klant+"%");
            getResults.setString(5, project);
            getResults.setString(6, project+"%");

            System.out.println(begindatum);
            System.out.println(einddatum);

            results = getResults.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    /**
     * Vraagt database om de gewerkte uren van een specifiek persoon. Geeft een resultset terug.
     * @param begindatum
     * @param einddatum
     * @param klant
     * @param project
     * @param personeelsId
     * @return
     */
    public ResultSet getPersoneelOverzicht(String begindatum, String einddatum, String klant, String project, int personeelsId) {
        ResultSet results;
        results = null;
        try {
            PreparedStatement getResults = db.getConnection().prepareStatement("SELECT *" +
                    "FROM geregistreerdetijd " +
                    //"JOIN personeel " +
                    //"ON geregistreerdetijd.persoonID = personeel.persoonID " +
                    "WHERE persoonID = ? " +
                    "AND begindatum >=? " +
                    "AND einddatum<=? " +
                    "AND (klant_naam = ? " +
                    "OR klant_naam LIKE ?) " +
                    "AND (project_naam = ? " +
                    "OR project_naam LIKE ?)");

            getResults.setString(2, begindatum);
            getResults.setString(3, einddatum);
            getResults.setInt(1, personeelsId);
            getResults.setString(4, klant);
            getResults.setString(5, klant+"%");
            getResults.setString(6, project);
            getResults.setString(7, project+"%");

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

    public void voegNieuweKlantToe(String klant, String project, String onderwerp) {

        try {
            PreparedStatement voegKlantToe = db.getConnection().prepareStatement("INSERT INTO klant (klant_naam) VALUES(?)");

            voegKlantToe.setString(1, klant);
            voegKlantToe.executeQuery();

            voegNieuwProjectToe(klant, project, onderwerp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void voegNieuwProjectToe(String klant, String project, String onderwerp) {

        try {
            PreparedStatement voegProjectToe = db.getConnection().prepareStatement("INSERT INTO project (klant_naam, project_naam) VALUES(?,?)");

            voegProjectToe.setString(1,klant);
            voegProjectToe.setString(2, project);
            voegProjectToe.executeQuery();

            voegNiewOnderwerpToe(project, onderwerp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void voegNiewOnderwerpToe(String project, String onderwerp) {
        try {
            PreparedStatement voegOnderwerpToe = db.getConnection().prepareStatement("INSERT INTO onderwerp (project_naam, onderwerp_naam) VALUES(?,?)");

            voegOnderwerpToe.setString(1, project);
            voegOnderwerpToe.setString(2, onderwerp);
            voegOnderwerpToe.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
