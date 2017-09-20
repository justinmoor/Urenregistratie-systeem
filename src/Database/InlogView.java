package Database;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InlogView extends Application{
    TextField emailField;
    PasswordField wachtwoordField;
    Button okButton;
    DatabaseConnectie dbc;


    @Override
    public void start(Stage primaryStage) throws Exception {
        dbc = new DatabaseConnectie();
        BorderPane bp = new BorderPane();

        emailField = new TextField("E-mailadres");
        wachtwoordField = new PasswordField();
        okButton = new Button("Inloggen");

        VBox vbox = new VBox(emailField, wachtwoordField, okButton);
        bp.setCenter(vbox);
        Scene scene = new Scene(bp, 200, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Urenregistratie - Inloggen");
        primaryStage.show();

        okButton.setOnAction((ActionEvent e) ->{
            System.out.println("Inlogknop ingedrukt. Naam en wachtwoord: "+ emailField.getText()+", "+ wachtwoordField.getText());
            dbc.wachtwoordJuist(wachtwoordField.getText(), emailField.getText());
        });
    }

    public void run(DatabaseConnectie dbc){
        //this.dbc = dbc;
        launch();
    }
}
