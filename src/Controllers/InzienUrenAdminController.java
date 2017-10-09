package Controllers;

import DAO.IngevuldeTijdDAO;
import Database.DatabaseConnectie;
import Views.InzienUrenAdminView;
import javafx.stage.Stage;

public class InzienUrenAdminController {
    Stage stage;
    DatabaseConnectie dbc;
    InzienUrenAdminView view;
    IngevuldeTijdDAO dao = new IngevuldeTijdDAO(dbc);

    public  InzienUrenAdminController(Stage stage, DatabaseConnectie dbc){
        view = new InzienUrenAdminView(this);
        stage.setScene(view);
    }
}
