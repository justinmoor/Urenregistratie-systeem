package Views;

import Controllers.AutoCompletionTextfieldController;
import Controllers.InzienUrenController;
import Models.IngevuldeTijdModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * De view die een overzicht geeft van alle gemaakte uren van de medewerker die is ingelogd
 *
 * @author Ian Beemsterboer
 * @author Stan Hoenson
 *
 * @version 3.0
 */

public class InzienUrenView extends Scene {

    /**
     * Initialiseer constanten
     */
    private static final String KNOPSTRING = "VERVERS";
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
    private InzienUrenController controller;
    private DatePicker begindatumPicker;
    private DatePicker einddatumPicker;
    private BorderPane pane;
    private GridPane gridpane;
    private VBox leftFilterPanel;
    private Button verversKnop;
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
    private TableView<IngevuldeTijdModel> overzichtTableView;
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
    public InzienUrenView(InzienUrenController controller){

        /**
         * Initialiseer alle beeldobjecten.
         */
        super(new BorderPane(), PANEWIDTH, PANEHEIGHT);
        pane = (BorderPane) this.getRoot();
        gridpane = new GridPane();
        
        pane.setId("pane");

        this.controller = controller;

        klantLabel = new Label("Klantnaam: ");
        klantNaamInput = new AutoCompletionTextfieldController();
        klantNaamInput.setId("text-field-week");
        klantBox = new VBox(klantLabel, klantNaamInput);

        projectLabel = new Label("Projectnaam: ");
        projectNaamInput = new AutoCompletionTextfieldController();
        projectNaamInput.setId("text-field-week");
        projectBox = new VBox(projectLabel, projectNaamInput);

        onderwerpLabel = new Label("Onderwerpnaam: ");
        onderwerpNaamInput = new AutoCompletionTextfieldController();
        onderwerpNaamInput.setId("text-field-week");
        onderwerpBox = new VBox(onderwerpLabel, onderwerpNaamInput);
        onderwerpBox.setPadding(new Insets(0, 0, 20, 0));

        begindatumLabel = new Label("Begindatum: ");
        begindatumPicker = new DatePicker();

        einddatumLabel = new Label("Einddatum: ");
        einddatumPicker = new DatePicker();

        begindatumVbox = new VBox(begindatumLabel, begindatumPicker);
        einddatumVbox = new VBox(einddatumLabel, einddatumPicker);

        overzichtTableView = new TableView();

        verversKnop = new Button(KNOPSTRING);
        verversKnop.setId("button-week");

        leftFilterPanel = new VBox(begindatumVbox, einddatumVbox, klantBox, projectBox,onderwerpBox, verversKnop);

        /**
         * ActionListener op de textfield van project. Op het moment dat deze is gefocussed haalt hiji de projecten uit de database en zet hij ze in de dictionary van de AutoComplete.
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

        leftFilterPanel.setPrefWidth(160);
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

        verversKnop.setPrefWidth(leftFilterPanel.getPrefWidth());
        einddatumPicker.setPrefWidth(leftFilterPanel.getPrefWidth());
        begindatumPicker.setPrefWidth(leftFilterPanel.getPrefWidth());


        /**
         * Bepaal wat de knoppen doen.
         */
        verversKnop.setOnAction(a ->{
            verversButtonPressed();
        });

        /**
         * Initialiseer de tabel.
         */
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
     * Wordt uitgevoerd wanneer de "Ververs" knop wordt ingedrukt.
     */
    public void verversButtonPressed(){
        convertDates();
        if(begindatumPicker.getValue().isAfter(einddatumPicker.getValue())){
            Alert datumIncorrect = new Alert(Alert.AlertType.ERROR, "De begindatum is na de einddatum.");
            datumIncorrect.show();
        }else {
            controller.verversButtonPressed();
        }
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

    public String getOnderwerpNaam(){
        System.out.println(onderwerpNaamInput.getText());
        return onderwerpNaamInput.getText();
    }
}
