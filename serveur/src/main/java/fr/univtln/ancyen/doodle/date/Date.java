package fr.univtln.ancyen.doodle.date;

public class Date {
    private int jour;
    private int mois;
    private int annee;
    private int dureeHeure;

    public Date(int jour, int mois, int annee, int dureeHeure) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.dureeHeure = dureeHeure;
    }

    public int getDureeHeure() {
        return dureeHeure;
    }

    @Override
    public String toString() {
        return jour+"/"+mois+"/"+"/"+annee;
    }
}
