package DAO;

import Database.DatabaseConnectie;
import Models.GebruikerModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GebruikerDAO {

    private DatabaseConnectie db;

    public GebruikerDAO(DatabaseConnectie db) {
        this.db = db;
    }


    // Accepteert een email string en geeft de bijbehorende gebruiker uit de database terug.
    public GebruikerModel GetGebruikerFromDB(String email){
        GebruikerModel model = new GebruikerModel();
        try {
            PreparedStatement getGebruiker = db.getConnection().prepareStatement("SELECT * FROM personeel WHERE email =?");
            getGebruiker.setString(1, email);

            ResultSet results = getGebruiker.executeQuery();

            if(results.next() ){
                if(results.getString("tussenvoegsel")!=null){
                    model = new GebruikerModel(results.getInt("persoonID"), results.getString("achternaam"), results.getString("tussenvoegsel"), results.getString("voornaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"));
                } else{
                    model = new GebruikerModel(results.getInt("persoonID"), results.getString("achternaam"), results.getString("voornaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"));
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
    //Accepteert een email string en geeft het bijbehorende wachtwoord terug.
    public String getWachtwoordQuery(String email) {
        String wachtwoord = "";

        try {
            PreparedStatement statement = db.getConnection().prepareStatement("SELECT wachtwoord FROM personeel WHERE email =?");
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                wachtwoord = result.getString("wachtwoord");
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return wachtwoord;
    }
}

