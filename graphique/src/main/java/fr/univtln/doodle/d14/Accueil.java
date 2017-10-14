package fr.univtln.doodle.d14;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Accueil {
    private Text text = new Text(100,200, "Participer à un événement:");
    private TextField lien_event = new TextField();
    private Button creer_event = new Button("Creer un nouvel événement");

    public Accueil() {

        lien_event.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(lien_event.getText());
            }
        });

        creer_event.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Event créé.");
            }
        });
        text.setFont(new Font(20));
        lien_event.setLayoutX(100); lien_event.setLayoutY(215);
        creer_event.setLayoutX(600); creer_event.setLayoutY(200); creer_event.setFont(new Font(20));
    }

    public Text getText() {
        return text;
    }

    public TextField getLien_event() {
        return lien_event;
    }

    public Button getCreer_event() {
        return creer_event;
    }
}
