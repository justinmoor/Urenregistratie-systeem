package Views;

import Controllers.HoofdMenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PersoneelHoofdmenuView extends Scene {
	private BorderPane pane;
    
    private BorderPane navigatie;
    
    private VBox img_box;
    private Image img;
    private ImageView terug;
    
    private Label home;
    private Label gebruiker;
    
    private Image img2;
    private ImageView lijntje;
    
    private VBox knoppen;
    private Button uren;
    private Button week;
		
	private HoofdMenuController controller;
	
	public PersoneelHoofdmenuView(HoofdMenuController controller){
		super(new BorderPane(), 600,  400); 		// Nieuwe pane meegeven aan de superklasse (dus scene).
		pane = (BorderPane) this.getRoot(); 		// Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
		this.controller = controller; 			// Controller zaken, moeten we nog overleggen.

		initGui(); 			// InitGui om alle grafische elementen te initialiseren.
		InitAction();		// InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
	}

	public void initGui() {
		pane.setStyle("-fx-background-image: url('/assets/background.png')");
		
		navigatie = new BorderPane();
		
		img_box = new VBox();
		img = new Image("/assets/back.png");
		terug = new ImageView(img);
		img_box.getChildren().add(terug);
		img_box.setPadding(new Insets(15, 83, 15, 14));
		
		home = new Label("HOME");
		home.setId("home");
		home.setPadding(new Insets(15));
		
		gebruiker = new Label("Peter van Vliet");
		gebruiker.setPadding(new Insets(15, 14, 15, 15));
		
		img2 = new Image("/assets/lijntje.png");
		lijntje = new ImageView(img2);
		lijntje.setFitWidth(600);
		
		navigatie.setLeft(img_box);
		navigatie.setCenter(home);
		navigatie.setRight(gebruiker);
		navigatie.setBottom(lijntje);

		knoppen = new VBox(19);
		
		uren = new Button("UREN REGISTREREN");
		uren.setId("knoppen");
		week = new Button("WEEKOVERZICHT");
		week.setId("knoppen");
		
		knoppen.getChildren().addAll(uren, week);
		
		pane.setTop(navigatie);
		pane.setCenter(knoppen);
		knoppen.setAlignment(Pos.CENTER);
    
		getStylesheets().add("Views/styles.css");
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

