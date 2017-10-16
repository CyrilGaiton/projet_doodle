package fr.univtln.ancyen.doodle;

import fr.univtln.ancyen.doodle.date.Date;
import fr.univtln.ancyen.doodle.utilisateur.Createur;

import java.io.*;

public class Evenement {
    private static long cpt = 0;
    private final int id;
    private String nom;
    private String lieu;
    private String description;
    private Date dateCreation;
    private Date dateFinalisation;
    private final int idCreateur;


    public Evenement(String nom, String lieu, String description, Date dateCreation, Date dateFinalisation, int idCreateur) {
        id = cpt++;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateFinalisation = dateFinalisation;
        this.idCreateur = idCreateur;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateFinalisation() {
        return dateFinalisation;
    }

    public void setDateFinalisation(Date dateFinalisation) {
        this.dateFinalisation = dateFinalisation;
    }




    public static void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("evenements.save");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(evenements);
        //rajouer les cpt; ou faire en fonctionde la liste recupérée

        oos.close();
    }

    public static List<Evenement> load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("evenements.save");
        ObjectInputStream ois = new ObjectInputStream(fis);

        List<Evenement> evenements = (ArrayList<Evenement>) ois.readObject();

        ois.close();
        return evenements;
    }
}
