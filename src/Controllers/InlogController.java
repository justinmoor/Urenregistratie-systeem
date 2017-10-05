package Controllers;

import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Views.InlogView;
import javafx.stage.Stage;


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

            menuController = new HoofdMenuController(stage,model, db);

<<<<<<< HEAD
            if(model.getRechten().equals("p")) {
                menuController.setPersoneelHoofdmenu();
            } else if (model.getRechten().equals("a")){
=======
            if(model.getRechten().equals("0")) {
                menuController.setPersoneelHoofdmenu();
            } else if (model.getRechten().equals("1")){
>>>>>>> 1a64c329d8c15ef71ccd481d43528547def52df1
                menuController.setAdminHoofdMenu();
            }

        } else {

            System.out.println("Gebruikersnaam of wachtwoord is fout.");
            loginView.setFoutief();

        }
    }
}