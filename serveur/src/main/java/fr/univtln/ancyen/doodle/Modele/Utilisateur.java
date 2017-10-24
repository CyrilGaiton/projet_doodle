package fr.univtln.ancyen.doodle.Modele;


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
