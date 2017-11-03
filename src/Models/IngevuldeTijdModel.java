package Models;

/**
 * Het model dat gemaakt wordt elke keer als er tijd uit de database gehaald wordt
 *
 * @author Alex de Bruin
 * @author Ian beemsterboer
 * @author Justin Moor
 *
 * @version 3.0
 */

public class IngevuldeTijdModel {

 private int uurId;
 private int personeelID;
 private String beginDatum;
 private String beginTijd;
 private String eindDatum;
 private String eindTijd;
 private String klantNaam;
 private String projectNaam;
 private String onderwerpNaam;
 private String commentaar;
 private boolean goedgekeurd;
 private String persoonNaam;
 private boolean isChanged = false;


 private String goedgekeurdString;

 //met commentaar
 public IngevuldeTijdModel(int uurId, int personeelId, String beginDatum, String beginTijd, String eindDatum, String eindTijd, String klantNaam, String projectNaam, String onderwerpNaam, String commentaar) {
  this.uurId = uurId;
  this.personeelID = personeelId;
  this.beginDatum = beginDatum;
  this.beginTijd = beginTijd;
  this.eindDatum = eindDatum;
  this.eindTijd = eindTijd;
  this.klantNaam = klantNaam;
  this.projectNaam = projectNaam;
  this.onderwerpNaam = onderwerpNaam;
  this.commentaar = commentaar;
 }

 public IngevuldeTijdModel(int uurId, String beginDatum, String eindDatum, String beginTijd,  String eindTijd, String commentaar,  boolean goedgekeurd, int personeelId,  String klantNaam, String projectNaam, String onderwerpNaam, String persoonNaam) {
  this.uurId = uurId;
  this.personeelID = personeelId;
  this.beginDatum = beginDatum;
  this.beginTijd = beginTijd;
  this.eindDatum = eindDatum;
  this.eindTijd = eindTijd;
  this.klantNaam = klantNaam;
  this.projectNaam = projectNaam;
  this.onderwerpNaam = onderwerpNaam;
  this.commentaar = commentaar;
  this.goedgekeurd = goedgekeurd;
  this.persoonNaam = persoonNaam;
  if(goedgekeurd){
   this.goedgekeurdString = "Ja";
  } else{
   this.goedgekeurdString = "Nee";
  }
 }

 public int getUurId() {
  return uurId;
 }

 public void setUurId(int uurId) {
  this.uurId = uurId;
 }

 public int getPersoneelID() {
  return personeelID;
 }

 public void setPersoneelID(int personeelID) {
  this.personeelID = personeelID;
 }

 public String getBeginDatum() {
  return beginDatum;
 }

 public void setBeginDatum(String beginDatum) {
  this.beginDatum = beginDatum;
 }

 public String getBeginTijd() {
  return beginTijd;
 }

 public void setBeginTijd(String beginTijd) {
  this.beginTijd = beginTijd;
 }

 public String getEindDatum() {
  return eindDatum;
 }

 public void setEindDatum(String eindDatum) {
  this.eindDatum = eindDatum;
 }

 public String getEindTijd() {
  return eindTijd;
 }

 public void setEindTijd(String eindTijd) {
  this.eindTijd = eindTijd;
 }

 public String getKlantNaam() {
  return klantNaam;
 }

 public void setKlantNaam(String klantNaam) {
  this.klantNaam = klantNaam;
 }

 public String getProjectNaam() {
  return projectNaam;
 }

 public void setProjectNaam(String projectNaam) {
  this.projectNaam = projectNaam;
 }

 public String getOnderwerpNaam() {
  return onderwerpNaam;
 }

 public void setOnderwerpNaam(String onderwerpNaam) {
  this.onderwerpNaam = onderwerpNaam;
 }

 public String getCommentaar() {
  return commentaar;
 }

 public void setCommentaar(String commentaar) {
  this.commentaar = commentaar;
 }

 public boolean isGoedgekeurd() {
  return goedgekeurd;
 }

 public void setGoedgekeurd(boolean goedgekeurd) {
  this.goedgekeurd = goedgekeurd;
  if(goedgekeurd){
   setGoedgekeurdString("Ja");
  } else{
   setGoedgekeurdString("Nee");
  }
 }

 public boolean isChanged() {
  return isChanged;
 }

 public String getPersoonNaam() {
  return persoonNaam;
 }

 public void setPersoonNaam(String persoonNaam) {
  this.persoonNaam = persoonNaam;
 }


 public void setChanged(boolean changed) {
  isChanged = changed;
 }

 public String getGoedgekeurdString() {
  return goedgekeurdString;
 }

 public void setGoedgekeurdString(String goedgekeurdString) {
  this.goedgekeurdString = goedgekeurdString;
 }

}


