package Views;

import Controllers.AutoCompletionTextfieldController;
import Controllers.InzienUrenAdminController;
import Models.IngevuldeTijdModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InzienUrenAdminView extends Scene {

    /**
     * Initialiseer constanten
     */
    private static final String KNOPSTRING = "VERVERS";
    private static final String EXPORTSTRING = "EXPORTEER";
    private static final double PANEWIDTH = 1346;               //Grootte van de pane
    private static final double PANEHEIGHT = 781;

    private final double PERSONEELNAAMCOLUMNWIDTH = 150;               //Breedten van de kolommen.
    private final double BEGINDATUMCOLUMNWIDTH = 90;
    private final double EINDDATUMCOLUMNWIDTH = 90;
    private final double BEGINTIJCOLUMNDWIDTH = 90;
    private final double EINDTIJDCOLUMNWIDTH = 90;
    private final double KLANTNAAMCOLUMNWIDTH = 100;
    private final double PROJECTNAAMCOLUMNWIDTH = 100;
    private final double ONDERWERPNAAMCOLUMNWIDTH = 100;
    private final double COMMENTAARCOLUMNWIDTH = 350;
    private final double GOEDGEKEURDCOLUMNWIDTH = 80;

    private final double TABLEWIDTH = 1150;                 //Grootte van de tabel.
    private final int TABLEHEIGHT = 700;

    /**
     * init alle top field onderdelen
     */

    private BorderPane navigatie;
    private VBox img_box;
    private Image img;
    private ImageView terug;
    private Label inzienUrenLabel;
    private Label persoonLabel;
    private Image img2;
    private ImageView lijntje;
    private Image img3;
    private ImageView blauw_lijntje;

    /**
     * Allocate space voor alle beeldobjecten.
     */
    private InzienUrenAdminController controller;
    private DatePicker begindatumPicker;
    private DatePicker einddatumPicker;
    private BorderPane pane;
    private GridPane gridpane;
    private VBox leftFilterPanel;
    private Button verversKnop;
    private Button exporteerKnop;
    private VBox begindatumVbox;
    private VBox einddatumVbox;
    private Label einddatumLabel;
    private Label begindatumLabel;
    private Label klantLabel;
    private Label projectLabel;
    private Label onderwerpLabel;
    private VBox klantBox;
    private VBox projectBox;
    private VBox onderwerpBox;
    private AutoCompletionTextfieldController klantNaamInput;
    private AutoCompletionTextfieldController projectNaamInput;
    private AutoCompletionTextfieldController onderwerpNaamInput;

    /**
     * Maak de tabel en alle bijbehorende kolommen.
     */
    private TableView <IngevuldeTijdModel>overzichtTableView;
    private TableColumn <IngevuldeTijdModel, String> personeelNaamColumn;
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
        super(new BorderPane(), PANEWIDTH, PANEHEIGHT);
        pane = (BorderPane) this.getRoot();
        gridpane = new GridPane();
        
        pane.setId("pane");

        this.controller = controller;

        klantLabel = new Label("Klantnaam: ");
        klantLabel.setPadding(new Insets(0, 0, 5, 0));
        klantNaamInput = new AutoCompletionTextfieldController();
        klantNaamInput.setId("text-field-week");
        klantBox = new VBox(klantLabel, klantNaamInput);

        projectLabel = new Label("Projectnaam: ");
        projectLabel.setPadding(new Insets(0, 0, 5, 0));
        projectNaamInput = new AutoCompletionTextfieldController();
        projectNaamInput.setId("text-field-week");
        projectBox = new VBox(projectLabel, projectNaamInput);

        onderwerpLabel = new Label("Onderwerpnaam: ");
        onderwerpLabel.setPadding(new Insets(0,0,5,0));
        onderwerpNaamInput = new AutoCompletionTextfieldController();
        onderwerpNaamInput.setId("text-field-week");
        onderwerpBox = new VBox(onderwerpLabel, onderwerpNaamInput);
        onderwerpBox.setPadding(new Insets(0, 0, 20, 0));

        begindatumLabel = new Label("Begindatum: ");
        begindatumLabel.setPadding(new Insets(0, 0, 5, 0));
        begindatumPicker = new DatePicker();

        einddatumLabel = new Label("Einddatum: ");
        einddatumLabel.setPadding(new Insets(0, 0, 5, 0));
        einddatumPicker = new DatePicker();

        begindatumVbox = new VBox(begindatumLabel, begindatumPicker);
        einddatumVbox = new VBox(einddatumLabel, einddatumPicker);

        overzichtTableView = new TableView();

        verversKnop = new Button(KNOPSTRING);
        verversKnop.setId("button-week");
        exporteerKnop = new Button(EXPORTSTRING);
        exporteerKnop.setId("button-week");
       
        leftFilterPanel = new VBox(begindatumVbox, einddatumVbox, klantBox, projectBox,onderwerpBox, verversKnop, exporteerKnop);


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

        leftFilterPanel.setMaxWidth(160);
        leftFilterPanel.setSpacing(12);

        /**
         * init kop van view
         */

        navigatie = new BorderPane();

        img_box = new VBox();
        img = new Image("/Assets/back.png");
        terug = new ImageView(img);
        img_box.getChildren().add(terug);
        img_box.setPadding(new Insets(15, 0, 15, 15));

        inzienUrenLabel = new Label("INZIEN UREN");
        inzienUrenLabel.setId("home");
        inzienUrenLabel.setPadding(new Insets(15));

        persoonLabel = new Label();
        persoonLabel.setPadding(new Insets(15, 15, 15, 15));

        img2 = new Image("/Assets/lijntje.png");
        lijntje = new ImageView(img2);
        lijntje.setFitWidth(pane.getWidth());

        navigatie.setLeft(img_box);
        navigatie.setCenter(inzienUrenLabel);
        navigatie.setRight(persoonLabel);
        navigatie.setBottom(lijntje);

        pane.setTop(navigatie);

        /**
         * init action terug image
         */

        img_box.setOnMouseClicked(e -> {
            controller.getHoofdMenuController().startHoofdmenuView();
        });

        /**
         * voeg gridpane toe aan borderpane
         */
        pane.setCenter(gridpane);

        img3 = new Image("/Assets/blauwlijntje.png");

        blauw_lijntje = new ImageView(img3);
        blauw_lijntje.setFitWidth(pane.getWidth());

        pane.setBottom(blauw_lijntje);

        /**
         * Bepaal de breedte van alle knoppen in het linkerpanel.
         */

//        verversKnop.setPrefWidth(leftFilterPanel.getPrefWidth());
//        exporteerKnop.setPrefWidth(leftFilterPanel.getPrefWidth());
//        klantNaamInput.setPrefWidth(leftFilterPanel.getPrefWidth());
//        projectNaamInput.setPrefWidth(leftFilterPanel.getPrefWidth());
//        einddatumPicker.setPrefWidth(leftFilterPanel.getPrefWidth());
//        begindatumPicker.setPrefWidth(leftFilterPanel.getPrefWidth());


        /**
         * Bepaal wat de knoppen doen.
         */
        verversKnop.setOnAction(a ->{
            verversButtonPressed();
        });
        exporteerKnop.setOnAction(a ->{
            try {
				controller.writeExcel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        /**
         * ActionListener op de textfield van project. Op het moment dat deze is gefocussed haalt hij de projecten uit de database en zet hij ze in de dictionary van de AutoComplete.
         */
        projectNaamInput.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    controller.vulProjectenEntries(klantNaamInput.getText());
                }
                else
                {
                }
            }
        });

        /**
         * Actionlistener op de textfield van onderwerp. Op het moment dat deze is gefocussed haalt hij de onderwerpen uit de database en zet hij ze in de dictionary van de AutoComplete.
         */
        onderwerpNaamInput.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    controller.vulOnderwerpenEntries(projectNaamInput.getText());
                }
                else
                {
                }
            }
        });
        /**
         * Maakt de context menu voor wanneer er met de rechtermuisknop wordt gedrukt op een rij. Voegt de "Goedkeuren" optie er aan toe.
         */
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
        personeelNaamColumn = new TableColumn("Medewerker");
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
         * Stringvalues dienen gelijk te zijn aan de namen die voor de attributen in het model zijn gebruikt.
         * De gebruikte attributen dienen een getter en setter te hebben.
         */
        personeelNaamColumn.setCellValueFactory(new PropertyValueFactory<>("persoonNaam"));
        begindatumColumn.setCellValueFactory(new PropertyValueFactory<>("beginDatum"));
        einddatumColumn.setCellValueFactory(new PropertyValueFactory<>("eindDatum"));
        begintijdColumn.setCellValueFactory(new PropertyValueFactory<>("beginTijd"));
        eindtijdColumn.setCellValueFactory(new PropertyValueFactory<>("eindTijd"));
        klantnaamColumn.setCellValueFactory(new PropertyValueFactory<>("klantNaam"));
        projectnaamColumn.setCellValueFactory(new PropertyValueFactory<>("projectNaam"));
        onderwerpnaamColumn.setCellValueFactory(new PropertyValueFactory<>("onderwerpNaam"));
        commentaarColumn.setCellValueFactory(new PropertyValueFactory<>("commentaar"));
        goedgekeurdColumn.setCellValueFactory(new PropertyValueFactory<>("goedgekeurd"));

        /**
         * Configureer de breedte van de kolommen. Constante waarden staan bovenaan.
         */
        personeelNaamColumn.setMaxWidth(PERSONEELNAAMCOLUMNWIDTH);
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
                personeelNaamColumn,
                onderwerpnaamColumn,
                begintijdColumn,
                eindtijdColumn,
                begindatumColumn,
                einddatumColumn,
                klantnaamColumn,
                projectnaamColumn,
                commentaarColumn,
                goedgekeurdColumn);
        overzichtTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        gridpane.getChildren().addAll(leftFilterPanel, overzichtTableView);               //Voegt alles toe aan de GridPane.

        getStylesheets().add("Views/styles.css");


    }

    /**
     * Wordt uitgevoerd wanneer de "Ververs" knop wordt ingedrukt.
     */
    public void verversButtonPressed(){
        convertDates();
        controller.verversButtonPressed();
    }

    public void setKlanten(ArrayList<String> klanten) {
        klantNaamInput.getEntries().addAll(klanten);
    }

    public void setProjecten(ArrayList<String> projecten) {
        projectNaamInput.getEntries().addAll(projecten);

    }

    public void setOnderwerpen(ArrayList<String> onderwerpen) {
        onderwerpNaamInput.getEntries().addAll(onderwerpen);
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

    public String getKlantnaam(){
        System.out.println(klantNaamInput.getText());
        return this.klantNaamInput.getText();

    }

    public String getProjectnaam(){
        System.out.println(projectNaamInput.getText());
        return this.projectNaamInput.getText();
    }

    public void setPersoonLabel(String volledigeNaam) {
        persoonLabel.setText(volledigeNaam);
    }

    public String getOnderwerpnaam(){
        System.out.println(onderwerpNaamInput.getText());
        return onderwerpNaamInput.getText();
    }


}
