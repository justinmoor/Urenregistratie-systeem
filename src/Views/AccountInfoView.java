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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * De view die de informatie laat zien van de gebruiker die  ingelogd is
 *
 * @author Alex de Bruin
 *
 * @version 3.0
 */


public class AccountInfoView  extends Scene{
	/**
	 * Initialiseren van de bijhorende controller
	 */
	private AccountInfoController controller;
	
	/**
	 * Initialiseren van de algemene pane
	 */
    private BorderPane pane;

    /**
     * Initialiseren van de navigatie balk en de benodigde onderdelen
     * @author stanhoenson
     */
    private BorderPane navigatie;
    
    private StackPane left_pane;
    private VBox img_box;
    private Image img;
    private ImageView terug;
    
    private StackPane mid_pane;
    private Label accountLabel;

    private Image img2;
    private ImageView lijntje;

    /**
     * Initialiseren van de gridpane waar alle onderdelen in komen
     */
    private GridPane gridpane;

    /**
     * Initialiseren van alle benodigde onderdelen voor deze specifieke view
     */
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

    /**
     * Initialiseren van HBox's en VBox's voor het maken van de layout
     */
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

    /**
     * Maakt de view aan. Ook wordt de controller meegegeven, zodat de controller de logica van de knoppen regelt
     * @param controller
     */ 
    public AccountInfoView(AccountInfoController controller) {
        super(new BorderPane(), 600, 400);
        pane = (BorderPane) this.getRoot();
        this.controller = controller;
    }

