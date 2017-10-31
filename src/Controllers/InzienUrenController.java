package Controllers;

import DAO.IngevuldeTijdDAO;
import DAO.KlantDAO;
import DAO.OnderwerpDAO;
import DAO.ProjectDAO;
import Database.DatabaseConnectie;
import Models.*;
import Views.InzienUrenView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InzienUrenController {
    private final String ERRORMESSAGENORESULTS = "De zoekopdracht heeft geen resultaten opgeleverd";

    /**
     * Maak alle hulpklassen aan.
     */
    Stage stage;
    DatabaseConnectie dbc;
    InzienUrenView view;
    IngevuldeTijdDAO ingevuldeTijdDAO;
    KlantDAO klantDAO;
    ProjectDAO projectDAO;
    OnderwerpDAO onderwerpDAO;
    ResultSet results;
    ArrayList<IngevuldeTijdModel> resultatenlijst;
    HoofdMenuController hoofdMenuController;
    GebruikerModel gebruiker;

    /**
     * Is de klasse die de InzienUrenAdminView onderhoudt.
     * Krijgt DatabaseConnectie mee, zodat deze kan worden doorgegeven aan de DAO.
     *
     * @param dbc
     */
    public InzienUrenController(Stage stage, DatabaseConnectie dbc, HoofdMenuController hoofdMenuController, GebruikerModel gebruiker) {
        this.hoofdMenuController = hoofdMenuController;
        ingevuldeTijdDAO = new IngevuldeTijdDAO(dbc);
        this.klantDAO = new KlantDAO(dbc);
        this.projectDAO = new ProjectDAO(dbc);
        this.onderwerpDAO = new OnderwerpDAO(dbc);
        view = new InzienUrenView(this);
        stage = stage;
        stage.show();
        stage.setScene(view);
        view.setPersoonLabel(hoofdMenuController.getGebruikerModel().getVolledigeNaam());
        this.gebruiker = gebruiker;
        vulKlantenEntries();
    }

    /**
     * Wordt uitgevoerd wanneer de 'Ververs' knop wordt ingedrukt.
     * Krijgt een ResultSet van de DAO, maakt IngevuldeTijdModels van de resultset en voert deze door naar de view.
     */
    public void buttonPressed() {
        System.out.println(gebruiker.getGebruikerID());
        results = ingevuldeTijdDAO.getPersoneelOverzicht(view.getBegindatum(), view.getEinddatum(), view.getKlantnaam(), view.getProjectnaam(), gebruiker.getGebruikerID(), view.getOnderwerpNaam());
        makeModelsFromResultSet(results);

        if (resultatenlijst.isEmpty()) {
            Alert legeResultatenLijstAlert = new Alert(Alert.AlertType.ERROR, ERRORMESSAGENORESULTS);
            legeResultatenLijstAlert.show();
        } else {
            makeTableViewFromArrayList();
        }
    }

    /**
     * Krijgt een ResultSet van de geregistreerde tijden, vult hier de resultatenlijst ArrayList mee.
     *
     * @param results
     */
    private void makeModelsFromResultSet(ResultSet results) {
        try {
            if (results.next()) {
                resultatenlijst = new ArrayList<>();
                do {
                    String personeelsNaam = gebruiker.getVolledigeNaam();
                    System.out.println("Adding models to arraylist");
                    resultatenlijst.add(new IngevuldeTijdModel(
                            results.getInt("uurID"),
                            results.getString("begindatum"),
                            results.getString("einddatum"),
                            results.getString("begintijd"),
                            results.getString("eindtijd"),
                            results.getString("commentaar"),
                            results.getBoolean("goedgekeurd"),
                            results.getInt("persoonID"),
                            results.getString("klant_naam"),
                            results.getString("project_naam"),
                            results.getString("onderwerp_naam"),
                            personeelsNaam
                    ));

                    System.out.println(results.getString("klant_naam"));
                } while (results.next());
            } else {
                resultatenlijst = new ArrayList<>();
            }
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Roept de view aan en vult de TableView met de data uit de resultatenlijst ArrayList.
     */
    private void makeTableViewFromArrayList() {
        ObservableList<IngevuldeTijdModel> records = FXCollections.observableArrayList(resultatenlijst);
        view.vulTabelUitLijst(records);
    }

    public HoofdMenuController getHoofdMenuController() {
        return hoofdMenuController;
    }

    /**
     * Vult de dictionary van de autocomplete in de view voor de klanteninput.
     */
    private void vulKlantenEntries() {
        ArrayList<String> klanten = new ArrayList<>();
        try {
            for (KlantModel klant : klantDAO.haalKlantenOp()) {
                klanten.add(klant.getNaam());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.setKlanten(klanten);

    }

    /**
     * Vult de dictionary van de autocomplete in de view voor de projecteninput.
     *
     * @param klantNaam
     */
    public void vulProjectenEntries(String klantNaam) {
        ArrayList<String> projecten = new ArrayList<>();

        for (ProjectModel project : projectDAO.haalProjectenOp(klantNaam)) {
            System.out.println(project.getProject_naam());
            projecten.add(project.getProject_naam());
        }
        projectDAO.haalProjectenOp(klantNaam);

        view.setProjecten(projecten);
    }

    /**
     * Vult de dictionary van de autocomplete in de view voor de onderwerpinput.
     *
     * @param projectnaam
     */
    public void vulOnderwerpenEntries(String projectnaam) {
        ArrayList<String> onderwerpen = new ArrayList<>();

        try {
            for (OnderwerpModel onderwerp : onderwerpDAO.haalOnderwerpenOp(projectnaam)) {
                onderwerpen.add(onderwerp.getOnderwerp_naam());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.setOnderwerpen(onderwerpen);
    }
}

