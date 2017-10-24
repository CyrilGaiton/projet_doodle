package fr.univtln.doodle.d14.Modele;




public class Date {
    private static int cpt = 0;
    private final int idDate;
    private String date;

    public Date(String date) {
        idDate = cpt++;
        this.date = date;
    }

    public Date(int idDate, String date) {
        this.idDate = idDate;
        this.date = date;
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
}