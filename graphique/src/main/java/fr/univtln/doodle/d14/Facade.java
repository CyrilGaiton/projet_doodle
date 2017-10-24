package fr.univtln.doodle.d14;

import fr.univtln.doodle.d14.Modele.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Facade {

    private Map<Integer, GroupEvenement> listGroupEvenements = new HashMap<>();
    private SocketChannel clientSocket;


    public Facade () {
        this.seConnecter();
    }


    public void seConnecter() {

        try {
            clientSocket = SocketChannel.open(new InetSocketAddress("localhost", 5625));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Evenement getEvenement(int idEvenement) throws IOException, ClassNotFoundException {
        Evenement evenement = findEvenement(idEvenement);
        if(evenement != null)
            return evenement;
        else {
            loadEvenement(idEvenement);
            evenement = findEvenement(idEvenement);
            return evenement;
        }
    }

    public ArrayList<Date> getDates(int idEvenement){
        ArrayList<Date> dates = new ArrayList<>();
        for (DateEvenement dateEvenement:listGroupEvenements.get(idEvenement).getListDateEvenement()
             ) {
            if (dateEvenement.getIdEvenement() == idEvenement){
                dates.add(findDate(idEvenement, dateEvenement.getIdDate()));
            }
        }
        return dates;
    }

    public ArrayList<Utilisateur> getUtilisateurs(int idEvenement){
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        for (Vote vote:listGroupEvenements.get(idEvenement).getListVote()
                ) {
            if (vote.getIdEvenement() == idEvenement){
                if (integers.contains(vote.getIdParticipant())) {
                    integers.add(vote.getIdParticipant());
                    utilisateurs.add(findUtilisateur(idEvenement, vote.getIdParticipant()));
                }
            }
        }
        return utilisateurs;
    }

    public Participant getParticipant(int idEvenement, int idUtilisateur){
        ArrayList<Integer> idsDates = new ArrayList<>();
        ArrayList<Date> dates = getDates(idEvenement);
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


    public void addEvenement(Evenement evenement) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.socket().getOutputStream());
        oos.writeObject("addEvenement");
        oos.writeObject(evenement);
        oos.close();
    }




//Pas sur du type de retour de cette fonction. Une arraylist des 5 arraylists utilisees ici ou simplement une instance de la classe Evenement ?
    
    public void loadEvenement(int idEvenement) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.socket().getOutputStream());
        oos.writeObject("getEvenement");
        oos.writeInt(idEvenement);

        ObjectInputStream ois = new ObjectInputStream((clientSocket.socket().getInputStream()));

        listGroupEvenements.put(idEvenement, new GroupEvenement());

        String s = (String) ois.readObject();
        while(!s.equals("close")) {

            if (s.equals("evenement")) {
                Evenement evenement = (Evenement) ois.readObject();
                listGroupEvenements.get(idEvenement).setEvenement(evenement);
            }
            else if (s.equals("date")) {
                Date date = (Date) ois.readObject();
                listGroupEvenements.get(idEvenement).addDate(date);
            }

            else if (s.equals("dateEvenement")) {
                DateEvenement dateEvenement = (DateEvenement) ois.readObject();
                listGroupEvenements.get(idEvenement).addDateEvenement(dateEvenement);
            }

            else if (s.equals("utilisateur")) {
                Utilisateur utilisateur = (Utilisateur) ois.readObject();
                listGroupEvenements.get(idEvenement).addUtilisateur(utilisateur);
            }

            else if (s.equals("vote")){
                Vote vote = (Vote) ois.readObject();
                listGroupEvenements.get(idEvenement).addVote(vote);
            }

            s = (String) ois.readObject();
        }
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

}
