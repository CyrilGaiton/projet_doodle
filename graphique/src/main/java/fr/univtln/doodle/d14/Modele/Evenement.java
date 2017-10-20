package fr.univtln.doodle.d14.Modele;

public class Evenement {
    private static int cpt = 0;
    private final int idEvenement;
    private String nom;
    private String lieu;
    private String description;
    private Date dateCreation;
    private Date dateFinalisation;
    private final int idCreateur;


    public Evenement(String nom, String lieu, String description, Date dateCreation, Date dateFinalisation, int idCreateur) {
        idEvenement = cpt++;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateFinalisation = dateFinalisation;
        this.idCreateur = idCreateur;
    }

    public Evenement(int idEvenement, String nom, String lieu, String description, Date dateCreation, Date dateFinalisation, int idCreateur) {
        this.idEvenement = idEvenement;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateFinalisation = dateFinalisation;
        this.idCreateur = idCreateur;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateFinalisation() {
        return dateFinalisation;
    }

    public void setDateFinalisation(Date dateFinalisation) {
        this.dateFinalisation = dateFinalisation;
    }

    public int getIdCreateur() {
        return idCreateur;
    }

//    public static void save() throws IOException {
//        FileOutputStream fos = new FileOutputStream("evenements.save");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//        oos.writeObject(evenements);
//        //rajouer les cpt; ou faire en fonctionde la liste recupérée
//
//        oos.close();
//    }
//
//    public static List<Evenement> load() throws IOException, ClassNotFoundException {
//        FileInputStream fis = new FileInputStream("evenements.save");
//        ObjectInputStream ois = new ObjectInputStream(fis);
//
//        List<Evenement> evenements = (ArrayList<Evenement>) ois.readObject();
//
//        ois.close();
//        return evenements;
//    }
}
