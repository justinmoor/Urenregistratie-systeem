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
import java.util.Date;

public class InzienUrenAdminController {
    Stage stage;
    DatabaseConnectie dbc;
    InzienUrenAdminView view;
    IngevuldeTijdDAO dao;
    ResultSet results;
    ArrayList<IngevuldeTijdModel> resultatenlijst;

    public  InzienUrenAdminController(Stage stage, DatabaseConnectie dbc){
        dao = new IngevuldeTijdDAO(dbc);
        view = new InzienUrenAdminView(this);
        stage.setScene(view);

        resultatenlijst = new ArrayList<>();
    }

    public void buttonPressed(){
        results = dao.getAdminOverzicht(view.getBegindatum(), view.getEinddatum());
        makeModelsFromResultSet(results);
        makeTableViewFromArrayList();
    }

    public void makeModelsFromResultSet(ResultSet results) {
        try {
            while(results.next()){
                System.out.println("hey hoi");
                resultatenlijst.add(new IngevuldeTijdModel(
                        results.getInt("uurID"),
                        results.getInt("persoonID"),
                        results.getString("begindatum"),
                        results.getString("einddatum"),
                        results.getString("begintijd"),
                        results.getString("eindtijd"),
                        results.getString("klantID"),
                        results.getString("projectID"),
                        results.getString("onderwerpID"),
                        results.getString("commentaar"),
                        results.getBoolean("goedgekeurd")));
                System.out.println(results.getString("commentaar"));
            }
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void makeTableViewFromArrayList(){
        ObservableList<IngevuldeTijdModel> records = FXCollections.observableArrayList(resultatenlijst);
        view.setData(records);
    }
    public void updateView(){

    }
}
