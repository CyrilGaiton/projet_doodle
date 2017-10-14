package fr.univtln.ancyen.doodle.date;

import java.util.ArrayList;
import java.util.List;

public class Date {
    private static List<Date> dates = new ArrayList<>();
    private static int cpt;
    private final int id;
    private int jour;
    private int mois;
    private int annee;
    private int dureeHeure;

    public Date(int jour, int mois, int annee, int dureeHeure) {
        this.id = cpt++;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.dureeHeure = dureeHeure;
        dates.add(this);
    }

    public int getDureeHeure() {
        return dureeHeure;
    }

    @Override
    public String toString() {
        return jour+"/"+mois+"/"+"/"+annee;
    }
}
