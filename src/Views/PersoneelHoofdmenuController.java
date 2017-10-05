package controllers;

import views.PersoneelHoofdmenuView;
import javafx.stage.Stage;

public class PersoneelHoofdmenuController {

    private Stage stage;
    private PersoneelHoofdmenuView personeelHoofdmenuView;

    public PersoneelHoofdmenuController(Stage stage){
        this.stage = stage;
        personeelHoofdmenuView = new PersoneelHoofdmenuView(this);
        stage.setScene(personeelHoofdmenuView);
        stage.show();
    }

    public void logIn(){

    }
}
