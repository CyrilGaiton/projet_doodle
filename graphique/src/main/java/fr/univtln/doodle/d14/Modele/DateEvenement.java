package fr.univtln.ancyen.doodle.Modele;

import java.io.Serializable;

public class DateEvenement implements Serializable{
    private final int idEvenement;
    private final int idDate;

    public DateEvenement(int idEvenement, int idDate) {
        this.idEvenement = idEvenement;
        this.idDate = idDate;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public int getIdDate() {
        return idDate;
    }
}
