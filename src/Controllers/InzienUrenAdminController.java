package Controllers;

import DAO.IngevuldeTijdDAO;
import Database.DatabaseConnectie;
import Models.IngevuldeTijdModel;
import Views.InzienUrenAdminView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InzienUrenAdminController {

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
        makeTableViewFromArrayList();
    }

    /**
     * Krijgt een ResultSet van de geregistreerde tijden, vult hier de resultatenlijst ArrayList mee.
     * @param results
     */
    private void makeModelsFromResultSet(ResultSet results) {
        try {
            while(results.next()){
                resultatenlijst = new ArrayList<>();
                resultatenlijst.add(new IngevuldeTijdModel(
                        results.getInt("uurID"),
                        results.getInt("persoonID"),
                        results.getString("begindatum"),
                        results.getString("begintijd"),
                        results.getString("einddatum"),
                        results.getString("eindtijd"),
                        results.getString("klantID"),
                        results.getString("projectID"),
                        results.getString("onderwerpID"),
                        results.getString("commentaar"),
                        results.getBoolean("goedgekeurd")));

                System.out.println(results.getString("begindatum"));
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
