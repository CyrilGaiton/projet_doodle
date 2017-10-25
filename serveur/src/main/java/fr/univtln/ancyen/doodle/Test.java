package fr.univtln.ancyen.doodle;

import java.io.*;
import java.nio.ByteBuffer;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);

        oos.writeInt(1215451);
        oos.flush();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(bout.toByteArray());


        ByteArrayInputStream binp = new ByteArrayInputStream(buffer.array());
        System.out.println(binp.available());
        ObjectInputStream ois = new ObjectInputStream(binp);
        int i = ois.readInt();
        System.out.println(i);
    }
}

//                    // on switch en fonction du premier string
//                    System.out.println("on lit 1er string");
//                    String s = (String) ois.readObject();
//
//                    System.out.println("else if ...");
//                    if (s.equals("getEvenement")) {
//                        facade.sendEvenement(oos, ois.readInt());
//                    } else if (s.equals("addEvenement")) {
//                        facade.addEvenement(ois);
//                    } else if (s.equals("addVote")) {
//                        facade.addVote(ois);
//                    } else if (s.equals("addUtilisateur")) {
//                        facade.addUtilisateur(ois);
//                    } else if (s.equals("addDate")) {
//                        facade.addDate(ois);
//                    } else if (s.equals("addDateEvenement")) {
//                        facade.addDateEvenement(ois);
//                    } else if (s.equals("getNextIdEvenement")) {
//                        facade.sendNextIdEvenement(oos);
//                    } else if (s.equals("getNextIdDate")) {
//                        facade.sendNextIdDate(oos);
//                    } else if (s.equals("gerNextIdUtilisateur")) {
//                        facade.sendNextIdUtilisateur(oos);
//                    } else if (s.equals("close")) {
//                        clientSockect.close();
//                        System.out.println("Connexion fermée: " + clientSockect.getLocalAddress());
//                    }