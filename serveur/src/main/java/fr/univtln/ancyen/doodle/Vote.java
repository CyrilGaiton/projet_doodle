package fr.univtln.ancyen.doodle;

import fr.univtln.ancyen.doodle.date.Date;


import java.util.ArrayList;
import java.util.List

public class Vote {
    private final long idEvenement;
    private final long idParticipant;
    private List <Date> votes = new ArrayList<>();

    public Vote(long idEvenement, long idParticipant, List<Date> votes) {
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
