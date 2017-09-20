import Database.DatabaseConnectie;
import Database.InlogView;
import javafx.application.Application;

public class InlogController {
    InlogView inlogView;
    DatabaseConnectie dbc;

    public InlogController(DatabaseConnectie dbc){
        inlogView = new InlogView();
        inlogView.run(dbc);
        this.dbc = dbc;
    }


}
