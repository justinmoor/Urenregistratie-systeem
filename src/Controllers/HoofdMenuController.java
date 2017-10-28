package Controllers;

import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Models.IngevuldeTijdModel;
import Views.AdministratieHoofdmenuView;
import Views.PersoneelHoofdmenuView;
import javafx.stage.Stage;

public class HoofdMenuController {

    private Stage stage;
    private GebruikerModel gebruikerModel;
    private IngevuldeTijdModel tijdmodel;
    private PersoneelHoofdmenuView personeelView;
    private AdministratieHoofdmenuView adminView;
    private DatabaseConnectie db;
    private LoginController loginController;

    public HoofdMenuController(Stage stage, GebruikerModel gebruikerModel, DatabaseConnectie db, LoginController logincontroller) {
        this.stage = stage;
        this.gebruikerModel = gebruikerModel;
        this.tijdmodel = tijdmodel;
        this.db = db;
        this.loginController = logincontroller;
        personeelView = new PersoneelHoofdmenuView(this);
        adminView = new AdministratieHoofdmenuView(this);
    }

    public GebruikerModel getGebruikerModel() {
        return gebruikerModel;
    }

    public void startPersoneelHoofdmenuView(){
        stage.setScene(personeelView);
    }

    public void startAdminHoofdmenuView(){
       stage.setScene(adminView);
    }

    public void startAccountToevoegenView(){
        new GebruikerToevoegenController(stage, db, this);
        //stage.setScene(accountToevoegenView);
    }

    public void startInzienUrenAdminController(){
        new InzienUrenAdminController(stage, db, this);
    }

    public void startInvullenUrenView() {
        new InvullenUrenController(stage, db, this);
    }

    public void startGebruikerInfoView(){
        new GebruikerInfoController(stage, db, this);
    }
    
    public void startHoofdmenuView() {
    	 if (getGebruikerModel().getRechten().equals("1")) {
             startAdminHoofdmenuView();
         } else if (getGebruikerModel().getRechten().equals("0")) {
             startPersoneelHoofdmenuView();
         }
    }

    public void startAccountInfoView() {
        new AccountInfoController(stage, db, this);
    }

    public void startInzienUrenView(){
        new InzienUrenController(stage, db, this, gebruikerModel);
    }

    public LoginController getLoginController() {
        return loginController;
    }
}
