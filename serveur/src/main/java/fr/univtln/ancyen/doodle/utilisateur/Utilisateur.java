package fr.univtln.ancyen.doodle.utilisateur;


import fr.univtln.ancyen.doodle.Evenement;
import fr.univtln.ancyen.doodle.date.Date;

public class Utilisateur {
    private static int cpt = 0;
    private final int id;
    private String nom;
    private String prenom;


    public Utilisateur(String nom, String prenom) {
        id = cpt++;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void creerEvenement(String nom, String lieu, String description, Date dateCreation, Date dateFinalisation){
        new Evenement(nom, lieu, description, dateCreation, dateFinalisation, getId());
    }

}
