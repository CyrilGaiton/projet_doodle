package fr.univtln.doodle.d14.Modele;


import java.io.Serializable;

public class Date implements Serializable {
    private final int idDate;
    private String date;

    public Date(int idDate, String date) {
        this.idDate = idDate;
        this.date = date;
    }

    public Date(int idDate) {
        this.idDate = idDate;
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