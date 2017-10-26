package Controllers;

import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Views.AccountToevoegenView;
import javafx.stage.Stage;

public class GebruikerToevoegenController {
	private HoofdMenuController hoofdmenucontroller;
    private AccountToevoegenView accountToevoegenView;
    private DatabaseConnectie db;
    private GebruikerDAO dao;

    public GebruikerToevoegenController(Stage stage, DatabaseConnectie db, HoofdMenuController hoofdmenucontroller) {
        accountToevoegenView = new AccountToevoegenView(this);
        stage.setScene(accountToevoegenView);
        stage.show();
        this.db = db;
        this.hoofdmenucontroller = hoofdmenucontroller;
        dao = new GebruikerDAO(db);
    }

    public void insert(String voornaam, String tussenvoegsel, String achternaam, String email, String rechten){

        if(rechten.equals("Administratie")){
            rechten = "1";
        } else if (rechten.equals("Personeel")){
            rechten = "0";
        }

        dao.insertAccount(voornaam, tussenvoegsel, achternaam, email, rechten);
    }

	public HoofdMenuController getHoofdMenuController() {
		// TODO Auto-generated method stub
		return hoofdmenucontroller;
	}
}
