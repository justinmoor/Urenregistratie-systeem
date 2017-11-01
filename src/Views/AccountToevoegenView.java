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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AccountToevoegenView extends Scene {
		/**
		 * Initialiseren van alle benodigde onderdelen voor deze view
		 * @author stanhoenson
		 */
		private BorderPane pane;

		private BorderPane navigatie;
		    
		private StackPane left_pane;
		private VBox img_box;
		private Image img;
		private ImageView terug;
		    
		private StackPane mid_pane;
		private Label home;
		    
		private StackPane right_pane;
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
	    private Label fout;
	    
	    /**
	     * Initialiseren van de bijhorende controller
	     */
		private GebruikerToevoegenController controller;

		/**
	     * Maakt de view aan. Ook wordt de controller meegegeven, zodat de controller de logica van de knoppen regelt
	     * @param controller
	     * @author stanhoenson
	     */ 	
	public AccountToevoegenView(GebruikerToevoegenController controller){
		super(new BorderPane(), 600,  400); 			// Nieuwe pane meegeven aan de superklasse (dus scene).
		pane = (BorderPane) this.getRoot(); 			// Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
		this.controller = controller;
		// Controller zaken, moeten we nog overleggen.

		initGui(); 			// InitGui om alle grafische elementen te initialiseren.
		InitAction();		// InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
	}

	/**
	 * Haalt de volledigenaam op van de huidige gebruiker
	 * @param volledigenaam
	 * @author stanhoenson
	 */
	public void setGebruikerLabel(String volledigenaam){
		gebruiker.setText(volledigenaam);
	}

	/**
	 * Maken en plaatsen van alle onderdelen voor de GUI
	 * @author stanhoenson
	 */
	public void initGui() {
		pane.setId("pane");
		
		navigatie = new BorderPane();
		fout = new Label("");
		left_pane = new StackPane();
		left_pane.setMinWidth(140);
		left_pane.setPadding(new Insets(15, 0, 15, 15));
		img_box = new VBox();
		img = new Image("/Assets/back.png");
		terug = new ImageView(img);
		img_box.getChildren().add(terug);
		left_pane.getChildren().add(img_box);
		
		mid_pane = new StackPane();
		mid_pane.setPrefWidth(320);
		home = new Label("ACCOUNT TOEVOEGEN");
		home.setId("home");
		mid_pane.getChildren().add(home);
		
		right_pane = new StackPane();
		right_pane.setPrefWidth(140);
		gebruiker = new Label();
		right_pane.getChildren().add(gebruiker);
		
		img2 = new Image("/Assets/lijntje.png");
		lijntje = new ImageView(img2);
		lijntje.setFitWidth(600);
		
		navigatie.setLeft(left_pane);
		navigatie.setCenter(mid_pane);
		navigatie.setRight(right_pane);
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
		groep.getChildren().addAll(voornaam, tussenvoegsel, achternaam, email, rechten, vbox_lijntje, toevoegen, fout);
		groep.setPadding(new Insets(0, 0, 16, 0));
		
		img3 = new Image("/Assets/blauwlijntje.png");
		blauw_lijntje = new ImageView(img3);
		blauw_lijntje.setFitWidth(600);
		
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

	/**
     * Methode waar acties worden meegegeven aan verschillende knoppen of andere onderdelen uit de view
     */
	private void InitAction(){
		toevoegen.setOnAction(e -> {
		    if(controller.checkVelden(tf1.getText(), tf3.getText(), tf4.getText())) {
                controller.insert(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), cb1.getValue().toString());
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                cb1.setValue("Personeel");
            } else {
		        fout.setText("Niet alle velden zijn ingevuld!");
            }

		});
		
		terug.setOnMouseClicked(e -> {
    	 	controller.getHoofdMenuController().startHoofdmenuView();
    });
	}
}

