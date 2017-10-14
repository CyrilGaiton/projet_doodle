package fr.univtln.ancyen.doodle.date;

public class DateEvenement {
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
