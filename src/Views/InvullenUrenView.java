package Views;
        import Controllers.AutoCompletionTextfieldController;
        import Controllers.HoofdMenuController;
        import Controllers.InvullenUrenController;
        import Models.KlantModel;
        import Models.OnderwerpModel;
        import Models.ProjectModel;
        import javafx.geometry.Insets;
        import javafx.geometry.Pos;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
        import javafx.scene.layout.GridPane;
        import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
        import javafx.scene.text.Font;
        import javafx.scene.text.FontWeight;

        import java.sql.SQLException;
        import java.time.LocalDate;
        import java.time.LocalTime;
        import java.time.format.DateTimeFormatter;
        import java.util.ArrayList;
        import java.util.Optional;

public class InvullenUrenView extends Scene {
    private HoofdMenuController hoofdMenuController;
    
    private BorderPane pane;
    
    private BorderPane navigatie;
    
    private StackPane left_pane;
    private VBox img_box;
    private Image img;
    private ImageView terug;
    
    private StackPane mid_pane;
    private Label home;
    
    private StackPane right_pane;
    private Label gebruiker;
    
    private Image img2;
    private ImageView lijntje;
    
    private HBox groep;
    
    private VBox linkergroep;
    		private HBox klant;
    		private Label lbl1;
    		private AutoCompletionTextfieldController klantField;
    		
    		private HBox project;
    		private Label lbl2;
    		private AutoCompletionTextfieldController projectField;
    		
    		private HBox onderwerp;
    		private Label lbl3;
    		private AutoCompletionTextfieldController onderwerpField;
    
    		private HBox commentaar;
    		private Label lbl4;
    		private TextArea commentaarField;
    		
    	private VBox rechtergroep;
    		private Label begin;
    		
    		private HBox datum1;
    		private Label lbl5;
    		private DatePicker BeginDatum;
    		
    		private HBox tijd1;
    		private Label lbl6;
    		private Label BeginTijd;
    		
    		private Image img3;
    		private ImageView midlijnjte;
    		
    		private Label eind;
    		
    		private HBox datum2;
    		private Label lbl7;
    		private DatePicker EindDatum;
    		
    		private HBox tijd2;
    		private Label lbl8;
    		private Label EindTijd;
    		
    		private Button bevestig;

    private ArrayList<KlantModel> klanten;
    private ArrayList<ProjectModel> projecten;
    private ArrayList<OnderwerpModel> onderwerpen;

	private InvullenUrenController controller;

