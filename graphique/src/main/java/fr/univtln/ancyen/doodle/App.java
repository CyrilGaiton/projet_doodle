package fr.univtln.ancyen.doodle;

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
        FenetreEvenement even = new FenetreEvenement(fen.getRoot(), accueil, fen.getControleur(), facade);
        CreationEvenement new_event = new CreationEvenement(fen.getRoot(), accueil, even, fen.getControleur());
        primaryStage.setTitle("Doodle");
        primaryStage.setScene(fen.getScene());
        primaryStage.setOnCloseRequest(event -> {
            facade.closeConnection();
        });
        primaryStage.show();
    }
}
