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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GebruikerInfoView extends Scene {

    private VBox box;
    private Label personeelsInfo;

    private TableView <GebruikerModel> table;
    private TableColumn <GebruikerModel, String> naam;
    private TableColumn <GebruikerModel, String> tussenvoegsel;
    private TableColumn <GebruikerModel, String> achternaam;
    private TableColumn <GebruikerModel, String> email;
    private TableColumn <GebruikerModel, String> rechten;

    private GebruikerInfoController controller;

  //  private HoofdMenuController controller;

    public GebruikerInfoView(GebruikerInfoController controller){
        super(new VBox(), 600, 500);
        box = (VBox) this.getRoot();
        this.controller = controller;
        initGui();
    }

    private void initGui(){
        personeelsInfo = new Label("Personeels info");

        table = new TableView();
        table.setEditable(false);

        naam = new TableColumn("Voornaam");
        tussenvoegsel = new TableColumn("Tussenvoegsel");
        achternaam = new TableColumn("Achternaam");
        email = new TableColumn("Email");
        rechten = new TableColumn("Rechten");

        table.getColumns().addAll(naam, tussenvoegsel, achternaam, email, rechten);

        box.setPadding(new Insets(10, 10, 10, 10));
        box.getChildren().addAll(personeelsInfo, table);

        InitTable();
    }

    private void InitTable(){
        ObservableList<GebruikerModel> gebruikers = FXCollections.observableList(controller.getGebruikers());

        table.setItems(gebruikers);

        naam.setCellValueFactory(
                new PropertyValueFactory<>("voornaam"));

        tussenvoegsel.setCellValueFactory(
                new PropertyValueFactory<>("tussenvoegsel"));

        achternaam.setCellValueFactory(
                new PropertyValueFactory<>("achternaam"));

        email.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        rechten.setCellValueFactory(
                new PropertyValueFactory<>("rechten"));


    }

}
