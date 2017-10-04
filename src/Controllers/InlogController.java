package Controllers;


import Views.InlogView;
import javafx.stage.Stage;

public class InlogController {

    private Stage stage;
    private InlogView loginView;

    public InlogController(Stage stage){
        this.stage = stage;
        loginView = new InlogView(this);
        stage.setScene(loginView);
        stage.show();
    }


    public void logIn(){

    }
}
