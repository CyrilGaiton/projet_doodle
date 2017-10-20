package fr.univtln.ancyen.doodle.PackageDAO;


import fr.univtln.ancyen.doodle.Modele.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UtilisateurDAO extends DAO<Utilisateur> {

    public Utilisateur create(Utilisateur utilisateur) {
        try {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO UTILISATEUR VALUES(?, ?, ?)"
                    );
            prepare.setLong(1, utilisateur.getIdUtilisateur());
            prepare.setString(2, utilisateur.getNom());
            prepare.setString(3, utilisateur.getPrenom());

            prepare.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateur;
    }

    public Utilisateur find(Utilisateur utilisateur) {
        Utilisateur retour = null;
        try {
            ResultSet result = this.connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM UTILISATEUR WHERE IDUTILISATEUR = " + utilisateur.getIdUtilisateur()
                    );
            if(result.first())
                retour = new Utilisateur(
                        utilisateur.getIdUtilisateur(),
                        result.getString("nom"),
                        result.getString("prenom")
                );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retour;

    }


    public Utilisateur update(Utilisateur utilisateur) {
        try {

            this.connect
                    .createStatement()
                    .executeUpdate(
                            "UPDATE UTILISATEUR SET NOM = " + utilisateur.getIdUtilisateur()
                                    + ", PRENOM = " + utilisateur.getPrenom()
                                    + " WHERE IDUTILISATEUR = " + utilisateur.getIdUtilisateur()
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateur;
    }


    public void delete(Utilisateur utilisateur) {
        try {

            this.connect
                    .createStatement()
                    .executeUpdate(
                            "DELETE FROM date WHERE idDate = " + utilisateur.getIdUtilisateur()
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
                            "SELECT count(*) as count FROM UTILISATEUR"
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
