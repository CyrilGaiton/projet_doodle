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
        Accueil accueil = new Accueil();
        primaryStage.setTitle("Doodle");
        fen.getRoot().getChildren().addAll(fen.getMb(), accueil.getText(), accueil.getLien_event(), accueil.getCreer_event());
        primaryStage.setScene(fen.getScene());
        primaryStage.show();
    }
}
