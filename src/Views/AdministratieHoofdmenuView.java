package Views;


import Controllers.HoofdMenuController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AdministratieHoofdmenuView extends Scene {
	private GridPane gridpane;
	
	private HBox hbox;
	private VBox links;
	private Button uren;
	private Button week;
		
	private VBox rechts;
	private Button account;
	private Button personeel;

	private HoofdMenuController controller;

	public AdministratieHoofdmenuView(HoofdMenuController controller){
		super(new GridPane(), 600,  400); 		// Nieuwe pane meegeven aan de superklasse (dus scene).
		gridpane = (GridPane) this.getRoot(); 	// Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
		this.controller = controller;
		initGui(); 		// InitGui om alle grafische elementen te initialiseren.
		InitAction();	// InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
	}

	public void initGui() {
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setPadding(new Insets(25, 25, 25, 25));
		gridpane.setStyle("-fx-background-color: #f9f9f7");
		
		links = new VBox(20);
		links.setPrefWidth(200);
		links.setPrefHeight(60);
		
		uren = new Button("Uren registreren");
		uren.setMinWidth(links.getPrefWidth());
		uren.setMinHeight(links.getPrefHeight());
		
		week = new Button("Weekoverzicht");
		week.setMinWidth(links.getPrefWidth());
		week.setMinHeight(links.getPrefHeight());
		
		links.getChildren().addAll(uren, week);
		
		rechts = new VBox(20);
		rechts.setPrefWidth(200);
		rechts.setPrefHeight(60);
		
		account = new Button("Account toevoegen");
		account.setMinWidth(rechts.getPrefWidth());
		account.setMinHeight(rechts.getPrefHeight());
		
		personeel = new Button("Personeel info");
		personeel.setMinWidth(rechts.getPrefWidth());
		personeel.setMinHeight(rechts.getPrefHeight());
		
		rechts.getChildren().addAll(account, personeel);
		
		hbox = new HBox(20);
		hbox.getChildren().addAll(links, rechts);
		
		gridpane.add(hbox, 1, 0);
	}
	
	private void InitAction(){
		uren.setOnAction(e -> {
			controller.setInvullenUrenView();
		});
		week.setOnAction(e -> {
			// controller.doedingen();
		});
		account.setOnAction(e -> {
			controller.setAccountToevoegenView();
		});
		personeel.setOnAction(e -> {
			// controller.doedingen();
		});
	}

}


