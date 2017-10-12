package Controllers;

import DAO.IngevuldeTijdDAO;
import Database.DatabaseConnectie;
import Models.IngevuldeTijdModel;
import Views.InzienUrenAdminView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InzienUrenAdminController {

    private final String ERRORMESSAGENORESULTS = "De zoekopdracht heeft geen resultaten opgeleverd";
    /**
     * Maak alle hulpklassen aan.
     */
    Stage stage;
    DatabaseConnectie dbc;
    InzienUrenAdminView view;
    IngevuldeTijdDAO dao;
    ResultSet results;
    ArrayList<IngevuldeTijdModel> resultatenlijst;

    /**
     * Krijgt DatabaseConnectie mee, zodat deze kan worden doorgegeven aan de DAO.
     * @param stage
     * @param dbc
     */
    public  InzienUrenAdminController(Stage stage, DatabaseConnectie dbc){
        dao = new IngevuldeTijdDAO(dbc);
        view = new InzienUrenAdminView(this);
        stage.setScene(view);
    }

    /**
     * Wordt uitgevoerd wanneer de 'Ververs' knop wordt ingedrukt.
     * Krijgt een ResultSet van de DAO, maakt IngevuldeTijdModels van de resultset en voert deze door naar de view.
     */
    public void buttonPressed(){
        results = dao.getAdminOverzicht(view.getBegindatum(), view.getEinddatum());
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
                do{
                    resultatenlijst = new ArrayList<>();
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
                            results.getString( "onderwerp_naam")));

                    System.out.println(results.getString("begindatum"));
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
}
