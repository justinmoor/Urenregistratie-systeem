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

public class InlogView extends Application {
	private GridPane gridpane;
	
	private VBox vbox;
		private Label title;

		private Label email;
		private Label password;
				
		private TextField email_input;
		private TextField password_input;
		
		private Button login;
		
	private Scene scene;

	public void start(Stage stage) {
		gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(20);
		gridpane.setVgap(15);
		gridpane.setPadding(new Insets(25, 25, 25, 25));
		gridpane.setStyle("-fx-background-color: #f9f9f7");
		
			title = new Label("Urenregistratie");
			title.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
			title.setTextFill(Color.GREY);
			gridpane.add(title, 0, 0, 2, 1);
			
			email = new Label("E-Mail:");
			email.setTextFill(Color.GREY);
			gridpane.add(email, 0, 1);
			
			email_input = new TextField();
			gridpane.add(email_input, 1, 1);
			
			password = new Label("Wachtwoord:");
			password.setTextFill(Color.GREY);
			gridpane.add(password, 0, 2);
					
			password_input = new TextField();
			gridpane.add(password_input, 1, 2);
					
			login = new Button("Log in");
			gridpane.add(login, 0, 3);
		
		scene = new Scene(gridpane, 600, 400);

		stage.setTitle("Inlog");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
    }

}
