import Database.DatabaseConnectie;
import Database.InlogView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Ian on 9/11/2017.
 */
public class Main {

    public static void main (String[] args){
        DatabaseConnectie dbc = new DatabaseConnectie();
        InlogController ilc = new InlogController(dbc);


    }

}
