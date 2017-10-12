import Controllers.LoginController;
import Database.DatabaseConnectie;
import Views.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Created by Ian on 9/11/2017.
 */
public class Main extends Application {

    private LoginView login;
    private LoginController loginController;

    public static void main (String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{

        loginController = new LoginController(primaryStage);

    }

}
