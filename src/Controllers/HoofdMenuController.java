package Controllers;

import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Models.IngevuldeTijdModel;
import Views.AccountToevoegenView;
import Views.AdministratieHoofdmenuView;
import Views.GebruikerInfoView;
import Views.PersoneelHoofdmenuView;
import javafx.stage.Stage;

public class HoofdMenuController {

    private Stage stage;
    private GebruikerModel gebruikerModel;
    private IngevuldeTijdModel tijdmodel;
    private PersoneelHoofdmenuView personeelView;
    private AdministratieHoofdmenuView adminView;
    private GebruikerInfoView gebruikerInfoView;
    private AccountToevoegenView accountToevoegenView;

    private DatabaseConnectie db;

    public HoofdMenuController(Stage stage, GebruikerModel gebruikerModel, DatabaseConnectie db){
        this.stage = stage;
        this.gebruikerModel = gebruikerModel;
        this.tijdmodel = tijdmodel;
        this.db = db;


        personeelView = new PersoneelHoofdmenuView(this);
        adminView = new AdministratieHoofdmenuView(this);
        //accountToevoegenView = new AccountToevoegenView(this);
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
        new GebruikerToevoegenController(stage, db);
        //stage.setScene(accountToevoegenView);
    }

    public void setInvullenUrenView() {
        new InvullenUrenController(stage, db, this);
    }

    public void setGebruikerInfoView(){
        new GebruikerInfoController(stage, db);
    }

}
