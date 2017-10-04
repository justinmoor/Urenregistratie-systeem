package Models;

public class GebruikerModel {

    private String gebruikerID;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String email;
    private String wachtwoord;
    private int rechten;

    public GebruikerModel(String gebruikerID, String voornaam, String tussenvoegsel, String achternaam, String email, String wachtwoord, int rechten) {
        this.gebruikerID = gebruikerID;
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.rechten = rechten;
    }

    // Mensen zonder tussenvoegsel
    public GebruikerModel(String gebruikerID, String voornaam, String achternaam, String email, String wachtwoord, int rechten) {
        this.gebruikerID = gebruikerID;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.rechten = rechten;
    }

    public String getGebruikerID() {
        return gebruikerID;
    }

    public void setGebruikerID(String gebruikerID) {
        this.gebruikerID = gebruikerID;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public int getRechten() {
        return rechten;
    }

    public void setRechten(int rechten) {
        this.rechten = rechten;
    }
}
