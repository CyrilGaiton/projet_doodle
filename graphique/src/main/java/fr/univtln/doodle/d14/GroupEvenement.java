package fr.univtln.doodle.d14;

import fr.univtln.doodle.d14.Modele.*;

import java.util.ArrayList;
import java.util.List;

public class GroupEvenement {
    private Evenement evenement;
    private List<Vote> listVote = new ArrayList<>();
    private List<DateEvenement> listDateEvenement = new ArrayList<>();
    private List<Utilisateur> listUtilisateur = new ArrayList<>();
    private List<Date> listDate = new ArrayList<>();

    public Evenement getEvenement() {
        return evenement;
    }

    public List<Vote> getListVote() {
        return listVote;
    }

    public List<DateEvenement> getListDateEvenement() {
        return listDateEvenement;
    }

    public List<Utilisateur> getListUtilisateur() {
        return listUtilisateur;
    }

    public List<Date> getListDate() {
        return listDate;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public void addVote(Vote vote) {
        listVote.add(vote);
    }

    public void addDateEvenement(DateEvenement dateEvenement) {
        listDateEvenement.add(dateEvenement);
    }

    public void addUtilisateur(Utilisateur utilisateur) {
        listUtilisateur.add(utilisateur);
    }

    public void addDate(Date date) {
        listDate.add(date);
    }

    public Date findDate(int idDate){
        for (Date date:listDate
             ) {
            if (date.getIdDate() == idDate){
                return date;
            }
        }
        return null;
    }

    public Utilisateur findUtilisateur(int idUtilisateur){
        for (Utilisateur utilisateur:listUtilisateur
                ) {
            if (utilisateur.getIdUtilisateur() == idUtilisateur){
                return utilisateur;
            }
        }
        return null;
    }
}
