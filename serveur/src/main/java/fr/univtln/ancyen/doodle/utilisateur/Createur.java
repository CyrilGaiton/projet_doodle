package fr.univtln.ancyen.doodle.utilisateur;

import fr.univtln.ancyen.doodle.Evenement;
import fr.univtln.ancyen.doodle.date.Date;

public class Createur extends Participant{

    public Createur(String nom, String prenom) {
        super(nom, prenom);
    }

    public void creerEvenement(String nom, String lieu, String description, Date dateCreation, Date dateFinalisation){
        new Evenement(nom, lieu, description, dateCreation, dateFinalisation, this);
    }

}
