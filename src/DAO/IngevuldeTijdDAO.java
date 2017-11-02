package DAO;

import Database.DatabaseConnectie;
import Models.IngevuldeTijdModel;
import Models.KlantModel;
import Models.OnderwerpModel;
import Models.ProjectModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * De dao voor alle data uit de database die met de tijd te maken hebben
 *
 * @author Alex de Bruin
 * @author Ian Beemsterboer
 * @author Justin Moor
 *
 * @version 3.0
 */
public class IngevuldeTijdDAO {


    private DatabaseConnectie db;

    public IngevuldeTijdDAO (DatabaseConnectie db) {
        this.db = db;
    }

    /**
     * Voegt commentaar bij een ingevulde tijd.
     * @param getPersoonsID
     * @param klant
     * @param project
     * @param onderwerp
     * @param commentaar
     * @param begindatum
     * @param begintijd
     * @param einddatum
     * @param eindtijd
     */
    public void insertMetCommentaar(int getPersoonsID, String klant, String project, String onderwerp, String commentaar, String begindatum, String begintijd, String einddatum, String eindtijd){

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
    public ResultSet getAdminOverzicht(String begindatum, String einddatum, String klant, String project, String onderwerp) {
        ResultSet results;
        results = null;
        try {
            PreparedStatement getResults = db.getConnection().prepareStatement("SELECT geregistreerdetijd.*, personeel.voornaam, personeel.tussenvoegsel, personeel.achternaam FROM geregistreerdetijd JOIN personeel ON geregistreerdetijd.persoonID = personeel.persoonID\n WHERE begindatum >=? AND einddatum<=?"+ "AND (klant_naam = ? OR klant_naam LIKE ?)"+ "AND (project_naam = ? OR project_naam LIKE ?)"+ "AND (onderwerp_naam = ? OR onderwerp_naam LIKE ?)" );
            getResults.setString(1, begindatum);
            getResults.setString(2, einddatum);

            getResults.setString(3, klant);
            getResults.setString(4, klant+"%");
            getResults.setString(5, project);
            getResults.setString(6, project+"%");
            getResults.setString(7, onderwerp);
            getResults.setString(8, onderwerp+ "%");
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
    public ResultSet getPersoneelOverzicht(String begindatum, String einddatum, String klant, String project, int personeelsId, String onderwerp) {
        ResultSet results = null;
        try {
            PreparedStatement getResults = db.getConnection().prepareStatement("SELECT * " +
                    "FROM geregistreerdetijd " +
                    "WHERE persoonID = ? " +
                    "AND begindatum >=? " +
                    "AND einddatum<=? " +
                    "AND (klant_naam = ? " +
                    "OR klant_naam LIKE ?) " +
                    "AND (project_naam = ? " +
                    "OR project_naam LIKE ?)" +
                    "AND (onderwerp_naam = ?" +
                    "OR onderwerp_naam LIKE ?)"
            );

            getResults.setInt(1, personeelsId);
            getResults.setString(2, begindatum);
            getResults.setString(3, einddatum);
            getResults.setString(4, klant);
            getResults.setString(5, klant+"%");
            getResults.setString(6, project);
            getResults.setString(7, project+"%");
            getResults.setString(8, onderwerp);
            getResults.setString(9, onderwerp+"%");

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
