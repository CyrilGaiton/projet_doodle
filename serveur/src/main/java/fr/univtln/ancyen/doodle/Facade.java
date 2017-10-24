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
        oos.writeObject("evenement");
        oos.writeObject(new Evenement(idEvenement));
        List<Integer> idDateList = dateEvenementDAO.getByIdEvenement(idEvenement);
        for (int idDate:idDateList
             ) {
            oos.writeObject("date");
            oos.writeObject(dateDAO.find(new Date(idDate)));
            oos.writeObject("dateEvenement");
            oos.writeObject(new DateEvenement(idEvenement, idDate));
        }
        List<ArrayList<Integer>> idsList = voteDAO.getByIdEvenement(idEvenement);
        for (ArrayList<Integer> ids:idsList
                ) {
            oos.writeObject("utilisateur");
            oos.writeObject(utilisateurDAO.find(new Utilisateur(ids.get(0))));
            oos.writeObject("vote");
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
        oos.writeInt(evenementDAO.getNextId());
    }

    public void sendNextIdDate(ObjectOutputStream oos) throws IOException {
        oos.writeInt(dateDAO.getNextId());
    }

    public void sendNextIdUtilisateur(ObjectOutputStream oos) throws IOException {
        oos.writeInt(utilisateurDAO.getNextId());
    }
}
