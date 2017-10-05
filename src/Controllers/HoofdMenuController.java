package Controllers;

import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Models.IngevuldeTijdModel;
import Views.AccountToevoegenView;
import Views.AdministratieHoofdmenuView;
import Views.PersoneelHoofdmenuView;
import javafx.stage.Stage;

public class HoofdMenuController {

    private Stage stage;
    private GebruikerModel gebruikerModel;
    private IngevuldeTijdModel tijdmodel;
    private PersoneelHoofdmenuView personeelView;
    private AdministratieHoofdmenuView adminView;
    private AccountToevoegenView accountToevoegenView;

    private GebruikerController gebruikerController;

    private DatabaseConnectie db;

    public HoofdMenuController(Stage stage, GebruikerModel gebruikerModel, IngevuldeTijdModel tijdmodel, DatabaseConnectie db){
        this.stage = stage;
        this.gebruikerModel = gebruikerModel;
        this.tijdmodel = tijdmodel;
        this.db = db;

        //gebruikerController = new GebruikerController(this);

        personeelView = new PersoneelHoofdmenuView(this);
        adminView = new AdministratieHoofdmenuView(this);
        //accountToevoegenView = new AccountToevoegenView(this);
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

    public void setIvullenUrenView() {
        new InvullenUrenController(stage, db);
    }

}
