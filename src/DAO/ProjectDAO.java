package DAO;

import Database.DatabaseConnectie;
import Models.ProjectModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectDAO {
    DatabaseConnectie db;

    public ProjectDAO(DatabaseConnectie db){
        this.db = db;
    }

    public ArrayList<ProjectModel> haalProjectenOp(String klant_naam) throws SQLException {

        ArrayList<ProjectModel> projecten = null;
        try {
            ResultSet results;
            PreparedStatement haalProjectenOp = db.getConnection().prepareStatement("SELECT project_naam FROM project WHERE klant_naam =?;");
            haalProjectenOp.setString(1, klant_naam);
            results = haalProjectenOp.executeQuery();
            projecten = new ArrayList<>();
            while(results.next()) {
                System.out.println("Vul de arraylist met projectmodels");
                projecten.add(new ProjectModel(results.getString("project_naam")));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        return projecten;
    }
}
