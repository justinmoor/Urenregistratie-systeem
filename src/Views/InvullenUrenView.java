package Views;

import Controllers.AutoCompletionTextfieldController;
import Controllers.HoofdMenuController;
import Controllers.InvullenUrenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.SQLException;


public class InvullenUrenView extends Scene {

    private GridPane gridpane;
    private BorderPane pane;
    private InvullenUrenController controller;
    private HoofdMenuController hoofdMenuController;

    private Label urenRegistrerenLabel;
    private Label klantLabel;
    private Label projectLabel;
    private Label onderwerpLabel;
    private Label commentaarLabel;
    private Label BeginLabel;
    private Label BdatumLabel;
    private Label BtijdLabel;
    private Label EindLabel;
    private Label EDatumLabel;
    private Label ETijdLabel;

    private AutoCompletionTextfieldController klantField;
    private AutoCompletionTextfieldController projectField;
    private AutoCompletionTextfieldController onderwerpField;
    private TextArea commentaarField;
    private TextField BeginDatum;
    private TextField BeginTijd;
    private TextField EindDatum;
    private TextField EindTijd;

    private Button Opslaan;
    private Button home;

    public InvullenUrenView(InvullenUrenController controller) {
        super(new BorderPane(), 600, 500);
        pane = (BorderPane) this.getRoot();
        this.controller = controller;
        this.hoofdMenuController = controller.getHoofdMenuController();
        try {
            initgui();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        InitAction();

    }


    private void initgui() throws SQLException {

        gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(15);
        gridpane.setPadding(new Insets(10,10,10,30));
        gridpane.setStyle("-fx-background-color: #f9f9f7");

        pane.setCenter(gridpane);

        urenRegistrerenLabel = new Label("UrenRegistratie");
        urenRegistrerenLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
        urenRegistrerenLabel.setTextFill(Color.GREY);
        pane.setTop(urenRegistrerenLabel);


        klantLabel = new Label("Klant");
        klantLabel.setTextFill(Color.GREY);
        gridpane.add(klantLabel, 0,2);

        projectLabel = new Label("Project");
        projectLabel.setTextFill(Color.GREY);
        gridpane.add(projectLabel, 0,3);

        onderwerpLabel = new Label("onderwerp");
        onderwerpLabel.setTextFill(Color.GREY);
        gridpane.add(onderwerpLabel, 0, 4);

        commentaarLabel = new Label("Commentaar");
        commentaarLabel.setTextFill(Color.GREY);
        gridpane.add(commentaarLabel, 0, 5);

        klantField = new AutoCompletionTextfieldController();
        gridpane.add(klantField, 1,2);
        klantField.setPrefColumnCount(1);

        projectField = new AutoCompletionTextfieldController();
        gridpane.add(projectField, 1, 3);

        onderwerpField = new AutoCompletionTextfieldController();
        gridpane.add(onderwerpField, 1, 4);

        commentaarField = new TextArea();
        gridpane.add(commentaarField, 1, 5);
        commentaarField.setPrefColumnCount(5);
        commentaarField.setPrefRowCount(2);

        BeginLabel = new Label("BEGIN");
        BeginLabel.setTextFill(Color.GREY);
        BeginLabel.setPadding(new Insets(10,10,10,30));
        gridpane.add(BeginLabel, 2, 1, 2, 1);

        BdatumLabel= new Label("Datum");
        BdatumLabel.setTextFill(Color.GREY);
        gridpane.add(BdatumLabel, 2, 2);

        BtijdLabel = new Label("Tijd");
        BtijdLabel.setTextFill(Color.GREY);
        gridpane.add(BtijdLabel, 2, 3);

        EindLabel = new Label("EIND");
        EindLabel.setTextFill(Color.GREY);
        EindLabel.setPadding(new Insets(10,10,10,30));
        gridpane.add(EindLabel, 2,4,2,1);

        EDatumLabel = new Label("Datum");
        EDatumLabel.setTextFill(Color.GREY);
        gridpane.add(EDatumLabel, 2,5);

        ETijdLabel = new Label("Tijd");
        ETijdLabel.setTextFill(Color.GREY);
        gridpane.add(ETijdLabel, 2, 6);

        BeginDatum = new TextField();
        gridpane.add(BeginDatum, 3, 2);

        BeginTijd = new TextField();
        gridpane.add(BeginTijd, 3, 3);

        EindDatum = new TextField();
        gridpane.add(EindDatum, 3, 5);

        EindTijd = new TextField();
        gridpane.add(EindTijd, 3,6);

        Opslaan = new Button("Bevestigen");
        gridpane.add(Opslaan, 2,7, 2, 1);

        home = new Button("home");
        pane.setTop(home);

        klantField.getEntries().addAll(controller.getKlanten());
        projectField.getEntries().addAll(controller.getProjecten());
        onderwerpField.getEntries().addAll(controller.getOnderwerpen());

    }

    private void InitAction(){
        Opslaan.setOnAction(e -> {
            if(commentaarField.getText() != null){
                controller.insert(klantField.getText(), projectField.getText(), onderwerpField.getText(), commentaarField.getText(), BeginDatum.getText(),
                        BeginTijd.getText(), EindDatum.getText(), EindTijd.getText());
            } else {
                controller.insert(klantField.getText(), projectField.getText(), onderwerpField.getText(), BeginDatum.getText(),
                        BeginTijd.getText(), EindDatum.getText(), EindTijd.getText());
            }

        });

        home.setOnAction(e -> {
            if (controller.getHoofdMenuController().getGebruikerModel().getRechten().equals("1")) {
                controller.getHoofdMenuController().setAdminHoofdMenu();
            } else if (controller.getHoofdMenuController().getGebruikerModel().getRechten().equals("0")) {
                controller.getHoofdMenuController().setPersoneelHoofdmenu();
            }

        });
    }


}
