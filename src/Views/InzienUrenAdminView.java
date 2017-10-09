package Views;

import Controllers.InzienUrenAdminController;
import Models.IngevuldeTijdModel;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.time.format.DateTimeFormatter;

public class InzienUrenAdminView extends Scene {
    private InzienUrenAdminController controller;
    private DatePicker begindatumPicker;
    private DatePicker einddatumPicker;
    private GridPane gridpane;
    public javafx.scene.control.TableView overzichtTableView;
    private VBox datepickers;
    private Button goButton;
    private String begindatum;
    private String einddatum;

    public InzienUrenAdminView(InzienUrenAdminController controller){
        /**
         * Initialiseer alle beeldobjecten.
         */
        super(new GridPane(), 1200, 623);
        this.controller = controller;
        gridpane = (GridPane) this.getRoot();
        begindatumPicker = new DatePicker();
        einddatumPicker = new DatePicker();
        overzichtTableView = new javafx.scene.control.TableView();
        datepickers = new VBox(begindatumPicker, einddatumPicker);
        goButton = new Button("Ververs");

        gridpane.setRowIndex(datepickers, 1);
        gridpane.setColumnIndex(datepickers, 1);

        gridpane.setRowIndex(overzichtTableView, 1);
        gridpane.setColumnIndex(overzichtTableView, 3);


        gridpane.setRowIndex(goButton, 2);
        gridpane.setColumnIndex(goButton, 3);

        overzichtTableView.setMinWidth(1000);
        overzichtTableView.setMinHeight(600);


        /**
         * Vertel de button wat zijn taak is.
         */
        goButton.setOnAction(a ->{
            buttonPressed();
        });



        gridpane.getChildren().addAll(datepickers, overzichtTableView, goButton);
    }


    public String getBegindatum() {
        return begindatum;
    }

    public String getEinddatum() {
        return einddatum;
    }
    public void buttonPressed(){
        convertDates();
        System.out.println("convert dates");
        controller.buttonPressed();
        System.out.println(begindatum);
        System.out.println(einddatum);

    }
    public void convertDates(){
        this.begindatum = begindatumPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.einddatum = einddatumPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setData(ObservableList data){
        overzichtTableView.setItems(data);

        TableColumn uuridColumn = new TableColumn("Uur ID");
        uuridColumn.setCellValueFactory(
                new PropertyValueFactory<IngevuldeTijdModel, String>("uurId"));

        TableColumn personeelidColumn = new TableColumn("Personeel ID");
        personeelidColumn.setCellValueFactory(
                new PropertyValueFactory<IngevuldeTijdModel, String>("personeelID"));

        TableColumn begindatumColumn = new TableColumn("Begindatum");
        begindatumColumn.setCellValueFactory(
                new PropertyValueFactory<IngevuldeTijdModel, String>("beginDatum"));

        TableColumn einddatumColumn = new TableColumn("Einddatum");
        einddatumColumn.setCellValueFactory(
                new PropertyValueFactory<IngevuldeTijdModel, String>("eindDatum"));

        TableColumn begintijdColumn = new TableColumn("Begintijd");
        begindatumColumn.setCellValueFactory(
                new PropertyValueFactory<IngevuldeTijdModel, String>("beginTijd"));

        TableColumn eindtijdColumn = new TableColumn("Eindtijd");
        einddatumColumn.setCellValueFactory(
                new PropertyValueFactory<IngevuldeTijdModel, String>("eindTijd"));

        TableColumn klantnaamColumn = new TableColumn("Klant");
        klantnaamColumn.setCellValueFactory(
                new PropertyValueFactory<IngevuldeTijdModel, String>("klant"));

        TableColumn projectnaamColumn = new TableColumn("Project");
        projectnaamColumn.setCellValueFactory(
                new PropertyValueFactory<IngevuldeTijdModel, String>("project"));

        TableColumn onderwerpnaamColumn = new TableColumn("Onderwerp");
        onderwerpnaamColumn.setCellValueFactory(
                new PropertyValueFactory<IngevuldeTijdModel, String>("onderwerp"));

        TableColumn commentaarColumn = new TableColumn("Comentaar");
        commentaarColumn.setCellValueFactory(
                new PropertyValueFactory<IngevuldeTijdModel, String>("commentaar"));

        TableColumn goedgekeurdColumn = new TableColumn("Goedgekeurd");
        goedgekeurdColumn.setCellValueFactory(
                new PropertyValueFactory<IngevuldeTijdModel, String>("goedgekeurd"));

        overzichtTableView.getColumns().addAll(
                uuridColumn,
                personeelidColumn,
                begindatumColumn,
                einddatumColumn,
                begintijdColumn,
                eindtijdColumn,
                klantnaamColumn,
                projectnaamColumn,
                onderwerpnaamColumn,
                commentaarColumn,
                goedgekeurdColumn);
    }
}
