package fr.univtln.ancyen.doodle;

import fr.univtln.ancyen.doodle.date.Date;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class Evenement {
    private static Collection<Evenement> evenements = new ArrayList<>();
    private String nom;
    private String lieu;
    private String description;
    private String dateCreation;
    private Utilisateur createur;
    private Collection<Date> dates = new ArrayList<>();

    public Evenement() {
        evenements.add(this);
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

    public Utilisateur getCreateur() {
        return createur;
    }

    public void setCreateur(Utilisateur createur) {
        this.createur = createur;
    }

    public Collection<Date> getDates() {
        return dates;
    }

    public void addDate(Date date){
        dates.add(date);
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
