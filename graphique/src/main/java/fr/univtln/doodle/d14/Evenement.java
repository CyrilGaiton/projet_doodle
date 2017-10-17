package fr.univtln.doodle.d14;

import javafx.scene.Group;
import javafx.scene.control.Button;

public class Evenement {
    private Button btn_retour = new Button("Retour");

    public Evenement(Group grp, Accueil accueil) {
        accueil.setEven(this);
        btn_retour.setLayoutY(31); btn_retour.setLayoutX(939);
        btn_retour.setOnAction(event -> {
            Evenement_cache(grp);
            accueil.Accueil_affiche(grp);
        });
    }

    public void Evenement_affiche(Group grp){
        grp.getChildren().addAll(btn_retour);
    }

    public void Evenement_cache(Group grp){
        grp.getChildren().removeAll(btn_retour);
    }
}
