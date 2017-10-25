package fr.univtln.ancyen.doodle;


import java.io.*;
import java.net.*;

public class Serveur {

    private static int port = 5700;
    private static Facade facade = new Facade();


    public static void main(String[] args) {

        ServerSocket serveur;

        try {
            serveur = new ServerSocket(port);
            while (true) {
                Socket client = serveur.accept();
                new MaConnexion(client, facade);
            }
        } catch (IOException e) {

            System.out.println("Erreur a la creation d'un objet socket : " + e.getMessage());
            System.exit(1);
        }


    }
}