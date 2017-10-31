package Views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;

import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimePicker extends Stage{

    private String soort;
    private InvullenUrenView urenView;

    private Button opslaan;
    private Stage newStage;
    private GridPane pane;
    private Scene scene;

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

    public TimePicker(InvullenUrenView urenView, String soort) {
        this.urenView = urenView;
        this.soort = soort;

        initGui();
        initActions();

        newStage.show();

    }

    private void initGui() {

        newStage = new Stage();
        pane = new GridPane();

        urenTeller = Integer.parseInt(LocalTime.now().format(DateTimeFormatter.ofPattern("hh")));
        minTeller = Integer.parseInt(LocalTime.now().format(DateTimeFormatter.ofPattern("mm")));

        uren = new Label("" + urenTeller);
        min = new Label("" + minTeller);

        imageOmhoog = new Image("Assets/up.png");
        arrowUpuren = new ImageView(imageOmhoog);
        arrowUpuren.setFitWidth(16);
        arrowUpuren.setFitHeight(16);

        imageOmlaag = new Image("Assets/down.png");
        arrowdownuren = new ImageView(imageOmlaag);
        arrowdownuren.setFitHeight(16);
        arrowdownuren.setFitWidth(16);

        urenOmhoog = new Button();
        urenOmhoog.setGraphic(arrowUpuren);
        urenOmlaag = new Button();
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
        minutenOmlaag = new Button();
        minutenOmlaag.setGraphic(arrowdownmin);

        opslaan = new Button("opslaan");

        pane.add(urenOmhoog, 1, 1);
        pane.add(minutenOmhoog, 2, 1);
        pane.add(uren, 1, 2);
        pane.add(min, 2, 2);
        pane.add(urenOmlaag, 1, 3);
        pane.add(minutenOmlaag, 2, 3);
        pane.add(opslaan, 3, 4, 3, 3);

        scene = new Scene(pane, 300, 300);
        newStage.setScene(scene);

    }

    private void initActions() {

        opslaan.setOnAction(e -> {
            if (Integer.parseInt(min.getText()) < 10) {
                String tijd = "0"+ Integer.parseInt(min.getText());
                min.setText(tijd);
            }
            if (soort.equals("BeginTijd")) {
                String tijd = uren.getText() + " : "  + min.getText();
                urenView.setBeginTijd(tijd);
            } else if (soort.equals("EindTijd")) {
                String tijd = uren.getText() +" : "+ min.getText();
                urenView.setEindTijd(tijd);
            }
        });

        urenOmhoog.setOnAction(e -> {

            if (Integer.parseInt(uren.getText()) < 25) {
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


    }

}
