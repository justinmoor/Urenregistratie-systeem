package Controllers;

import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Views.AccountToevoegenView;
import javafx.stage.Stage;

public class GebruikerToevoegenController {
    private AccountToevoegenView accountToevoegenView;
    private GebruikerModel gebruikerModel;
    private DatabaseConnectie db;
    private GebruikerDAO dao;

    public GebruikerToevoegenController(Stage stage, DatabaseConnectie db) {
        accountToevoegenView = new AccountToevoegenView(this);
        stage.setScene(accountToevoegenView);
        stage.show();
        this.db = db;
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


}
