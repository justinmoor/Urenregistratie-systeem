package Views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PersoneelHoofdmenuView extends Application {
	private GridPane gridpane;
	
	private VBox vbox;
		
	private Button uren;
	private Button week;
		
	private Scene scene;

	public void start(Stage stage) {
		gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setPadding(new Insets(25, 25, 25, 25));
		gridpane.setStyle("-fx-background-color: #f9f9f7");
		
		vbox = new VBox(20);
		vbox.setPrefWidth(270);
		vbox.setPrefHeight(70);
		
		uren = new Button("Uren registreren");
		uren.setMinWidth(vbox.getPrefWidth());
		uren.setMinHeight(vbox.getPrefHeight());
		
		week = new Button("Weekoverzicht");
		week.setMinWidth(vbox.getPrefWidth());
		week.setMinHeight(vbox.getPrefHeight());
		
		vbox.getChildren().addAll(uren, week);
		
		gridpane.add(vbox, 1, 0);
		
		scene = new Scene(gridpane, 600, 400);

		stage.setTitle("Hoofdmenus");
		stage.setScene(scene);
		stage.show();
	}
	


}

