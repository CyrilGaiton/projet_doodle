package fr.univtln.ancyen.doodle;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;


public class App
{
    private static Facade facade = new Facade();

    public static void main( String[] args ) throws IOException, ClassNotFoundException, SQLException {
        // creation bdd h2

        // creation sélecteur
        Selector selector = Selector.open();

        // creation socket et binding avec addresse
        ServerSocketChannel serverSockect = ServerSocketChannel.open();
        String hostname = "localhost";
        int port = 5625;
        InetSocketAddress address = new InetSocketAddress(hostname, port);
        serverSockect.bind(address);
        System.out.println("Serveur lancé sur le port " + port);

        // on met le channel en mode non-bloquant
        serverSockect.configureBlocking(false);

        // on récupère les opérations autorisées par le socket
        // donc l'autorisation des nouvelles connexions (OP_ACCEPT)
        int validops = serverSockect.validOps();

        SelectionKey selectionKey = serverSockect.register(selector, validops);

        boolean server_running = true;
        while (server_running) {

            // on crée l'ensemble des opérations en attente
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            // on fait une boucle pour traiter toutes les ops en attente
            while (selectionKeyIterator.hasNext()) {
                SelectionKey key = selectionKeyIterator.next();

                if (key.isAcceptable()) {
                    SocketChannel clientSocket = serverSockect.accept();

                    // non bloquant
                    clientSocket.configureBlocking(false);

                    // on l'enregistre avec une action valide de lecture
                    clientSocket.register(selector, SelectionKey.OP_READ);
                    System.out.println("Connexion aceptée: " + clientSocket.getLocalAddress());
                } else if (key.isReadable()) {
                    SocketChannel clientSockect = (SocketChannel) key.channel();
                    ObjectInputStream ois = new ObjectInputStream(clientSockect.socket().getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(clientSockect.socket().getOutputStream());

                    // on switch en fonction du premier string
                    String s = (String) ois.readObject();

                    if (s.equals("getEvenement")) {
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
                    } else if (s.equals("close")) {
                        clientSockect.close();
                        System.out.println("Connexion fermée: " + clientSockect.getLocalAddress());
                    }
                }
            }
        }
        serverSockect.close();
    }
}