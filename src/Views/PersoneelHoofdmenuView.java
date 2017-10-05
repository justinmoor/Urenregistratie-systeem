package Views;

import Controllers.HoofdMenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PersoneelHoofdmenuView extends Scene {
	private GridPane gridpane;	
	private VBox vbox;	
	private Button uren;
	private Button week;
		
	private HoofdMenuController controller;
	
	public PersoneelHoofdmenuView(HoofdMenuController controller){
		super(new GridPane(), 600,  400); 		// Nieuwe pane meegeven aan de superklasse (dus scene).
		gridpane = (GridPane) this.getRoot(); 	// Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
		this.controller = controller; 			// Controller zaken, moeten we nog overleggen.

		initGui(); 		// InitGui om alle grafische elementen te initialiseren.
		InitAction();	// InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
	}

	public void initGui() {
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setPadding(new Insets(25, 25, 25, 25));
		gridpane.setStyle("-fx-background-color: #f9f9f7");
		
		vbox = new VBox(20);
		vbox.setPrefWidth(270);
		vbox.setPrefHeight(70);
		
		uren = new Button("Uren registreren");
		uren.setMinWidth(vbox.getPrefWidth());
		uren.setMinHeight(vbox.getPrefHeight());
		
		week = new Button("Weekoverzicht");
		week.setMinWidth(vbox.getPrefWidth());
		week.setMinHeight(vbox.getPrefHeight());
		
		vbox.getChildren().addAll(uren, week);
		
		gridpane.add(vbox, 1, 0);
	}
	
	private void InitAction(){
		uren.setOnAction(e -> {
			// controller.doedingen();
		});
		week.setOnAction(e -> {
			// controller.doedingen();
		});
	}
}

