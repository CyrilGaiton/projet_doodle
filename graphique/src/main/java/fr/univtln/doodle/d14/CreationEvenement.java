package fr.univtln.doodle.d14;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CreationEvenement {
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
    private DatePicker new_date;

    private ComboBox heures;
    private ObservableList<String> liste_heures = FXCollections.observableArrayList(
                    "00h00", "00h15", "00h30","00h45","01h00","01h15","01h30","01h45","02h00","02h15",
                    "02h30","02h45","03h00","03h15","03h30","03h45","04h00","04h15","04h30","04h45","05h00",
                    "05h15","05h30","05h45","06h00","06h15","06h30","06h45","07h00","07h15","07h30","07h45",
                    "08h00","08h15","08h30","08h45","09h00","09h15","09h30","09h45","10h00","10h15","10h30",
                    "10h45","11h00","11h15","11h30","11h45","12h00","12h15","12h30","12h45","13h00","13h15",
                    "13h30","13h45","14h00","14h15","14h30","14h45","15h00","15h15","15h30","15h45","16h00",
                    "16h15","16h30","16h45","17h00","17h15","17h30","17h45","18h00","18h15","18h30","18h45",
                    "19h00","19h15","19h30","19h45","20h00","20h15","20h30","20h45","21h00","21h15","21h30",
                    "21h45","22h00","22h15","22h30","22h45","23h00","23h15","23h30","23h45");



    public CreationEvenement(Group grp, Accueil accueil) {
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
        grp.getChildren().addAll(text_nom, text_description, text_localisation, field_nom, field_description, field_localisation, text_date, btn_suivant, btn_retour, btn_add_date, heures, new_date);
    }

    public void Creation_evenement_cache(Group grp){
        grp.getChildren().removeAll(text_nom, text_description, text_localisation, field_nom, field_description, field_localisation, text_date, btn_suivant, btn_retour, btn_add_date, heures);
        for (DatePicker date:calendar) {
            grp.getChildren().remove(date);
        }
        calendar.clear();
        btn_add_date.setLayoutY(70);
    }

    public void Ajout_Date(){
        new_date = new DatePicker();
        new_date.setLayoutX(600); new_date.setLayoutY(btn_add_date.getLayoutY());
        heures = new ComboBox();
        heures.setItems(liste_heures);
        heures.setLayoutX(800); heures.setLayoutY(btn_add_date.getLayoutY());
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
