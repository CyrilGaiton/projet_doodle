package fr.univtln.ancyen.doodle.DAO;

import fr.univtln.ancyen.doodle.Vote;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VoteDAO extends DAO<Vote>{

    public Vote create(Vote obj) {
        try {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO vote VALUES(?, ?, ?)"
                    );
            prepare.setLong(1, obj.getIdEvenement());
            prepare.setInt(2, obj.getIdParticipant());
            prepare.setObject(3, obj.getVotes());

            prepare.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public Vote find(long id) {
        Vote lang = new Vote();
        try {
            ResultSet result = this.connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM langage WHERE lan_id = " + id
                    );
            if(result.first())
                lang = new Vote(
                        id,
                        result.getString("lan_nom")
                );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lang;

    }


    public Langage update(Langage obj) {
        try {

            this .connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                    "UPDATE langage SET lan_nom = '" + obj.getNom() + "'"+
                            " WHERE lan_id = " + obj.getId()
            );

            obj = this.find(obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }


    public void delete(Langage obj) {
        try {

            this    .connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                    "DELETE FROM langage WHERE lan_id = " + obj.getId()
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
