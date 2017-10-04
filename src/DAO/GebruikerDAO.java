package DAO;

import Database.DatabaseConnectie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GebruikerDAO {

    private DatabaseConnectie db;

    public GebruikerDAO(DatabaseConnectie db) {
        this.db = db;
    }

    public void GetGebruikerFromDB(String email){

    }

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

