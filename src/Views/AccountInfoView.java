package Views;

import Controllers.AccountInfoController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class AccountInfoView  extends Scene{

    private BorderPane pane;
    private GridPane gridpane;
    private AccountInfoController controller;

    private Label accountLabel;
    private Label voornaamlabel;
    private Label tussenVoegselLabel;
    private Label achternaamLabel;
    private Label emailLabel;
    private Label wachtwoordLabel;
    private Label nWachtwoordLabel;
    private Label nHerhaalWachtwoordLabel;

    private Label voornaam;
    private Label tussenvoegsel;
    private Label achternaam;
    private Label email;
    private Label wachtwoord;
    private Label indent;

    private PasswordField nWachtwoord;
    private PasswordField nHerhaalWachtwoord;

    private Button home;
    private Button opslaan;

    private HBox topGedeelte;
    private HBox voornaamBox;
    private HBox tussenvoegselBox;
    private HBox achternaamBox;
    private HBox emailBox;
    private HBox wachtwoordBox;
    private HBox nWachtwoordBox;
    private HBox nHerhaalWachtwoordBox;
    private HBox opslaanBox;

    private VBox gegevensOnderElkaarBox;

    public AccountInfoView(AccountInfoController controller) {
        super(new BorderPane(), 600, 400);
        pane = (BorderPane) this.getRoot();
        this.controller = controller;
    }

    public void initGui() {
        gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(15);
        gridpane.setPadding(new Insets(10,10,10,30));
        gridpane.setStyle("-fx-background-color: #f9f9f7");
        pane.setCenter(gridpane);

        accountLabel = new Label("ACCOUNT");
        accountLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
        accountLabel.setTextFill(Color.GREY);
        accountLabel.setAlignment(Pos.CENTER);

        home = new Button("Hoofdmenu");
        topGedeelte = new HBox(30);
        topGedeelte.getChildren().addAll(home, accountLabel);
        pane.setTop(topGedeelte);

        voornaamlabel = new Label("Voornaam: \t\t\t\t");
        voornaamlabel.setTextFill(Color.GREY);

        voornaam = new Label(controller.getVoornaam());
        voornaam.setTextFill(Color.GREY);

        voornaamBox = new HBox();
        voornaamBox.getChildren().addAll(voornaamlabel, voornaam);

        tussenVoegselLabel = new Label("Tussenvoegsel: \t\t\t");
        tussenVoegselLabel.setTextFill(Color.GREY);

        tussenvoegsel = new Label(controller.getTussenVoegsel());
        tussenvoegsel.setTextFill(Color.GREY);

        tussenvoegselBox = new HBox();
        tussenvoegselBox.getChildren().addAll(tussenVoegselLabel, tussenvoegsel);

        achternaamLabel = new Label("Achternaam: \t \t \t\t");
        achternaamLabel.setTextFill(Color.GREY);

        achternaam = new Label(controller.getAchternaam());
        achternaam.setTextFill(Color.GREY);

        achternaamBox = new HBox();
        achternaamBox.getChildren().addAll(achternaamLabel, achternaam);

        emailLabel = new Label("E-mail: \t\t\t\t\t");
        emailLabel.setTextFill(Color.GREY);

        email = new Label(controller.getEmail());
        email.setTextFill(Color.GREY);

        emailBox = new HBox();
        emailBox.getChildren().addAll(emailLabel, email);

        wachtwoordLabel = new Label("Huidige Wachtwoord: \t\t");
        wachtwoordLabel.setTextFill(Color.GREY);

        wachtwoord = new Label(controller.getWachtwoord());
        wachtwoord.setTextFill(Color.GREY);

        wachtwoordBox = new HBox();
        wachtwoordBox.getChildren().addAll(wachtwoordLabel, wachtwoord);

        nWachtwoordLabel = new Label("Nieuw Wachtwoord:\t\t");
        nWachtwoordLabel.setTextFill(Color.GREY);


        nWachtwoord = new PasswordField();

        nWachtwoordBox = new HBox(nWachtwoordLabel, nWachtwoord);

        nHerhaalWachtwoordLabel = new Label("Herhaal wachtwoord: \t\t");
        nHerhaalWachtwoordLabel.setTextFill(Color.GREY);

        nHerhaalWachtwoord = new PasswordField();

        nHerhaalWachtwoordBox = new HBox(nHerhaalWachtwoordLabel, nHerhaalWachtwoord);

        opslaan = new Button("Opslaan");
        indent = new Label("\t\t\t\t\t\t");
        opslaanBox = new HBox();
        opslaanBox.getChildren().addAll(indent, opslaan);

        gegevensOnderElkaarBox = new VBox(8);
        gegevensOnderElkaarBox.getChildren().addAll(voornaamBox, tussenvoegselBox, achternaamBox, emailBox, wachtwoordBox, nWachtwoordBox, nHerhaalWachtwoordBox, opslaanBox);
        gridpane.add(gegevensOnderElkaarBox, 1, 1);
    }

    public void initAction() {

        home.setOnAction(e -> {
            controller.getHoofdMenuController().setHoofdMenuView();
        });

        opslaan.setOnAction(e -> {
            if (nWachtwoord.getText().equals(nHerhaalWachtwoord.getText())) {
                controller.veranderGebruiker(nWachtwoord.getText());
                controller.getHoofdMenuController().getGebruikerModel().setWachtwoord(nWachtwoord.getText());
                controller.getHoofdMenuController().setAccountInfoView();
            } else {
                System.out.println("Wachtwoorden zijn niet het zelfde");
            }

        });
        
    }

}
