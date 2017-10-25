package fr.univtln.ancyen.doodle;

import fr.univtln.ancyen.doodle.Modele.*;
import fr.univtln.ancyen.doodle.PackageDAO.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Facade {
    private EvenementDAO evenementDAO = new EvenementDAO();
    private VoteDAO voteDAO = new VoteDAO();
    private DateDAO dateDAO = new DateDAO();
    private DateEvenementDAO dateEvenementDAO = new DateEvenementDAO();
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    public void sendEvenement(ObjectOutputStream oos, int idEvenement) throws IOException, SQLException {
        System.out.println("ecriture evenement");
        oos.writeObject("evenement");
        System.out.println("OK");
        System.out.println("ecriture instance evenement");
        oos.writeObject(new Evenement(idEvenement));
        System.out.println("OK");
        List<Integer> idDateList = dateEvenementDAO.getByIdEvenement(idEvenement);
        for (int idDate:idDateList
             ) {
            System.out.println("ecriture date");
            oos.writeObject("date");
            System.out.println("ecriture date2");
            oos.writeObject(dateDAO.find(new Date(idDate)));
            System.out.println("ecriture date evenement");
            oos.writeObject("dateEvenement");
            System.out.println("ecriture date evenement 2");
            oos.writeObject(new DateEvenement(idEvenement, idDate));
        }
        List<ArrayList<Integer>> idsList = voteDAO.getByIdEvenement(idEvenement);
        for (ArrayList<Integer> ids:idsList
                ) {
            System.out.println("ecriture utilisateur");
            oos.writeObject("utilisateur");
            System.out.println("ecriture utilisateur 2");
            oos.writeObject(utilisateurDAO.find(new Utilisateur(ids.get(0))));
            System.out.println("ecriture vote");
            oos.writeObject("vote");
            System.out.println("ecriture vote 2");
            oos.writeObject(new Vote(idEvenement, ids.get(0), ids.get(1)));
        }
    }

    public void addEvenement(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        evenementDAO.create((Evenement) ois.readObject());
    }

    public void addVote(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        voteDAO.create((Vote) ois.readObject());
    }

    public void addUtilisateur(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        utilisateurDAO.create((Utilisateur) ois.readObject());
    }

    public void addDate(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        dateDAO.create((Date) ois.readObject());
    }

    public void addDateEvenement(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        dateEvenementDAO.create((DateEvenement) ois.readObject());
    }

    public void sendNextIdEvenement(ObjectOutputStream oos) throws IOException {
        oos.writeObject(evenementDAO.getNextId());
    }

    public void sendNextIdDate(ObjectOutputStream oos) throws IOException {
        oos.writeObject(dateDAO.getNextId());
    }

    public void sendNextIdUtilisateur(ObjectOutputStream oos) throws IOException {
        oos.writeObject(utilisateurDAO.getNextId());
    }
}
