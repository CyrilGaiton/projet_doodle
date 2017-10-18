package fr.univtln.ancyen.doodle.PackageDAO;

import fr.univtln.ancyen.doodle.date.DateEvenement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DateEvenementDAO extends DAO<DateEvenement> {

    public DateEvenement create(DateEvenement dateEvenement) {
        try {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO DATEEVENEMENT VALUES(?, ?)"
                    );
            prepare.setLong(1, dateEvenement.getIdEvenement());
            prepare.setInt(2, dateEvenement.getIdDate());

            prepare.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return dateEvenement;
    }

    public DateEvenement find(DateEvenement dateEvenement) {
        DateEvenement retour = null;
        try {
            ResultSet result = this.connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM DATEEVENEMENT WHERE idEvenement = " + dateEvenement.getIdEvenement()
                                    + " AND IDDATE = " + dateEvenement.getIdDate()
                    );
            if(result.first())
                retour = new DateEvenement(
                        dateEvenement.getIdEvenement(),
                        dateEvenement.getIdDate()
                );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retour;

    }


    public DateEvenement update(DateEvenement dateEvenement) {

            System.out.println("pas d'update sur cette table");

        return null;
    }


    public void delete(DateEvenement dateEvenement) {
        try {

            this.connect
                    .createStatement()
                    .executeUpdate(
                            "DELETE FROM DATEEVENEMENT WHERE idEvenement = " + dateEvenement.getIdEvenement()
                                    + " AND IDDATE = " + dateEvenement.getIdDate()
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
                            "SELECT count(*) as count FROM DATEEVENEMENT"
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
