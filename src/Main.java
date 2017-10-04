import Controllers.InlogController;
import Views.InlogView;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Created by Ian on 9/11/2017.
 */
public class Main extends Application {

    private InlogView login;
    private InlogController loginController;

    public static void main (String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{

        loginController = new InlogController(primaryStage);

    }

}
