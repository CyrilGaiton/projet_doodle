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
        Evenement event= facade.getEvenement(idEvenement);
        return event;
    }


    public void addEvenement(String nom, String description, String lieu, Date dateCreation, Date dateFinalisation) {
    }
}

