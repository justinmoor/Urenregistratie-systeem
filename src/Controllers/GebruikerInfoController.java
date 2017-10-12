package Controllers;

import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Views.GebruikerInfoView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GebruikerInfoController {

    private GebruikerDAO dao;
    private DatabaseConnectie db;
    private Stage stage;
    private GebruikerInfoView gebruikerInfoView;

    private ArrayList<GebruikerModel> gebruikers;

    public GebruikerInfoController(Stage stage, DatabaseConnectie db){
        this.stage = stage;
        this.db = db;
        dao = new GebruikerDAO(db);

        gebruikerInfoView = new GebruikerInfoView(this);

        stage.setScene(gebruikerInfoView);
        stage.show();
    }

    public ArrayList getGebruikers(){
        gebruikers = dao.getAllAccount();
        return gebruikers;
    }

}
