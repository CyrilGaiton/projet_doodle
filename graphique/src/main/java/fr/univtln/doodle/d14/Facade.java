package fr.univtln.doodle.d14;

import fr.univtln.doodle.d14.Modele.Evenement;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Facade {


    private List<Evenement> listEvenement = new ArrayList<>();
    private SocketChannel clientSocket;


    public Facade () {

        this.seConnecter();
    }



    public void seConnecter() {

        try {

            clientSocket = SocketChannel.open(new InetSocketAddress("localhost", 4242));


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





    public Evenement getEvenement(int id) throws IOException, ClassNotFoundException {
        int var = findEvenement(id);
        if(var != -1)
            return listEvenement.get(var);
        else {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.socket().getOutputStream());
            oos.writeObject("getEvenement : ");
            oos.writeInt(var);

            ObjectInputStream ois = new ObjectInputStream((clientSocket.socket().getInputStream()));

            Evenement evenement = (Evenement) ois.readObject();
            return evenement;

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
