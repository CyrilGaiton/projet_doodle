package fr.univtln.ancyen.doodle.PackageDAO;

import fr.univtln.ancyen.doodle.DAO;
import fr.univtln.ancyen.doodle.Date;
import fr.univtln.ancyen.doodle.date.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DateDAO extends DAO<Date> {

    public Date create(Date date) {
        try {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO date VALUES(?, ?, ?)"
                    );
            prepare.setLong(1, date.getIdDate());
            prepare.setInt(2, date.getDate());
            prepare.setObject(3, date.getDureeHeure());

            prepare.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return date;
    }

    public Date find(Date date) {
        Date retour = null;
        try {
            ResultSet result = this.connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM vote WHERE idEvenement = " + date.getIdDate()
                    );
            if(result.first())
                retour = new Date(
                        date.getIdDate(),
                        result.getTimestamp("date").toString(),
                        (ArrayList<Date>) result.getObject("votes")
                );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retour;

    }


    public Date update(Date date) {
        try {

            this.connect
                    .createStatement()
                    .executeUpdate(
                            "UPDATE vote SET date = " + date.getDate()
                                    + " dureeHeure = " + date.getDureeHeure()
                                    + " WHERE idEvenement = " + date.getIdDate()
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return date;
    }


    public void delete(Date date) {
        try {

            this.connect
                    .createStatement()
                    .executeUpdate(
                            "DELETE FROM vote WHERE idEvenement = " + date.getIdDate()
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int count(){
        try {
            this.connect
                    .createStatement()
                    .executeQuery(
                            "SELECT count(*) FROM date"
                    )
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
