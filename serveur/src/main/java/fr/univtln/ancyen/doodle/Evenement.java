package fr.univtln.ancyen.doodle;

import fr.univtln.ancyen.doodle.date.Date;
import fr.univtln.ancyen.doodle.utilisateur.Createur;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class Evenement {
    private static Collection<Evenement> evenements = new ArrayList<>();
    private String nom;
    private String lieu;
    private String description;
    private String dateCreation;
    private String dateFinalisation;
    private final Createur createur;


    public Evenement(String nom, String lieu, String description, String dateCreation, String dateFinalisation, Createur createur) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateFinalisation = dateFinalisation;
        this.createur = createur;
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

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Createur getCreateur() {
        return createur;
    }




    public static void save() throws IOException {
        FileOutputStream fos = new FileOutputStream("evenements.save");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(evenements);

        oos.close();
    }

    public static Collection<Evenement> load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("evenements.save");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Collection<Evenement> evenements = (ArrayList<Evenement>) ois.readObject();

        ois.close();
        return evenements;
    }
}
