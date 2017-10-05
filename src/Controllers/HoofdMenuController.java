package Controllers;

import Models.GebruikerModel;
import Views.AdministratieHoofdmenuView;
import Views.InlogView;
import Views.PersoneelHoofdmenuView;
import javafx.stage.Stage;

public class HoofdMenuController {

    private Stage stage;
    private GebruikerModel gebruikerModel;
    private PersoneelHoofdmenuView personeelView;
    private AdministratieHoofdmenuView adminView;

    public HoofdMenuController(Stage stage, GebruikerModel gebruikerModel){
        this.stage = stage;
        this.gebruikerModel = gebruikerModel;

        personeelView = new PersoneelHoofdmenuView(this);
        adminView = new AdministratieHoofdmenuView(this);
    }


    public void setPersoneelHoofdmenu(){
        stage.setScene(personeelView);
    }

    public void setAdminHoofdMenu(){
        stage.setScene(adminView);
    }

}
