package fr.univtln.ancyen.doodle;

import fr.univtln.ancyen.doodle.Modele.Evenement;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class Accueil {

    // Items graphiques
    private Text text = new Text(100,200, "Participer à un événement:");
    private TextField lien_event = new TextField();
    private Button creer_event = new Button("Creer un nouvel événement");
    private FenetreEvenement even;
    private CreationEvenement new_event;

    public Accueil(Group grp, Controleur controleur) {

        // Initialisation des actions liées à certains items
        lien_event.setOnAction(event -> {
            if (!lien_event.getText().equals("")) {
                try {
                    System.out.println("recup evenement");
                    Evenement evenement = controleur.getEvenement(lien_event.getText());
                    System.out.println("recup participants");
                    List<Participant> participants = controleur.getParticipants(evenement.getIdEvenement());
                    List<String> dates = controleur.getDates(evenement.getIdEvenement());
                    Accueil_cache(grp);
                    even.Evenement_affiche(grp, evenement, participants, dates);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez indiquer un evenement à rejoindre.");
                alert.showAndWait();
            }
        });

        creer_event.setOnAction(event -> {
            System.out.println("Event créé.");
            Accueil_cache(grp);
            new_event.Creation_evenement_affiche(grp);
        });

        // Paramétrage des items
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

    // Affiche les items de cette classe
    public void Accueil_affiche (Group grp){
        grp.getChildren().addAll(text, lien_event, creer_event);
    }

    // Cache les items de cette classe
    public void Accueil_cache(Group grp){
        grp.getChildren().removeAll(text, lien_event, creer_event);
        lien_event.clear();
    }
}
