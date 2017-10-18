package fr.univtln.ancyen.doodle.PackageDAO;



import fr.univtln.ancyen.doodle.Evenement;
import fr.univtln.ancyen.doodle.date.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EvenementDAO extends DAO<Evenement> {

    public Evenement create(Evenement evenement) {
        try {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO EVENEMENT VALUES(?, ?, ?, ?, ?, ?, ?)"
                    );
            prepare.setLong(1, evenement.getIdEvenement());
            prepare.setString(2, evenement.getNom());
            prepare.setString(3, evenement.getLieu());
            prepare.setString(3, evenement.getDescription());
            prepare.setObject(3, evenement.getDateCreation());
            prepare.setObject(3, evenement.getDateFinalisation());
            prepare.setInt(3, evenement.getIdCreateur());

            prepare.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return evenement;
    }

    public Evenement find(Evenement evenement) {
        Evenement retour = null;
        try {
            ResultSet result = this.connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM EVENEMENT WHERE IDEVENEMENT = " + evenement.getIdEvenement()
                    );
            if(result.first())
                retour = new Evenement(
                        evenement.getIdEvenement(),
                        result.getString("nom"),
                        result.getString("lieu"),
                        result.getString(("description")),
                        (Date) result.getObject("dateCreation"),
                        (Date) result.getObject("dateFinalisation"),
                        result.getInt("idCreateur")
                );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retour;

    }


    public Evenement update(Evenement evenement) {
        try {

            this.connect
                    .createStatement()
                    .executeUpdate(
                            "UPDATE EVENEMENT SET NOM = " + evenement.getNom()
                                    + ", LIEU = " + evenement.getLieu()
                                    + ", DESCRIPTION = " + evenement.getDescription()
                                    + ", DATECREATION = " + evenement.getDateCreation()
                                    + ", DATEFINALISATION = " + evenement.getDateFinalisation()
                                    + ", IDCREATEUR = " + evenement.getIdCreateur()
                                    + " WHERE IDEVENEMENT = " + evenement.getIdEvenement()
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return evenement;
    }


    public void delete(Evenement evenement) {
        try {

            this.connect
                    .createStatement()
                    .executeUpdate(
                            "DELETE FROM EVENEMENT WHERE IDEVENEMENT = " + evenement.getIdEvenement()
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
                            "SELECT count(*) as count FROM EVENEMENT"
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
