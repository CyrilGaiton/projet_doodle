package fr.univtln.ancyen.doodle.Modele;


import fr.univtln.ancyen.doodle.PackageDAO.UtilisateurDAO;

public class Utilisateur {
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
