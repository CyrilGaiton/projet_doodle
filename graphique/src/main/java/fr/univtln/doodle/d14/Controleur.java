package fr.univtln.doodle.d14;

import fr.univtln.doodle.d14.Modele.Date;
import fr.univtln.doodle.d14.Modele.Evenement;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Controleur {

    private Facade facade;

    public Controleur(Facade facade) {

        this.facade = facade;


    }


    public Evenement getEvenement(String id) throws IOException, ClassNotFoundException {

        int idEvenement = Integer.parseInt(id);
        Evenement evenement = facade.getEvenement(idEvenement);
        return evenement;
    }


    public void addEvenement(String nom, String lieu, String description, Date dateCreation, Date dateFinalisation) throws IOException, ClassNotFoundException {


        Evenement evenement = new Evenement(nom, lieu, description, dateCreation, dateFinalisation);
        facade.addEvenement(evenement);

    }


    public void updatevote() {}

}
