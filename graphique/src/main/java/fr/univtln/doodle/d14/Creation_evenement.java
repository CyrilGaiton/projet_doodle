package fr.univtln.doodle.d14;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Creation_evenement {
    private final int MAX_DATES = 15;
    private Button btn_suivant = new Button("Suivant");
    private Button btn_retour = new Button("Retour");
    private Button btn_add_date = new Button("Ajouter une date");
    private Text text_nom = new Text(100,200, "Nom de l'événement:");
    private Text text_date = new Text(600, 50, "Choisissez une date:");
    private TextField nom_event = new TextField();
    private List <DatePicker> calendar = new ArrayList<>();
    private DatePicker new_date = new DatePicker();



    public Creation_evenement(Group grp, Accueil accueil) {
        accueil.setNew_event(this);

        btn_retour.setOnAction(event -> {
            Creation_evenement_cache(grp);
            accueil.Accueil_affiche(grp);
        });

        btn_suivant.setOnAction(event -> {
            if (!(nom_event.getText().equals(""))) {
                String nom = nom_event.getText();
                for (DatePicker date:calendar) {
                    System.out.println(date.getEditor().getText());
                }
                Creation_evenement_cache(grp);
                // affiche suite
            }
        });

        btn_add_date.setOnAction(event -> {
            Ajout_Date();
            grp.getChildren().add(new_date);
        });

        btn_suivant.setLayoutX(450); btn_suivant.setLayoutY(500);
        btn_retour.setLayoutY(31); btn_retour.setLayoutX(939);
        btn_add_date.setLayoutX(600); btn_add_date.setLayoutY(70);
        nom_event.setLayoutX(100); nom_event.setLayoutY(220);
        text_nom.setFont(new Font(20));
        text_date.setFont(new Font(20));
    }

    public void Creation_evenement_affiche(Group grp){
        Ajout_Date();
        grp.getChildren().addAll(text_nom, nom_event, text_date, btn_suivant, btn_retour, btn_add_date, new_date);
    }

    public void Creation_evenement_cache(Group grp){
        grp.getChildren().removeAll(text_nom, nom_event, text_date, btn_suivant, btn_retour, btn_add_date);
        for (DatePicker date:calendar) {
            grp.getChildren().remove(date);
        }
        calendar.clear();
        btn_add_date.setLayoutY(70);
    }

    public void Ajout_Date(){
        new_date = new DatePicker();
        new_date.setLayoutX(600); new_date.setLayoutY(btn_add_date.getLayoutY());
        new_date.setPromptText("yyyy-MM-dd");
        calendar.add(new_date);
        btn_add_date.setLayoutY(btn_add_date.getLayoutY()+30);
        btn_add_date.setDisable(true);
        new_date.setOnAction(event -> {
            if (MAX_DATES > calendar.size())
                btn_add_date.setDisable(false);
        });
    }
}
