package fr.univtln.ancyen.doodle;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Evenement {
    private static Collection<Evenement> evenements = new ArrayList<>();
    private String nom;
    private String lieu;
    private String description;
    private String dateCreation;
    private Utilisateur createur;
    private Collection<Date>

    public Evenement() {
        evenements.add(this);
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
