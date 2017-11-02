package Views;

import Controllers.GebruikerInfoController;
import Models.GebruikerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * De view die alle gebruikers in de database laad zien
 *
 * @author Justin Moor
 * @author Stan Hoenson
 *
 * @version 3.0
 */

public class GebruikerInfoView extends Scene {
	/**
	 * Initialiseren van alle benodigde onderdelen voor deze view
	 */
	private BorderPane pane;
	
	private BorderPane navigatie;
	
	private VBox img_box;
    private Image img;
    private ImageView terug;
    
    private Label home;
    private Label gebruiker;
    
    private Image img2;
    private ImageView lijntje;
	
    private VBox table_box;
    
    private TableView <GebruikerModel> table;
    private TableColumn <GebruikerModel, String> naam;
    private TableColumn <GebruikerModel, String> tussenvoegsel;
    private TableColumn <GebruikerModel, String> achternaam;
    private TableColumn <GebruikerModel, String> email;
    private TableColumn <GebruikerModel, String> rechten;
    
    private Image img3;
    private ImageView blauw_lijntje;
    private TableColumn <GebruikerModel, String> werkzaam;

    /**
     * Initialiseren van de bijhorende controller
     */
    private GebruikerInfoController controller;

    private ContextMenu context;
    private MenuItem inActief;
    private MenuItem actief;

  //  private HoofdMenuController controller;

    /**
     * Maakt de view aan. Ook wordt de controller meegegeven, zodat de controller de logica van de knoppen regelt
     * @param controller
     */ 	
    public GebruikerInfoView(GebruikerInfoController controller){
        super(new BorderPane(), 900, 600);
        pane = (BorderPane) this.getRoot();
        this.controller = controller;
        initGui();
        InitAction();
    }

    /**
	 * Maken en plaatsen van alle onderdelen voor de GUI
	 */
    private void initGui(){
        pane.setId("pane");
		
		navigatie = new BorderPane();
		
		img_box = new VBox();
		img = new Image("/Assets/back.png");
		terug = new ImageView(img);
		img_box.getChildren().add(terug);
		img_box.setPadding(new Insets(15, 83, 15, 14));
		
		home = new Label("PERSONEEL INFO");
		home.setId("home");
		home.setPadding(new Insets(15));
		
		gebruiker = new Label("Peter van Vliet");
		gebruiker.setPadding(new Insets(15, 14, 15, 15));
		
		img2 = new Image("/Assets/lijntje.png");
		lijntje = new ImageView(img2);
		lijntje.setFitWidth(900);
		
		navigatie.setLeft(img_box);
		navigatie.setCenter(home);
		navigatie.setRight(gebruiker);
		navigatie.setBottom(lijntje);

		table_box = new VBox();
		
        table = new TableView();
        table.setEditable(false);
        
        table_box.getChildren().add(table);

        naam = new TableColumn("Voornaam");
        tussenvoegsel = new TableColumn("Tussenvoegsel");
        achternaam = new TableColumn("Achternaam");
        email = new TableColumn("Email");
        rechten = new TableColumn("Rechten");
        werkzaam = new TableColumn("Werkzaam");

        werkzaam.setEditable(true);

        table.getColumns().addAll(naam, tussenvoegsel, achternaam, email, rechten, werkzaam);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPadding(new Insets(10));
        
        navigatie.setPadding(new Insets(0, 0, 10, 0));
        
        img3 = new Image("/Assets/blauwlijntje.png");
		blauw_lijntje = new ImageView(img3);
		blauw_lijntje.setFitWidth(900);
		
		pane.setBottom(blauw_lijntje);
        pane.setTop(navigatie);
		pane.setCenter(table);
		
		getStylesheets().add("Views/styles.css");

		context = new ContextMenu();
		inActief = new MenuItem("Inactief");
		actief = new MenuItem("Actief");

		context.getItems().addAll(inActief, actief);
		table.setContextMenu(context);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        InitTable();
    }

    /**
     * Methode waar acties worden meegegeven aan verschillende knoppen of andere onderdelen uit de view
     */
    private void InitAction(){
        img_box.setOnMousePressed(e -> {
            controller.getHoofdMenuController().startHoofdmenuView();
         });

        inActief.setOnAction(e -> {
            final ArrayList<GebruikerModel> rijen = new ArrayList<>(table.getSelectionModel().getSelectedItems());
            controller.setNietWerkzaam(rijen);
            InitTable();
        });

        actief.setOnAction(e -> {
            final ArrayList<GebruikerModel> rijen = new ArrayList<>(table.getSelectionModel().getSelectedItems());
            controller.setWerkzaam(rijen);
            InitTable();
        });

	}


    /**
     * Initialiseert de tabel
     */
    private void InitTable(){
        ObservableList<GebruikerModel> gebruikers = FXCollections.observableList(controller.getGebruikers());

        table.setItems(gebruikers);

        naam.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        tussenvoegsel.setCellValueFactory(new PropertyValueFactory<>("tussenvoegsel"));
        achternaam.setCellValueFactory(new PropertyValueFactory<>("achternaam"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        rechten.setCellValueFactory(new PropertyValueFactory<>("rechten"));

        werkzaam.setCellValueFactory(new PropertyValueFactory<>("werkzaam"));

    }

    public void setGebruiker() {
        gebruiker.setText(controller.getHoofdMenuController().getGebruikerModel().getVolledigeNaam());
    }
}
