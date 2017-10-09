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
}
