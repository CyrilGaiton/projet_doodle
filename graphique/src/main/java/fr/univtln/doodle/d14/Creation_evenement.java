package fr.univtln.doodle.d14;

import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Creation_evenement {
    private Text text = new Text(100,200, "Nom de l'événement:");
    private TextField nom = new TextField();

    public Creation_evenement(Group grp) {
        nom.setOnAction(event -> {
            System.out.println(nom.getText());
        });
    }
}
