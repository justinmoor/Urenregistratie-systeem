package Models;

public class IngevuldeTijdModel {

 private int uurId;
 private int personeelID;
 private String beginDatum;
 private String beginTijd;
 private String eindDatum;
 private String eindTijd;
 private String klant;
 private String project;
 private String onderwerp;
 private String commentaar;
 private boolean goedgekeurd;


 //met commentaar
 public IngevuldeTijdModel(int uurId, int personeelId, String beginDatum, String beginTijd, String eindDatum, String eindTijd, String klant, String project, String onderwerp, String commentaar) {
  this.uurId = uurId;
  this.personeelID = personeelId;
  this.beginDatum = beginDatum;
  this.beginTijd = beginTijd;
  this.eindDatum = eindDatum;
  this.eindTijd = eindTijd;
  this.klant = klant;
  this.project = project;
  this.onderwerp = onderwerp;
  this.commentaar = commentaar;
 }
 public IngevuldeTijdModel(int uurId, int personeelId, String beginDatum, String beginTijd, String eindDatum, String eindTijd, String klant, String project, String onderwerp, String commentaar, boolean goedgekeurd) {
  this.uurId = uurId;
  this.personeelID = personeelId;
  this.beginDatum = beginDatum;
  this.beginTijd = beginTijd;
  this.eindDatum = eindDatum;
  this.eindTijd = eindTijd;
  this.klant = klant;
  this.project = project;
  this.onderwerp = onderwerp;
  this.commentaar = commentaar;
  this.goedgekeurd = goedgekeurd;
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

 public String getKlant() {
  return klant;
 }

 public void setKlant(String klant) {
  this.klant = klant;
 }

 public String getProject() {
  return project;
 }

 public void setProject(String project) {
  this.project = project;
 }

 public String getOnderwerp() {
  return onderwerp;
 }

 public void setOnderwerp(String onderwerp) {
  this.onderwerp = onderwerp;
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
 }
}
