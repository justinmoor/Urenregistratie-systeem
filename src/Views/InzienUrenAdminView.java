package Views;

import Controllers.InzienUrenAdminController;
import Models.IngevuldeTijdModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InzienUrenAdminView extends Scene {

    /**
     * Initialiseer constanten
     */
    private static final String KNOPSTRING = "Ververs";
    private static final double PANEWIDTH = 1400;               //Grootte van de pane
    private static final double PANEHEIGHT = 800;

    private final double PERSONEELIDCOLUMNWIDTH = 30;               //Breedten van de kolommen.
    private final double BEGINDATUMCOLUMNWIDTH = 90;
    private final double EINDDATUMCOLUMNWIDTH = 90;
    private final double BEGINTIJCOLUMNDWIDTH = 90;
    private final double EINDTIJDCOLUMNWIDTH = 90;
    private final double KLANTNAAMCOLUMNWIDTH = 100;
    private final double PROJECTNAAMCOLUMNWIDTH = 100;
    private final double ONDERWERPNAAMCOLUMNWIDTH = 100;
    private final double UURIDCOLUMNWIDTH = 30;
    private final double COMMENTAARCOLUMNWIDTH = 350;
    private final double GOEDGEKEURDCOLUMNWIDTH = 80 ;

    private final double TABLEWIDTH = 1150;                 //Grootte van de tabel.
    private final int TABLEHEIGHT = 780;

    /**
     * Allocate space voor alle beeldobjecten.
     */
    private InzienUrenAdminController controller;
    private DatePicker begindatumPicker;
    private DatePicker einddatumPicker;
    private GridPane gridpane;
    private VBox leftFilterPanel;
    private Button goButton;
    private VBox begindatumVbox;
    private VBox einddatumVbox;
    private Label einddatumLabel;
    private Label begindatumLabel;

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
     * Maak het contextmenu voor het goedkeuren van de uren, en de opties.
     */
    private ContextMenu contextMenuRightClick;
    private MenuItem keurGoed;

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
        einddatumLabel = new Label("Einddatum: ");
        begindatumLabel = new Label("Begindatum: ");

        einddatumPicker = new DatePicker();
        begindatumPicker = new DatePicker();

        begindatumVbox = new VBox(begindatumLabel, begindatumPicker);
        einddatumVbox = new VBox(einddatumLabel, einddatumPicker);

        this.controller = controller;

        gridpane = (GridPane) this.getRoot();

        overzichtTableView = new TableView();

        goButton = new Button(KNOPSTRING);

        leftFilterPanel = new VBox(begindatumVbox, einddatumVbox, goButton);


        /**
         * Zet alle beeldobjecten op de goede plek.
         */
        gridpane.setRowIndex(leftFilterPanel, 1);
        gridpane.setColumnIndex(leftFilterPanel, 1);
        gridpane.setHgap(12);
        gridpane.setVgap(12);
        gridpane.setRowIndex(overzichtTableView, 1);
        gridpane.setColumnIndex(overzichtTableView, 2);

        overzichtTableView.setMinWidth(TABLEWIDTH);           //Zet de grootte van de tabel.
        overzichtTableView.setMinHeight(TABLEHEIGHT);

        leftFilterPanel.setPrefWidth(200);
        leftFilterPanel.setSpacing(12);
        goButton.setPrefWidth(leftFilterPanel.getPrefWidth());
        einddatumPicker.setPrefWidth(leftFilterPanel.getPrefWidth());
        begindatumPicker.setPrefWidth(leftFilterPanel.getPrefWidth());

        /**
         * Wordt uitgevoerd wanneer de 'Ververs' knop wordt ingedrukt.
         */
        goButton.setOnAction(a ->{
            buttonPressed();
        });
         contextMenuRightClick = new ContextMenu();
        keurGoed = new MenuItem("Goedkeuren");

        keurGoed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final ArrayList<IngevuldeTijdModel> aangevinkteRijen = new ArrayList<IngevuldeTijdModel>(overzichtTableView.getSelectionModel().getSelectedItems());
                controller.keurGoed(aangevinkteRijen);
            }
        });
        contextMenuRightClick.getItems().add(keurGoed);
        overzichtTableView.setContextMenu(contextMenuRightClick);
        overzichtTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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

        /**
         * Configureer de breedte van de kolommen. Constante waarden staan bovenaan.
         */
        uuridColumn.setMaxWidth(UURIDCOLUMNWIDTH);
        personeelidColumn.setMaxWidth(PERSONEELIDCOLUMNWIDTH);
        begindatumColumn.setMaxWidth(BEGINDATUMCOLUMNWIDTH);
        einddatumColumn.setMaxWidth(EINDDATUMCOLUMNWIDTH);
        begintijdColumn.setMaxWidth(BEGINTIJCOLUMNDWIDTH);
        eindtijdColumn.setMaxWidth(EINDTIJDCOLUMNWIDTH);
        klantnaamColumn.setMaxWidth(KLANTNAAMCOLUMNWIDTH);
        projectnaamColumn.setMaxWidth(PROJECTNAAMCOLUMNWIDTH);
        onderwerpnaamColumn.setMaxWidth(ONDERWERPNAAMCOLUMNWIDTH);
        commentaarColumn.setMaxWidth(COMMENTAARCOLUMNWIDTH);
        goedgekeurdColumn.setMaxWidth(GOEDGEKEURDCOLUMNWIDTH);

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

        gridpane.getChildren().addAll(leftFilterPanel, overzichtTableView);               //Voegt alles toe aan de GridPane.

        /**
         * Zorgt ervoor dat wanneer er op de ESCAPE key wordt gedrukt dat er teruggegaan wordt naar het hoofdmenu.
         */
        this.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
                () {
            @Override
            public void handle(KeyEvent t) {
                if(t.getCode()== KeyCode.ESCAPE)
                {
                    backToHomeScreen();
                }
            }
        });
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

    private void backToHomeScreen(){
        controller.backToHomeScreen();
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
