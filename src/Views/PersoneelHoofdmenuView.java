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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * De view voor personeel om makkelijk mee te navigeren door de applicatie heen
 *
 * @author Stan Hoenson
 * @author Justin Moor
 * @author Alex de Bruin
 *
 * @version 3.0
 */

public class PersoneelHoofdmenuView extends Scene {
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
    
    private VBox knoppen;
    private Button uren;
    private Button week;
    
    private Image img3;
    private ImageView blauw_lijntje;
	
    /**
     * Initialiseren van de bijhorende controller
     * @author stanhoenson
     */
	private HoofdMenuController controller;
	
	/**
     * Maakt de view aan. Ook wordt de controller meegegeven, zodat de controller de logica van de knoppen regelt
     * @param controller
     * @author stanhoenson
     */ 
	public PersoneelHoofdmenuView(HoofdMenuController controller){
		super(new BorderPane(), 600,  400); 		// Nieuwe pane meegeven aan de superklasse (dus scene).
		pane = (BorderPane) this.getRoot(); 		// Deze nieuwe gridpane van de superklasse wordt de gridpane die hier gebruikt wordt.
		this.controller = controller; 			// Controller zaken, moeten we nog overleggen.

		initGui(); 			// InitGui om alle grafische elementen te initialiseren.
		InitAction();		// InitAction om alle functionaliteiten te initialiseren, denk aan knop.setOnAction() etc.
	}

	/**
	 * Maken en plaatsen van alle onderdelen voor de GUI
	 * @author stanhoenson
	 */
	public void initGui() {
		pane.setId("pane");
		
		navigatie = new BorderPane();
		
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
		home = new Label("HOME");
		home.setId("home");
		mid_pane.getChildren().add(home);
		
		right_pane = new StackPane();
		right_pane.setPrefWidth(140);
		gebruiker = new Label(controller.getGebruikerModel().getVolledigeNaam());
		right_pane.getChildren().add(gebruiker);
		
		img2 = new Image("/Assets/lijntje.png");
		lijntje = new ImageView(img2);
		lijntje.setFitWidth(600);
		
		navigatie.setLeft(left_pane);
		navigatie.setCenter(mid_pane);
		navigatie.setRight(right_pane);
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
		
		img3 = new Image("/Assets/blauwlijntje.png");
		blauw_lijntje = new ImageView(img3);
		blauw_lijntje.setFitWidth(600);
		pane.setBottom(blauw_lijntje);
    
		getStylesheets().add("Views/styles.css");
	}
	
	/**
     * Methode waar acties worden meegegeven aan verschillende knoppen of andere onderdelen uit de view
     * @author stanhoenson
     */
	private void InitAction(){
		uren.setOnAction(e -> {
			controller.startInvullenUrenView();
		});
		week.setOnAction(e -> {
			controller.startInzienUrenView();
		});

		img_box.setOnMouseClicked(e -> {
			controller.getLoginController().logUit();
		});

		gebruiker.setOnMouseClicked(e -> {
			controller.startAccountInfoView();
		});
	}
}

