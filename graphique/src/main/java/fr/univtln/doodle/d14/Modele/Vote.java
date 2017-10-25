package fr.univtln.doodle.d14.Modele;

import java.io.Serializable;

public class Vote implements Serializable{
    private final int idEvenement;
    private final int idParticipant;
    private final int idDate;

    public Vote(int idEvenement, int idParticipant, int idDate) {
        this.idEvenement = idEvenement;
        this.idParticipant = idParticipant;
        this.idDate= idDate;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    public int getIdDate() {
        return idDate;
    }
}
