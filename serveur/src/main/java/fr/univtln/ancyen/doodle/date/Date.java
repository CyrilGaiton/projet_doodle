package fr.univtln.ancyen.doodle.date;


import fr.univtln.ancyen.doodle.PackageDAO.DateDAO;

public class Date {
    private static int cpt = new DateDAO().count();
    private final int idDate;
    private String date;
    private int dureeHeure;

    public Date(String date, int dureeHeure) {
        idDate = cpt++;
        this.date = date;
        this.dureeHeure = dureeHeure;
    }

    public Date(int idDate, String date, int dureeHeure) {
        this.idDate = idDate;
        this.date = date;
        this.dureeHeure = dureeHeure;
    }

    public int getIdDate() {
        return idDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDureeHeure() {
        return dureeHeure;
    }
}
