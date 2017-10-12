package Controllers;

import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import Models.GebruikerModel;
import Views.LoginView;
import javafx.stage.Stage;

/**
 * Bevat alle logica om in te loggen.
 * @author Justin, Ian
 */
public class LoginController {

    private Stage stage;
    private LoginView loginView;
    private HoofdMenuController menuController;
    private DatabaseConnectie db;
    private GebruikerDAO gebruikerDAO;
    private GebruikerModel model;
    private final String NAAMWWFOUTMESSAGE = "Gebruikersnaam of wachtwoord is fout";

    /**
     * Constructor. Initialiseert alle benodigdheden.
     * @param stage
     */
    public LoginController(Stage stage) {

        /**
         * initialiseer alle nodige klassen.
         */
        this.stage = stage;
        loginView = new LoginView(this);
        db = new DatabaseConnectie();
        gebruikerDAO = new GebruikerDAO(db);

        /**
         * Zet de LoginView als actieve view.
         */
        stage.setScene(loginView);
        stage.show();
    }

    /**
     * Ontvangt een email en een wachtwoord. Zoekt in de database naar het ingevoerde email, haalt hier het wachtwoord voor op, en vergelijkt het ingevoerde wachtwoord hiermee.
     * @param email
     * @param wachtwoord
     */
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
                System.out.println(NAAMWWFOUTMESSAGE);
            }

    }
}