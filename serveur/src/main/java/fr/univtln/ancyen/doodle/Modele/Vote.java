package fr.univtln.ancyen.doodle.Modele;

import fr.univtln.ancyen.doodle.PackageDAO.VoteDAO;


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

    public static void main(String[] args) {
        VoteDAO voteDAO =  new VoteDAO();
        Vote vote = new Vote(1,1, null);
        voteDAO.create(vote);
        Vote v = voteDAO.find(vote);
        System.out.println(v.getIdEvenement() + " " + v.getIdParticipant());
    }
}
