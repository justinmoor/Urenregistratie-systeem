package Controllers;

import DAO.IngevuldeTijdDAO;
import Database.DatabaseConnectie;
import Views.InvullenUrenView;
import javafx.stage.Stage;

public class InvullenUrenController {

    private InvullenUrenView invullenurenView;
    private DatabaseConnectie db;
    private IngevuldeTijdDAO dao;

    InvullenUrenController(Stage stage, DatabaseConnectie db) {
        invullenurenView = new InvullenUrenView(this);
        stage.setScene(invullenurenView);
        stage.show();

        this.db = db;
        dao = new IngevuldeTijdDAO(db);
    }

}
