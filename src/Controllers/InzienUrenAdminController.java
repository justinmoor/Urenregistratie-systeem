package Controllers;

import DAO.IngevuldeTijdDAO;
import DAO.KlantDAO;
import DAO.OnderwerpDAO;
import DAO.ProjectDAO;
import Database.DatabaseConnectie;
import Models.IngevuldeTijdModel;
import Models.KlantModel;
import Models.OnderwerpModel;
import Models.ProjectModel;
import Views.InzienUrenAdminView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InzienUrenAdminController {

    private final String ERRORMESSAGENORESULTS = "De zoekopdracht heeft geen resultaten opgeleverd";

    /**
     * Maak alle hulpklassen aan.
     */
    Stage stage;
    DatabaseConnectie dbc;
    InzienUrenAdminView view;
    IngevuldeTijdDAO ingevuldetijdDAO;
    ProjectDAO projectDAO;
    OnderwerpDAO onderwerpDAO;
    ResultSet results;
    ArrayList<IngevuldeTijdModel> resultatenlijst;
    HoofdMenuController hoofdMenuController;
    KlantDAO klantDAO = new KlantDAO(dbc);
    private final String COMMA = ";";

    /**
     * Is de klasse die de InzienUrenAdminView onderhoudt.
     * Krijgt DatabaseConnectie mee, zodat deze kan worden doorgegeven aan de DAO.
     * @param dbc
     */
    public  InzienUrenAdminController(Stage stage, DatabaseConnectie dbc, HoofdMenuController hoofdMenuController){
        this.dbc = dbc;
        this.hoofdMenuController = hoofdMenuController;
        ingevuldetijdDAO = new IngevuldeTijdDAO(dbc);
        onderwerpDAO = new OnderwerpDAO(dbc);
        view = new InzienUrenAdminView(this);
        this.klantDAO = new KlantDAO(dbc);
        this.projectDAO = new ProjectDAO(dbc);

        stage = stage;
        stage.show();
        stage.setScene(view);
        view.setPersoonLabel(hoofdMenuController.getGebruikerModel().getVolledigeNaam());
        vulKlantenEntries();
    }

    /**
     * Maakt van de dataset die ook aanwezig is in de view een CSV file.
     * @throws Exception
     */
    public void writeExcel() throws Exception {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(view.getBegindatum()+"-"+view.getEinddatum()+"gewerkte_uren.csv");
        File saveFile = fileChooser.showSaveDialog(new Stage());
        PrintWriter printWriter = new PrintWriter(saveFile);
        String goedgekeurd = "";

        String firstString = "uur ID"+COMMA+"Begindatum"+COMMA+"Einddatum"+ COMMA+ "Begintijd"+COMMA+"Eindtijd"+ COMMA+ "Commentaar"+ COMMA+ "Goedgekeurd"+ COMMA+ "Personeelslid"+ COMMA+ "Klant"+ COMMA+ "Project"+ COMMA+ "Onderwerp"+"\n";
        printWriter.write(firstString);
        for (IngevuldeTijdModel model :resultatenlijst) {
            if (model.isGoedgekeurd()){
                goedgekeurd = "ja";
            } else{
                goedgekeurd = "nee";
            }
            printWriter.write(model.getUurId()+COMMA+model.getBeginDatum()+COMMA+model.getEindDatum()+COMMA+model.getBeginTijd()+COMMA+model.getEindTijd()+ COMMA+ model.getCommentaar()+ COMMA+ goedgekeurd +COMMA+model.getPersoonNaam()+COMMA+ model.getKlantNaam()+COMMA+model.getProjectNaam()+COMMA+model.getOnderwerpNaam()+ "\n");
        }
        printWriter.close();
    }

    /**
     * Wordt uitgevoerd wanneer de 'Ververs' knop wordt ingedrukt.
     * Krijgt een ResultSet van de DAO, maakt IngevuldeTijdModels van de resultset en voert deze door naar de view.
     */
    public void verversButtonPressed(){
        results = ingevuldetijdDAO.getAdminOverzicht(view.getBegindatum(), view.getEinddatum(), view.getKlantnaam(), view.getProjectnaam(), view.getOnderwerpnaam());
        makeModelsFromResultSet(results);

        if(resultatenlijst.isEmpty()){
            Alert legeResultatenLijstAlert = new Alert(Alert.AlertType.ERROR, ERRORMESSAGENORESULTS);
            legeResultatenLijstAlert.show();
        } else {
            makeTableViewFromArrayList();
        }
    }

    /**
     * Krijgt een ResultSet van de geregistreerde tijden, vult hier de resultatenlijst ArrayList mee.
     * @param results
     */
    private void makeModelsFromResultSet(ResultSet results) {
        try {
            if(results.next()){
                resultatenlijst = new ArrayList<>();
                do{
                    String personeelsNaam;
                    if(results.getString("tussenvoegsel")!=null) {
                        personeelsNaam = results.getString("voornaam") +" "+ results.getString("tussenvoegsel") + " "+ results.getString("achternaam");
                    }else{
                        personeelsNaam = results.getString("voornaam")+" "+ results.getString("achternaam");
                    }
                    resultatenlijst.add(new IngevuldeTijdModel(
                            results.getInt("uurID"),
                            results.getString("begindatum"),
                            results.getString("einddatum"),
                            results.getString("begintijd"),
                            results.getString("eindtijd"),
                            results.getString("commentaar"),
                            results.getBoolean("goedgekeurd"),
                            results.getInt("persoonID"),
                            results.getString("klant_naam"),
                            results.getString("project_naam"),
                            results.getString( "onderwerp_naam"),
                            personeelsNaam
                    ));

                    System.out.println(results.getString("klant_naam"));
                } while(results.next());
            } else{
                resultatenlijst = new ArrayList<>();
            }
            results.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Roept de view aan en vult de TableView met de data uit de resultatenlijst ArrayList.
     */
    private void makeTableViewFromArrayList(){
        ObservableList<IngevuldeTijdModel> records = FXCollections.observableArrayList(resultatenlijst);
        view.vulTabelUitLijst(records);
    }

    /**
     * Ontvangt een lijst van models die geselecteerd zijn in de view, verandert de goedgekeurd boolean
     * naar true en stuurt ze vervolgens een voor een door naar de ingevuldetijdDAO om weggeschreven te worden.
     * @param models
     */
    public void keurGoed(List<IngevuldeTijdModel> models){
        for (IngevuldeTijdModel model:models
             ) {
            model.setGoedgekeurd(true);
            ingevuldetijdDAO.veranderGoedgekeurdKolom(model);
        }
    }

    public HoofdMenuController getHoofdMenuController() {
        return hoofdMenuController;
    }

    /**
     * Vult de dictionary van de autocomplete in de view voor de klanteninput.
     */
    private void vulKlantenEntries(){
        ArrayList<String> klanten = new ArrayList<>();
        try {
            for (KlantModel klant:klantDAO.haalKlantenOp()) {
                klanten.add(klant.getNaam());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            view.setKlanten(klanten);

    }

    /**
     * Vult de dictionary van de autocomplete in de view voor de projecteninput.
     * @param klantNaam
     */
    public void vulProjectenEntries(String klantNaam){
        ArrayList<String> projecten = new ArrayList<>();
        try {
            for (ProjectModel project:projectDAO.haalProjectenOp(klantNaam)) {
                System.out.println(project.getProject_naam());
                projecten.add(project.getProject_naam());
            }
            projectDAO.haalProjectenOp(klantNaam);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.setProjecten(projecten);
    }

    /**
     * Vult de dictionary van de autocomplete in de view voor de onderwerpinput.
     * @param projectnaam
     */
    public void vulOnderwerpenEntries( String projectnaam){
        ArrayList<String> onderwerpen = new ArrayList<>();

        try {
            for (OnderwerpModel onderwerp:onderwerpDAO.haalOnderwerpenOp(projectnaam)) {
                onderwerpen.add(onderwerp.getOnderwerp_naam());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.setOnderwerpen(onderwerpen);
    }
}
