package Views;

import Controllers.InlogController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class InlogView extends Scene {
    private GridPane gridpane;

    private Label title;

    private Label email;
    private Label password;

    private TextField email_input;
    private PasswordField password_input;

    private Button login;

    private InlogController controller;
    private Label feedback;

    public InlogView(InlogController controller) {
        super(new GridPane(), 600, 400); // Nieuwe pane meegeven aan de superklasse (dus scene).
        gridpane = (GridPane) this.getRoot(); // Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
        this.controller = controller; // Controller zaken, moeten we nog overleggen.

        initGui(); // InitGui om alle grafische elementen te initialiseren.
        InitAction(); // InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
    }

    //Grafische elementen initialiseren.
    public void initGui() {
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(20);
        gridpane.setVgap(15);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
        gridpane.setStyle("-fx-background-color: #f9f9f7");

        title = new Label("Urenregistratie");
        title.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
        title.setTextFill(Color.GREY);
        gridpane.add(title, 0, 0, 2, 1);

        email = new Label("E-Mail:");
        email.setTextFill(Color.GREY);
        gridpane.add(email, 0, 1);

        email_input = new TextField();
        gridpane.add(email_input, 1, 1);

        password = new Label("Wachtwoord:");
        password.setTextFill(Color.GREY);
        gridpane.add(password, 0, 2);

        password_input = new PasswordField();
        gridpane.add(password_input, 1, 2);

        login = new Button("Log in");
        gridpane.add(login, 0, 3);

        feedback = new Label("");
        gridpane.add(feedback, 0, 4, 4, 1);


    }

    //Functionele zaken initialiseren.
    private void InitAction() {
        login.setOnAction(e -> {
            if(email_input.getText()==null||password_input.getText()==null){
                feedback.setText("Vul een email en wachtwoord in pls");
            } else {
                controller.logIn(email_input.getText(), password_input.getText());
            }
        });
    }

    public void setFeedback(String feedback) {
        this.feedback.setText(feedback);
    }
}
