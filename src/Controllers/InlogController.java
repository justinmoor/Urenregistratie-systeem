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
    private GebruikerModel model;

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
            model = gebruikerDAO.GetGebruikerFromDB(email);
            System.out.println(model.toString());

            menuController = new HoofdMenuController(stage,model);

            if(model.getRechten().equals("p")) {
                menuController.setPersoneelHoofdmenu();
            } else if (model.getRechten().equals("a")){
                menuController.setAdminHoofdMenu();
            }

        } else {
<<<<<<< HEAD
            System.out.println("Gebruikersnaam of wachtwoord is fout.");
            loginView.setFoutief();
=======
            System.out.println("Gebruikersnaam of wachtwoord is fout!!!!");
>>>>>>> f5a68df5883cadee6e751dc3225a83253223821c
        }
    }
}
