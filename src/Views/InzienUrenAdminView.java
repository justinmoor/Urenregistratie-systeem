package Views;

import Controllers.InzienUrenAdminController;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class InzienUrenAdminView extends Scene {
    InzienUrenAdminController controller;

    public InzienUrenAdminView(InzienUrenAdminController controller){
        super(new GridPane(), 600, 600);

    }

}
