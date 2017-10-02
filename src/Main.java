import Database.DatabaseConnectie;
import Views.LoginView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * Created by Ian on 9/11/2017.
 */
public class Main extends Application {

    public static void main (String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{

        Pane pane  = FXMLLoader.load(getClass().getResource("/Views/inlog.fxml"));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
