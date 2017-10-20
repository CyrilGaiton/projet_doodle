package fr.univtln.doodle.d14.Modele;



import java.util.ArrayList;
import java.util.List;

public class Vote {
    private final int idEvenement;
    private final int idParticipant;
    private List <Date> votes = new ArrayList<>();

    public Vote(int idEvenement, int idParticipant, List<Date> votes) {
        this.idEvenement = idEvenement;
        this.idParticipant = idParticipant;
        this.votes = votes;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    public List<Date> getVotes() {
        return votes;
    }


}