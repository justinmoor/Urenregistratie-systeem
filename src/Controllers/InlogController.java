package Controllers;

import DAO.GebruikerDAO;
import Database.DatabaseConnectie;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InlogController {

    DatabaseConnectie db = new DatabaseConnectie();

    @FXML
    public Button logInButton = new Button();

    @FXML
    public TextField naam = new TextField();

    @FXML
    public PasswordField ww = new PasswordField();

    GebruikerDAO dao = new GebruikerDAO();

    public void logIn(){
        if(dao.query(naam.getText(), ww.getText())){
            System.out.println("Nice");
        }
    }
}
