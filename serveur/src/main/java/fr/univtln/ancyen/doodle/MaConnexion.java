package fr.univtln.ancyen.doodle;

import java.io.*;
import java.net.*;
import java.sql.SQLException;


public class MaConnexion implements Runnable {

    private Socket client; //Liaison avec le client
    private Facade facade;

    public MaConnexion(Socket client, Facade facade) {

        this.client = client;
        this.facade = facade;

        new Thread(this).start();
    }


    public void run() {

        boolean running = true;
        try {

            while (running) {

                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

                // on switch en fonction du premier string
                System.out.println("on lit 1er string");
                String s = (String) ois.readObject();

                System.out.println("else if ...");
                if (s.equals("getEvenement")) {
                    System.out.println("instance evenement");
                    int idEvenement = ois.readInt();
                    System.out.println("on a lu le int ideve");
                    facade.sendEvenement(oos, ois.readInt());
                } else if (s.equals("addEvenement")) {
                    facade.addEvenement(ois);
                } else if (s.equals("addVote")) {
                    facade.addVote(ois);
                } else if (s.equals("addUtilisateur")) {
                    facade.addUtilisateur(ois);
                } else if (s.equals("addDate")) {
                    facade.addDate(ois);
                } else if (s.equals("addDateEvenement")) {
                    facade.addDateEvenement(ois);
                } else if (s.equals("getNextIdEvenement")) {
                    facade.sendNextIdEvenement(oos);
                } else if (s.equals("getNextIdDate")) {
                    facade.sendNextIdDate(oos);
                } else if (s.equals("gerNextIdUtilisateur")) {
                    facade.sendNextIdUtilisateur(oos);
                } else if (s.equals("close")) {
                    ois.close();
                    oos.close();
                    running= false;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stop();
    }

    public void stop() {


        try {
            System.out.println("Connexion fermée: " + client.getLocalAddress());
            client.close();
        } catch (IOException e) {
            System.out.println("Exception a la fermeture d'une connexion"+e);
        }

    }
}
