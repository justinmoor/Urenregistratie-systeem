package Views;

import Controllers.GebruikerToevoegenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AccountToevoegenView extends Scene {
		private BorderPane pane;

		private BorderPane navigatie;
	    
	    private VBox img_box;
	    private Image img;
	    private ImageView terug;
	    
	    private Label home;
	    private Label gebruiker;
	    
	    private Image img2;
	    private ImageView lijntje;
	    
	    private VBox groep;
	
	    private HBox voornaamBox;
	    private HBox tussenvoegselBox;
	    private HBox achternaamBox;
	    private HBox emailBox;
	    private HBox rechtenBox;
	    
		private Label voornaam;
		private Label tussenvoegsel;
		private Label achternaam;
		private Label email;
		private Label rechten;
					
		private TextField voornaamInput;
		private TextField tussenvoegselInput;
		private TextField achternaamInput;
		private TextField emailInput;
		private HBox hbox;

		private ComboBox rechtenKeuze;
			
		private Button toevoegen;
		
		private Image img3;
	    private ImageView blauw_lijntje; 
	    
		private GebruikerToevoegenController controller;

	public AccountToevoegenView(GebruikerToevoegenController controller){
		super(new BorderPane(), 600,  400); 			// Nieuwe pane meegeven aan de superklasse (dus scene).
		pane = (BorderPane) this.getRoot(); 			// Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
		this.controller = controller; 				// Controller zaken, moeten we nog overleggen.

		initGui(); 			// InitGui om alle grafische elementen te initialiseren.
		InitAction();		// InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
	}

	//Grafische elementen initialiseren.
	public void initGui() {
		pane.setId("pane");
		
		navigatie = new BorderPane();
		
		img_box = new VBox();
		img = new Image("/Assets/back.png");
		terug = new ImageView(img);
		img_box.getChildren().add(terug);
		img_box.setPadding(new Insets(15, 83, 15, 14));
		
		home = new Label("ACCOUNT TOEVOEGEN");
		home.setId("home");
		home.setPadding(new Insets(15));
		
		gebruiker = new Label("Peter van Vliet");
		gebruiker.setPadding(new Insets(15, 14, 15, 15));
		
		img2 = new Image("/Assets/lijntje.png");
		lijntje = new ImageView(img2);
		lijntje.setFitWidth(600);
		
		navigatie.setLeft(img_box);
		navigatie.setCenter(home);
		navigatie.setRight(gebruiker);
		navigatie.setBottom(lijntje);

		voornaamBox = new HBox(20);
		voornaam = new Label("Voornaam:");
		voornaamInput = new TextField();
		voornaamBox.getChildren().addAll(voornaam, voornaamInput);
			
		tussenvoegselBox = new HBox(20);
		tussenvoegsel = new Label("Tussenvoegsel:");
		tussenvoegselInput = new TextField();
		tussenvoegselBox.getChildren().addAll(tussenvoegsel, tussenvoegselInput);
		
		achternaamBox = new HBox(20);
		achternaam = new Label("Achternaam:");
		achternaamInput = new TextField();
		achternaamBox.getChildren().addAll(achternaam, achternaamInput);
		
		emailBox = new HBox(20);
		email = new Label("E-Mail:");
		emailInput = new TextField();
		emailBox.getChildren().addAll(email, emailInput);
		
		rechtenBox = new HBox(20);
		rechten = new Label("Rechten:");
		rechtenKeuze = new ComboBox();
		rechtenKeuze.getItems().addAll("Personeel", "Administratie");
		rechtenKeuze.setValue("Personeel");
		rechtenBox.getChildren().addAll(rechten, rechtenKeuze);
					
		toevoegen = new Button("TOEVOEGEN");
		toevoegen.setId("toevoegen");
		
		groep.getChildren().addAll(voornaamBox, tussenvoegselBox, achternaamBox, emailBox, rechtenBox, toevoegen);
		
		img3 = new Image("/Assets/blauwlijntje.png");
		blauw_lijntje = new ImageView(img3);
		blauw_lijntje.setFitWidth(612);
		
		pane.setTop(navigatie);
		pane.setCenter(groep);
		pane.setBottom(blauw_lijntje);
		
		getStylesheets().add("Views/styles.css");
	}

	//Functionele zaken initialiseren.
	private void InitAction(){
		toevoegen.setOnAction(e -> {
			controller.insert(voornaamInput.getText(), tussenvoegselInput.getText(), achternaamInput.getText(), emailInput.getText(), rechtenKeuze.getValue().toString());

		});
	}
}

