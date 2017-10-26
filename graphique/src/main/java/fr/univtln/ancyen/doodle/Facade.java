package fr.univtln.ancyen.doodle;

import fr.univtln.ancyen.doodle.Modele.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade {

    private Map<Integer, GroupEvenement> listGroupEvenements = new HashMap<>();
    private Socket client;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;


    public Facade () {
        this.seConnecter();
    }


    public void seConnecter() {

        try {
            System.out.println("En attente connexion");
            client = new Socket("localhost", 5700);
            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("connexion réussie");
    }


    public Evenement getEvenement(int idEvenement) throws IOException, ClassNotFoundException {
        Evenement evenement = findEvenement(idEvenement);
        if(evenement != null)
            return evenement;
        else {
            System.out.println("load evenement");
            loadEvenement(idEvenement);
            System.out.println("find evenement");
            evenement = findEvenement(idEvenement);
            return evenement;
        }
    }

    public List<Date> getDates(int idEvenement){
        return listGroupEvenements.get(idEvenement).getListDate();
    }

    public List<Utilisateur> getUtilisateurs(int idEvenement){
        return listGroupEvenements.get(idEvenement).getListUtilisateur();
    }

    public Participant getParticipant(int idEvenement, int idUtilisateur){
        List<Integer> idsDates = new ArrayList<>();
        List<Date> dates = getDates(idEvenement);
        for (Date date:dates
             ) {
            idsDates.add(date.getIdDate());
        }

        Participant participant = new Participant(idUtilisateur, listGroupEvenements.get(idEvenement).findUtilisateur(idUtilisateur).getNom(), idsDates.size());

        for (Vote vote:listGroupEvenements.get(idEvenement).getListVote()
                ) {
            if (vote.getIdEvenement() == idEvenement) {
                if (vote.getIdParticipant() == idUtilisateur) {
                    participant.setVote(true, idsDates.indexOf(vote.getIdDate()));
                }
            }
        }
        return participant;
    }

    public ArrayList<Integer> getIdDates(int idEvenement){
        System.out.println("DEBUT GET ID DATES");
        ArrayList<Integer> ids = new ArrayList<>();
        System.out.println(listGroupEvenements.get(idEvenement));
        System.out.println("1 passé");
        System.out.println(listGroupEvenements.get(idEvenement).getListDateEvenement());
        for (DateEvenement dateEvenement:listGroupEvenements.get(idEvenement).getListDateEvenement()
                ) {
            if (dateEvenement.getIdEvenement() == idEvenement){
                ids.add(dateEvenement.getIdDate());
            }
        }
        return ids;
    }


    public void addEvenement(Evenement evenement) throws IOException, ClassNotFoundException {
        oos.writeObject("addEvenement");
        oos.writeObject(evenement);

        oos.flush();
    }

    public void addDate(Date date) throws IOException, ClassNotFoundException {
        oos.writeObject("addDate");
        oos.writeObject(date);

        oos.flush();
    }

    public void addUtilisateur(Utilisateur utilisateur) throws IOException, ClassNotFoundException {
        oos.writeObject("addUtilisateur");
        oos.writeObject(utilisateur);

        oos.flush();
    }

    public void addVote(Vote vote) throws IOException, ClassNotFoundException {
        oos.writeObject("addVote");
        oos.writeObject(vote);

        oos.flush();
    }

    public void addDateEvenement(DateEvenement dateEvenement) throws IOException {
        oos.writeObject("addDateEvenement");
        oos.writeObject(dateEvenement);

        oos.flush();
    }


//Pas sur du type de retour de cette fonction. Une arraylist des 5 arraylists utilisees ici ou simplement une instance de la classe Evenement ?
    
    public void loadEvenement(int idEvenement) throws IOException, ClassNotFoundException {

        oos.writeObject("getEvenement");
        oos.writeObject(idEvenement);
        oos.flush();

        listGroupEvenements.put(idEvenement, new GroupEvenement());

        String s = (String) ois.readObject();
        while(!s.equals("stop")) {
            if (s.equals("evenement")) {
                System.out.println("evenement");
                Evenement evenement = (Evenement) ois.readObject();
                listGroupEvenements.get(idEvenement).setEvenement(evenement);
            }
            else if (s.equals("date")) {
                System.out.println("date");
                Date date = (Date) ois.readObject();
                listGroupEvenements.get(idEvenement).addDate(date);
            }

            else if (s.equals("dateEvenement")) {
                System.out.println("dateEvenement");
                DateEvenement dateEvenement = (DateEvenement) ois.readObject();
                listGroupEvenements.get(idEvenement).addDateEvenement(dateEvenement);
            }

            else if (s.equals("utilisateur")) {
                System.out.println("utilisateur");
                Utilisateur utilisateur = (Utilisateur) ois.readObject();
                System.out.println(utilisateur.getNom());
                listGroupEvenements.get(idEvenement).addUtilisateur(utilisateur);
            }

            else if (s.equals("vote")){
                System.out.println("vote");
                Vote vote = (Vote) ois.readObject();
                listGroupEvenements.get(idEvenement).addVote(vote);
            }

            s = (String) ois.readObject();
        }
        System.out.println("stop load");
    }


    public Evenement findEvenement(int idEvenement){
        if (listGroupEvenements.containsKey(idEvenement)){
            return listGroupEvenements.get(idEvenement).getEvenement();
        }
    return null;
    }

    public Date findDate(int idEvenement, int idDate){
        if (listGroupEvenements.containsKey(idEvenement)){
            return listGroupEvenements.get(idEvenement).findDate(idDate);
        }
        return null;
    }

    public Utilisateur findUtilisateur(int idEvenement, int idUtilisateur){
        if (listGroupEvenements.containsKey(idEvenement)){
            return listGroupEvenements.get(idEvenement).findUtilisateur(idUtilisateur);
        }
        return null;
    }

    public int getNextIdEvenement() throws IOException, ClassNotFoundException {
        oos.writeObject("getNextIdEvenement");
        oos.flush();

        Integer idEvenement = (Integer) ois.readObject();
        return idEvenement;
    }

    public int getNextIdDate() throws IOException, ClassNotFoundException {
        oos.writeObject("getNextIdDate");
        oos.flush();

        Integer idDate = (Integer) ois.readObject();
        return idDate;
    }

    public int getNextIdUtilisateur() throws IOException, ClassNotFoundException {
        oos.writeObject("getNextIdUtilisateur");
        oos.flush();

        Integer idUtilisateur = (Integer) ois.readObject();
        return idUtilisateur;
    }

    public void supVotes(int idEvenement, int idUtilisateur) throws IOException {
        oos.writeObject("supVotes");
        oos.writeObject(idEvenement);
        oos.writeObject(idUtilisateur);
    }

//    public void test() throws IOException {
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(bout);
//
//        oos.writeInt(1);
//        oos.flush();
//


//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        buffer.put(bout.toByteArray());
//        int read = client.write(buffer);
//        System.out.println(read + "read------------------");
//
//        oos.close();
//    }
//
//    public void test2() throws IOException {
//        ObjectOutputStream oos = new ObjectOutputStream(Channels.newOutputStream(clientSocket));
//        oos.writeInt(42);
//        oos.close();
//    }

}
