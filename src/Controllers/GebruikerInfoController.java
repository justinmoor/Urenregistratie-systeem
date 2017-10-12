package Controllers;

import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Views.AdministratieHoofdmenuView;
import Views.GebruikerInfoView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GebruikerInfoController {

    private GebruikerDAO dao;
    private DatabaseConnectie db;
    private Stage stage;
    private GebruikerInfoView gebruikerInfoView;

    private ArrayList<GebruikerModel> gebruikers;
	private AdministratieHoofdmenuView adminView;
	
	private HoofdMenuController hoofdmenucontroller;

    public GebruikerInfoController(Stage stage, DatabaseConnectie db, HoofdMenuController hoofdmenucontroller){
        this.stage = stage;
        this.db = db;
        this.hoofdmenucontroller = hoofdmenucontroller;
        dao = new GebruikerDAO(db);

        gebruikerInfoView = new GebruikerInfoView(this);

        stage.setScene(gebruikerInfoView);
        stage.show();
    }

    public ArrayList getGebruikers(){
        gebruikers = dao.getAllAccount();
        return gebruikers;
    }
    
    public HoofdMenuController getHoofdMenuController() {
    		return hoofdmenucontroller;
    }

	public void setAdminHoofdMenu() {
		stage.setScene(adminView);
	}

}
