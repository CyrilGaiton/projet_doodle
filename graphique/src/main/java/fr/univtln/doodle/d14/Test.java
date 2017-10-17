package fr.univtln.doodle.d14;

import javafx.application.Application;
import javafx.stage.Stage;


public class Test extends Application {

    public static void main(String[] args) {
        Application.launch(Test.class, args);
    }


    @Override
    public void start(Stage primaryStage) {
        Fenetre fen = new Fenetre();
        Accueil accueil = new Accueil(fen.getRoot());
        Evenement even = new Evenement(fen.getRoot(), accueil);
        Creation_evenement new_event = new Creation_evenement(fen.getRoot(), accueil);
        primaryStage.setTitle("Doodle");
        primaryStage.setScene(fen.getScene());
        primaryStage.show();
    }
}
