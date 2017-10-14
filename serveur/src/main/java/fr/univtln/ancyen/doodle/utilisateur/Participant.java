package fr.univtln.ancyen.doodle.utilisateur;

import fr.univtln.ancyen.doodle.Database;


public class Participant {
    private static Database database = Database.getInstance();
    private static int cpt = 0;
    private final int id;
    private String nom;
    private String prenom;


    public Participant(String nom, String prenom) {
        id = cpt++;
        this.nom = nom;
        this.prenom = prenom;
        database.insert("Participant", id, nom, prenom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
