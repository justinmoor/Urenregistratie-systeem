package Views;

import Controllers.AutoCompletionTextfieldController;
import Controllers.InvullenUrenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import Controllers.InvullenUrenController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class InvullenUrenView extends Scene {

    private GridPane gridpane;
    private InvullenUrenController urencontroller;
    private GridPane gridpane;

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
    private Label EtijdLabel;

    private AutoCompletionTextfieldController klantField;
    private AutoCompletionTextfieldController projectField;
    private AutoCompletionTextfieldController onderwerpField;
    private TextField commentaarField;
    private TextField BeginDatum;
    private TextField BeginTijd;
    private TextField EindDatum;
    private TextField EindTijd;

    private Button Opslaan;

    public InvullenUrenView(InvullenUrenController controller) {
        super(new GridPane(), 600, 500);
        gridpane = (GridPane) this.getRoot();
        this.urencontroller = controller;
        initgui();
    }


    private void initgui() {

        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(20);
        gridpane.setVgap(15);
        gridpane.setPadding(new Insets(25,25,25,25));
        gridpane.setStyle("-fx-background-color: #f9f9f7");


        urenRegistrerenLabel = new Label("UrenRegistratie");
        urenRegistrerenLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
        urenRegistrerenLabel.setTextFill(Color.GREY);
        gridpane.add(urenRegistrerenLabel, 0, 0, 2, 1);

        klantLabel = new Label("Klant");
        klantLabel.setTextFill(Color.GREY);
        gridpane.add(klantLabel, 0,1);

        projectLabel = new Label("Project");
        projectLabel.setTextFill(Color.GREY);
        gridpane.add(projectLabel, 0,2);

        onderwerpLabel = new Label("onderwerp");
        onderwerpLabel.setTextFill(Color.GREY);
        gridpane.add(onderwerpLabel, 0, 3);

        
    }

}
