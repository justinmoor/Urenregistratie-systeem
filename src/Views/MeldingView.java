package Views;

import DAO.KlantDAO;
import DAO.OnderwerpDAO;
import DAO.ProjectDAO;
import Database.DatabaseConnectie;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MeldingView extends Stage{

    private DatabaseConnectie db;
    private InvullenUrenView view;
    private AccountInfoView accountView;
    private KlantDAO klantenDao;
    private ProjectDAO projectDao;
    private OnderwerpDAO onderwerpDao;

    private String categorie;
    private String klant;
    private String project;
    private String onderwerp;

    private Stage newStage;
    private GridPane pane;
    private Scene scene;

    private Label tekst;
    private Label subTekst;

    private Button btOK;
    private Button btCancel;


    //alert als uren een nieuwe klant project of onderwerp heeft
    public MeldingView(String categorie, DatabaseConnectie db, String klant, String project, String onderwerp, InvullenUrenView view) {

        this.view = view;
        this.klant = klant;
        this.project = project;
        this.onderwerp = onderwerp;
        this.categorie = categorie;
        newStage = new Stage();
        this.db = db;
        klantenDao = new KlantDAO(db);
        projectDao = new ProjectDAO(db);
        onderwerpDao = new OnderwerpDAO(db);
        initUrenGui();
        initUrenAction();
    }

    //alert als een wachtwoord te kort is

    public MeldingView(String categorie, AccountInfoView accountView) {

        setStage();
        this.accountView = accountView;
        this.categorie = categorie;
        initWachtwoordGui();
        initWachtwoordAction();
    }

    private void initUrenGui() {

        setStage();

        tekst = new Label("kiesTekst");
        subTekst = new Label("Klik Oke om bovenstaande toetevoegen aan de database. Klik Cancel als u bovenstaande niet wil toevoegen");
        btOK = new Button("Oke");
        btCancel = new Button("Cancel");

        pane.add(tekst, 1,1,2,1);
        pane.add(btCancel, 1, 2, 1,1);
        pane.add(btOK, 2, 2, 1, 1);
    }

    private void initWachtwoordGui() {

        tekst = new Label();
        btOK = new Button("Oke");

        if (categorie.equals("teKort")) {
            tekst.setText("Het wachtwoord moet minimaal 8 tekens lang zijn.");
        } else if (categorie.equals("nietZelfde")) {
            tekst.setText("De opgegeven wachtwoorden zijn niet het zelfde");
        }

        pane.add(tekst,1, 1);
        pane.add(btOK, 1, 2);

    }




    public void setString(String tekstVoorView) {
        tekst.setText(tekstVoorView);
    }

    private Stage setStage() {
        pane = new GridPane();
        scene = new Scene(pane, 260, 300);
        newStage = new Stage();
        newStage.setScene(scene);
        newStage.setAlwaysOnTop(true);
        newStage.show();


        return newStage;
    }

    public void initUrenAction() {

        btOK.setOnAction(e -> {
                    if (categorie.equals("Klanten")) {

                        klantenDao.voegNieuweKlantToe(klant, project, onderwerp);
                        view.InvullenUren();
                        newStage.close();

                    } else if (categorie.equals("projecten")) {

                        projectDao.voegNieuwProjectToe(klant, project, onderwerp);
                        view.InvullenUren();
                        newStage.close();

                    } else if (categorie.equals("Onderwerpen")) {

                        onderwerpDao.voegNiewOnderwerpToe(project, onderwerp);
                        view.InvullenUren();
                        newStage.close();
                    }
        });

        btCancel.setOnAction(e -> {
            view.setTekstFields();
            newStage.close();
        });

    }

    private void initWachtwoordAction() {

        btOK.setOnAction(e -> {
            accountView.clearNewWachtwoord();
            newStage.close();
        });
    }
}
