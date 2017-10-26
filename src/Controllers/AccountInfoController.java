package Controllers;

import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import Views.AccountInfoView;
import javafx.stage.Stage;


public class AccountInfoController {


    private DatabaseConnectie db;
    private HoofdMenuController hoofdMenuController;
    private GebruikerDAO dao;
    private AccountInfoView accountInfoView;

    private String voornaam;
    private String tussenVoegsel;
    private String achternaam;
    private String email;
    private String wachtwoord;


    AccountInfoController(Stage stage, DatabaseConnectie db, HoofdMenuController controller) {
        accountInfoView = new AccountInfoView(this);
        stage.setScene(accountInfoView);
        stage.show();
        this.db = db;
        this.hoofdMenuController = controller;
        dao = new GebruikerDAO(db);
        initPersoon();
        accountInfoView.initGui();
        accountInfoView.initAction();
    }

    public HoofdMenuController getHoofdMenuController() {
        System.out.println(hoofdMenuController);
        return hoofdMenuController;
    }

    public void initPersoon() {
        voornaam = getHoofdMenuController().getGebruikerModel().getVoornaam();
        if (getHoofdMenuController().getGebruikerModel().getTussenvoegsel() != null) {
            tussenVoegsel = getHoofdMenuController().getGebruikerModel().getTussenvoegsel();
        } else {
            tussenVoegsel = " ";
        }
        achternaam = getHoofdMenuController().getGebruikerModel().getAchternaam();
        email = getHoofdMenuController().getGebruikerModel().getEmail();
        wachtwoord = getHoofdMenuController().getGebruikerModel().getWachtwoord();
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getTussenVoegsel() {
        return tussenVoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getEmail() {
        return email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }
}
