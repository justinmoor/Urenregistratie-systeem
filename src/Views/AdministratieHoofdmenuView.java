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
	private BorderPane pane;
    
    private BorderPane navigatie;
    
    private VBox img_box;
    private Image img;
    private ImageView terug;
    
    private Label home;
    private Label gebruiker;
    
    private Image img2;
    private ImageView lijntje;
    
    private HBox knoppen;
    
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
		pane.setStyle("-fx-background-image: url('/Assets/background.png')");
		
		navigatie = new BorderPane();
		
		img_box = new VBox();
		img = new Image("/Assets/back.png");
		terug = new ImageView(img);
		img_box.getChildren().add(terug);
		img_box.setPadding(new Insets(15, 83, 15, 14));
		
		home = new Label("HOME");
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

		knoppen = new HBox(19);
		
		links = new VBox(19);
		uren = new Button("UREN REGISTREREN");
		uren.setId("knoppen2");
		week = new Button("WEEKOVERZICHT");
		week.setId("knoppen2");
		links.getChildren().addAll(uren, week);
		
		rechts = new VBox(19);
		account = new Button("ACCOUNT TOEVOEGEN");
		account.setId("knoppen2");
		personeel = new Button("PERSONEEL INFO");
		personeel.setId("knoppen2");
		rechts.getChildren().addAll(account, personeel);
		
		knoppen.getChildren().addAll(links, rechts);
		knoppen.setPadding(new Insets(80, 0, 0, 0));
		
		pane.setTop(navigatie);
		pane.setCenter(knoppen);
		knoppen.setAlignment(Pos.CENTER);
    
    		getStylesheets().add("Views/styles.css");
	}
	
	private void InitAction(){
		uren.setOnAction(e -> {
	//		controller.setInvullenUrenView();
		});
		week.setOnAction(e -> {
			controller.startInzienUrenAdminController();
		});
		account.setOnAction(e -> {
			controller.setAccountToevoegenView();
		});
		personeel.setOnAction(e -> {
			controller.setPersoneelInfoView();
		});
	}

}


