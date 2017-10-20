package fr.univtln.doodle.d14;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FenetreEvenement {
    private Button btn_retour = new Button("Retour");
    private TableView tableau = new TableView();

    public FenetreEvenement(Group grp, Accueil accueil) {
        accueil.setEven(this);

        btn_retour.setLayoutY(31); btn_retour.setLayoutX(939);
        btn_retour.setOnAction(event -> {
            Evenement_cache(grp);
            accueil.Accueil_affiche(grp);
        });

        TableColumn colonne1 = new TableColumn("Date1");
        colonne1.setEditable(false);
        TableColumn colonne2 = new TableColumn("Date2");
        colonne2.setEditable(false);
        TableColumn colonne3 = new TableColumn("Date3");
        colonne3.setEditable(false);

        tableau.getColumns().addAll(colonne1, colonne2, colonne3);
        tableau.setEditable(false);
        tableau.setLayoutX(100); tableau.setLayoutY(100);
    }

    public void Evenement_affiche(Group grp){
        grp.getChildren().addAll(btn_retour, tableau);
    }

    public void Evenement_cache(Group grp){
        grp.getChildren().removeAll(btn_retour, tableau);
    }
}
