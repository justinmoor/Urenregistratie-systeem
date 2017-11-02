package Models;

/**
 * Het model dat aangemaakt wordt elke keer dat een project uit de database gehaald wordt
 *
 * @author Alex de Bruin
 *
 * @version 2.0
 */

public class ProjectModel {

    private String project_naam;

    public ProjectModel(String project_naam) {

        this.project_naam = project_naam;
    }

    public String getProject_naam() {
        return project_naam;
    }


}
