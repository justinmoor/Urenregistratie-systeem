package Views;

import Controllers.InvullenUrenController;
import javafx.scene.Scene;
import Controllers.InvullenUrenController;
import javafx.scene.layout.GridPane;


public class InvullenUrenView extends Scene {

    private InvullenUrenController urencontroller;
    private GridPane gridpane;

    public InvullenUrenView(InvullenUrenController controller) {
        super(new GridPane(), 600,  400); 		// Nieuwe pane meegeven aan de superklasse (dus scene).
        gridpane = (GridPane) this.getRoot();
        this.urencontroller = controller;
        initgui();
        initAction();
    }



    private void initgui() {

    }

    private void initAction() {

    }

}
