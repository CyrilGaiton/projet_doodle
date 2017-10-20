package fr.univtln.ancyen.doodle.Modele;


import fr.univtln.ancyen.doodle.PackageDAO.UtilisateurDAO;

public class Utilisateur {
    private static int cpt = new UtilisateurDAO().count();
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

    public void creerEvenement(String nom, String lieu, String description, Date dateCreation, Date dateFinalisation){
        new Evenement(nom, lieu, description, dateCreation, dateFinalisation, getIdUtilisateur());
    }

}
