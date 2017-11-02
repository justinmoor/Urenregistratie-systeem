package Services;

import Models.IngevuldeTijdModel;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * De klasse die het mogelijk maakt om een overzicht naar een csv bestand te exporteren
 *
 * @author Stan Hoenson
 * @author Ian Beemsterboer
 *
 * @version 3.0
 */

public class ExcelService {
    private final String COMMA = ";";
    public ExcelService(){
    }

    public void exportCSV(String begindatum, String einddatum, ArrayList<IngevuldeTijdModel> resultatenlijst){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(begindatum + "-" + einddatum + "gewerkte_uren.csv");
        File saveFile = fileChooser.showSaveDialog(new Stage());
        if (saveFile != null) {
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(saveFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String goedgekeurd = "";
            String firstString = "uur ID" + COMMA + "Begindatum" + COMMA + "Einddatum" + COMMA + "Begintijd" + COMMA + "Eindtijd" + COMMA + "Commentaar" + COMMA + "Goedgekeurd" + COMMA + "Personeelslid" + COMMA + "Klant" + COMMA + "Project" + COMMA + "Onderwerp" + "\n";
            printWriter.write(firstString);
            for (IngevuldeTijdModel model : resultatenlijst) {
                if (model.isGoedgekeurd()) {
                    goedgekeurd = "ja";
                } else {
                    goedgekeurd = "nee";
                }
                printWriter.write(model.getUurId() + COMMA + model.getBeginDatum() + COMMA + model.getEindDatum() + COMMA + model.getBeginTijd() + COMMA + model.getEindTijd() + COMMA + model.getCommentaar() + COMMA + goedgekeurd + COMMA + model.getPersoonNaam() + COMMA + model.getKlantNaam() + COMMA + model.getProjectNaam() + COMMA + model.getOnderwerpNaam() + "\n");
            }
            printWriter.close();
        }
    }
}
