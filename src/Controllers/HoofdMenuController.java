package Controllers;

import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Models.IngevuldeTijdModel;
import Views.AdministratieHoofdmenuView;
import Views.PersoneelHoofdmenuView;
import javafx.stage.Stage;

/**
 * Deze klasse regelt het switchen van views
 *
 * @author Justin Moor
 * @author Ian Beemsterboer
 * @author Stan Hoenson
 * @author Alex de Bruin
 *
 * @version 3.0
 */
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

    /**
     * Zet het hoofdmenu voor personeel
     */
    public void startPersoneelHoofdmenuView(){
        stage.setScene(personeelView);
    }

    /**
     * Zet het hoofdmenu voor een administrator
     */
    public void startAdminHoofdmenuView(){
       stage.setScene(adminView);
    }

    /**
     * Zet de view om een account toe te voegen
     */
    public void startAccountToevoegenView(){
        new GebruikerToevoegenController(stage, db, this);
        //stage.setScene(accountToevoegenView);
    }

    /**
     * Zet de view voor het inzien van de uren
     */
    public void startInzienUrenAdminController(){
        new InzienUrenAdminController(stage, db, this);
    }

    /**
     * Zet de view voor het inzien van je uren
     */
    public void startInvullenUrenView() {
        new InvullenUrenController(stage, db, gebruikerModel, this);
    }

    /**
     * Zet de account info view
     */
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

    public boolean checkConnectie(){
        if(db.getConnection() != null){
            return true;
        }
        return false;
    }
}
