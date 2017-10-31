package DAO;

import Database.DatabaseConnectie;
import Models.ProjectModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Is verantwoordelijk voor het halen van data uit de "project" tabel uit de database.
 */
public class ProjectDAO {

    private DatabaseConnectie db;
    private OnderwerpDAO onderwerpDao;

    public ProjectDAO(DatabaseConnectie db){
        this.db = db;
        onderwerpDao = new OnderwerpDAO(db);
    }

    /**
     * Haalt projecten uit de database.
     * @param klant_naam
     * @return
     */
    public ArrayList<ProjectModel> haalProjectenOp(String klant_naam)  {

        ArrayList<ProjectModel> projecten = null;
        try {
            ResultSet results;
            PreparedStatement haalProjectenOp = db.getConnection().prepareStatement("SELECT project_naam FROM project WHERE klant_naam =?;");
            haalProjectenOp.setString(1, klant_naam);
            results = haalProjectenOp.executeQuery();
            projecten = new ArrayList<>();
            while(results.next()) {
                projecten.add(new ProjectModel(results.getString("project_naam")));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        return projecten;
    }

    /**
     * Voegt niew project toe
     * @param klant
     * @param project
     * @param onderwerp
     */
    public void voegNieuwProjectToe(String klant, String project, String onderwerp) {

        try {
            PreparedStatement voegProjectToe = db.getConnection().prepareStatement("INSERT INTO project (klant_naam, project_naam) VALUES(?,?)");

            voegProjectToe.setString(1,klant);
            voegProjectToe.setString(2, project);
            voegProjectToe.executeQuery();

            onderwerpDao.voegNiewOnderwerpToe(project, onderwerp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
