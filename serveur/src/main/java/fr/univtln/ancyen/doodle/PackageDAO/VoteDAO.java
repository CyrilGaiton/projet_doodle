package fr.univtln.ancyen.doodle.PackageDAO;

import fr.univtln.ancyen.doodle.Modele.Vote;
import fr.univtln.ancyen.doodle.Modele.Date;

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
            prepare.setInt(3, vote.getIdDate());

            prepare.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return vote;
    }

    public Vote find(Vote vote) {

        System.out.println("pas de find pour la table vote");
        return null;

    }


    public Vote update(Vote vote) {

        System.out.println("pas d'update sur cette table");
        return null;
    }


    public void delete(Vote vote) {
        try {

            this.connect
                    .createStatement()
                    .executeUpdate(
                    "DELETE FROM vote WHERE idEvenement = " + vote.getIdEvenement()
                            + " AND idParticipant = " + vote.getIdParticipant()
                            + " AND  IDDATE = " + vote.getIdDate()
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int count(){
        int c = -1;
        try {
            ResultSet result = this.connect
                    .createStatement()
                    .executeQuery(
                            "SELECT count(*) as count FROM vote"
                    );
            if(result.first()){
                c = result.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public ArrayList<ArrayList<Integer>> getByIdEvenement(int idEvenement) throws SQLException {
        ArrayList<ArrayList<Integer>> outer = new ArrayList<>();
        ArrayList<Integer> inner = new ArrayList<>();
        ResultSet result = this.connect
                .createStatement()
                .executeQuery(
                        "SELECT IDPARTICIPANT, IDDATE from VOTE" +
                                " WHERE IDEVENEMENT = " + idEvenement
                );
        while(result.next()){
            inner.add(result.getInt("idParticipant"));
            inner.add(result.getInt("idDate"));
            outer.add(inner);
            inner.clear();
        }
        return outer;
    }

}
