package Views;


import Controllers.HoofdMenuController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.stage.Stage;

public class AdministratieHoofdmenuView extends Scene {
	private GridPane gridpane;
	private BorderPane pane;
	
	private Image img;
	private ImageView back;
	
	private Label home;
	
	private Label gebruiker;
	
	private HBox hbox;
	private VBox links;
	private Button uren;
	private Button week;
		
	private VBox rechts;
	private Button account;
	private Button personeel;

	private HoofdMenuController controller;

	public AdministratieHoofdmenuView(HoofdMenuController controller){
		super(new BorderPane(), 600,  400); 		// Nieuwe pane meegeven aan de superklasse (dus scene).
		pane = (BorderPane) this.getRoot(); 	// Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
		this.controller = controller;
		initGui(); 		// InitGui om alle grafische elementen te initialiseren.
		InitAction();	// InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
	}

	public void initGui() {
		gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setStyle("-fx-background-image: url('/Assets/test.png')");
		
		home = new Label("HOME");
		home.setId("home");
		home.setPadding(new Insets(15));
		
		img = new Image("/Assets/back.png");
		back = new ImageView(img);
		back.setId("back");
		
		pane.setTop(home);
		pane.setAlignment(home, Pos.CENTER);
		//pane.setLeft(back);
		pane.setCenter(gridpane);
		
		pane.setStyle("-fx-background-image: url('/Assets/navlijntje.png')");
		
		links = new VBox(15);
		links.setPrefWidth(200);
		links.setPrefHeight(60);
		
		uren = new Button("UREN REGISTREREN");
		uren.setMinWidth(links.getPrefWidth());
		uren.setMinHeight(links.getPrefHeight());
		uren.setId("administratieKnoppen");
		
		week = new Button("WEEKOVERZICHT");
		week.setMinWidth(links.getPrefWidth());
		week.setMinHeight(links.getPrefHeight());
		week.setId("administratieKnoppen");
		
		links.getChildren().addAll(uren, week);
		
		rechts = new VBox(15);
		rechts.setPrefWidth(200);
		rechts.setPrefHeight(60);
		
		account = new Button("ACCOUNT TOEVOEGEN");
		account.setMinWidth(rechts.getPrefWidth());
		account.setMinHeight(rechts.getPrefHeight());
		account.setId("administratieKnoppen");
		
		personeel = new Button("PERSONEEL INFO");
		personeel.setMinWidth(rechts.getPrefWidth());
		personeel.setMinHeight(rechts.getPrefHeight());
		personeel.setId("administratieKnoppen");
		
		rechts.getChildren().addAll(account, personeel);
		
		hbox = new HBox(15);
		hbox.getChildren().addAll(links, rechts);
		
		getStylesheets().add("Views/styles.css");
		
		gridpane.add(hbox, 1, 0);
	}
	
	private void InitAction(){
		uren.setOnAction(e -> {
			//controller.setInvullenUrenView();
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


