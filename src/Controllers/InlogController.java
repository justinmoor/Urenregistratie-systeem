package Controllers;


import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Views.InlogView;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InlogController {

    private Stage stage;
    private InlogView loginView;

    private HoofdMenuController menuController;

    private DatabaseConnectie db;
    private GebruikerDAO gebruikerDAO;

    public InlogController(Stage stage) {

        this.stage = stage;
        loginView = new InlogView(this);
        db = new DatabaseConnectie();
        gebruikerDAO = new GebruikerDAO(db);
        stage.setScene(loginView);
        stage.show();
    }


    public void logIn(String email, String wachtwoord) {
        if(gebruikerDAO.getWachtwoordQuery(email).equals(wachtwoord)){
            System.out.println("Ingelogd");
            gebruikerDAO.GetGebruikerFromDB(email);
        } else {
            System.out.println("Werk2");
        }
    }
}
