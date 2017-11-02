package Controllers;

import DAO.IngevuldeTijdDAO;
import DAO.KlantDAO;
import DAO.OnderwerpDAO;
import DAO.ProjectDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Models.KlantModel;
import Models.OnderwerpModel;
import Models.ProjectModel;
import Views.InvullenUrenView;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * De controller voor het invullen van de uren
 *
 * @author Alex de Bruin
 * @author Stan Hoenson
 * @version 3.0
 *
 */

public class InvullenUrenController {

    private InvullenUrenView invullenurenView;
    private GebruikerModel gebruikerModel;
    private DatabaseConnectie db;
    private IngevuldeTijdDAO dao;
    private KlantDAO klantDao;
    private ProjectDAO projectDao;
    private OnderwerpDAO onderwerpDao;
    private HoofdMenuController hoofdmenucontroller;


    InvullenUrenController(Stage stage, DatabaseConnectie db, GebruikerModel gebruikerModel, HoofdMenuController hoofdmenucontroller) {
        invullenurenView = new InvullenUrenView(this);
        this.gebruikerModel = gebruikerModel;
        stage.setScene(invullenurenView);
        stage.show();
        this.db = db;
        this.gebruikerModel = gebruikerModel;
        this.hoofdmenucontroller = hoofdmenucontroller;
        dao = new IngevuldeTijdDAO(db);
        klantDao = new KlantDAO(db);
        projectDao = new ProjectDAO(db);
        onderwerpDao = new OnderwerpDAO(db);
        invullenurenView.setGebruiker();
    }
    
    public GebruikerModel getGebruikerModel() {
        return gebruikerModel;
    }

    public ArrayList<KlantModel> getKlanten() throws SQLException {

        ArrayList<KlantModel> klanten = klantDao.haalKlantenOp();
        return klanten;

    }

    public ArrayList<ProjectModel> getProjecten(String klant_naam) throws SQLException {

        ArrayList<ProjectModel> projecten = projectDao.haalProjectenOp(klant_naam);
        return projecten;
    }

    public ArrayList<OnderwerpModel> getOnderwerpen(String project_naam) throws SQLException {

        ArrayList<OnderwerpModel> onderwerpen = onderwerpDao.haalOnderwerpenOp(project_naam);

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
        klantDao.voegNieuweKlantToe(klant, project,onderwerp);
    }

    public void voegProjectToe(String klant, String project, String onderwerp) {
        projectDao.voegNieuwProjectToe(klant,project,onderwerp);
    }

    public void voegOnderwerpToe(String project, String onderwerp) {
        onderwerpDao.voegNiewOnderwerpToe(project, onderwerp);
    }

    public DatabaseConnectie getDb() {
        return db;
    }
}
