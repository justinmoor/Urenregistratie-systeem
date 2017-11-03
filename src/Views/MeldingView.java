package Views;

import DAO.KlantDAO;
import DAO.OnderwerpDAO;
import DAO.ProjectDAO;
import Database.DatabaseConnectie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MeldingView extends Stage{

    private DatabaseConnectie db;
    private InvullenUrenView view;
    private AccountInfoView accountView;
    private KlantDAO klantenDao;
    private ProjectDAO projectDao;
    private OnderwerpDAO onderwerpDao;

    private String categorie;
    private String klant;
    private String project;
    private String onderwerp;

    private Stage newStage;
    private BorderPane pane;
    private Scene scene;

    private BorderPane navigatie;

    private StackPane mid_pane;
    private Label home;
    
    private Image img2;
    private ImageView lijntje;
    
    private VBox groep;
    
    private Label tekst;
    private Label subTekst;

    private HBox buttonBox;
    private Button btOK;
    private Button btCancel;
    
    private Image img3;
    private ImageView blauw_lijntje;


    //alert als uren een nieuwe klant project of onderwerp heeft
    public MeldingView(String categorie, DatabaseConnectie db, String klant, String project, String onderwerp, InvullenUrenView view) {

        this.view = view;
        this.klant = klant;
        this.project = project;
        this.onderwerp = onderwerp;
        this.categorie = categorie;
        newStage = new Stage();
        this.db = db;
        klantenDao = new KlantDAO(db);
        projectDao = new ProjectDAO(db);
        onderwerpDao = new OnderwerpDAO(db);
        initUrenGui();
        initUrenAction();
    }

    //alert als een wachtwoord te kort is

    public MeldingView(String categorie, AccountInfoView accountView) {

        setStage();
        this.accountView = accountView;
        this.categorie = categorie;
        initWachtwoordGui();
        initWachtwoordAction();
    }

    private void initUrenGui() {

        setStage();
        
        pane.setId("pane");
        
        navigatie = new BorderPane();

        mid_pane = new StackPane();
		mid_pane.setPrefWidth(700);
		home = new Label("TOEVOEGEN");
		home.setId("home");
		home.setPadding(new Insets(15, 0, 15, 0));
		mid_pane.getChildren().add(home);

		img2 = new Image("/Assets/lijntje.png");
		lijntje = new ImageView(img2);
		lijntje.setFitWidth(700);

		navigatie.setCenter(mid_pane);
		navigatie.setBottom(lijntje);

        groep = new VBox(30);
        tekst = new Label("kiesTekst");
        subTekst = new Label("Klik Oke om bovenstaande toetevoegen aan de database. Klik Cancel als u bovenstaande niet wil toevoegen");
        
        buttonBox = new HBox(20);
        btOK = new Button("OKE");
        btCancel = new Button("CANCEL");
        btOK.setId("button-melding");
        btCancel.setId("button-melding");
        buttonBox.getChildren().addAll(btCancel, btOK);
        buttonBox.setAlignment(Pos.CENTER);
        
        groep.getChildren().addAll(tekst, buttonBox);
        groep.setAlignment(Pos.CENTER);
        
		img3 = new Image("/Assets/blauwlijntje.png");
		blauw_lijntje = new ImageView(img3);
		blauw_lijntje.setFitWidth(700);
        
        pane.setTop(navigatie);
        pane.setCenter(groep); 
        pane.setBottom(blauw_lijntje);
        
        scene.getStylesheets().add("Views/styles.css");
    }

    private void initWachtwoordGui() {
    	 	pane.setId("pane");
    	 	
    	 	groep = new VBox(30);
         
        navigatie = new BorderPane();

        mid_pane = new StackPane();
 		mid_pane.setPrefWidth(700);
 		home = new Label("WACHTWOORD WIJZIGEN MISLUKT");
 		home.setId("home");
 		home.setPadding(new Insets(15, 0, 15, 0));
 		mid_pane.getChildren().add(home);

 		img2 = new Image("/Assets/lijntje.png");
 		lijntje = new ImageView(img2);
 		lijntje.setFitWidth(700);

 		navigatie.setCenter(mid_pane);
 		navigatie.setBottom(lijntje);
    	
        tekst = new Label();
        btOK = new Button("OKE");
        btOK.setId("button-melding");
        
        groep.getChildren().addAll(tekst, btOK);
        groep.setAlignment(Pos.CENTER);

        if (categorie.equals("teKort")) {
            tekst.setText("Het wachtwoord moet minimaal 8 tekens lang zijn.");
        } else if (categorie.equals("nietZelfde")) {
            tekst.setText("De opgegeven wachtwoorden zijn niet het zelfde");
        }
        
        img3 = new Image("/Assets/blauwlijntje.png");
		blauw_lijntje = new ImageView(img3);
		blauw_lijntje.setFitWidth(700);
        
        pane.setTop(navigatie);
        pane.setCenter(groep); 
        pane.setBottom(blauw_lijntje);
        
        scene.getStylesheets().add("Views/styles.css");

    }




    public void setString(String tekstVoorView) {
        tekst.setText(tekstVoorView);
    }

    private Stage setStage() {
        pane = new BorderPane();
        scene = new Scene(pane, 700, 260);
        newStage = new Stage();
        newStage.setScene(scene);
        newStage.setAlwaysOnTop(true);
        newStage.show();


        return newStage;
    }

    public void initUrenAction() {

        btOK.setOnAction(e -> {
                    if (categorie.equals("Klanten")) {

                        klantenDao.voegNieuweKlantToe(klant, project, onderwerp);
                        view.InvullenUren();
                        newStage.close();

                    } else if (categorie.equals("projecten")) {

                        projectDao.voegNieuwProjectToe(klant, project, onderwerp);
                        view.InvullenUren();
                        newStage.close();

                    } else if (categorie.equals("Onderwerpen")) {

                        onderwerpDao.voegNiewOnderwerpToe(project, onderwerp);
                        view.InvullenUren();
                        newStage.close();
                    }
        });

        btCancel.setOnAction(e -> {
            view.setTekstFields();
            newStage.close();
        });

    }

    private void initWachtwoordAction() {

        btOK.setOnAction(e -> {
            accountView.clearNewWachtwoord();
            newStage.close();
        });
    }
}
