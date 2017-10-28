package Views;

import Controllers.AccountInfoController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class AccountInfoView  extends Scene{



    private BorderPane pane;

    private BorderPane navigatie;

    private VBox img_box;
    private Image img;
    private ImageView terug;

    private Label accountLabel;

    private Image img2;
    private ImageView lijntje;

    private GridPane gridpane;
    private AccountInfoController controller;

    private Image img4;
    private ImageView vbox_lijntje;

    private Image img3;
    private ImageView blauw_lijntje;


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

    private PasswordField nWachtwoord;
    private PasswordField nHerhaalWachtwoord;

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
        pane.setId("pane");

        navigatie = new BorderPane();

        img_box = new VBox();
        img = new Image("/Assets/back.png");
        terug = new ImageView(img);
        img_box.getChildren().add(terug);
        img_box.setPadding(new Insets(15, 0, 15, 14));

        accountLabel = new Label("ACCOUNT");
        accountLabel.setId("home");
        accountLabel.setPadding(new Insets(15));

        img2 = new Image("/Assets/lijntje.png");
        lijntje = new ImageView(img2);
        lijntje.setFitWidth(600);

        navigatie.setLeft(img_box);
        navigatie.setCenter(accountLabel);
        navigatie.setBottom(lijntje);

        pane.setTop(navigatie);

        gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(15);
        gridpane.setPadding(new Insets(10,10,10,30));
        gridpane.setStyle("-fx-background-color: #f9f9f7");
        pane.setCenter(gridpane);

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

        img4 = new Image("/Assets/lijntje.png");

        vbox_lijntje = new ImageView(img4);
        vbox_lijntje.setFitWidth(324);

        nWachtwoordLabel = new Label("Nieuw Wachtwoord:\t\t");
        nWachtwoordLabel.setTextFill(Color.GREY);

        nWachtwoord = new PasswordField();

        nWachtwoordBox = new HBox(nWachtwoordLabel, nWachtwoord);

        nHerhaalWachtwoordLabel = new Label("Herhaal wachtwoord: \t\t");
        nHerhaalWachtwoordLabel.setTextFill(Color.GREY);

        nHerhaalWachtwoord = new PasswordField();

        nHerhaalWachtwoordBox = new HBox(nHerhaalWachtwoordLabel, nHerhaalWachtwoord);

        opslaan = new Button("Opslaan");
        opslaan.setId("opslaan");

        gegevensOnderElkaarBox = new VBox(12);
        gegevensOnderElkaarBox.getChildren().addAll(voornaamBox, tussenvoegselBox, achternaamBox, emailBox, wachtwoordBox, vbox_lijntje, nWachtwoordBox, nHerhaalWachtwoordBox, opslaan);

        gridpane.add(gegevensOnderElkaarBox, 1, 1);

        img3 = new Image("/Assets/blauwlijntje.png");

        blauw_lijntje = new ImageView(img3);
        blauw_lijntje.setFitWidth(612);

        pane.setBottom(blauw_lijntje);

        getStylesheets().add("Views/styles.css");
    }

    public void initAction() {

        img_box.setOnMouseClicked(e -> {
            controller.getHoofdMenuController().startHoofdmenuView();
        });

        opslaan.setOnAction(e -> {
            if (nWachtwoord.getText().equals(nHerhaalWachtwoord.getText())) {
                controller.veranderGebruiker(nWachtwoord.getText());
                controller.getHoofdMenuController().getGebruikerModel().setWachtwoord(nWachtwoord.getText());
                controller.getHoofdMenuController().startAccountInfoView();
            } else {
                System.out.println("Wachtwoorden zijn niet het zelfde");
            }

        });
        
    }

}
