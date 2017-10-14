package fr.univtln.ancyen.doodle;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class App
{
    public static void main( String[] args ) throws IOException {
        // creation bdd h2


        // creation sélecteur
        Selector selector = Selector.open();

        // creation socket et binding avec addresse
        ServerSocketChannel serverSockect = ServerSocketChannel.open();
        String hostname = "localhost";
        int port = 4242;
        InetSocketAddress address = new InetSocketAddress(hostname, port);
        serverSockect.bind(address);
        System.out.println("Serveur lancé sur le port "+port);

        // on met le channel en mode non-bloquant
        serverSockect.configureBlocking(false);

        // on récupère les opérations autorisées par le socket
        // donc l'autorisation des nouvelles connexions (OP_ACCEPT)
        int validops = serverSockect.validOps();

        SelectionKey selectionKey = serverSockect.register(selector, validops);

        boolean server_running = true;
        while (server_running){

            // on crée l'ensemble des opérations en attente
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            // on fait une boucle pour traiter toutes les ops en attente
            while(selectionKeyIterator.hasNext()){
                SelectionKey key = selectionKeyIterator.next();

                if (key.isAcceptable()){
                    SocketChannel clientSocket = serverSockect.accept();

                    // non bloquant
                    clientSocket.configureBlocking(false);

                    // on l'enregistre avec une action valide de lecture
                    clientSocket.register(selector, SelectionKey.OP_READ);
                    System.out.println("Connexion aceptée: " + clientSocket.getLocalAddress());
                }

                else if (key.isReadable()){
                    SocketChannel clientSockect = (SocketChannel) key.channel();

                    // on crée un buffer puis on recupere dans un String
                    ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                    clientSockect.read(byteBuffer);
                    String result = new String(byteBuffer.array()).trim();

                    System.out.println("Message reçu: " + result);

                    if (result.equals("close")) {
                        clientSockect.close();
                        System.out.println("Connexion fermée: " + clientSockect.getLocalAddress());
                    }
                }
            }
        }
    }
}