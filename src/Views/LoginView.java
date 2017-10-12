package Views;

import Controllers.LoginController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginView extends Scene {
	private LoginController controller;
	
	private BorderPane pane;
    
    private VBox groep;
    
    private Label titel;
    
    private Image img;
    private ImageView lijntje;
    
    private HBox email;
    private Label lbl1;
    private TextField tf1;

    private HBox wachtwoord;
    private Label lbl2;
    private PasswordField tf2;
    
    private Button login;
    
    private Label foutief;

    public LoginView(LoginController controller) {
        super(new BorderPane(), 600, 400); 		// Nieuwe pane meegeven aan de superklasse (dus scene).
        pane = (BorderPane) this.getRoot(); 		// Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
        this.controller = controller; 			// Controller zaken, moeten we nog overleggen.

        initGui(); 			// InitGui om alle grafische elementen te initialiseren.
        InitAction(); 		// InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
    }

    //Grafische elementen initialiseren.
    public void initGui() {
		pane.setStyle("-fx-background-image: url('/Assets/background.png')");

		groep = new VBox(12);
		
		titel = new Label("Urenregistratie");
		titel.setId("title");
		
		img = new Image("/Assets/lijntje.png");
		lijntje = new ImageView(img);
		lijntje.setFitWidth(241);
		
		email = new HBox(50);
		lbl1 = new Label("E-Mail:");
		tf1 = new TextField();
		email.getChildren().addAll(lbl1, tf1);
		
		wachtwoord = new HBox(15);
		lbl2 = new Label("Wachtwoord:");
		tf2 = new PasswordField();
		wachtwoord.getChildren().addAll(lbl2, tf2);
		
		login = new Button("LOG IN");
		
		groep.getChildren().addAll(titel, lijntje, email, wachtwoord, login);
		
		pane.setCenter(groep);
		groep.setAlignment(Pos.CENTER);
		email.setAlignment(Pos.CENTER);
		wachtwoord.setAlignment(Pos.CENTER);

        foutief = new Label("");

        getStylesheets().add("Views/styles.css");
    }

    //Functionele zaken initialiseren.
    private void InitAction() {
        login.setOnAction(e -> {
            controller.logIn(tf1.getText(), tf2.getText());
        });

        login.setDefaultButton(true); 	// Gebruik enter om in te loggen. (enige knop op het scherm, dus default knop.)
    }

}
