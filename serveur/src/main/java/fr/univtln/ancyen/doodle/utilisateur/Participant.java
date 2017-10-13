package fr.univtln.ancyen.doodle.utilisateur;

public class Participant {
    private static int cpt = 0;
    private final int id;
    private String nom;
    private String prenom;


    public Participant(String nom, String prenom) {
        this.id = cpt++;
        this.nom = nom;
        this.prenom = prenom;
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
