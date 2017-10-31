package Controllers;

import DAO.IngevuldeTijdDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Models.KlantModel;
import Models.OnderwerpModel;
import Models.ProjectModel;
import Views.InvullenUrenView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvullenUrenController {

    private InvullenUrenView invullenurenView;
    private GebruikerModel gebruikerModel;
    private DatabaseConnectie db;
    private IngevuldeTijdDAO dao;
    private HoofdMenuController hoofdmenucontroller;

    private ArrayList<String> klanten;
    private ArrayList<String> producten;
    private ArrayList<String> onderwerpen;


    InvullenUrenController(Stage stage, DatabaseConnectie db, GebruikerModel gebruikerModel, HoofdMenuController hoofdmenucontroller) {
        invullenurenView = new InvullenUrenView(this);
        this.gebruikerModel = gebruikerModel;
        stage.setScene(invullenurenView);
        stage.show();
        this.db = db;
        this.gebruikerModel = gebruikerModel;
        this.hoofdmenucontroller = hoofdmenucontroller;
        dao = new IngevuldeTijdDAO(db);
        invullenurenView.setGebruiker();
    }
    
    public GebruikerModel getGebruikerModel() {
        return gebruikerModel;
    }

    public ArrayList<KlantModel> getKlanten() throws SQLException {

        ArrayList<KlantModel> klanten = dao.haalKlantenOp();
        return klanten;

    }

    public ArrayList<ProjectModel> getProjecten(String klant_naam) throws SQLException {

        ArrayList<ProjectModel> projecten = dao.haalProjectenOp(klant_naam);
        return projecten;
    }

    public ArrayList<OnderwerpModel> getOnderwerpen(String project_naam) throws SQLException {

        ArrayList<OnderwerpModel> onderwerpen = dao.haalOnderwerpenOp(project_naam);

        return onderwerpen;
    }




    public void insert(String klant, String project, String onderwerp, String commentaar, String begindatum, String begintijd, String einddatum, String eindtijd) throws SQLException {
        int  PersoonsID = hoofdmenucontroller.getGebruikerModel().getGebruikerID();


        dao.insertMetCommentaar(PersoonsID, klant, project, onderwerp, commentaar, begindatum, begintijd, einddatum, eindtijd);
    }

    public HoofdMenuController getHoofdMenuController() {
        return hoofdmenucontroller;
    }

    public void voegKlantToe(String klant, String project, String onderwerp) {
        dao.voegNieuweKlantToe(klant, project,onderwerp);
    }

    public void voegProjectToe(String klant, String project, String onderwerp) {
        dao.voegNieuwProjectToe(klant,project,onderwerp);
    }

    public void voegOnderwerpToe(String project, String onderwerp) {
        dao.voegNiewOnderwerpToe(project, onderwerp);
    }
}
