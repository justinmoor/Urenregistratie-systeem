package Controllers;

import DAO.IngevuldeTijdDAO;
import Database.DatabaseConnectie;
import Views.InvullenUrenView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvullenUrenController {

    private InvullenUrenView invullenurenView;
    private DatabaseConnectie db;
    private IngevuldeTijdDAO dao;
    private HoofdMenuController hoofdMenuController;

    private ArrayList<String> klanten;
    private ArrayList<String> producten;
    private ArrayList<String> onderwerpen;

    InvullenUrenController(Stage stage, DatabaseConnectie db, HoofdMenuController controller) {
        invullenurenView = new InvullenUrenView(this);
        stage.setScene(invullenurenView);
        stage.show();
        this.db = db;
        this.hoofdMenuController = controller;
    }

    public ArrayList getKlanten() throws SQLException {
        dao = new IngevuldeTijdDAO(db);
        ArrayList<String> klanten = dao.haalKlantenOp();
        return klanten;

    }

    public ArrayList getProjecten(String klant_naam) throws SQLException {
        dao = new IngevuldeTijdDAO(db);
        ArrayList<String> projecten = dao.haalProjectenOp(klant_naam);
        return projecten;
    }

    public ArrayList<String> getOnderwerpen(String project_naam) throws SQLException {
        dao = new IngevuldeTijdDAO(db);
        ArrayList<String> onderwerpen = dao.haalOnderwerpenOp(project_naam);

        return onderwerpen;
    }




    public void insert(String klant, String project, String onderwerp, String commentaar, String begindatum, String begintijd, String einddatum, String eindtijd) throws SQLException {
        int  PersoonsID = hoofdMenuController.getGebruikerModel().getGebruikerID();

        dao = new IngevuldeTijdDAO(db);
        dao.insertMetCommentaar(PersoonsID, klant, project, onderwerp, commentaar, begindatum, begintijd, einddatum, eindtijd);
    }

    public HoofdMenuController getHoofdMenuController() {
        return hoofdMenuController;
    }

}
