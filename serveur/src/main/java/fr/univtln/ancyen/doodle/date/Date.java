package fr.univtln.ancyen.doodle.date;


import fr.univtln.ancyen.doodle.Database;

public class Date {
    private static Database database = Database.getInstance();
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
        database.insert("Date", id, jour, mois, annee, dureeHeure);
    }

}
