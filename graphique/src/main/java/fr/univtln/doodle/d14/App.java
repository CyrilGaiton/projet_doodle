package fr.univtln.doodle.d14;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }


    @Override
    public void start(Stage primaryStage) {
        Facade facade = new Facade();
        Fenetre fen = new Fenetre(facade);
        Accueil accueil = new Accueil(fen.getRoot(), fen.getControleur());
        FenetreEvenement even = new FenetreEvenement(fen.getRoot(), accueil);
        CreationEvenement new_event = new CreationEvenement(fen.getRoot(), accueil, even);
        primaryStage.setTitle("Doodle");
        primaryStage.setScene(fen.getScene());
        primaryStage.show();
    }
}
