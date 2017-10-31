package Views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimePicker extends Stage{

    private Stage newStage;
    private GridPane pane;
    private Scene scene;

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

    public TimePicker(Label label) {

        initGui();
        initActions();
        newStage.show();

    }

    private void initGui() {

        newStage = new Stage();
        pane = new GridPane();

        uren = new Label(LocalTime.now().format(DateTimeFormatter.ofPattern("hh")));
        min = new Label(LocalTime.now().format(DateTimeFormatter.ofPattern("mm")));

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

        pane.add(urenOmhoog, 1, 1);
        pane.add(minutenOmhoog, 2, 1);
        pane.add(uren, 1, 2);
        pane.add(min, 2, 2);
        pane.add(urenOmlaag, 1, 3);
        pane.add(minutenOmlaag, 2, 3);

        scene = new Scene(pane, 300, 300);
        newStage.setScene(scene);
        newStage.show();


        Button opslaan = new Button();
    }

    private void initActions() {



    }
}
