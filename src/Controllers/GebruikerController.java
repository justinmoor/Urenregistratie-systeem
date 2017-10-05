package Controllers;

import Database.DatabaseConnectie;
import Models.GebruikerModel;

public class GebruikerController {

    private GebruikerModel model;
    private DatabaseConnectie dbc;
    public GebruikerController(){

    }

    public GebruikerController(DatabaseConnectie dbc, GebruikerModel model) {
        this.model = model;
        this.dbc = dbc;
    }
}
