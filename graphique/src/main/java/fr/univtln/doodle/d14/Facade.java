package fr.univtln.doodle.d14;

import fr.univtln.doodle.d14.Modele.*;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Facade {


    private List<Evenement> listEvenement = new ArrayList<>();
    private List<Vote> listVote = new ArrayList<>();
    private List<DateEvenement> listDateEvenement = new ArrayList<>();
    private List<Utilisateur> listUtilisateur = new ArrayList<>();
    private List<Date> listDate = new ArrayList<>();
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



    public void addEvenement(Evenement evenement) throws IOException, ClassNotFoundException {


        boolean confirmAddEvenement;

        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.socket().getOutputStream());
        oos.writeObject("addEvenement : ");
        oos.writeObject(evenement);

        ObjectInputStream ois = new ObjectInputStream((clientSocket.socket().getInputStream()));

        confirmAddEvenement = (boolean)ois.readObject();

    }




//Pas sur du type de retour de cette fonction. Une arraylist des 5 arraylists utilisees ici ou simplement une instance de la classe Evenement ?
    
    public Evenement getEvenement(int id) throws IOException, ClassNotFoundException {
        int var = findEvenement(id);
        if(var != -1)
            return listEvenement.get(var);
        else {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.socket().getOutputStream());
            oos.writeObject("getEvenement : ");
            oos.writeInt(var);

            ObjectInputStream ois = new ObjectInputStream((clientSocket.socket().getInputStream()));
            ObjectInputStream ois2 = new ObjectInputStream((clientSocket.socket().getInputStream()));

            String s = " ";
            while(!s.equals("close")) {
                s = (String) ois.readObject();

                if (s.equals("evenement")) {
                    Evenement event = (Evenement) ois2.readObject();
                    listEvenement.add(event);
                }
                else if (s.equals("date")) {

                    Date date = (Date) ois2.readObject();
                    listDate.add(date);
                }

                else if (s.equals("dateEvenement")) {

                    DateEvenement dateevenement = (DateEvenement) ois2.readObject();
                    listDateEvenement.add(dateevenement);
                }

                else if (s.equals("utilisateur")) {

                    Utilisateur user = (Utilisateur) ois2.readObject();
                    listUtilisateur.add(user);
                }

            }



            return event;

        }

    }



    public int findEvenement(int id){

        for (int i = 0; i<listEvenement.size(); i++) {

            if(listEvenement.get(i).getIdEvenement() == id)
                return i;
        }


    return -1;

    }



}
