package Controllers;

import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Models.IngevuldeTijdModel;
import Views.AccountToevoegenView;
import Views.AdministratieHoofdmenuView;
import Views.GebruikerInfoView;
import Views.InvullenUrenView;
import Views.PersoneelHoofdmenuView;
import javafx.stage.Stage;

public class HoofdMenuController {

    private Stage stage;
    private GebruikerModel gebruikerModel;
    private IngevuldeTijdModel tijdmodel;
    private PersoneelHoofdmenuView personeelView;
    private AdministratieHoofdmenuView adminView;
    private DatabaseConnectie db;

    public HoofdMenuController(Stage stage, GebruikerModel gebruikerModel, DatabaseConnectie db){
        this.stage = stage;
        this.gebruikerModel = gebruikerModel;
        this.tijdmodel = tijdmodel;
        this.db = db;
        personeelView = new PersoneelHoofdmenuView(this);
        adminView = new AdministratieHoofdmenuView(this);
    }

    public GebruikerModel getGebruikerModel() {
        return gebruikerModel;
    }

    public void setPersoneelHoofdmenu(){
        stage.setScene(personeelView);
    }

    public void setAdminHoofdMenu(){
       stage.setScene(adminView);
    }

    public void setAccountToevoegenView(){
        new GebruikerToevoegenController(stage, db, this);
        //stage.setScene(accountToevoegenView);
    }

    public void startInzienUrenAdminController(){
        new InzienUrenAdminController(db, this);
    }

    public void setIvullenUrenView() {
        new InvullenUrenController(stage, db, this);
    }

    public void setGebruikerInfoView(){
        new GebruikerInfoController(db, this);
    }
    
    public void setHoofdMenuView() {
    	 if (getGebruikerModel().getRechten().equals("1")) {
             setAdminHoofdMenu();
         } else if (getGebruikerModel().getRechten().equals("0")) {
             setPersoneelHoofdmenu();
         }
    }

    public void setAccountInfoView() { new AccountInfoController(stage, db, this); }

}
