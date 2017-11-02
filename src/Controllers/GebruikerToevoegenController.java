package Controllers;

import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Views.AccountToevoegenView;
import javafx.stage.Stage;

/**
 * Deze klasse wordt gebruikt om een nieuw account in te voegen
 * @author Justin Moor
 */
public class GebruikerToevoegenController {
	private HoofdMenuController hoofdmenucontroller;
    private AccountToevoegenView accountToevoegenView;
    private DatabaseConnectie db;
    private GebruikerDAO dao;

    /**
     * Constructor
     * @param stage
     * @param db
     * @param hoofdmenucontroller
     */
    public GebruikerToevoegenController(Stage stage, DatabaseConnectie db, HoofdMenuController hoofdmenucontroller) {
        accountToevoegenView = new AccountToevoegenView(this);
        stage.setScene(accountToevoegenView);
        stage.show();
        this.db = db;
        this.hoofdmenucontroller = hoofdmenucontroller;
        dao = new GebruikerDAO(db);
        accountToevoegenView.setGebruikerLabel(hoofdmenucontroller.getGebruikerModel().getVolledigeNaam());
    }

    /**
     * Nieuw account toevoegen met de meegegeven informatie
     * @param voornaam
     * @param tussenvoegsel
     * @param achternaam
     * @param email
     * @param rechten
     */
    public void insert(String voornaam, String tussenvoegsel, String achternaam, String email, String rechten){

        if(rechten.equals("Administratie")){
            rechten = "1";
        } else if (rechten.equals("Personeel")){
            rechten = "0";
        }

        dao.insertAccount(voornaam, tussenvoegsel, achternaam, email, rechten);
    }

    public boolean checkVelden(String voornaam, String achternaam, String email){
        if(voornaam.isEmpty() || achternaam.isEmpty() || email.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean checkConnection(){
        if(db.getConnection() != null){
            return true;
        }
        return false;
    }

    /**
     * Geeft Hoofdmenucontroller terug
     * @return hoofdmenucontroller
     */
	public HoofdMenuController getHoofdMenuController() {
		return hoofdmenucontroller;
	}
}
