package Controllers;

import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Views.AdministratieHoofdmenuView;
import Views.GebruikerInfoView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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
        gebruikerInfoView.setGebruiker();
    }

    public ArrayList getGebruikers(){
        gebruikers = dao.getAllAccount();
        return gebruikers;
    }

    /**
     * Zet de geselecteerde gebruikers op actief binnen het bedrijf.
     * @param models
     */
    public void setWerkzaam(List<GebruikerModel> models){
        for(GebruikerModel model : models) {
            dao.setWerkzaam(model);
        }
    }

    /**
     * Zet geselecteerde gebruikers op non-actief binnen het bedrijf.
     * @param models
     */
    public void setNietWerkzaam(List<GebruikerModel> models){
        for(GebruikerModel model : models) {
            dao.setNietWerkzaam(model);
        }
    }
    
    public HoofdMenuController getHoofdMenuController() {
    		return hoofdmenucontroller;
    }

	public void setAdminHoofdMenu() {
		stage.setScene(adminView);
	}

}
