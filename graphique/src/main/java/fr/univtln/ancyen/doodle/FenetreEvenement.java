package fr.univtln.ancyen.doodle;

import fr.univtln.ancyen.doodle.Modele.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FenetreEvenement {

    // Items et variables associées
    private int size;
    private Button btn_retour = new Button("Retour");
    private TableView <Participant> tableau = new TableView<>();
    private ObservableList<Participant> users = FXCollections.observableArrayList();
    private Button btn_add_participant = new Button("Ajouter un participant");
    private Button btn_refresh = new Button("Refresh");
    private Button btn_modif = new Button ("Enregistrer");
    private Text titre_event = new Text();
    private Text description_event = new Text();
    private Text localisation_event = new Text();
    private Text duree_event = new Text();
    private Evenement evenement;
    private List<Participant> liste_participants;
    private List<String> liste_dates = new ArrayList<>();

    public FenetreEvenement(Group grp, Accueil accueil) {
        accueil.setEven(this);

        // Initialisation des actions liées à certains items
        btn_retour.setOnAction(event -> {
            for (Participant usr:users) {
                System.out.println(usr);
            }
            Evenement_cache(grp);
            accueil.Accueil_affiche(grp);
        });

        btn_add_participant.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("nom");
            dialog.setTitle("Ajout d'un participant");
            dialog.setHeaderText(null);
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(this::Ajout_Participant);
        });

        btn_refresh.setOnAction(event -> {
            // controleur_refresh()
        });

        btn_modif.setOnAction(event -> {
            // controlleur_save_modif()
        });

        // Paramétrage des items
        btn_add_participant.setLayoutX(350); btn_add_participant.setLayoutY(520);
        btn_retour.setLayoutY(31); btn_retour.setLayoutX(938);
        btn_refresh.setLayoutX(932); btn_refresh.setLayoutY(61);
        btn_modif.setLayoutX(520); btn_modif.setLayoutY(520);

        localisation_event.setLayoutX(100); localisation_event.setLayoutY(80);
        titre_event.setTextAlignment(TextAlignment.CENTER); titre_event.setWrappingWidth(1000);
        titre_event.setLayoutY(50); titre_event.setFont(new Font(20));
        description_event.setLayoutX(350); description_event.setLayoutY(80); description_event.setWrappingWidth(300);
        duree_event.setLayoutX(100); duree_event.setLayoutY(110);

        tableau.setItems(users);
        tableau.setEditable(true);
        tableau.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableau.setMinWidth(800); tableau.setMaxHeight(350);
        tableau.setLayoutX(100); tableau.setLayoutY(150);
    }

    // Affiche les items de cette classe
    public void Evenement_affiche(Group grp, Evenement evenement, List<Participant> participants, List<String> dates){
        grp.getChildren().addAll(titre_event, btn_retour, btn_add_participant, tableau, description_event, localisation_event, duree_event, btn_refresh, btn_modif);
        this.evenement = evenement;
        liste_participants = participants;
        liste_dates = dates;
        setInfos(this.evenement.getNom(), this.evenement.getDescription(), this.evenement.getLieu(), evenement.getDuree());
        Creer_Tableau(liste_participants, liste_dates);
    }

    // Cache les items de cette classe
    public void Evenement_cache(Group grp){
        grp.getChildren().removeAll(btn_retour, btn_add_participant, tableau, titre_event, description_event, localisation_event, duree_event, btn_refresh, btn_modif);
        liste_participants.clear();
    }

    // Cree le tableau affichant l'evenement
    public void Creer_Tableau(List<Participant> utilisateurs, List<String> calendar_str){
        TableColumn <Participant, String> colonne1 = new TableColumn<>("Utilisateurs");
        colonne1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableau.getColumns().add(colonne1);

        size = calendar_str.size();
        for (int i = 0; i < size; i++) {
            TableColumn <Participant, Boolean>colonne = new TableColumn<>(calendar_str.get(i));
            int finalI = i;
            colonne.setCellFactory(CheckBoxTableCell.forTableColumn(param -> utilisateurs.get(param).voteProperty(finalI)));
            tableau.getColumns().add(colonne);
        }
        users.addAll(utilisateurs);
    }

    // Ajoute un participant dans le tableau
    public void Ajout_Participant(String nom){
        tableau.getColumns().clear();
        users.clear();
        liste_participants.add(new Participant(nom, size));
        Creer_Tableau(liste_participants, liste_dates);
    }

    // Definie les informations de l'evenement qui seront affichees a l'ecran
    public void setInfos(String titre, String description, String localisation, String duree){
        titre_event.setText(titre);
        description_event.setText(description);
        if (!localisation.equals("")) localisation = "Lieu: "+localisation;
        localisation_event.setText(localisation);
        duree_event.setText("Duree: "+duree);
    }
}