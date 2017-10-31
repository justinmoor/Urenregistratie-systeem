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
    private final String WERKZAAM = "1"; //Werkzaam = 1, Niet werkzaam = 0

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
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Ontvangt een email en een wachtwoord. Zoekt in de database naar het ingevoerde email, haalt hier het wachtwoord voor op, en vergelijkt het ingevoerde wachtwoord hiermee.
     * @param email
     * @param wachtwoordUitView
     */
    public void logIn(String email, String wachtwoordUitView) {

            if (gebruikerDAO.getWachtwoordQuery(email).equals(wachtwoordUitView) && gebruikerDAO.getWerkzaam(email).equals(WERKZAAM)) {
                model = gebruikerDAO.GetGebruikerFromDB(email);

                System.out.println(model.toString());                       //Print de
                menuController = new HoofdMenuController(stage, model, db, this);

                if (model.getRechten().equals("0")) {
                    menuController.startPersoneelHoofdmenuView();
                } else if (model.getRechten().equals("1")) {
                    menuController.startAdminHoofdmenuView();
                }

            } else {
                System.out.println(NAAMWWFOUTMESSAGE);
            }
    }

    public void logUit() {
        stage.setScene(loginView);
        stage.show();
        loginView.clearFields();
    }
}