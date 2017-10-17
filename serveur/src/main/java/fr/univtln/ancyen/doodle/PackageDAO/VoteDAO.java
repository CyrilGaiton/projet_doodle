package fr.univtln.ancyen.doodle.PackageDAO;

import fr.univtln.ancyen.doodle.DAO;
import fr.univtln.ancyen.doodle.Vote;
import fr.univtln.ancyen.doodle.date.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class VoteDAO extends DAO<Vote> {

    public Vote create(Vote vote) {
        try {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO vote VALUES(?, ?, ?)"
                    );
            prepare.setLong(1, vote.getIdEvenement());
            prepare.setInt(2, vote.getIdParticipant());
            prepare.setObject(3, vote.getVotes());

            prepare.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return vote;
    }

    public Vote find(Vote vote) {
        Vote retour = null;
        try {
            ResultSet result = this.connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM vote WHERE idEvenement = " + vote.getIdEvenement()
                            + " AND idParticipant = " + vote.getIdParticipant()
                    );
            if(result.first())
                retour = new Vote(
                        vote.getIdEvenement(),
                        vote.getIdParticipant(),
                        (ArrayList<Date>) result.getObject("votes")
                );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retour;

    }


    public Vote update(Vote vote) {
        try {

            this.connect
                    .createStatement()
                    .executeUpdate(
                    "UPDATE vote SET votes = " + vote.getVotes()
                            + " WHERE idEvenement = " + vote.getIdEvenement()
                            + " AND idParticipant = " + vote.getIdParticipant()
            );
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vote;
    }


    public void delete(Vote vote) {
        try {

            this.connect
                    .createStatement()
                    .executeUpdate(
                    "DELETE FROM vote WHERE idEvenement = " + vote.getIdEvenement()
                            + " AND idParticipant = " + vote.getIdParticipant()
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
