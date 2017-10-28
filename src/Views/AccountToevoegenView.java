package Views;

import Controllers.GebruikerToevoegenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
	
	    private HBox voornaam;
	    private Label lbl1;
	    private TextField tf1;
	    
	    private HBox tussenvoegsel;
	    private Label lbl2;
	    private TextField tf2;
	    
	    private HBox achternaam;
	    private Label lbl3;
	    private TextField tf3;
	    
	    private HBox email;
	    private Label lbl4;
	    private TextField tf4;
	    
	    private HBox rechten;
		private Label lbl5;
		private ComboBox cb1;
		
		private Image img4;
		private ImageView vbox_lijntje;
			
		private Button toevoegen;
		
		private Image img3;
	    private ImageView blauw_lijntje; 
	    
		private GebruikerToevoegenController controller;

	public AccountToevoegenView(GebruikerToevoegenController controller){
		super(new BorderPane(), 600,  400); 			// Nieuwe pane meegeven aan de superklasse (dus scene).
		pane = (BorderPane) this.getRoot(); 			// Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
		this.controller = controller;
		// Controller zaken, moeten we nog overleggen.

		initGui(); 			// InitGui om alle grafische elementen te initialiseren.
		InitAction();		// InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
	}

	public void setGebruikerLabel(String volledigenaam){
		gebruiker.setText(volledigenaam);
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
		home.setPadding(new Insets(15, 0, 15, 15));
		
		gebruiker = new Label();
		gebruiker.setPadding(new Insets(15, 14, 15, 15));
		
		img2 = new Image("/Assets/lijntje.png");
		lijntje = new ImageView(img2);
		lijntje.setFitWidth(600);
		
		navigatie.setLeft(img_box);
		navigatie.setCenter(home);
		navigatie.setRight(gebruiker);
		navigatie.setBottom(lijntje);
		
		groep = new VBox(12);

		voornaam = new HBox(43);
		lbl1 = new Label("Voornaam:");
		tf1 = new TextField();
		voornaam.getChildren().addAll(lbl1, tf1);
			
		tussenvoegsel = new HBox(16);
		lbl2 = new Label("Tussenvoegsel:");
		tf2 = new TextField();
		tussenvoegsel.getChildren().addAll(lbl2, tf2);
		
		achternaam = new HBox(35);
		lbl3 = new Label("Achternaam:");
		tf3 = new TextField();
		achternaam.getChildren().addAll(lbl3, tf3);
		
		email = new HBox(38);
		lbl4 = new Label("E-Mail:");
		tf4 = new TextField();
		tf4.setId("email_text-field");
		email.getChildren().addAll(lbl4, tf4);
		
		img4 = new Image("/Assets/lijntje.png");
		vbox_lijntje = new ImageView(img4);
		vbox_lijntje.setFitWidth(259);
		
		rechten = new HBox(26);
		lbl5 = new Label("Rechten:");
		cb1 = new ComboBox();
		cb1.getItems().addAll("Personeel", "Administratie");
		cb1.setValue("Personeel");
		rechten.getChildren().addAll(lbl5, cb1);
					
		toevoegen = new Button("TOEVOEGEN");
		toevoegen.setId("toevoegen");

		groep = new VBox(12);
		groep.getChildren().addAll(voornaam, tussenvoegsel, achternaam, email, rechten, vbox_lijntje, toevoegen);
		groep.setPadding(new Insets(0, 0, 16, 0));
		
		img3 = new Image("/Assets/blauwlijntje.png");
		blauw_lijntje = new ImageView(img3);
		blauw_lijntje.setFitWidth(612);
		
		pane.setTop(navigatie);
		
		pane.setCenter(groep);
		groep.setAlignment(Pos.CENTER);
		voornaam.setAlignment(Pos.CENTER);
		tussenvoegsel.setAlignment(Pos.CENTER);
		achternaam.setAlignment(Pos.CENTER);
		email.setAlignment(Pos.CENTER);
		rechten.setAlignment(Pos.CENTER);
		
		pane.setBottom(blauw_lijntje);
		
		getStylesheets().add("Views/styles.css");
	}

	//Functionele zaken initialiseren.
	private void InitAction(){
		toevoegen.setOnAction(e -> {
			controller.insert(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), cb1.getValue().toString());

		});
		
		terug.setOnMouseClicked(e -> {
    	 	controller.getHoofdMenuController().startHoofdmenuView();
    });
	}
}

