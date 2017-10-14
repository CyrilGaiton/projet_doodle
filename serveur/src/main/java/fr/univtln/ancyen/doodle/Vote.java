package fr.univtln.ancyen.doodle;

import fr.univtln.ancyen.doodle.date.Date;
import fr.univtln.ancyen.doodle.utilisateur.Participant;


import java.util.ArrayList;
import java.util.List;

public class Vote {

    private int idEvenement;
    private int idParticipant;
    private  List <Date> votes = new ArrayList<Date>();

    public Vote(int idParticipant, int idEvenement, List<Date> votes) {


        this.idEvenement = idEvenement;
        this.idParticipant  = idParticipant;
        this.votes = votes;
    }


}
