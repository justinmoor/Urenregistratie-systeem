package Views;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginView extends Pane {

    private Scene scene;
    private TextField naamField;
    private PasswordField wachtwoordField;
    private Label naamLabel;
    private Label wachtwoordLabel;
    private Pane pane;

   public LoginView(){
       initGUI();
   }

   private void initGUI(){

       pane = new Pane();
       pane.setId("hoofdPane");

       naamField = new TextField(); //email
       naamField.setId("naamField");

       wachtwoordField = new PasswordField();
       wachtwoordField.setId("wachtwoordField");

       naamLabel = new Label("E-mail: ");
       naamLabel.setId("standaardLabel");
       wachtwoordLabel = new Label("Wachtwoord: ");
       wachtwoordLabel.setId("standaardLabel");

       pane.getChildren().addAll(naamField, wachtwoordField, naamLabel, wachtwoordLabel);

       scene = new Scene(pane);
       scene.getStylesheets().add("styles.css");
   }

}

