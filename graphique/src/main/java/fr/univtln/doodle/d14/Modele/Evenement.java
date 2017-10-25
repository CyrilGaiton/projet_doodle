package fr.univtln.doodle.d14.Modele;

import java.io.Serializable;

public class Evenement implements Serializable{
    private static int cpt = 0;
    private final int idEvenement;
    private String nom;
    private String lieu;
    private String description;
    private Date dateCreation;
    private Date dateFinalisation;
    private String duree;


    public Evenement(String nom, String lieu, String description, Date dateCreation, Date dateFinalisation, String duree) {
        idEvenement = cpt++;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateFinalisation = dateFinalisation;
        this.duree = duree;
    }

    public Evenement(int idEvenement, String nom, String lieu, String description, Date dateCreation, Date dateFinalisation, String duree) {
        this.idEvenement = idEvenement;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Date getDateFinalisation() {
        return dateFinalisation;
    }

    public void setDateFinalisation(Date dateFinalisation) {
        this.dateFinalisation = dateFinalisation;
    }

    public String getDuree() {
        return duree;
    }

}
