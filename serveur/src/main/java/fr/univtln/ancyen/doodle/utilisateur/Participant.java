package fr.univtln.ancyen.doodle.utilisateur;


public class Participant {
    private static int cpt = 0;
    private final int id;
    private String nom;
    private String prenom;


    public Participant(String nom, String prenom) {
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

}
