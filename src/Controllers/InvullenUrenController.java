package Controllers;

import DAO.IngevuldeTijdDAO;
import Database.DatabaseConnectie;
import Views.InvullenUrenView;
import javafx.stage.Stage;

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

    public ArrayList getProjecten() throws SQLException {
        dao = new IngevuldeTijdDAO(db);
        ArrayList<String> projecten = dao.haalProjectenOp();
        return projecten;
    }

    public ArrayList<String> getOnderwerpen() throws SQLException {
        dao = new IngevuldeTijdDAO(db);
        ArrayList<String> onderwerpen = dao.haalOnderwerpenOp();

        return onderwerpen;
    }


    public void insert(String Klant, String Project, String onderwerp, String commentaar, String begindatum, String begintijd, String einddatum, String eindtijd) {

    }

    public void insert(String Klant, String Project, String onderwerp, String begindatum, String begintijd, String einddatum, String eindtijd) {

    }

    public HoofdMenuController getHoofdMenuController() {
        return hoofdMenuController;
    }

}
