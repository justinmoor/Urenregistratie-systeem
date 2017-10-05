package Views;

import Controllers.GebruikerToevoegenController;
import Controllers.HoofdMenuController;
import Models.GebruikerModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AccountToevoegenView extends Scene {
	private GridPane gridpane;
		private Label title;
	
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
		private GebruikerToevoegenController controller;



	public AccountToevoegenView(GebruikerToevoegenController controller){
		super(new GridPane(), 600,  400); 		// Nieuwe pane meegeven aan de superklasse (dus scene).
		gridpane = (GridPane) this.getRoot(); 	// Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
		this.controller = controller; 			// Controller zaken, moeten we nog overleggen.

		initGui(); 		// InitGui om alle grafische elementen te initialiseren.
		InitAction();	// InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
	}

	//Grafische elementen initialiseren.
	public void initGui() {
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(20);
		gridpane.setVgap(15);
		gridpane.setPadding(new Insets(25, 25, 25, 25));
		gridpane.setStyle("-fx-background-color: #f9f9f7");
		
		title = new Label("Account toevoegen");
		title.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
		title.setTextFill(Color.GREY);
		gridpane.add(title, 0, 0, 2, 1);

		voornaam = new Label("Voornaam:");
		voornaam.setTextFill(Color.GREY);
		gridpane.add(voornaam, 0, 1);
			
		voornaamInput = new TextField();
		gridpane.add(voornaamInput, 1, 1);
			
		tussenvoegsel = new Label("Tussenvoegsel:");
		tussenvoegsel.setTextFill(Color.GREY);
		gridpane.add(tussenvoegsel, 0, 2);
					
		tussenvoegselInput = new TextField();
		gridpane.add(tussenvoegselInput, 1, 2);
		
		achternaam = new Label("Achternaam:");
		achternaam.setTextFill(Color.GREY);
		gridpane.add(achternaam, 0, 3);
					
		achternaamInput = new TextField();
		gridpane.add(achternaamInput, 1, 3);
		
		email = new Label("E-Mail:");
		email.setTextFill(Color.GREY);
		gridpane.add(email, 0, 4);
					
		emailInput = new TextField();
		gridpane.add(emailInput, 1, 4);
		
		rechten = new Label("Rechten:");
		rechten.setTextFill(Color.GREY);
		gridpane.add(rechten, 0, 5);
					
		hbox = new HBox(10);
		rechtenKeuze = new ComboBox();
		rechtenKeuze.getItems().addAll(
				"Personeel",
				"Administratie"
		);
		rechtenKeuze.setValue("Personeel");
		hbox.getChildren().addAll(rechtenKeuze);
		gridpane.add(hbox, 1, 5);
					
		toevoegen = new Button("Toevoegen");
		gridpane.add(toevoegen, 0, 6);
	}

	//Functionele zaken initialiseren.
	private void InitAction(){
		toevoegen.setOnAction(e -> {
			controller.insert(voornaamInput.getText(), tussenvoegselInput.getText(), achternaamInput.getText(), emailInput.getText(), rechtenKeuze.getValue().toString());
		});
	}


}

