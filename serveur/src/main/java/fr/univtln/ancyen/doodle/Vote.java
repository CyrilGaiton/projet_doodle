package fr.univtln.ancyen.doodle;

import fr.univtln.ancyen.doodle.date.Date;


import java.util.ArrayList;
import java.util.List

public class Vote {
    private static long cpt = 0;
    private final long id;
    private int idEvenement;
    private int idParticipant;
    private  List <Date> votes = new ArrayList<>();

    public Vote(int idParticipant, int idEvenement, List<Date> votes) {
        id = cpt++;
        this.idEvenement = idEvenement;
        this.idParticipant  = idParticipant;
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
