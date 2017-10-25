package fr.univtln.ancyen.doodle;

import fr.univtln.ancyen.doodle.Modele.*;
import javafx.beans.property.BooleanProperty;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Controleur {

    private Facade facade;

    public Controleur(Facade facade) {
        this.facade = facade;
    }

    public Evenement getEvenement(String id) throws IOException, ClassNotFoundException {
        int idEvenement = Integer.parseInt(id);
        Evenement evenement= facade.getEvenement(idEvenement);
        return evenement;
    }

    public List<String> getDates(int idEvenement) throws IOException, ClassNotFoundException {
        List<String> dates_str = new ArrayList<>();
        List<Date> dates = facade.getDates(idEvenement);
        for (int i = 0; i < dates.size(); i++) {
            dates_str.add(dates.get(i).getDate());
        }
        return dates_str;
    }

    public ArrayList<Participant> getParticipants(int idEvenement) throws IOException, ClassNotFoundException {
        ArrayList<Participant> participants = new ArrayList<>();
        List<Utilisateur> utilisateurs = facade.getUtilisateurs(idEvenement);
        for (Utilisateur utilisateur :utilisateurs
                ) {
            System.out.println(utilisateur.getNom());
        }
        for (Utilisateur utilisateur:utilisateurs
             ) {
            participants.add(facade.getParticipant(idEvenement, utilisateur.getIdUtilisateur()));
        }
        return participants;
    }

    public int addEvenement(String nom, String lieu, String description, String duree) throws IOException, ClassNotFoundException {
        int idEvenement = facade.getNextIdEvenement();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dateCreation = new Date(facade.getNextIdDate(), dateFormat.format(new java.util.Date()));
        Evenement evenement = new Evenement(idEvenement, nom, lieu, description, dateCreation, null, duree);
        facade.addEvenement(evenement);
        return idEvenement;
    }

    public void addDates(int idEvenement, List<String> dates) throws IOException, ClassNotFoundException {
        Date date;
        for (int i=0;i<dates.size();i++){
            date = new Date(facade.getNextIdDate(), dates.get(i));
            facade.addDate(date);
            facade.addDateEvenement(new DateEvenement(idEvenement, date.getIdDate()));
        }
    }

    public void addParticipant(int idEvenement, String nom, ArrayList<BooleanProperty> boolVotes) throws IOException, ClassNotFoundException {
        Utilisateur utilisateur = new Utilisateur(facade.getNextIdUtilisateur(), nom, "");
        facade.addUtilisateur(utilisateur);

        ArrayList<Integer> idDates = facade.getIdDates(idEvenement);
        for (int i=0;i<boolVotes.size();i++){
            if (boolVotes.get(i).get()){
                addVote(idEvenement, utilisateur.getIdUtilisateur(), idDates.get(i));
            }
        }
    }

    public void addVote(int idEvenement, int idUtilisateur, int idDate) throws IOException, ClassNotFoundException {
        facade.addVote(new Vote(idEvenement, idUtilisateur, idDate));
    }

}


