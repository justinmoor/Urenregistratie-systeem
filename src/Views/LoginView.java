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
		/**
	     * Initialiseren van de bijhorende controller
	     */
		private LoginController controller;
		
		/**
		 * Initialiseren van alle benodigde onderdelen voor deze view
		 */
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
	    
	    private Image img2;
	    private ImageView blauw_lijntje; 

	    /**
	     * Maakt de view aan. Ook wordt de controller meegegeven, zodat de controller de logica van de knoppen regelt
	     * @param controller
	     */ 	
    public LoginView(LoginController controller) {
        super(new BorderPane(), 600, 400); 		// Nieuwe pane meegeven aan de superklasse (dus scene).
        pane = (BorderPane) this.getRoot(); 		// Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
        this.controller = controller; 			// Controller zaken, moeten we nog overleggen.
        initGui(); 			// InitGui om alle grafische elementen te initialiseren.
        InitAction(); 		// InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
    }

    /**
	 * Maken en plaatsen van alle onderdelen voor de GUI
	 */
    public void initGui() {
    		pane.setId("pane");
    	
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
		login.setId("login");
		
		groep.getChildren().addAll(titel, lijntje, email, wachtwoord, login);
		
		pane.setCenter(groep);
		groep.setAlignment(Pos.CENTER);
		email.setAlignment(Pos.CENTER);
		wachtwoord.setAlignment(Pos.CENTER);

        foutief = new Label("");
        
        img2 = new Image("/Assets/blauwlijntje.png");
		blauw_lijntje = new ImageView(img2);
		blauw_lijntje.setFitWidth(612);
		
		pane.setBottom(blauw_lijntje);

        getStylesheets().add("Views/styles.css");
    }

    /**
     * Methode waar acties worden meegegeven aan verschillende knoppen of andere onderdelen uit de view
     */
    private void InitAction() {
        login.setOnAction(e -> {
            controller.logIn(tf1.getText(), tf2.getText());
        });

        login.setDefaultButton(true); 	// Gebruik enter om in te loggen. (enige knop op het scherm, dus default knop.)
    }

    public void clearFields() {
    	tf1.setText("");
    	tf2.setText("");
	}

}
