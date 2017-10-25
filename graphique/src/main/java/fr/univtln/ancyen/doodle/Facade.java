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


    public Facade () {
        this.seConnecter();
    }


    public void seConnecter() {

        try {
            System.out.println("En attente connexion");
            client = new Socket("localhost", 5700);
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

        Participant participant = new Participant(listGroupEvenements.get(idEvenement).findUtilisateur(idUtilisateur).getNom(), idsDates.size());

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
        ArrayList<Integer> ids = new ArrayList<>();
        for (DateEvenement dateEvenement:listGroupEvenements.get(idEvenement).getListDateEvenement()
                ) {
            if (dateEvenement.getIdEvenement() == idEvenement){
                ids.add(dateEvenement.getIdDate());
            }
        }
        return ids;
    }


    public void addEvenement(Evenement evenement) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject("addEvenement");
        oos.writeObject(evenement);

        oos.close();
    }

    public void addDate(Date date) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject("addDate");
        oos.writeObject(date);

        oos.close();
    }

    public void addUtilisateur(Utilisateur utilisateur) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject("addUtilisateur");
        oos.writeObject(utilisateur);

        oos.close();
    }

    public void addVote(Vote vote) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject("addVote");
        oos.writeObject(vote);

        oos.close();
    }

    public void addDateEvenement(DateEvenement dateEvenement) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject("addDateEvenement");
        oos.writeObject(dateEvenement);

        oos.close();
    }


//Pas sur du type de retour de cette fonction. Une arraylist des 5 arraylists utilisees ici ou simplement une instance de la classe Evenement ?
    
    public void loadEvenement(int idEvenement) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

        oos.writeObject("getEvenement");
        oos.writeObject(idEvenement);

        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

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
        System.out.println("stop");
        oos.close();
        ois.close();
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
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject("getNextIdEvenement");

        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        Integer idEvenement = (Integer) ois.readObject();
        oos.close();
        ois.close();
        return idEvenement;
    }

    public int getNextIdDate() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject("getNextIdDate");

        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        Integer idDate = (Integer) ois.readObject();
        oos.close();
        ois.close();
        return idDate;
    }

    public int getNextIdUtilisateur() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject("getNextIdUtilisateur");

        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        Integer idUtilisateur = (Integer) ois.readObject();
        oos.close();
        ois.close();
        return idUtilisateur;
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