    /**
     * Maken en plaatsen van alle onderdelen voor de GUI
     */ 
    public void initGui() {
        pane.setId("pane");

        /**
         * Aanmaken van de navigatie balk en de benodigde onderdelen
         */
        navigatie = new BorderPane();
		
        /**
         * Aanmaken van de linker kant van de navigatie balk
         */
        left_pane = new StackPane();
		left_pane.setMinWidth(30);
		left_pane.setPadding(new Insets(15, 0, 15, 15));
		img_box = new VBox();
		img = new Image("/Assets/back.png");
		terug = new ImageView(img);
		img_box.getChildren().add(terug);
		left_pane.getChildren().add(img_box);
		
		/**
         * Aanmaken van het midden van de navigatie balk
         */
		mid_pane = new StackPane();
		mid_pane.setPrefWidth(570);
		mid_pane.setPadding(new Insets(0, 37, 0, 0));
		accountLabel = new Label("ACCOUNT INFO");
		accountLabel.setId("home");
		accountLabel.setAlignment(Pos.CENTER);
		mid_pane.getChildren().add(accountLabel);

		/**
         * Aanmaken van het lijntje om de navigatie balk beter kunnen onderscheiden van de rest van de view
         */
        img2 = new Image("/Assets/lijntje.png");
        lijntje = new ImageView(img2);
        lijntje.setFitWidth(600);

        /**
         * Plaatsen van de onderdelen in de navigatie pane
         */
        navigatie.setLeft(left_pane);
        navigatie.setCenter(mid_pane);
        navigatie.setBottom(lijntje);

        pane.setTop(navigatie);

        /**
         * Aanmaken van de gridpane, wat styling en het plaatsen van de gridpane in de algemene pane
         */
        gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setStyle("-fx-background-color: #f9f9f7");
        pane.setCenter(gridpane);

        /**
         * Aanmaken van alle benodigde onderdelen voor deze specifieke view
         */
        voornaamlabel = new Label("Voornaam: \t\t\t\t");
        voornaamlabel.setTextFill(Color.GREY);

        voornaam = new Label(controller.getVoornaam());
        voornaam.setTextFill(Color.GREY);
        voornaam.setStyle("-fx-font-weight: bold");

        voornaamBox = new HBox();
        voornaamBox.getChildren().addAll(voornaamlabel, voornaam);

        tussenVoegselLabel = new Label("Tussenvoegsel: \t\t\t");
        tussenVoegselLabel.setTextFill(Color.GREY);

        tussenvoegsel = new Label(controller.getTussenVoegsel());
        tussenvoegsel.setTextFill(Color.GREY);
        tussenvoegsel.setStyle("-fx-font-weight: bold");

        tussenvoegselBox = new HBox();
        tussenvoegselBox.getChildren().addAll(tussenVoegselLabel, tussenvoegsel);

        achternaamLabel = new Label("Achternaam: \t \t \t\t");
        achternaamLabel.setTextFill(Color.GREY);

        achternaam = new Label(controller.getAchternaam());
        achternaam.setStyle("-fx-font-weight: bold");
        achternaam.setTextFill(Color.GREY);

        achternaamBox = new HBox();
        achternaamBox.getChildren().addAll(achternaamLabel, achternaam);

        emailLabel = new Label("E-mail: \t\t\t\t\t");
        emailLabel.setTextFill(Color.GREY);

        email = new Label(controller.getEmail());
        email.setTextFill(Color.GREY);
        email.setStyle("-fx-font-weight: bold");

        emailBox = new HBox();
        emailBox.getChildren().addAll(emailLabel, email);

        wachtwoordLabel = new Label("Huidig wachtwoord: \t\t");
        wachtwoordLabel.setTextFill(Color.GREY);

        wachtwoord = new Label(controller.getWachtwoord());
        wachtwoord.setTextFill(Color.GREY);
        wachtwoord.setStyle("-fx-font-weight: bold");

        wachtwoordBox = new HBox();
        wachtwoordBox.getChildren().addAll(wachtwoordLabel, wachtwoord);

        img4 = new Image("/Assets/lijntje.png");

        vbox_lijntje = new ImageView(img4);
        vbox_lijntje.setFitWidth(324);

        nWachtwoordLabel = new Label("Nieuw wachtwoord:\t\t\t");
        nWachtwoordLabel.setPadding(new Insets(6, 0, 0, 0));
        nWachtwoordLabel.setTextFill(Color.GREY);

        nWachtwoord = new PasswordField();

        nWachtwoordBox = new HBox(nWachtwoordLabel, nWachtwoord);

        nHerhaalWachtwoordLabel = new Label("Herhaal wachtwoord: \t\t");
        nHerhaalWachtwoordLabel.setPadding(new Insets(6, 0, 0, 0));
        nHerhaalWachtwoordLabel.setTextFill(Color.GREY);

        nHerhaalWachtwoord = new PasswordField();

        nHerhaalWachtwoordBox = new HBox(nHerhaalWachtwoordLabel, nHerhaalWachtwoord);

        opslaan = new Button("OPSLAAN");
        opslaan.setId("opslaan");

        /**
         * Aanmaken van de VBox waar alle onderdelen aan worden toegevoegd en het plaatsen van deze VBox
         */
        gegevensOnderElkaarBox = new VBox(12);
        gegevensOnderElkaarBox.getChildren().addAll(voornaamBox, tussenvoegselBox, achternaamBox, emailBox, wachtwoordBox, vbox_lijntje, nWachtwoordBox, nHerhaalWachtwoordBox, opslaan);
        gegevensOnderElkaarBox.setPadding(new Insets(0, 0, 20, 0));

        gridpane.add(gegevensOnderElkaarBox, 1, 1);

        img3 = new Image("/Assets/blauwlijntje.png");

        blauw_lijntje = new ImageView(img3);
        blauw_lijntje.setFitWidth(600);

        pane.setBottom(blauw_lijntje);

        /**
         * Toevoegen van een stylesheet
         */
        getStylesheets().add("Views/styles.css");
    }

    /**
     * Methode waar acties worden meegegeven aan verschillende knoppen of andere onderdelen uit de view
     */
    public void initAction() {

        img_box.setOnMouseClicked(e -> {
            controller.getHoofdMenuController().startHoofdmenuView();
        });

        opslaan.setOnAction(e -> {
            if (nWachtwoord.getText().equals(nHerhaalWachtwoord.getText()) && nWachtwoord.getLength() >= 8) {
                controller.veranderGebruiker(nWachtwoord.getText());
                	controller.getHoofdMenuController().getGebruikerModel().setWachtwoord(nWachtwoord.getText());
                	controller.getHoofdMenuController().startAccountInfoView();
            } else if (nWachtwoord.getLength() < 8){
            		System.out.println("Wachtwoord is niet lang genoeg");
            } else {
                System.out.println("Wachtwoorden zijn niet het zelfde");
            }

        });
        
    }

}
