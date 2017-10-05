package Controllers;

public class GebruikerController {

    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String email;
    private char rechten;

    public GebruikerController(String voornaam, String tussenvoegsel, String achternaam, String email, char rechten) {
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.email = email;
        this.rechten = rechten;
    }
}
