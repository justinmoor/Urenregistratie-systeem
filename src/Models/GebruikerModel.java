package Models;


public class GebruikerModel {

    private int gebruikerID;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String email;
    private String wachtwoord;
    private String rechten;
    private String werkzaam;

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
                ", werkzaam='" + werkzaam + '\'' +
                '}';
    }

    public GebruikerModel(){

    }

    public GebruikerModel(int gebruikerID, String achternaam, String tussenvoegsel, String voornaam, String email, String wachtwoord, String rechten, String werkzaam) {
        this.gebruikerID = gebruikerID;
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.rechten = rechten;
        this.werkzaam = werkzaam;
    }

    // Mensen zonder tussenvoegsel
    public GebruikerModel(int gebruikerID, String achternaam, String voornaam, String email, String wachtwoord, String rechten, String werkzaam) {
        this.gebruikerID = gebruikerID;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.rechten = rechten;
        this.werkzaam = werkzaam;
    }

    public String getWerkzaam(){
        return werkzaam;
    }

    public void setWerkzaam(String werkzaam){
        this.werkzaam = werkzaam;
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

    public String getVolledigeNaam() {
        if (tussenvoegsel != null) {
            return voornaam + " " + tussenvoegsel + " " + achternaam;
        } else {
            return voornaam + " " + achternaam;
        }

    }
}
