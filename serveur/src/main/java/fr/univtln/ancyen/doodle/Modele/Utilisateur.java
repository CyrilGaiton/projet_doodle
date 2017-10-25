package fr.univtln.ancyen.doodle.Modele;


import java.io.Serializable;

public class Utilisateur implements Serializable{
    private final int idUtilisateur;
    private String nom;
    private String prenom;

    public Utilisateur(int idUtilisateur, String nom, String prenom) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Utilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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
