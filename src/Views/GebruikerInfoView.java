package Views;

import Controllers.GebruikerInfoController;
import Controllers.HoofdMenuController;
import Models.GebruikerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GebruikerInfoView extends Scene {
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

    private GebruikerInfoController controller;

  //  private HoofdMenuController controller;

    public GebruikerInfoView(GebruikerInfoController controller){
        super(new VBox(), 1200, 700);
        box = (VBox) this.getRoot();
        this.controller = controller;
        initGui();
    }

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
        table.setPadding(new Insets(10));
        
        table_box.getChildren().add(table);
        table_box.setPadding(new Insets(10));

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

        InitTable();
    }

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

}
