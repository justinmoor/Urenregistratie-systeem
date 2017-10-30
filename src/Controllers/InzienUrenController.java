package Controllers;

import DAO.IngevuldeTijdDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Models.IngevuldeTijdModel;
import Views.InzienUrenAdminView;
import Views.InzienUrenView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InzienUrenController {
    private final String ERRORMESSAGENORESULTS = "De zoekopdracht heeft geen resultaten opgeleverd";

    /**
     * Maak alle hulpklassen aan.
     */
    Stage stage;
    DatabaseConnectie dbc;
    InzienUrenView view;
    IngevuldeTijdDAO dao;
    ResultSet results;
    ArrayList<IngevuldeTijdModel> resultatenlijst;
    HoofdMenuController hoofdMenuController;
    GebruikerModel gebruiker;

    /**
     * Is de klasse die de InzienUrenAdminView onderhoudt.
     * Krijgt DatabaseConnectie mee, zodat deze kan worden doorgegeven aan de DAO.
     * @param dbc
     */
    public  InzienUrenController(Stage stage, DatabaseConnectie dbc, HoofdMenuController hoofdMenuController, GebruikerModel gebruiker){
        this.hoofdMenuController = hoofdMenuController;
        dao = new IngevuldeTijdDAO(dbc);
        view = new InzienUrenView(this);
        stage = stage;
        stage.show();
        stage.setScene(view);
        view.setPersoonLabel(hoofdMenuController.getGebruikerModel().getVolledigeNaam());
        this.gebruiker = gebruiker;
    }

    /**
     * Wordt uitgevoerd wanneer de 'Ververs' knop wordt ingedrukt.
     * Krijgt een ResultSet van de DAO, maakt IngevuldeTijdModels van de resultset en voert deze door naar de view.
     */
    public void buttonPressed(){
        System.out.println(gebruiker.getGebruikerID());
        results = dao.getPersoneelOverzicht(view.getBegindatum(), view.getEinddatum(), view.getKlantnaam(), view.getProjectnaam(), gebruiker.getGebruikerID(), view.getOnderwerpNaam());
        makeModelsFromResultSet(results);

        if(resultatenlijst.isEmpty()){
            Alert legeResultatenLijstAlert = new Alert(Alert.AlertType.ERROR, ERRORMESSAGENORESULTS);
            legeResultatenLijstAlert.show();
        } else {
            makeTableViewFromArrayList();
        }
    }

    /**
     * Krijgt een ResultSet van de geregistreerde tijden, vult hier de resultatenlijst ArrayList mee.
     * @param results
     */
    private void makeModelsFromResultSet(ResultSet results) {
        try {
            if(results.next()){
                resultatenlijst = new ArrayList<>();
                do{
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
                            results.getString( "onderwerp_naam"),
                            personeelsNaam
                    ));

                    System.out.println(results.getString("klant_naam"));
                } while(results.next());
            } else{
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
    private void makeTableViewFromArrayList(){
        ObservableList<IngevuldeTijdModel> records = FXCollections.observableArrayList(resultatenlijst);
        view.vulTabelUitLijst(records);
    }

    public HoofdMenuController getHoofdMenuController() {
        return hoofdMenuController;
    }

}

