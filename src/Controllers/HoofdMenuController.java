package Controllers;

import Models.GebruikerModel;
import Views.AccountToevoegenView;
import Views.AdministratieHoofdmenuView;
import Views.PersoneelHoofdmenuView;
import javafx.stage.Stage;

public class HoofdMenuController {

    private Stage stage;
    private GebruikerModel gebruikerModel;
    private PersoneelHoofdmenuView personeelView;
    private AdministratieHoofdmenuView adminView;
    private AccountToevoegenView accountToevoegenView;

    public HoofdMenuController(Stage stage, GebruikerModel gebruikerModel){
        this.stage = stage;
        this.gebruikerModel = gebruikerModel;

        personeelView = new PersoneelHoofdmenuView(this);
        adminView = new AdministratieHoofdmenuView(this);
        accountToevoegenView = new AccountToevoegenView(this);
    }


    public void setPersoneelHoofdmenu(){
        stage.setScene(personeelView);
    }

    public void setAdminHoofdMenu(){
        stage.setScene(adminView);
    }

    public void setAccountToevoegenView(){
        stage.setScene(accountToevoegenView);
    }

}
