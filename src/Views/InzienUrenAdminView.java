package Views;

import Controllers.InzienUrenAdminController;
import Models.IngevuldeTijdModel;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.time.format.DateTimeFormatter;

public class InzienUrenAdminView extends Scene {

    /**
     * Initialiseer constanten
     */
    private static final String KNOPSTRING = "Ververs";
    private static final double PANEWIDTH = 1200;
    private static final double PANEHEIGHT = 623;

    /**
     * Allocate space voor alle beeldobjecten.
     */
    private InzienUrenAdminController controller;
    private DatePicker begindatumPicker;
    private DatePicker einddatumPicker;
    private GridPane gridpane;
    private VBox datepickers;
    private Button goButton;

    /**
     * Maak de tabel en alle bijbehorende kolommen.
     */
    private TableView <IngevuldeTijdModel>overzichtTableView;
    private TableColumn <IngevuldeTijdModel, String> uuridColumn;
    private TableColumn <IngevuldeTijdModel, String> personeelidColumn;
    private TableColumn <IngevuldeTijdModel, String> begindatumColumn;
    private TableColumn <IngevuldeTijdModel, String> einddatumColumn;
    private TableColumn <IngevuldeTijdModel, String> begintijdColumn;
    private TableColumn <IngevuldeTijdModel, String> eindtijdColumn;
    private TableColumn <IngevuldeTijdModel, String> klantnaamColumn;
    private TableColumn <IngevuldeTijdModel, String> projectnaamColumn;
    private TableColumn <IngevuldeTijdModel, String> onderwerpnaamColumn;
    private TableColumn <IngevuldeTijdModel, String> commentaarColumn;
    private TableColumn <IngevuldeTijdModel, String> goedgekeurdColumn;

    /**
     * Maak de begin en einddatum.
     */
    private String begindatum;
    private String einddatum;

    /**
     * Maak de view aan. De controller wordt meegegeven, zodat de knoppen de logica aan de controller door kunnen geven.
     * @param controller
     */
    public InzienUrenAdminView(InzienUrenAdminController controller){

        /**
         * Initialiseer alle beeldobjecten.
         */
        super(new GridPane(), PANEWIDTH, PANEHEIGHT);
        this.controller = controller;
        gridpane = (GridPane) this.getRoot();
        begindatumPicker = new DatePicker();
        einddatumPicker = new DatePicker();
        overzichtTableView = new TableView();
        datepickers = new VBox(begindatumPicker, einddatumPicker);
        goButton = new Button(KNOPSTRING);

        /**
         * Zet alle beeldobjecten op de goede plek.
         */
        gridpane.setRowIndex(datepickers, 1);
        gridpane.setColumnIndex(datepickers, 1);

        gridpane.setRowIndex(overzichtTableView, 1);
        gridpane.setColumnIndex(overzichtTableView, 3);


        gridpane.setRowIndex(goButton, 2);
        gridpane.setColumnIndex(goButton, 3);

        overzichtTableView.setMinWidth(1000);           //Zet de grootte van de tabel.
        overzichtTableView.setMinHeight(600);


        /**
         * Wordt uitgevoerd wanneer de 'Ververs' knop wordt ingedrukt.
         */
        goButton.setOnAction(a ->{
            buttonPressed();
        });

        /**
         * Initialiseer de tabel.
         */
        uuridColumn = new TableColumn("Uur ID");
        personeelidColumn = new TableColumn("Personeel ID");
        begindatumColumn = new TableColumn("Begindatum");
        einddatumColumn = new TableColumn("Einddatum");
        begintijdColumn = new TableColumn("Begintijd");
        eindtijdColumn = new TableColumn("Eindtijd");
        klantnaamColumn = new TableColumn("Klant");
        projectnaamColumn = new TableColumn("Project");
        onderwerpnaamColumn = new TableColumn("Onderwerp");
        commentaarColumn = new TableColumn("Comentaar");
        goedgekeurdColumn = new TableColumn("Goedgekeurd");

        /**
         * Koppel de kollommen uit de tabel met de attributen uit IngevuldeTijdModel.
         */
        uuridColumn.setCellValueFactory(new PropertyValueFactory<>("uurId"));
        personeelidColumn.setCellValueFactory(new PropertyValueFactory<>("personeelID"));
        begindatumColumn.setCellValueFactory(new PropertyValueFactory<>("beginDatum"));
        einddatumColumn.setCellValueFactory(new PropertyValueFactory<>("eindDatum"));
        begintijdColumn.setCellValueFactory(new PropertyValueFactory<>("beginTijd"));
        eindtijdColumn.setCellValueFactory(new PropertyValueFactory<>("eindTijd"));
        klantnaamColumn.setCellValueFactory(new PropertyValueFactory<>("klant"));
        projectnaamColumn.setCellValueFactory(new PropertyValueFactory<>("project"));
        onderwerpnaamColumn.setCellValueFactory(new PropertyValueFactory<>("onderwerp"));
        commentaarColumn.setCellValueFactory(new PropertyValueFactory<>("commentaar"));
        goedgekeurdColumn.setCellValueFactory(new PropertyValueFactory<>("goedgekeurd"));

        overzichtTableView.getColumns().addAll(         //voeg alle gemaakte kolommen toe aan de tabel.
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
        overzichtTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        gridpane.getChildren().addAll(datepickers, overzichtTableView, goButton);               //Voegt alles toe aan de GridPane.
    }

    /**
     * Wordt uitgevoerd wanneer de "Ververs" knop wordt ingedrukt.
     */
    public void buttonPressed(){
        convertDates();
        controller.buttonPressed();
    }

    /**
     * Converteert de data uit de DataPickers naar 'YYYY-MM-DD' formaat en assigned ze aan de klassenvariabelen.
     */
    public void convertDates(){
        this.begindatum = begindatumPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.einddatum = einddatumPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Haalt alles uit de tabel. ontvangt een ObservableList van IngevuldeTijdModels en vult de tabel hiermee.
     * @param data
     */
    public void vulTabelUitLijst(ObservableList data){
        overzichtTableView.getItems().clear();
        overzichtTableView.setItems(data);

    }

    /**
     * Getters en setters
     */
    public String getBegindatum() {
        return begindatum;
    }

    public String getEinddatum() {
        return einddatum;
    }
}