    public InvullenUrenView(InvullenUrenController controller) {
        super(new BorderPane(), 600, 400);
        pane = (BorderPane) this.getRoot();
        this.controller = controller;
        this.hoofdMenuController = controller.getHoofdMenuController();
        try {
            InitGui();
            setTijd();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        InitAction();
    }


    private void InitGui() throws SQLException {
		pane.setStyle("-fx-background-image: url('/Assets/background.png')");
		
navigatie = new BorderPane();
		
		left_pane = new StackPane();
		left_pane.setMinWidth(80);
		left_pane.setPadding(new Insets(15, 0, 15, 15));
		img_box = new VBox();
		img = new Image("/Assets/back.png");
		terug = new ImageView(img);
		img_box.getChildren().add(terug);
		left_pane.getChildren().add(img_box);
		
		mid_pane = new StackPane();
		mid_pane.setPrefWidth(440);
		home = new Label("UREN REGISTREREN");
		home.setId("home");
		mid_pane.getChildren().add(home);
		
		right_pane = new StackPane();
		right_pane.setPrefWidth(80);
//		gebruiker = new Label(controller.getGebruikerModel().getVolledigeNaam());
		gebruiker = new Label("WTF");
		gebruiker.setAlignment(Pos.CENTER_RIGHT);
		right_pane.getChildren().add(gebruiker);
		
		img2 = new Image("/Assets/lijntje.png");
		lijntje = new ImageView(img2);
		lijntje.setFitWidth(600);
		
		navigatie.setLeft(left_pane);
		navigatie.setCenter(mid_pane);
		navigatie.setRight(right_pane);
		navigatie.setBottom(lijntje);
		
		groep = new HBox();
		
		linkergroep = new VBox(12);
		linkergroep.setPadding(new Insets(40, 0, 0, 35));
		
		klant = new HBox();
		lbl1 = new Label("Klant:\t\t");
		lbl1.setPadding(new Insets(6, 2, 0, 0));
		klantField = new AutoCompletionTextfieldController();
		klantField.setId("text-field-big");
		klant.getChildren().addAll(lbl1, klantField);
		
		project = new HBox();
		lbl2 = new Label("Project:\t\t");
		lbl2.setPadding(new Insets(6, 2, 0, 0));
		projectField = new AutoCompletionTextfieldController();
		projectField.setId("text-field-big");
		project.getChildren().addAll(lbl2, projectField);
		
		onderwerp = new HBox();
		lbl3 = new Label("Onderwerp:\t");
		lbl3.setPadding(new Insets(6, 2, 0, 0));
		onderwerpField = new AutoCompletionTextfieldController();
		onderwerpField.setId("text-field-big");
		onderwerp.getChildren().addAll(lbl3, onderwerpField);
		
		commentaar = new HBox();
		lbl4 = new Label("Commentaar:\t");
		lbl4.setPadding(new Insets(6, 2, 0, 0));
		commentaarField = new TextArea();
		commentaar.getChildren().addAll(lbl4, commentaarField);
		
		linkergroep.getChildren().addAll(klant, project, onderwerp, commentaar);
		
		rechtergroep = new VBox(12);
		rechtergroep.setPadding(new Insets(0, 0, 0, 35));
		
		begin = new Label("BEGIN");
		begin.setPadding(new Insets(0, 0, 0, 106));
		begin.setId("label-bold");
		
		datum1 = new HBox();
		lbl5 = new Label("Datum:\t");
		lbl5.setPadding(new Insets(6, 2, 0, 0));
		BeginDatum = new DatePicker();
		BeginDatum.setId("text-field-small");
		BeginDatum.setPrefWidth(136);
		BeginDatum.setValue(LocalDate.now());
		datum1.getChildren().addAll(lbl5, BeginDatum);
		
		tijd1 = new HBox();
		lbl6 = new Label("Tijd:\t\t");
		lbl6.setPadding(new Insets(6, 2, 0, 0));
		BeginTijd = new Label(" ");
		BeginTijd.setId("text-field-small");
		BeginTijd.setPrefWidth(136);
		tijd1.getChildren().addAll(lbl6, BeginTijd);
		
		img3 = new Image("/Assets/lijntje.png");
		midlijnjte = new ImageView(img3);
		midlijnjte.setFitWidth(196);
		
		eind = new Label("EIND");
		eind.setPadding(new Insets(0, 0, 0, 112));
		eind.setId("label-bold");
		
		datum2 = new HBox();
		lbl7 = new Label("Datum:\t");
		lbl7.setPadding(new Insets(6, 2, 0, 0));
		EindDatum = new DatePicker();
		EindDatum.setId("text-field-small");
		EindDatum.setPrefWidth(136);
        EindDatum.setValue(LocalDate.now());
		datum2.getChildren().addAll(lbl7, EindDatum);
		
		tijd2 = new HBox();
		lbl8 = new Label("Tijd:\t\t");
		lbl8.setPadding(new Insets(6, 2, 0, 0));
		EindTijd = new Label();
		EindTijd.setId("text-field-small");
		EindTijd.setPrefWidth(136);
		tijd2.getChildren().addAll(lbl8, EindTijd);

		bevestig = new Button("BEVESTIG");
		bevestig.setId("bevestig");
		
		rechtergroep.getChildren().addAll(begin, datum1, tijd1, midlijnjte, eind, datum2, tijd2, bevestig);
		rechtergroep.setPadding(new Insets(12, 0, 0, 40));
		
		groep.getChildren().addAll(linkergroep, rechtergroep);
		
		pane.setTop(navigatie);
		pane.setCenter(groep);
    
		getStylesheets().add("Views/styles.css");
    }

    private void InitAction(){
	    	terug.setOnMouseClicked(e -> {
	    	 	controller.getHoofdMenuController().startHoofdmenuView();
	    });
    	
        klantField.setOnKeyPressed(e -> {
            klanten = null;
            try {
                klanten = controller.getKlanten();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            for (int i = 0; i < klanten.size(); i++) {
                klantField.getEntries().add(klanten.get(i).getNaam());
            }
        });
        projectField.setOnKeyPressed(e -> {
            projecten = null;
            try {
                projecten = controller.getProjecten(klantField.getText());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            for (int i = 0; i < projecten.size(); i++) {
                projectField.getEntries().add(projecten.get(i).getProject_naam());
            }
        });

        onderwerpField.setOnKeyPressed(e -> {
            onderwerpen = null;
            try {
                onderwerpen = controller.getOnderwerpen(projectField.getText());
            } catch (SQLException e3) {
                e3.printStackTrace();
            }
            for (int i = 0; i < onderwerpen.size(); i++) {
                onderwerpField.getEntries().add(onderwerpen.get(i).getOnderwerp_naam());
            }
        });

        bevestig.setOnAction(e -> {
            if(klantField.getLength() > 1 && projectField.getLength() > 1 && onderwerpField.getLength() > 1 && !BeginDatum.getValue().equals("") && !BeginTijd.getText().equals("") && !EindDatum.getValue().equals("") && !EindTijd.getText().equals("")) {

                boolean bestaandeKlant = false;
                boolean bestaandProject = false;
                boolean bestaandOnderwerp = false;

                //als klant voorkomt true anders melding met klant project en onderwerp

                for (int i = 0 ; i < klanten.size(); i++ ) {
                    if (klantField.getText().equals(klanten.get(i).getNaam())){
                        bestaandeKlant = true;
                    }
                }

                for (int i = 0; i < projecten.size(); i++) {
                    if (projectField.getText().equals(projecten.get(i).getProject_naam())) {
                        bestaandProject = true;
                    }
                }

                for (int i = 0; i < onderwerpen.size(); i++) {
                    if (onderwerpField.getText().equals(onderwerpen.get(i).getOnderwerp_naam())) {
                        bestaandOnderwerp = true;
                    }
                }

                if (bestaandeKlant != true) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Toevoeg scherm");
                    alert.setHeaderText("wilt u deze klant: '" +klantField.getText()+"' en het bijbehorende project: '"+projectField.getText()+"' en onderwerp: '"+onderwerpField.getText()+"' toevoegen?");
                    alert.setContentText("Klik ja om de bovenstaande gegevens toetevoegen, klik nee om een  nieuwe klant in te vullen ");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        controller.voegKlantToe(klantField.getText(), projectField.getText(), onderwerpField.getText());
                        InvullenUren();
                    } else if (result.get() == ButtonType.CANCEL) {
                        klantField.setText("");
                        projectField.setText("");
                        onderwerpField.setText("");
                    }

                } else if (bestaandProject != true) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Toevoegen scherm");
                    alert.setHeaderText("wilt u dit project: '"+projectField.getText()+"' en onderwerp: '"+onderwerpField.getText()+"' toevoegen?");
                    alert.setContentText("Klik ja om de bovenstaande gegevens toetevoegen, klik nee om een  nieuwe klant in te vullen ");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        controller.voegProjectToe(klantField.getText(), projectField.getText(), onderwerpField.getText());
                        InvullenUren();
                    } else if (result.get() == ButtonType.CANCEL) {
                        projectField.setText("");
                        onderwerpField.setText("");
                    }
                } else if (bestaandOnderwerp != true) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Toevoegen scherm");
                    alert.setHeaderText("wilt u dit onderwerp: '"+onderwerpField.getText()+"' toevoegen?");
                    alert.setContentText("Klik ja om de bovenstaande gegevens toetevoegen, klik nee om een  nieuwe klant in te vullen ");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        controller.voegOnderwerpToe(projectField.getText(), onderwerpField.getText());
                        InvullenUren();
                    } else if (result.get() == ButtonType.CANCEL) {
                        onderwerpField.setText("");
                    }
                } else {
                    InvullenUren();
                }

            } else {
                System.out.println("Invullen");
            }

        });

    }

    private void InvullenUren() {
        try {
            controller.insert(klantField.getText(), projectField.getText(), onderwerpField.getText(), commentaarField.getText(), BeginDatum.getValue().toString(),
                    BeginTijd.getText(), EindDatum.getValue().toString(), EindTijd.getText());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        klantField.setText("");
        projectField.setText("");
        onderwerpField.setText("");
        commentaarField.setText("");
        BeginTijd.setText("");
        EindTijd.setText("");
        setTijd();
    }

    public void setGebruiker() {
        gebruiker.setText(controller.getHoofdMenuController().getGebruikerModel().getVolledigeNaam());
    }

    public void setTijd() {
        BeginTijd.setText("  " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        EindTijd.setText("  " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
    }
}
