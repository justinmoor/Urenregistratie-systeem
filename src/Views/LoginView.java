package Views;

import Controllers.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class LoginView extends Scene {
    private GridPane gridpane;

    private Label title;
    
//    private ImageView grijs;
//    private Image lijntje;
    
//    private HBox hbox;

    private Label email;
    private Label password;

    private TextField email_input;
    private PasswordField password_input;

    private Button login;

    private LoginController controller;
    private Label feedback;

    public LoginView(LoginController controller) {
        super(new GridPane(), 600, 400); // Nieuwe pane meegeven aan de superklasse (dus scene).
        gridpane = (GridPane) this.getRoot(); // Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
        this.controller = controller; // Controller zaken, moeten we nog overleggen.

        initGui(); // InitGui om alle grafische elementen te initialiseren.
        InitAction(); // InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
    }

    //Grafische elementen initialiseren.
    public void initGui() {
    	   
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(15);
        gridpane.setVgap(15);
        gridpane.setStyle("-fx-background-color: #f9f9f7");
        
        getStylesheets().add("Views/styles.css");

//        hbox.setPrefHeight(27);
//        hbox.setPrefWidth(144);
        
        title = new Label("Urenregistratie");
        title.setId("title");
        gridpane.add(title, 0, 0, 2, 1);
        
//        lijntje = new Image("lijntje.png");
//        grijs = new ImageView();
//        grijs.setImage(lijntje);
//        gridpane.add(grijs, 0, 1);

        email = new Label("E-Mail:");
        email.setId("emailLabel");
        email.setTextFill(Color.GREY);
        gridpane.add(email, 0, 1);

        email_input = new TextField();
        email_input.setId("emailTextfield");
//        email_input.setMinHeight(hbox.getPrefHeight());
//        email_input.setMinWidth(hbox.getPrefWidth());
        gridpane.add(email_input, 1, 1);
        
//        hbox.getChildren().addAll(email, email_input);

        password = new Label("Wachtwoord:");
        password.setId("passwordLabel");
        password.setTextFill(Color.GREY);
        gridpane.add(password, 0, 2);

        password_input = new PasswordField();
        password_input.setId("passwordTextfield");
        gridpane.add(password_input, 1, 2);

        login = new Button("LOG IN");
        login.setId("loginButton");
        login.setAlignment(Pos.CENTER);
        gridpane.add(login, 1, 3);

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
