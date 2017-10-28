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
        import javafx.scene.layout.BorderPane;
        import javafx.scene.layout.GridPane;
        import javafx.scene.layout.HBox;
        import javafx.scene.paint.Color;
        import javafx.scene.text.Font;
        import javafx.scene.text.FontWeight;

        import java.sql.SQLException;
        import java.time.LocalDate;
        import java.util.ArrayList;
        import java.util.Optional;

public class InvullenUrenView extends Scene {

    private GridPane gridpane;
    private BorderPane pane;
    private InvullenUrenController controller;
    private HoofdMenuController hoofdMenuController;
    private HBox box;



    private Label urenRegistrerenLabel;
    private Label persoon;
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
    private DatePicker BeginDatum;
    private TextField BeginTijd;
    private DatePicker EindDatum;
    private TextField EindTijd;

    private Button Opslaan;
    private Button home;

    private ArrayList<KlantModel> klanten;
    private ArrayList<ProjectModel> projecten;
    private ArrayList<OnderwerpModel> onderwerpen;

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
        urenRegistrerenLabel.setAlignment(Pos.CENTER);

        //  persoon = new Label(controller.getGebruiker());
        //persoon.setTextFill(Color.GREY);

        klantLabel = new Label("Klant");
        klantLabel.setTextFill(Color.GREY);
        gridpane.add(klantLabel, 0,2);

        projectLabel = new Label("Project");
        projectLabel.setTextFill(Color.GREY);
        gridpane.add(projectLabel, 0,3);

        onderwerpLabel = new Label("Onderwerp");
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

        BeginDatum = new DatePicker();
        BeginDatum.setValue(LocalDate.now());
        gridpane.add(BeginDatum, 3, 2);

        BeginTijd = new TextField();
        gridpane.add(BeginTijd, 3, 3);

        EindDatum = new DatePicker();
        EindDatum.setValue(LocalDate.now());
        gridpane.add(EindDatum, 3, 5);

        EindTijd = new TextField();
        gridpane.add(EindTijd, 3,6);

        Opslaan = new Button("Bevestigen");
        gridpane.add(Opslaan, 2,7, 2, 1);

        home = new Button("home");
        home.setAlignment(Pos.CENTER_LEFT );
        box = new HBox(100);
        box.getChildren().addAll(home, urenRegistrerenLabel);
        pane.setTop(box);

    }

    private void InitAction(){



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

        Opslaan.setOnAction(e -> {
            if(klantField.getLength() > 1 && projectField.getLength() > 1 && onderwerpField.getLength() > 1 && !BeginDatum.getValue().equals("") && BeginTijd.getLength() > 1 && !EindDatum.getValue().equals("") && EindTijd.getLength() > 1) {

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

        home.setOnAction(e -> {
        	 	controller.getHoofdMenuController().startHoofdmenuView();
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
    }
}
