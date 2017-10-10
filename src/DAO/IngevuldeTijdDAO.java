package DAO;

import Database.DatabaseConnectie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngevuldeTijdDAO {


    private DatabaseConnectie db;

    public IngevuldeTijdDAO (DatabaseConnectie db) {
        this.db = db;
    }

    public ArrayList haalKlantenOp() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/UrenregistratieDatabase?user=root&password=ipsen123");
        ArrayList<String> klant_namen = null;
        try {
            ResultSet results;
            PreparedStatement haalKlantenOp = conn.prepareStatement("SELECT klant_naam FROM klant;");
                results = haalKlantenOp.executeQuery();
            klant_namen = new ArrayList<String>();
            while (results.next()) {
                klant_namen.add(results.getString("klant_naam"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        conn.close();
        return klant_namen;
    }

    public ArrayList haalProjectenOp() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/UrenregistratieDatabase?user=root&password=ipsen123");
        ArrayList<String> projecten = null;
        try {
            ResultSet results;
            PreparedStatement haalProjectenOp = conn.prepareStatement("SELECT project_naam FROM project;");
            results = haalProjectenOp.executeQuery();
            projecten = new ArrayList<String>();
            while(results.next()) {
                projecten.add(results.getString("project_naam"));
           }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        conn.close();
        return projecten;
    }

    public ArrayList haalOnderwerpenOp() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/UrenregistratieDatabase?user=root&password=ipsen123");
        ArrayList<String> onderwerpen = null;
        try {
            ResultSet results;
            PreparedStatement haalOnderwerpenOp = conn.prepareStatement("SELECT onderwerp_naam FROM onderwerp;");
            results = haalOnderwerpenOp.executeQuery();
            onderwerpen = new ArrayList<String>();
            while (results.next()) {
                onderwerpen.add(results.getString("onderwerp_naam"));
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        conn.close();
        return onderwerpen;
    }

}
