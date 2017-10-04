package Controllers;

import Models.GebruikerModel;
import Views.InlogView;
import Views.PersoneelHoofdmenuView;
import javafx.stage.Stage;

public class HoofdMenuController {

    private Stage stage;
    private GebruikerModel gebruikerModel;
    private PersoneelHoofdmenuView personeelView;

    public HoofdMenuController(Stage stage, GebruikerModel gebruikerModel){
        this.stage = stage;
        this.gebruikerModel = gebruikerModel;

        PersoneelHoofdmenuView view = new PersoneelHoofdmenuView(this);
    }


    public void setScene(){
        stage.setScene(personeelView);
    }
}
