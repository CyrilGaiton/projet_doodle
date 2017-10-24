package fr.univtln.doodle.d14;

import fr.univtln.doodle.d14.Modele.Evenement;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class Accueil {
    private Text text = new Text(100,200, "Participer à un événement:");
    private TextField lien_event = new TextField();
    private Button creer_event = new Button("Creer un nouvel événement");
    private FenetreEvenement even;
    private CreationEvenement new_event;

    public Accueil(Group grp, Controleur controleur) {

        lien_event.setOnAction(event -> {
            System.out.println(lien_event.getText());
            try {
                Evenement evenement = controleur.getEvenement(lien_event.getText());
                List<Participant> participants = controleur.getParticipants(Integer.toString(evenement.getIdEvenement()));
                Accueil_cache(grp);
                even.Evenement_affiche(grp, evenement, participants);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        creer_event.setOnAction(event -> {
            System.out.println("Event créé.");
            Accueil_cache(grp);
            new_event.Creation_evenement_affiche(grp);
        });
        text.setFont(new Font(20));
        lien_event.setLayoutX(100); lien_event.setLayoutY(215);
        creer_event.setLayoutX(600); creer_event.setLayoutY(200); creer_event.setFont(new Font(20));
        Accueil_affiche(grp);
    }

    public void setEven(FenetreEvenement even) {
        this.even = even;
    }

    public void setNew_event(CreationEvenement new_event) {
        this.new_event = new_event;
    }

    public void Accueil_affiche (Group grp){
        grp.getChildren().addAll(text, lien_event, creer_event);
    }

    public void Accueil_cache(Group grp){
        grp.getChildren().removeAll(text, lien_event, creer_event);
        lien_event.clear();
    }
}
