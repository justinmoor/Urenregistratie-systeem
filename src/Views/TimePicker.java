package Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * De view om een tijd te selecteren vanuit het invullenurenview
 *
 * @author Alex de Bruin
 * @author Stan Hoenson
 *
 * @version 3.0
 *
 */

public class TimePicker extends Stage{

    private String soort;
    private InvullenUrenView urenView;

    private Stage newStage;
    private GridPane pane;
    private BorderPane borderpane;
    private Scene scene;

    private Label titel;

    private int urenTeller;
    private int minTeller;

    private Label uren;
    private Label min;
    private Button urenOmhoog;
    private Button urenOmlaag;
    private Button minutenOmhoog;
    private Button minutenOmlaag;

    private Image imageOmhoog;
    private ImageView arrowUpuren;
    private ImageView arrowMin;
    private Image imageOmlaag;
    private ImageView arrowdownuren;
    private ImageView arrowdownmin;

    private BorderPane navigatie;

    private StackPane mid_pane;
    private Label home;

    private VBox bottom;

    private Image img2;
    private ImageView lijntje;

    private Image img3;
    private ImageView blauw_lijntje;

    private Button opslaan;

    public TimePicker(InvullenUrenView urenView, String soort) {
        this.urenView = urenView;
        this.soort = soort;

        initGui();
        initActions();
        newStage.show();

    }

    private void initGui() {

        newStage = new Stage();
        newStage.setAlwaysOnTop(true);
        navigatie = new BorderPane();

        mid_pane = new StackPane();
		mid_pane.setPrefWidth(260);
		home = new Label("KIES TIJD");
		home.setId("home");
		home.setPadding(new Insets(15, 0, 15, 0));
		mid_pane.getChildren().add(home);

		img2 = new Image("/Assets/lijntje.png");
		lijntje = new ImageView(img2);
		lijntje.setFitWidth(260);

		navigatie.setCenter(mid_pane);
		navigatie.setBottom(lijntje);

		bottom = new VBox(40);

		opslaan = new Button("OPSLAAN");
		opslaan.setId("opslaan-time");

		img3 = new Image("/Assets/blauwlijntje.png");
		blauw_lijntje = new ImageView(img3);
		blauw_lijntje.setFitWidth(260);

		//bottom.getChildren().addAll(blauw_lijntje);

        borderpane = new BorderPane();
        borderpane.setStyle("-fx-background-image: url('/Assets/timepicker_cheat.png')");

        pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(15);

        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(0, 10, 0, 0));

        borderpane.setTop(navigatie);
        borderpane.setCenter(pane);
        borderpane.setBottom(blauw_lijntje);

        urenTeller = Integer.parseInt(LocalTime.now().format(DateTimeFormatter.ofPattern("hh")));
        minTeller = Integer.parseInt(LocalTime.now().format(DateTimeFormatter.ofPattern("mm")));


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfUur = new SimpleDateFormat("HH");
        SimpleDateFormat sdfMinute = new SimpleDateFormat("mm");
        urenTeller = Integer.parseInt(sdfUur.format(cal.getTime()));
        minTeller = Integer.parseInt((sdfMinute.format(cal.getTime())));

        uren = new Label("" + urenTeller);
        min = new Label("" + minTeller);
        uren.setPadding(new Insets(0, 0, 0, 9));
        min.setPadding(new Insets(0, 0, 0, 9));

        imageOmhoog = new Image("Assets/up.png");
        arrowUpuren = new ImageView(imageOmhoog);
        arrowUpuren.setFitWidth(16);
        arrowUpuren.setFitHeight(16);

        imageOmlaag = new Image("Assets/down.png");
        arrowdownuren = new ImageView(imageOmlaag);
        arrowdownuren.setFitHeight(16);
        arrowdownuren.setFitWidth(16);

        urenOmhoog = new Button();
        urenOmhoog.setId("button-timepicker");
        urenOmhoog.setGraphic(arrowUpuren);
        urenOmlaag = new Button();
        urenOmlaag.setId("button-timepicker");
        urenOmlaag.setGraphic(arrowdownuren);

        arrowMin = new ImageView(imageOmhoog);
        arrowMin.setFitWidth(16);
        arrowMin.setFitHeight(16);

        imageOmlaag = new Image("Assets/down.png");
        arrowdownmin = new ImageView(imageOmlaag);
        arrowdownmin.setFitHeight(16);
        arrowdownmin.setFitWidth(16);

        minutenOmhoog = new Button();
        minutenOmhoog.setGraphic(arrowMin);
        minutenOmhoog.setId("button-timepicker");
        minutenOmlaag = new Button();
        minutenOmlaag.setId("button-timepicker");
        minutenOmlaag.setGraphic(arrowdownmin);

        opslaan = new Button("opslaan");

        pane.add(urenOmhoog, 1, 1);
        pane.add(minutenOmhoog, 2, 1);
        pane.add(uren, 1, 2);
        pane.add(min, 2, 2);
        pane.add(urenOmlaag, 1, 3);
        pane.add(minutenOmlaag, 2, 3);
        pane.add(opslaan, 1, 4, 2, 2);

        scene = new Scene(borderpane, 260, 300);
        newStage.setScene(scene);

        scene.getStylesheets().add("Views/styles.css");

    }

    private void initActions() {

        opslaan.setOnAction(e -> {
            if (Integer.parseInt(min.getText()) < 10) {
                String tijd = "0"+ Integer.parseInt(min.getText());
                min.setText(tijd);
            }
            if (soort.equals("BeginTijd")) {
                String tijd = uren.getText() + ":"  + min.getText();
                urenView.setBeginTijd("  " + tijd);
            } else if (soort.equals("EindTijd")) {
                String tijd = uren.getText() +":"+ min.getText();
                urenView.setEindTijd("  " + tijd);
            }
            newStage.close();
        });

        urenOmhoog.setOnAction(e -> {

            if (Integer.parseInt(uren.getText()) < 24) {
                urenTeller = Integer.parseInt(uren.getText()) + 1;
                if (urenTeller < 10) {
                    String tijd = "0"+ urenTeller;
                    uren.setText(tijd);
                } else {
                    uren.setText("" + urenTeller);
                }
            } else {
                uren.setText("01");

            }
        });

        urenOmlaag.setOnAction(e -> {

            if (Integer.parseInt(uren.getText()) > 01) {
                urenTeller = Integer.parseInt(uren.getText()) - 1;
                if (urenTeller < 10) {
                    String tijd = "0" + urenTeller;
                    uren.setText(tijd);
                } else {
                    uren.setText("" + urenTeller);
                }
            } else {
                uren.setText("24");
            }
        });

        minutenOmhoog.setOnAction(e -> {
            if (Integer.parseInt(min.getText()) < 59) {
                minTeller = Integer.parseInt(min.getText()) + 1;
                if (minTeller < 10) {
                    String tijd = "0" + minTeller;
                    min.setText(tijd);
                } else {
                    min.setText("" + minTeller);
                }
            } else {
                min.setText("00");
            }
        });

        minutenOmlaag.setOnAction(e -> {
            if (Integer.parseInt(min.getText()) > 00) {
                minTeller = Integer.parseInt(min.getText()) - 1;
                if (minTeller < 10) {
                    String tijd = "0" + minTeller;
                    min.setText(tijd);
                } else {
                    min.setText("" + minTeller);
                }
            } else {
                min.setText("59");
            }
        });


    }

}
