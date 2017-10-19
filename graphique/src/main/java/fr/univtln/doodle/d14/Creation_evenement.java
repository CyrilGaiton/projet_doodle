package fr.univtln.doodle.d14;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
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

    private Text text_nom = new Text(100,50, "Nom de l'événement:");
    private Text text_localisation = new Text(100, 150, "Localisation:");
    private Text text_description = new Text(100, 250, "Description:");
    private Text text_date = new Text(600, 50, "Choisissez une date:");

    private TextField field_nom = new TextField();
    private TextArea field_description = new TextArea();
    private TextField field_localisation = new TextField();

    private List <DatePicker> calendar = new ArrayList<>();
    private DatePicker new_date = new DatePicker();



    public Creation_evenement(Group grp, Accueil accueil) {
        accueil.setNew_event(this);

        btn_retour.setOnAction(event -> {
            Creation_evenement_cache(grp);
            accueil.Accueil_affiche(grp);
        });

        btn_suivant.setOnAction(event -> {
            if (!(field_nom.getText().equals(""))) {
                String nom = field_nom.getText();
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
        btn_retour.setLayoutX(939); btn_retour.setLayoutY(31);
        btn_add_date.setLayoutX(600); btn_add_date.setLayoutY(70);

        field_nom.setLayoutX(100); field_nom.setLayoutY(70);
        field_localisation.setLayoutX(100); field_localisation.setLayoutY(170);
        field_localisation.setPromptText("Facultatif");
        field_description.setLayoutX(100); field_description.setLayoutY(270);
        field_description.setMaxWidth(200);
        field_description.setWrapText(true);
        field_description.setPromptText("Facultatif");

        text_nom.setFont(new Font(20));
        text_description.setFont(new Font(20));
        text_localisation.setFont(new Font(20));
        text_date.setFont(new Font(20));
    }

    public void Creation_evenement_affiche(Group grp){
        Ajout_Date();
        grp.getChildren().addAll(text_nom, text_description, text_localisation, field_nom, field_description, field_localisation, text_date, btn_suivant, btn_retour, btn_add_date, new_date);
    }

    public void Creation_evenement_cache(Group grp){
        grp.getChildren().removeAll(text_nom, text_description, text_localisation, field_nom, field_description, field_localisation, text_date, btn_suivant, btn_retour, btn_add_date);
        for (DatePicker date:calendar) {
            grp.getChildren().remove(date);
        }
        calendar.clear();
        btn_add_date.setLayoutY(70);
    }

    public void Ajout_Date(){
        new_date = new DatePicker();
        new_date.setLayoutX(600); new_date.setLayoutY(btn_add_date.getLayoutY());
        new_date.setPromptText("dd/MM/yyyy");
        calendar.add(new_date);
        btn_add_date.setLayoutY(btn_add_date.getLayoutY()+30);
        btn_add_date.setDisable(true);
        new_date.setOnAction(event -> {
            if (MAX_DATES > calendar.size())
                btn_add_date.setDisable(false);
        });
    }
}
