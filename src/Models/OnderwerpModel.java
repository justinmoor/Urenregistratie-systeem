package Models;

/**
 * Het model dat gemaakt wordt elke keer dat een onderwerp de database uitghaald wordt
 *
 * @author Alex de Bruin
 * @version 2.0
 */

public class OnderwerpModel {


    private String onderwerp_naam;

    public OnderwerpModel(String onderwerp_naam) {
        this.onderwerp_naam = onderwerp_naam;
    }

    public String getOnderwerp_naam() {
        return onderwerp_naam;
    }

}
