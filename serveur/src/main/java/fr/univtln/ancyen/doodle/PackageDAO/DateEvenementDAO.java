package fr.univtln.ancyen.doodle.PackageDAO;

import fr.univtln.ancyen.doodle.Modele.DateEvenement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DateEvenementDAO extends DAO<DateEvenement> {

    public DateEvenement create(DateEvenement dateEvenement) {
        try {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO DATEEVENEMENT VALUES(?, ?)"
                    );
            prepare.setInt(1, dateEvenement.getIdEvenement());
            prepare.setInt(2, dateEvenement.getIdDate());

            prepare.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return dateEvenement;
    }

    public DateEvenement find(DateEvenement dateEvenement) {

        System.out.println("pas de find pour la table vote");
        return null;

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


    public ArrayList<Integer> getByIdEvenement(int idEvenement) throws SQLException {
        ArrayList<Integer> integers = new ArrayList<>();
        ResultSet result = this.connect
                .createStatement()
                .executeQuery(
                        "SELECT IDDATE from DATEEVENEMENT" +
                                " WHERE IDEVENEMENT = " + idEvenement
                );
        while(result.next()){
            integers.add(result.getInt("idDate"));
        }
        return integers;
    }

}
