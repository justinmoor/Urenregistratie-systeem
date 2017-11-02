package Models;

/**
 * Het model dat gemaakt wordt als klanten de database uitgehaald worden
 *
 * @author Alex de Bruin
 *
 * @version 2.0
 */

public class KlantModel {

    private String naam;

    public KlantModel(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

}
