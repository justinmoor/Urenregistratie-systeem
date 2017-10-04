package Controllers;


import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
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

    public int logIn(String naam, String wachtwoord) {
        return gebruikerDAO.query(naam, wachtwoord);
    }
}
