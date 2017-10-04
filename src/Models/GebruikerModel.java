package Models;

public class GebruikerModel {

    private int gebruikerID;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String email;
    private String wachtwoord;
    private String rechten;

    @Override
    public String toString() {
        return "GebruikerModel{" +
                "gebruikerID=" + gebruikerID +
                ", voornaam='" + voornaam + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", email='" + email + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                ", rechten='" + rechten + '\'' +
                '}';
    }

    public GebruikerModel(){

    }

    public GebruikerModel(int gebruikerID, String achternaam, String tussenvoegsel, String voornaam, String email, String wachtwoord, String rechten) {
        this.gebruikerID = gebruikerID;
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.rechten = rechten;
    }

    // Mensen zonder tussenvoegsel
    public GebruikerModel(int gebruikerID, String voornaam, String achternaam, String email, String wachtwoord, String rechten) {
        this.gebruikerID = gebruikerID;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.rechten = rechten;
    }

    public int getGebruikerID() {
        return gebruikerID;
    }

    public void setGebruikerID(int gebruikerID) {
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

    public String getRechten() {
        return rechten;
    }

    public void setRechten(String rechten) {
        this.rechten = rechten;
    }
}
