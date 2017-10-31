package DAO;

import Database.DatabaseConnectie;
import Models.GebruikerModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GebruikerDAO {

    private DatabaseConnectie db;

    public GebruikerDAO(DatabaseConnectie db) {
        this.db = db;
    }

    // Accepteert een email string en geeft de bijbehorende gebruiker uit de database terug.
    public GebruikerModel GetGebruikerFromDB(String email) {
        GebruikerModel model = new GebruikerModel();
        try {
            PreparedStatement getGebruiker = db.getConnection().prepareStatement("SELECT * FROM personeel WHERE email =?");
            getGebruiker.setString(1, email);

            ResultSet results = getGebruiker.executeQuery();

            if (results.next()) {
                if (results.getString("tussenvoegsel") != null) {
                    model = new GebruikerModel(results.getInt("persoonID"), results.getString("achternaam"), results.getString("tussenvoegsel"), results.getString("voornaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"), results.getString("werkzaam"));
                } else {
                    model = new GebruikerModel(results.getInt("persoonID"), results.getString("achternaam"), results.getString("voornaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"), results.getString("werkzaam"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    /**
     * Deze methode krijgt de data of de gebruiker die wilt inloggen werkzaam is.
     * @param email
     * @return Werkzaam string
     */
    public String getWerkzaam(String email) {
        String werkzaam = "";

        try {
            PreparedStatement statement = db.getConnection().prepareStatement("SELECT werkzaam FROM personeel WHERE email = ?");
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                werkzaam = result.getString("werkzaam");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return werkzaam;
    }

    /**
     * Haalt het wachtwoord uit de database om in te kunnen loggen
     * @param email
     * @return Wachtwoord string
     */
    public String getWachtwoordQuery(String email) {
        String wachtwoord = "";

        try {
            PreparedStatement statement = db.getConnection().prepareStatement("SELECT wachtwoord FROM personeel WHERE email =?");
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                wachtwoord = result.getString("wachtwoord");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wachtwoord;
    }

    /**
     * Maakt een nieuw account aan.
     * @param voornaam
     * @param tussenvoegsel
     * @param achternaam
     * @param email
     * @param rechten
     */
    public void insertAccount(String voornaam, String tussenvoegsel, String achternaam, String email, String rechten) {
        String query = "INSERT INTO personeel (achternaam, tussenvoegsel, voornaam, email, rechten) VALUES (?, ?, ?, ?, ?);";

        try {
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, achternaam);

            if (tussenvoegsel.equals("")) {
                statement.setString(2, null);
            } else {
                statement.setString(2, tussenvoegsel);
            }
            statement.setString(3, voornaam);
            statement.setString(4, email);
            statement.setString(5, rechten);

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Alle accounts krijgen
    private ArrayList<GebruikerModel> gebruikers;

    /**
     * Verkrijg alle accounts uit de database om een overzicht te maken
     * @return Arraylist van alle accounts
     */
    public ArrayList getAllAccount() {
        gebruikers = new ArrayList<>();
        String query = "SELECT persoonID, voornaam, tussenvoegsel, achternaam, email, rechten, werkzaam FROM personeel;";

        try {
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                GebruikerModel gebruiker = new GebruikerModel();
                gebruiker.setGebruikerID(result.getInt("persoonID"));
                gebruiker.setVoornaam(result.getString("voornaam"));
                gebruiker.setTussenvoegsel(result.getString("tussenvoegsel"));
                gebruiker.setAchternaam(result.getString("achternaam"));
                gebruiker.setEmail(result.getString("email"));

                if (result.getString("rechten").equals("1")) {
                    gebruiker.setRechten(("Administrator"));
                } else {
                    gebruiker.setRechten("Personeel");
                }

                if (result.getString("werkzaam").equals("1")) {
                    gebruiker.setWerkzaam("Ja");
                } else {
                    gebruiker.setWerkzaam("Nee");
                }

                gebruikers.add(gebruiker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gebruikers;
    }

    /**
     * Zet een bepaalde gebruiker op actief (werkzaam)
     * @param model van de gebruiker
     */
    public void setWerkzaam(GebruikerModel model) {
        try {
            PreparedStatement werkzaam = db.getConnection().prepareStatement("UPDATE personeel SET werkzaam = 1 WHERE persoonID = ?;");
            werkzaam.setInt(1, model.getGebruikerID());
            werkzaam.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Zet een bepaalde gebruiker op inactief (niet werkzaam)
     * @param model van de gebruiker
     */
    public void setNietWerkzaam(GebruikerModel model) {
        try {
            PreparedStatement werkzaam = db.getConnection().prepareStatement("UPDATE personeel SET werkzaam = 0 WHERE persoonID = ?;");
            werkzaam.setInt(1, model.getGebruikerID());
            werkzaam.executeQuery();

            System.out.println("Werkt " + model.getGebruikerID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Wijzigt het wachtwoord
     * @param gebruikerModel
     * @param nieuwWachtwoord
     */
    public void setgebruikerWachtwoord(GebruikerModel gebruikerModel, String nieuwWachtwoord) {

        try {
            PreparedStatement wijzigWachtwoord = db.getConnection().prepareStatement("UPDATE personeel SET wachtwoord = (?) WHERE persoonID = (?);");
            wijzigWachtwoord.setString(1, nieuwWachtwoord);
            wijzigWachtwoord.setInt(2, gebruikerModel.getGebruikerID());

            wijzigWachtwoord.executeQuery();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}

