package controllers;

import views.AdministratieHoofdmenuView;
import javafx.stage.Stage;

public class AdministratieHoofdmenuController {

    private Stage stage;
    private AdministratieHoofdmenuView administratieHoofdmenuView;

    public AdministratieHoofdmenuController(Stage stage){
        this.stage = stage;
        administratieHoofdmenuView = new AdministratieHoofdmenuView(this);
        stage.setScene(administratieHoofdmenuView);
        stage.show();
    }

    public void logIn(){

    }
}

