package fr.univtln.ancyen.doodle.PackageDAO;

import fr.univtln.ancyen.doodle.date.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DateDAO extends DAO<Date> {

    public Date create(Date date) {
        try {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO date VALUES(?, ?, ?)"
                    );
            prepare.setLong(1, date.getIdDate());
            prepare.setString(2, date.getDate());
            prepare.setInt(3, date.getDureeMinute());

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
                            "SELECT * FROM date WHERE idDate = " + date.getIdDate()
                    );
            if(result.first())
                retour = new Date(
                        date.getIdDate(),
                        result.getString("date"),
                        result.getInt("dureeHeure")
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
                            "UPDATE date SET date = " + date.getDate()
                                    + ", DUREEMINUTESEVENEMENT = " + date.getDureeMinute()
                                    + " WHERE idDate = " + date.getIdDate()
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
                            "DELETE FROM date WHERE idDate = " + date.getIdDate()
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
                            "SELECT count(*) as count FROM date"
                    );
            if(result.first()){
                c = result.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }


}
