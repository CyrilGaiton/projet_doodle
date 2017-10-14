package fr.univtln.ancyen.doodle.date;


import fr.univtln.ancyen.doodle.Database;

public class Date {
    private static int cpt;
    private final int id;
    private int jour;
    private int mois;
    private int annee;
    private int dureeHeure;

    public Date(int jour, int mois, int annee, int dureeHeure) {
        id = cpt++;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.dureeHeure = dureeHeure;
    }

    public int getId() {
        return id;
    }

    public int getJour() {
        return jour;
    }

    public int getMois() {
        return mois;
    }

    public int getAnnee() {
        return annee;
    }

    public int getDureeHeure() {
        return dureeHeure;
    }
}
