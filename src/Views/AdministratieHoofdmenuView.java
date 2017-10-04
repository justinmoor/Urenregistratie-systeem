package views;

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

public class AdministratieHoofdmenuView extends Application {
	private GridPane gridpane;
	
	private HBox hbox;
		private VBox links;
			private Button uren;
			private Button week;
		
		private VBox rechts;
			private Button account;
			private Button personeel;
		
	private Scene scene;

	public void start(Stage stage) {
		gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setPadding(new Insets(25, 25, 25, 25));
		gridpane.setStyle("-fx-background-color: #f9f9f7");
		
		links = new VBox(20);
		links.setPrefWidth(200);
		links.setPrefHeight(60);
		
		uren = new Button("Uren registreren");
		uren.setMinWidth(links.getPrefWidth());
		uren.setMinHeight(links.getPrefHeight());
		
		week = new Button("Weekoverzicht");
		week.setMinWidth(links.getPrefWidth());
		week.setMinHeight(links.getPrefHeight());
		
		links.getChildren().addAll(uren, week);
		
		rechts = new VBox(20);
		rechts.setPrefWidth(200);
		rechts.setPrefHeight(60);
		
		account = new Button("Account toevoegen");
		account.setMinWidth(rechts.getPrefWidth());
		account.setMinHeight(rechts.getPrefHeight());
		
		personeel = new Button("Personeel info");
		personeel.setMinWidth(rechts.getPrefWidth());
		personeel.setMinHeight(rechts.getPrefHeight());
		
		rechts.getChildren().addAll(account, personeel);
		
		hbox = new HBox(20);
		hbox.getChildren().addAll(links, rechts);
		
		gridpane.add(hbox, 1, 0);
		
		scene = new Scene(gridpane, 600, 400);

		stage.setTitle("Hoofdmenu");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}


