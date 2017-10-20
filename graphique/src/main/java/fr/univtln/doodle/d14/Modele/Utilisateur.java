package fr.univtln.doodle.d14.Modele;


import fr.univtln.doodle.d14.Modele.Date;

public class Utilisateur {
    private static int cpt = 0;
    private final int idUtilisateur;
    private String nom;
    private String prenom;


    public Utilisateur(String nom, String prenom) {
        idUtilisateur = cpt++;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Utilisateur(int idUtilisateur, String nom, String prenom) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }


}
