package fr.univtln.doodle.d14;

import fr.univtln.doodle.d14.Modele.Date;
import fr.univtln.doodle.d14.Modele.Evenement;
import fr.univtln.doodle.d14.Modele.Utilisateur;

import java.io.IOException;
import java.util.ArrayList;

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

    public ArrayList<Date> getDates(String id) throws IOException, ClassNotFoundException {
        int idEvenement = Integer.parseInt(id);
        ArrayList<Date> dates = facade.getDates(idEvenement);
        return dates;
    }

    public ArrayList<Participant> getParticipants(String id) throws IOException, ClassNotFoundException {
        int idEvenement = Integer.parseInt(id);
        ArrayList<Participant> participants = new ArrayList<>();
        ArrayList<Utilisateur> utilisateurs = facade.getUtilisateurs(idEvenement);
        for (Utilisateur utilisateur:utilisateurs
             ) {
            participants.add(facade.getParticipant(idEvenement, utilisateur.getIdUtilisateur()));
        }
        return participants;
    }

    public void addEvenement(String nom, String lieu, String description, Date dateCreation, Date dateFinalisation) throws IOException, ClassNotFoundException {
        Evenement evenement = new Evenement(nom, lieu, description, dateCreation, dateFinalisation);
        facade.addEvenement(evenement);
    }


    public void updatevote() {}



}


