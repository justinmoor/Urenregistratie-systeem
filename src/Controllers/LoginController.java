package Controllers;

import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Views.LoginView;
import javafx.stage.Stage;


public class LoginController {

    private Stage stage;
    private LoginView loginView;

    private HoofdMenuController menuController;

    private DatabaseConnectie db;
    private GebruikerDAO gebruikerDAO;
    private GebruikerModel model;

    public LoginController(Stage stage) {

        this.stage = stage;
        loginView = new LoginView(this);
        db = new DatabaseConnectie();
        gebruikerDAO = new GebruikerDAO(db);

        stage.setScene(loginView);
        stage.show();
    }


    public void logIn(String email, String wachtwoord) {

            if (gebruikerDAO.getWachtwoordQuery(email).equals(wachtwoord)) {
                model = gebruikerDAO.GetGebruikerFromDB(email);
                System.out.println(model.toString());

                menuController = new HoofdMenuController(stage, model, db);

                if (model.getRechten().equals("0")) {
                    menuController.setPersoneelHoofdmenu();
                } else if (model.getRechten().equals("1")) {
                    menuController.setAdminHoofdMenu();
                }

            } else {
                System.out.println("Gebruikersnaam of wachtwoord is fout!!!!");
                loginView.setFoutief();
            }

    }
}