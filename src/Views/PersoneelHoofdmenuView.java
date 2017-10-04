package Views;

import Controllers.HoofdMenuController;
import Controllers.InlogController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PersoneelHoofdmenuView extends Scene {
	HoofdMenuController controller;
	GridPane gridpane;
	public Label persoonNaam;
	public Label persoonEmail;
	public PersoneelHoofdmenuView(HoofdMenuController controller) {
		super(new GridPane(), 1200, 800); // Nieuwe pane meegeven aan de superklasse (dus scene).
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

		persoonEmail = new Label();
		persoonNaam = new Label();

		gridpane.getChildren().addAll(persoonEmail, persoonNaam);

	}

	//Functionele zaken initialiseren.
	private void InitAction() {

	}
}

