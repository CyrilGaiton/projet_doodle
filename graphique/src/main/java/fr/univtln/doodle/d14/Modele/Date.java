package fr.univtln.doodle.d14.Modele;




public class Date {
    private static int cpt = 0;
    private final int idDate;
    private String date;
    private int dureeMinute;

    public Date(String date, int dureeMinute) {
        idDate = cpt++;
        this.date = date;
        this.dureeMinute = dureeMinute;
    }

    public Date(int idDate, String date, int dureeHeure) {
        this.idDate = idDate;
        this.date = date;
        this.dureeMinute = dureeHeure;
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

    public int getDureeMinute() {
        return dureeMinute;
    }
}